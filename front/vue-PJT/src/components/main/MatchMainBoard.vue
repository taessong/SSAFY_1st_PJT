<template>
  <div class="container py-4">
    <div class="match-header d-flex justify-content-between align-items-end mb-3">
      <h2 :class="favoriteTeamColorClass">매칭 게시판</h2>
      <button @click="gotoMatchBoard" :class="['btn', favoriteTeamButtonClass, 'btn-sm']">+</button>
    </div>
    <div class="table-responsive">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th :class="favoriteTeamColorClass">분류</th>
            <th :class="favoriteTeamColorClass">내용</th>
            <th :class="favoriteTeamColorClass">작성자</th>
            <th :class="favoriteTeamColorClass">경기일자</th>
            <th :class="favoriteTeamColorClass">경기시간</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="match in store.matchList" :key="match.id">
            <td>{{ match.teamBId ? '[마감]' : '[모집]' }}</td>
            <td>{{ match.content }}</td>
            <td>{{ match.authorName }}</td>
            <td>{{ match.matchDate }}</td>
            <td>{{ match.matchTime }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup>
import { useRouter } from "vue-router";
import { useMatchStore } from "@/stores/match";
import { onMounted, ref, computed } from "vue";
import { getTeamColorClass } from "@/utils/teamColors";

const router = useRouter();
const store = useMatchStore();

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = computed(() => getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = computed(() => getTeamColorClass(favoriteTeam, 'btn'));

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamButtonClass:', favoriteTeamButtonClass.value);

const gotoMatchBoard = () => {
  router.push({ name: "matchboard" });
};

const getBoardListSummary = () => {
  store.getBoardListSummary();
  for(let i=0; i<store.matchList.length; i++){
    console.log(store.matchList[i]);
  }
}

onMounted(() => {
  getBoardListSummary();
});
</script>
<style scoped>
.container {
  padding: 15px;
  width: 100%;
}

.match-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.button {
  width: 20px;
  height: 20px;
  border: none;
  background-color: gainsboro;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 5px;
  font-weight: 900;
  margin-bottom: 3px;
}

h2 {
  font-weight: bolder;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1 !important;
}

/* Define color variables for different teams */
.mancity-btn, .mancity-color, .mancity-text {
  --team-bg: #6CABDD !important;
  --team-text: #6CABDD !important;
}

.manutd-btn, .manutd-color, .manutd-text {
  --team-bg: #DA291C !important;
  --team-text: #DA291C !important;
}

.liverpool-btn, .liverpool-color, .liverpool-text {
  --team-bg: #C8102E !important;
  --team-text: #C8102E !important;
}

.chelsea-btn, .chelsea-color, .chelsea-text {
  --team-bg: #034694 !important;
  --team-text: #034694 !important;
}

.arsenal-btn, .arsenal-color, .arsenal-text {
  --team-bg: #EF0107 !important;
  --team-text: #EF0107 !important;
}

.tottenham-btn, .tottenham-color, .tottenham-text {
  --team-bg: #132257 !important;
  --team-text: #132257 !important;
}

/* General button styles */
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

/* Table header style for team colors */
th {
  color: var(--team-text) !important;
}

th, td {
  text-align: center;
  vertical-align: middle;
}
</style>
