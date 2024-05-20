<template>
    <h2>매칭게시판입니당</h2>
    <div class="board-content">
          <table>
            <thead>
              <tr>
                <th>분류</th>
                <th>내용</th>
                <th>팀</th>
                <th>작성자</th>
                <th>경기일자</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="post in list" :key="post.matchId">
                <td>{{ post.teamBId ? '[마감]' : '[모집]' }}</td>
                <td @click="goToDetail(post.matchId)">{{ post.content }}</td>
                <td>{{ post.teamAName }}</td>
                <td>{{ post.authorName }}</td>
                <td>{{ post.matchTime }}</td>
              </tr>
            </tbody>
          </table>
        </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import router from "@/router";
axios.defaults.withCredentials = true;

const list = ref([]);

const boardList = async () => {
  try {
    const response = await axios.get("http://localhost:8080/futsal/match");
    list.value = response.data;
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
};

const goToDetail = (id) => {
  router.push({ name: "matchdetail", params: { id } });
};

onMounted(() => {
  boardList();
});
</script>

<style>

</style>