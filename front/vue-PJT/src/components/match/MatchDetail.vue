<template>
  <div>
    <h2>매치 디테일</h2>
    <table>
      <thead>
        <tr>
          <th>분류</th>
          <th>내용</th>
          <th>팀</th>
          <th>작성자</th>
          <th>경기일자</th>
          <th>경기시간</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="detail">
          <td>{{ detail.teamBId ? '[마감]' : '[모집]' }}</td>
          <td>{{ detail.content }}</td>
          <td>{{ detail.teamAName }}</td>
          <td>{{ detail.authorName }}</td>
          <td>{{ detail.matchDate }}</td>
          <td>{{ detail.matchTime }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <h3>팀원 목록</h3>
      <div>
        <p v-for="member in teamAMember" :key="member.id" class="team-member">
          {{ member.memberName }}
        </p>
      </div>
    </div>

    <div v-if="detail.teamBId">
      <h5>상대팀</h5>
      <table>
          <thead>
              <tr>
                  <th>팀</th>
                  <th>리더</th>
              </tr>
          </thead>
          <tbody>
              <tr v-if="detail.teamBName">
                  <td>{{ detail.teamBName }}</td>
                  <td>{{ detail.teamBLeader }}</td>
              </tr>
          </tbody>
      </table>
      <h3>팀원목록</h3>
        <p v-for="member in teamBMember" :key="member.id">
            {{ member.memberName }}
        </p>
    </div>

    <div v-if="detail.stadiumId">
      <div v-if="imgSrc === 1">
        <img src="@/asset/stadium/유성풋살구장/유성풋살구장(1).png" alt="Stadium Image">
        <img src="@/asset/stadium/유성풋살구장/유성풋살구장(2).png" alt="Stadium Image">
      </div>
      <div v-if="imgSrc === 2">
        <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png" alt="Stadium Image">
        <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(2).png" alt="Stadium Image">
      </div>
      <div v-if="imgSrc === 3">
        <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png" alt="Stadium Image">
        <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(2).png" alt="Stadium Image">
      </div>
      <div v-if="imgSrc === 4">
        <img src="@/asset/stadium/가오풋살장/가오풋살장(1).png" alt="Stadium Image">
        <img src="@/asset/stadium/가오풋살장/가오풋살장(2).png" alt="Stadium Image">
      </div>
    </div>
    <div>
      <p>경기장 이름 : {{ stadiumName }}</p>
      <p>경기장 장소 : {{ stadiumAddress }}</p>
    </div>

    <!-- 상대팀이 있거나 신청자가 게시글을 볼 경우 신청하기 버튼 없앰 -->
    <!-- 각 팀의 리더만 신청이 가능해야 함.. -->
    <div v-if="detail.teamBId == 0 && detail.authorName !== nickName">
      <button @click="registMatch()">신청하기</button>
    </div>
    <!-- 상대팀으로 등록한 사람한테만 신청취소 버튼이 보임 -->
    <div v-if="detail.teamBLeader === nickName">
      <button @click="cancelMatch()">신청취소</button>
    </div>
    <!-- 수정하기 버튼 -->
    <div v-if="detail.authorName === nickName">
      <router-link :to="{ name: 'matchupdate', params: { id: detail.id } }">
        <button>수정하기</button>
      </router-link>
    </div>
    <!-- 삭제하기 버튼 -->
    <div v-if="detail.authorName === nickName">
      <button @click="deleteMatch()">삭제하기</button>
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

// const getStadiumImage = (imageName) => {
//   return `/path/to/stadium/images/${imageName}`; // Update this path to your actual images folder
// };

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

const updateMatch = async () => {
  try {
    const response = await axios.put(`/futsal/match/${route.params.id}`);
    detail.value = response.data;
    console.log(response.data);
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

<style>
img {
max-width: 50%;
height: auto;
padding: 40px;
}
</style>