<template>
  <div class="board-content">
    <h2>매칭게시판입니당</h2>
    <table class="match-table">
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
        <tr v-for="post in matchList" :key="post.matchId">
          <td>{{ post.teamBId ? '[마감]' : '[모집]' }}</td>
          <td>
            <router-link :to="{ name: 'matchdetail', params: { id: post.matchId } }" class="link">
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
    <router-link :to="{ name: 'matchregist' }">
      <button class="register-button">등록하기</button>
    </router-link>
  </div>
</template>

<script setup>
import { useMatchStore } from '@/stores/match';
import { onMounted } from 'vue';
import { storeToRefs } from 'pinia';

const store = useMatchStore();
const { matchList } = storeToRefs(store);

onMounted(() => {
  store.getBoardList();
});
</script>

<style scoped>
.board-content {
  margin: 20px;
}

.match-table {
  width: 100%;
  border-collapse: collapse;
}

.match-table th, .match-table td {
  border: 1px solid #e2e8f0;
  padding: 12px;
  text-align: center;
}

.match-table th {
  background-color: #f8fafc;
}

.match-table td {
  background-color: #fff; /* 짝수 행과 홀수 행에 대한 배경색을 번갈아가며 설정하세요. */
}

.link {
  text-decoration: none;
  color: #333;
  font-weight: bold;
}

.register-button {
  margin-top: 20px;
  padding: 12px 20px;
  background-color: #4caf50; /* 색상 변경 */
  color: #ffffff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: block; /* 버튼을 블록 수준 요소로 변경하여 가로 폭을 꽉 채우도록 합니다. */
  width: 100%; /* 버튼을 부모 요소의 가로 폭에 맞추도록 설정합니다. */
}

.register-button:hover {
  background-color: #45a049; /* 호버 시 색상 변경 */
}
</style>
