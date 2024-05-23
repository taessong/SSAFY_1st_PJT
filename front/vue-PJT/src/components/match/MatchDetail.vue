<template>
  <div>
    <div class="detail-container">
      <div class="detail d-flex justify-content-evenly mb-5">
        <h4 class="status">{{ detail.teamBId ? '[마감]' : '[모집]' }}</h4>
        <h4 class="match-date">📆{{ detail.matchDate }}</h4>
        <h4 class="match-time">🕑{{ detail.matchTime }}</h4>
      </div>

      <div class="row logo-container">
        <!-- Team A Logo -->
        <div class="col-md-4 text-center">
          <img v-if="teamALogo" :src="teamALogo" alt="팀 A 로고" class="team-logo">
          <div class="team-container">
            <table class="table table-bordered team-table">
              <thead>
                <tr>
                  <th>{{ detail.teamAName }} 팀 리더</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{{ detail.authorName }}</td>
                </tr>
                <tr>
                  <th>멤버</th>
                </tr>
                <tr v-for="member in teamAMember" :key="member.id">
                  <td>{{ member.memberName }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Versus Text -->
        <div class="col-md-2 text-center versus">
          <h2 v-if="detail.teamBId" class="vs">VS</h2>
        </div>

        <!-- Team B Logo -->
        <div class="col-md-4 text-center" v-if="detail.teamBId">
          <img v-if="teamBLogo" :src="teamBLogo" alt="팀 B 로고" class="team-logo">
          <div class="team-container">
            <table class="table table-bordered team-table">
              <thead>
                <tr>
                  <th>{{ detail.teamBName }} 팀 리더</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{{ detail.teamBLeader }}</td>
                </tr>
                <tr>
                  <th>멤버</th>
                </tr>
                <tr v-for="member in teamBMember" :key="member.id">
                  <td>{{ member.memberName }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div v-if="detail.stadiumId" class="stadium-container mt-4">
        <div id="stadiumCarousel" class="carousel slide stadium" data-ride="carousel">
          <div class="carousel-inner">
            <div v-for="(image, index) in imgSrc" :key="index" :class="['carousel-item', { active: index === 0 }]">
              <img :src="image" class="d-block w-100 stadium-image" alt="Stadium Image">
            </div>
          </div>
          <a class="carousel-control-prev" href="#stadiumCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#stadiumCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
        <div class="stadium-info mt-3">
          <div><strong>{{ stadiumName }}</strong></div>
          <div>{{ stadiumAddress }}</div>
          <div>내용 : {{ detail.content }}</div>
        </div>
      </div>
      
      <div class="buttons">
        <div v-if="detail.teamBId == 0 && detail.authorName !== nickName">
          <button @click="registMatch" class="btn btn-primary">신청하기</button>
        </div>
        <div v-if="detail.teamBLeader === nickName">
          <button @click="cancelMatch" class="btn btn-warning">신청취소</button>
        </div>
        <div v-if="detail.authorName === nickName">
          <router-link :to="{ name: 'matchupdate', params: { id: detail.id } }">
            <button class="btn btn-secondary">수정하기</button>
          </router-link>
        </div>
        <div v-if="detail.authorName === nickName">
          <button @click="deleteMatch" class="btn btn-danger">삭제하기</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from '@/api/axios';
import { useMatchStore } from '@/stores/match';

const detail = ref([]);
const nickName = ref('');
const teamAMember = ref([]);
const teamBMember = ref([]);
const route = useRoute();
const router = useRouter();
const imgSrc = ref([]);
const store = useMatchStore();

// 경기장 이름, 장소, 이미지
const stadiumName = ref('');
const stadiumAddress = ref('');
const teamALogo = ref('');
const teamBLogo = ref('');

const teamLogos = {
  '토트넘': new URL('@/asset/logo/토트넘.png', import.meta.url).href,
  '리버풀': new URL('@/asset/logo/리버풀.png', import.meta.url).href,
  '맨시티': new URL('@/asset/logo/맨시티.png', import.meta.url).href,
  '맨유': new URL('@/asset/logo/맨유.png', import.meta.url).href,
  '아스널': new URL('@/asset/logo/아스널.png', import.meta.url).href,
  '첼시': new URL('@/asset/logo/첼시.png', import.meta.url).href
};

const stadiumImagesMap = {
  1: [
    new URL('@/asset/stadium/유성풋살구장/유성풋살구장(1).png', import.meta.url).href,
    new URL('@/asset/stadium/유성풋살구장/유성풋살구장(2).png', import.meta.url).href
  ],
  2: [
    new URL('@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png', import.meta.url).href,
    new URL('@/asset/stadium/대전가장풋살존/대전가장풋살존(2).png', import.meta.url).href
  ],
  3: [
    new URL('@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png', import.meta.url).href,
    new URL('@/asset/stadium/남선공원풋살장/남선공원풋살장(2).png', import.meta.url).href
  ],
  4: [
    new URL('@/asset/stadium/가오풋살장/가오풋살장(1).png', import.meta.url).href,
    new URL('@/asset/stadium/가오풋살장/가오풋살장(2).png', import.meta.url).href
  ]
};

const detailPost = async (id) => {
  try {
    const response = await axios.get(`/futsal/match/${id}`);
    detail.value = response.data;
    detail.value.matchTime = detail.value.matchTime.split(':').slice(0, 2).join(':');
    stadiumName.value = store.stadiums[response.data.stadiumId - 1].name;
    stadiumAddress.value = store.stadiums[response.data.stadiumId - 1].address;
    imgSrc.value = stadiumImagesMap[response.data.stadiumId];
    teamALogo.value = teamLogos[detail.value.teamAName];
    if (detail.value.teamBName) {
      teamBLogo.value = teamLogos[detail.value.teamBName];
    }
    showTeamA();
    if (detail.value.teamBId) {
      showTeamB();
    }
  } catch (error) {
    console.error(error);
  }
};

const registMatch = async () => {
  try {
    const response = await axios.put(`/futsal/match/${route.params.id}/regist`);
    detail.value = response.data;
    if (detail.value.teamBId) {
      showTeamB();
      router.go(0);
    }
  } catch (error) {
    console.log(error);
  }
};

const cancelMatch = async () => {
  try {
    const response = await axios.delete(`/futsal/match/${route.params.id}/cancel`);
    detail.value = response.data;
    router.go(0);
  } catch (error) {
    console.log(error);
  }
};

const deleteMatch = async () => {
  try {
    await axios.delete(`/futsal/match/${route.params.id}`);
    router.push('/match');
  } catch (error) {
    console.log(error);
  }
};

const showTeamA = async () => {
  try {
    const response = await axios.get(`/futsal/board/team/${detail.value.teamAId}`);
    teamAMember.value = response.data;
  } catch (error) {
    console.log(error);
  }
};

const showTeamB = async () => {
  try {
    const response = await axios.get(`/futsal/board/team/${detail.value.teamBId}`);
    teamBMember.value = response.data;
  } catch (error) {
    console.log(error);
  }
};

onMounted(() => {
  nickName.value = sessionStorage.getItem('nickName');
  detailPost(route.params.id);
});
</script>
<style scoped>
.detail-container {
  margin-top: 50px;
  margin-left: 50px;
  margin-right: 50px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.detail {
  margin-bottom: 20px;
}

.vs {
  margin-top: 80px;
}

.logo-container {
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  margin-bottom: 40px;
}
.stadium {
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}

.team-logo {
  max-width: 100%;
  height: auto;
}

.versus {
  display: flex;
  justify-content: center;
  align-items: center;
}

.team-container {
  margin-bottom: 30px;
}

.team-table {
  width: 60%;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  margin-top: 25px;
}

.team-table th, .team-table td {
  padding: 10px;
}

.stadium-container {
  margin-bottom: 30px;
}

.stadium-image {
  width: 100%;
  height: auto;
  margin-bottom: 10px;
}

.stadium-info {
  text-align: center;
}

.buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.buttons button {
  width: 120px;
}

.status,
.match-date,
.match-time {
  margin-right: 30px;
}
</style>
