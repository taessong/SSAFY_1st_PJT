<template>
  <div>
    <h2>매칭게시판</h2>
    <table>
      <thead>
        <tr>
          <th>마감여부</th>
          <th>내용</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>최대인원</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="post in list" :key="post.recruitmentId">
          <td>{{ post.full }}</td>
          <td @click="goToDetail(post.recruitmentId)">{{ post.content }}</td>
          <td>{{ post.authorName }}</td>
          <td>{{ post.regDate }}</td>
          <td>{{ post.maxMembers }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import router from "@/router";

axios.defaults.withCredentials = true;

const list = ref([]);

const boardList = async () => {
  try {
    const response = await axios.get("http://localhost:8080/futsal/board");
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

<style scoped>
</style>
