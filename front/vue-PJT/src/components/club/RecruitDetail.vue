<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <button @click="goBack" :class="['btn', favoriteTeamButtonClass]">◀️ 뒤로가기</button>
      <div v-if="isWriter">
        <button @click="gotoUpdate" :class="['btn', 'btn-warning', favoriteTeamButtonClass, 'me-2']">수정</button>
        <button @click="gotoDelete" :class="['btn', 'btn-danger', favoriteTeamButtonClass]">삭제</button>
      </div>
    </div>
    <h2 class="detail-title">모집 정보</h2>
    <div class="table-responsive">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th :class="favoriteTeamColorClass">마감여부</th>
            <th :class="favoriteTeamColorClass">내용</th>
            <th :class="favoriteTeamColorClass">작성자</th>
            <th :class="favoriteTeamColorClass">작성일</th>
            <th :class="favoriteTeamColorClass">최대인원</th>
          </tr>
        </thead>
        <tbody> 
          <tr>
            <td>{{ recruitItem.full }}</td>
            <td>{{ recruitItem.content }}</td>
            <td>{{ recruitItem.authorName }}</td>
            <td>{{ recruitItem.regDate }}</td>
            <td><span>{{ futsalMember.length + 1 }} / </span> {{ recruitItem.maxMembers }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="d-flex justify-content-end mb-4">
      <button @click="applyTeam" :class="['btn', favoriteTeamButtonClass, 'me-2']">신청하기</button>
      <button @click="cancleTeam" :class="['btn', favoriteTeamButtonClass]">취소하기</button>
    </div>
    <div v-for="member in futsalMember" :key="member.id" class="mb-2">
      {{ member.memberName }}
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useRecruitStore } from "@/stores/recruit";
import axios from "@/api/axios";
import { getTeamColorClass } from "@/utils/teamColors";

const route = useRoute();
const router = useRouter();
const isWriter = ref(false);
const store = useRecruitStore();
const recruitItem = computed(() => store.recruitItem);
const futsalMember = computed(() => store.futsalMember);

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamButtonClass:', favoriteTeamButtonClass.value);

const goBack = () => {
  router.back();
};

const gotoUpdate = () => {
  const recruitId = route.params.id;
  router.push({name: 'recruitUpdate', params: {id : recruitId}})
}

const gotoDelete = async () => {
  const recruitId = route.params.id;
  await store.deleteRecruitBoard(recruitId);
  //팀 삭제까지 동시에 수행하기
  await axios.delete('/futsal/board/team');
  console.log("삭제완료");
  router.push({name: 'clubBoard'});
}

const applyTeam = async () => {
  if(sessionStorage.getItem("nickName") === recruitItem.value.authorName){
    alert("작성자는 팀 리더이기 때문에 신청을 할 수 없습니다.");
  }
  else{
    try {
      await store.isTeamMember();
      router.go(0);
    } catch (error) {
      console.log("에러!!!", error)
    }
  }
}

const cancleTeam = async () => {
  for(let i = 0; i < futsalMember.value.length; i++){
    if(futsalMember.value[i].memberName === sessionStorage.getItem("nickName")){
      try {
        await axios.delete(`futsal/board/team/${futsalMember.value[i].futsalTeamId}`, {
          params: {
            memberName: futsalMember.value[i].memberName
          }
        });
        alert("삭제 됐어요~"); 
        router.go(0);
      }catch(err){
        console.log(err, "에러다")
      }
    }
  }
}

onMounted(async() => {
  const recruitmentId = route.params.id;
  // await store.fetchRcruitOneData(recruitmentId);
  await store.fetchTeamData(recruitmentId);
  if(sessionStorage.getItem("nickName") === recruitItem.value.authorName){
    isWriter.value = true;
  }

  if(futsalMember.value.length + 1 >= 6){
    recruitItem.value.full = true;
  }
  else{
    recruitItem.value.full = false;
  }
});
</script>
<style scoped>
.container {
  padding: 15px;
  width: 100%;
}

.gobackButton {
  border: none;
  padding: 6px;
  margin-top: 25px;
  border-radius: 8px;
  background-color: darkcyan;
  color: white;
  font-weight: 800;
  cursor: pointer;
}

h2 {
  font-weight: bolder;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1 !important;
}

th, td {
  text-align: center;
  vertical-align: middle;
}

/* Define color variables for different teams */
.mancity-btn, .mancity-color {
  --team-bg: #6CABDD !important;
  --team-text: #6CABDD !important;
}

.manutd-btn, .manutd-color {
  --team-bg: #DA291C !important;
  --team-text: #DA291C !important;
}

.liverpool-btn, .liverpool-color {
  --team-bg: #C8102E !important;
  --team-text: #C8102E !important;
}

.chelsea-btn, .chelsea-color {
  --team-bg: #034694 !important;
  --team-text: #034694 !important;
}

.arsenal-btn, .arsenal-color {
  --team-bg: #EF0107 !important;
  --team-text: #EF0107 !important;
}

.tottenham-btn, .tottenham-color {
  --team-bg: #132257 !important;
  --team-text: #132257 !important;
}

.btn.active {
  background-color: var(--team-bg) !important;
  color: var(--team-text) !important;
}

.btn-light {
  background-color: #d6d6d6 !important; /* 밝은 회색 */
  color: #333 !important; /* 어두운 회색 */
}

.btn {
  transition: background-color 0.3s, color 0.3s;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
}

.pagination .btn {
  margin: 0 0.25rem;
}
</style>
