<template>
  <div>
    <div class="detail-container">
      <div class="detail d-flex justify-content-evenly">
        <h4 style="margin-right: 100px;">분류 : {{ detail.teamBId ? '[마감]' : '[모집]' }}</h4>
        <h4 style="margin-right: 100px;">📆{{ detail.matchDate }}</h4>
        <h4>🕑{{ detail.matchTime }}</h4>
      </div>

      <div class="row logo-container">
        <!-- Team A Logo -->
        <div class="col-md-5 logo">
          <template v-if="detail.teamAName === '토트넘'">
            <img src="@/asset/logo/토트넘.png" alt="토트넘 로고">
          </template>
          <template v-if="detail.teamAName === '리버풀'">
            <img src="@/asset/logo/리버풀.png" alt="리버풀 로고">
          </template>
          <template v-if="detail.teamAName === '맨시티'">
            <img src="@/asset/logo/맨시티.png" alt="맨시티 로고">
          </template>
          <template v-if="detail.teamAName === '맨유'">
            <img src="@/asset/logo/맨유.png" alt="맨유 로고">
          </template>
          <template v-if="detail.teamAName === '아스널'">
            <img src="@/asset/logo/아스널.png" alt="아스널 로고">
          </template>
          <template v-if="detail.teamAName === '첼시'">
            <img src="@/asset/logo/첼시.png" alt="첼시 로고">
          </template>
        </div>

        <!-- Versus Text -->
        <div class="col-md-2 text-center versus">
          <h2 v-if="detail.teamBId">VS</h2>
        </div>

        <!-- Team B Logo -->
        <div class="col-md-5 logo" v-if="detail.teamBId">
          <template v-if="detail.teamBName === '토트넘'">
            <img src="@/asset/logo/토트넘.png" alt="토트넘 로고">
          </template>
          <template v-if="detail.teamBName === '리버풀'">
            <img src="@/asset/logo/리버풀.png" alt="리버풀 로고">
          </template>
          <template v-if="detail.teamBName === '맨시티'">
            <img src="@/asset/logo/맨시티.png" alt="맨시티 로고">
          </template>
          <template v-if="detail.teamBName === '맨유'">
            <img src="@/asset/logo/맨유.png" alt="맨유 로고">
          </template>
          <template v-if="detail.teamBName === '아스널'">
            <img src="@/asset/logo/아스널.png" alt="아스널 로고">
          </template>
          <template v-if="detail.teamBName === '첼시'">
            <img src="@/asset/logo/첼시.png" alt="첼시 로고">
          </template>
        </div>
      </div>

      <div class="row">
        <!-- Team A Members -->
        <div class="col-md-6">
          <div class="team-container">
            <div class="team-a">
              <div class="team-leader">
                <h4>{{ detail.teamAName }}팀 리더 : {{ detail.authorName }}</h4>
              </div>
              <div class="team-member">
                <h4 v-for="member in teamAMember" :key="member.id" class="team-member">
                  {{ member.memberName }}
                </h4>
              </div>
            </div>
          </div>
        </div>

        <!-- Team B Members -->
        <div class="col-md-6" v-if="detail.teamBId">
          <div class="team-container">
            <div class="team-b">
              <div class="team-leader">
                <h4>{{ detail.teamBName }}팀 리더 : {{ detail.teamBLeader }}</h4>
              </div>
              <div class="team-member">
                <h4 v-for="member in teamBMember" :key="member.id">
                  {{ member.memberName }}
                </h4>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div v-if="detail.stadiumId" class="stadium-container">
        <div class="row" v-if="imgSrc === 1">
          <div class="col-md-5">
            <img src="@/asset/stadium/유성풋살구장/유성풋살구장(1).png" alt="Stadium Image">
          </div>
          <div class="col-md-5">
            <img src="@/asset/stadium/유성풋살구장/유성풋살구장(2).png" alt="Stadium Image">
          </div>
          <div class="col-md-2">
            <div>{{ stadiumName }}</div>
            <div>{{ stadiumAddress }}</div>
            <div>내용 : {{ detail.content }}</div>
          </div>
        </div>
        <div class="row" v-if="imgSrc === 2">
          <div class="col-md-5">
            <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png" alt="Stadium Image">
          </div>
          <div class="col-md-5">
            <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(2).png" alt="Stadium Image">
          </div>
          <div class="col-md-2">
            <div><h4>{{ stadiumName }}</h4></div>
            <div><h5>{{ stadiumAddress }}</h5></div>
            <div><h5>{{ detail.authorName }} : {{ detail.content }}</h5></div>
          </div>
        </div>
        <div class="row" v-if="imgSrc === 3">
          <div class="col-md-5">
            <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png" alt="Stadium Image">
          </div>
          <div class="col-md-5">
            <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(2).png" alt="Stadium Image">
          </div>
          <div class="col-md-2">
            <div>{{ stadiumName }}</div>
            <div>{{ stadiumAddress }}</div>
            <div>내용 : {{ detail.content }}</div>
          </div>
        </div>
        <div class="row" v-if="imgSrc === 4">
          <div class="col-md-5">
            <img src="@/asset/stadium/가오풋살장/가오풋살장(1).png" alt="Stadium Image">
          </div>
          <div class="col-md-5">
            <img src="@/asset/stadium/가오풋살장/가오풋살장(2).png" alt="Stadium Image">
          </div>
          <div class="col-md-2">
            <div>{{ stadiumName }}</div>
            <div>{{ stadiumAddress }}</div>
            <div>내용 : {{ detail.content }}</div>
          </div>
        </div>
      </div>
      <div class="buttons">
        <div v-if="detail.teamBId == 0 && detail.authorName !== nickName">
          <button @click="registMatch()">신청하기</button>
        </div>
        <div v-if="detail.teamBLeader === nickName">
          <button @click="cancelMatch()">신청취소</button>
        </div>
        <div v-if="detail.authorName === nickName">
          <router-link :to="{ name: 'matchupdate', params: { id: detail.id } }">
            <button>수정하기</button>
          </router-link>
        </div>
        <div v-if="detail.authorName === nickName">
          <button @click="deleteMatch()">삭제하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "@/api/axios";
