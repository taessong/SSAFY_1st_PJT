<template>
  <div>
    <h2>상세페이지</h2>
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
        <tr v-if="detail">
          <td>{{ detail.full }}</td>
          <td>{{ detail.content }}</td>
          <td>{{ detail.authorName }}</td>
          <td>{{ detail.regDate }}</td>
          <td>{{ detail.maxMembers }}</td>
        </tr>
      </tbody>
    </table>
    <h2>신청하기를 클릭하면 작성자가 아닌 유저가 팀원이 된다!!</h2>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

export default {
  setup() {
    const detail = ref([]);
    const route = useRoute();

    const detailPost = async (id) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/futsal/board/${id}`
        );
        detail.value = response.data;
        console.log(response);
      } catch (error) {
        console.error(error);
      }
    };

    onMounted(() => {
      detailPost(route.params.id);
    });

    return { detail };
  },
};
</script>

<style scoped>
</style>