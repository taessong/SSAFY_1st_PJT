<template>
    <div>
      <h2>매치 디테일</h2>
      <table>
        <thead>
          <tr>
            <th>분류</th>
            <th>내용</th>
            <th>작성자</th>
            <th>경기일자</th>
            <th>경기시간</th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="detail">
            <td>{{ detail.teamBId ? '[마감]' : '[모집]' }}</td>
            <td>{{ detail.content }}</td>
            <td>{{ detail.authorName }}</td>
            <td>{{ detail.matchDate }}</td>
            <td>{{ detail.matchTime }}</td>
          </tr>
        </tbody>
      </table>
      <div>
        <h3>팀원목록</h3>
        <ul>
          <span v-for="member in teamAMember" :key="member.id">
            {{ member.memberName }}
          </span>
        </ul>
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
            <ul>
                <span v-for="member in teamBMember" :key="member.id">
                    {{ member.memberName }}
                </span>
            </ul>
        </div>

        <div>
            <!-- 로고 -->
            
        </div>

        <div v-if="detail.stadiumImg">
            <h3>경기장</h3>
            <img src="@/asset/stadium/유성풋살구장/유성풋살구장(1).png" alt="">
            <img :src="getStadiumImage(detail.stadiumImg)">
        </div>

  
      <!-- 상대팀이 있거나 신청자가 게시글을 볼 경우 신청하기 버튼 없앰 -->
      <div v-if="detail.teamBId == 0 && detail.authorName !== nickName">
        <button @click="registMatch()">신청하기</button>
      </div>
      <!-- 상대팀으로 등록한 사람한테만 신청취소 버튼이 보임 -->
      <div v-if="detail.teamBLeader === nickName">
        <button @click="cancelMatch()">신청취소</button>
      </div>
      <!-- 수정하기 버튼 -->
      <div v-if="detail.authorName === nickName">
        <button @click="updateMatch()">수정하기</button>
      </div>
      <!-- 삭제하기 버튼 -->
      <div v-if="detail.authorName === nickName">
        <button @click="deleteMatch()">삭제하기</button>
      </div>
    </div>
  </template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";

const detail = ref([]);
const nickName = ref('');
const teamAMember = ref([]);
const teamBMember = ref([]);
const route = useRoute();
const router = useRouter();

const detailPost = async (id) => {
    try {
    const response = await axios.get(
        `http://localhost:8080/futsal/match/${id}`
    );
    detail.value = response.data;
    console.log(response.data);
    showTeamA();
    showTeamB();
    } 
    catch (error) {
        console.error(error);
    }
};

const getStadiumImage = (imageName) => {
    console.log(`@/asset/stadium/${imageName}`);
    return `@/asset/stadium/${imageName}`;
};

const registMatch = async () => {
    try {
        const response = await axios.put(`http://localhost:8080/futsal/match/${route.params.id}/regist`);
        detail.value = response.data;
        console.log(response.data);
    }
    catch (error) {
        console.log(error)
    }
}

const cancelMatch = async () => {
    try {
        const response = await axios.delete(`http://localhost:8080/futsal/match/${route.params.id}/cancel`);
        detail.value = response.data;
        console.log(response.data)
        // 새로고침
        location.reload();
    }
    catch (error) {
        console.log(error)
    }
}

// const updateMatch = async () => {
//     try {
//         const response = await axios.
//     }
//     catch (error) {
//         console.log(error)
//     }
// }

const deleteMatch = async () => {
    try {
        const response = await axios.delete(`http://localhost:8080/futsal/match/${route.params.id}`);
        detail.value = response.data;
        router.push('/match')
    }
    catch (error) {
        console.log(error)
    }
}

// 팀원을 보여주자
const showTeamA = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/futsal/board/team/${detail.value.teamAId}`);
        teamAMember.value = response.data;
    }
    catch (error) {
        console.log(error)
    }
}

// 팀원을 보여주자
const showTeamB = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/futsal/board/team/${detail.value.teamBId}`);
        teamBMember.value = response.data;
        
    }
    catch (error) {
        console.log(error)
    }
}

onMounted(() => {
    nickName.value = sessionStorage.getItem("nickName"),
    detailPost(route.params.id)
});
</script>

<style>
img {
  max-width: 50%;
  height: auto;
}
</style>