import { useMatchStore } from "@/stores/match";

const detail = ref([]);
const nickName = ref('');
const teamAMember = ref([]);
const teamBMember = ref([]);
const route = useRoute();
const router = useRouter();
const imgSrc = ref('');
const store = useMatchStore();

// 경기장 이름, 장소
const stadiumName = ref('');
const stadiumAddress = ref('');

const detailPost = async (id) => {
  try {
      const response = await axios.get(`/futsal/match/${id}`);
      detail.value = response.data;
      detail.value.matchTime = detail.value.matchTime.split(':').slice(0, 2).join(':');
      stadiumName.value = store.stadiums[response.data.stadiumId-1].name;
      stadiumAddress.value = store.stadiums[response.data.stadiumId-1].address;
      console.log(response.data);
      showTeamA();
      if (detail.value.teamBId) {
          showTeamB();
      }
      imgSrc.value = detail.value.stadiumId;
  } 
  catch (error) {
      console.error(error);
  }
};

const registMatch = async () => {
  try {
      const response = await axios.put(`/futsal/match/${route.params.id}/regist`);
      detail.value = response.data;
      console.log(response.data);
      if (detail.value.teamBId) {
          showTeamB();
      }
  }
  catch (error) {
      console.log(error)
  }
}

const cancelMatch = async () => {
  try {
      const response = await axios.delete(`/futsal/match/${route.params.id}/cancel`);
      detail.value = response.data;
      console.log(response.data)
      // 새로고침
      location.reload();
  }
  catch (error) {
      console.log(error)
  }
}

const deleteMatch = async () => {
  try {
      await axios.delete(`/futsal/match/${route.params.id}`);
      router.push('/match');
  }
  catch (error) {
      console.log(error)
  }
}

const showTeamA = async () => {
  try {
      const response = await axios.get(`/futsal/board/team/${detail.value.teamAId}`);
      teamAMember.value = response.data;
  }
  catch (error) {
      console.log(error)
  }
}

const showTeamB = async () => {
  try {
      const response = await axios.get(`/futsal/board/team/${detail.value.teamBId}`);
      teamBMember.value = response.data;
  }
  catch (error) {
      console.log(error)
  }
}

onMounted(() => {
  nickName.value = sessionStorage.getItem("nickName");
  detailPost(route.params.id);
});
</script>

<style scoped>
.row img {
  max-width: 100%;
  height: auto;
}

.detail-container {
  margin-top: 100px;
  margin-left: 250px;
  margin-right: 250px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.detail {
  margin: 30px;
}

.logo-container {
  margin: 70px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  text-align: center;
}

.team-container {
  margin: 70px;
}

.team-leader {
  margin: 30px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.team-member {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.field {
  max-width: 30%;
  height: auto;
}

.versus {
  display: flex;
  justify-content: center;
  align-items: center;
}

.buttons {
  display: flex;
  align-items: flex-end;
  gap: 10px;
}
</style>