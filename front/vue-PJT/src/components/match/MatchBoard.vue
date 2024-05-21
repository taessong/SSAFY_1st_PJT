<template>
  <div class="board-content">
    <h2>매칭게시판입니당</h2>
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
        <tr v-for="post in matchList" :key="post.matchId">
          <td>{{ post.teamBId ? '[마감]' : '[모집]' }}</td>
          <td>
            <router-link :to="{ name: 'matchdetail', params: { id: post.matchId } }">
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
      <button>등록하기</button>
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

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f4f4f4;
}

button {
  margin-top: 10px;
}
</style>
