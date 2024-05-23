<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 :class="favoriteTeamColorClass">매칭게시판</h2>
      <router-link :to="{ name: 'matchregist' }">
        <button :class="['btn', 'btn-primary', favoriteTeamButtonClass]">등록하기</button>
      </router-link>
    </div>
    <div class="table-responsive">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th :class="favoriteTeamColorClass">분류</th>
            <th :class="favoriteTeamColorClass">내용</th>
            <th :class="favoriteTeamColorClass">팀</th>
            <th :class="favoriteTeamColorClass">작성자</th>
            <th :class="favoriteTeamColorClass">경기일자</th>
            <th :class="favoriteTeamColorClass">경기시간</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="post in matchList" :key="post.matchId">
            <td>{{ post.teamBId ? '[마감]' : '[모집]' }}</td>
            <td>
              <router-link :to="{ name: 'matchdetail', params: { id: post.matchId } }" class="click">
                {{ post.content }}
              </router-link>
            </td>
            <td>{{ post.teamAName }}</td>
            <td>{{ post.authorName }}</td>
            <td>{{ post.matchDate }}</td>
            <td>{{ post.matchTime }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup>
import { useMatchStore } from '@/stores/match';
import { onMounted,ref } from 'vue';
import { storeToRefs } from 'pinia';
import { getTeamColorClass } from "@/utils/teamColors";

const store = useMatchStore();
const { matchList } = storeToRefs(store);

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamButtonClass:', favoriteTeamButtonClass.value);

onMounted(() => {
  store.getBoardList();
});
</script>
<style scoped>
.container {
  margin-top: 20px;
}

.table-responsive {
  margin-bottom: 20px;
}

.table {
  width: 100%;
  border-collapse: collapse;
}

.table th, .table td {
  border: 1px solid #e2e8f0;
  padding: 12px;
  text-align: center;
  font-weight: bold;
}

.table th {
  background-color: #f8fafc;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1;
}

.click {
  cursor: pointer;
  color: #333;
  text-decoration: none;
  font-weight: bold;
}

.click:hover {
  color: #000;
  text-decoration: underline;
}

.btn-primary {
  background-color: #4caf50;
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #45a049;
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

a {
  text-decoration-color: none;
}

.pagination .btn {
  margin: 0 0.25rem;
}

.pagination .btn-light {
  background-color: #d6d6d6;
  color: #333;
}

.pagination .btn.active {
  background-color: #4caf50;
  color: #ffffff;
}

h2 {
  font-weight: bolder;
}
</style>
