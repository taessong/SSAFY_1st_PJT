<template>
    <div>
        <h2>수정 페이지</h2>

        <table>
            <thead>
                <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>경기일자</th>
                <th>경기시간</th>
                </tr>
            </thead>
            <tbody>
                <tr v-if="detail">
                <td>{{ detail.content }}</td>
                <td>{{ detail.authorName }}</td>
                <td>{{ detail.matchDate }}</td>
                <td>{{ detail.matchTime }}</td>
                </tr>
            </tbody>
        </table>

        <div v-if="detail.authorName === nickName">
            <button @click="updateMatch()">등록하기</button>
        </div>
    </div>


</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const detail = ref([]);
const nickName = ref('');
const route = useRoute();

const detailPost = async (id) => {
  try {
      const response = await axios.get(`http://localhost:8080/futsal/match/${id}`);
      detail.value = response.data;
      console.log(response.data);
  } 
  catch (error) {
      console.error(error);
  }
};

const updateMatch = async () => {
  try {
    const response = await axios.put(`http://localhost:8080/futsal/match/${route.params.id}`);
    detail.value = response.data;
    console.log(response.data);
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

</style>