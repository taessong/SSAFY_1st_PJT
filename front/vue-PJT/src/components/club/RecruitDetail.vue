<template>
  <div>
    <h2>상세페이지</h2>
    <button @click="goBack" class="gobackButton">◀️ 뒤로가기</button>
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
        <tr>
          <td>{{ recruitItem.full }}</td>
          <td>{{ recruitItem.content }}</td>
          <td>{{ recruitItem.authorName }}</td>
          <td>{{ recruitItem.regDate }}</td>
          <td>{{ recruitItem.maxMembers }}</td>
        </tr>
      </tbody>
    </table>
    <h2>신청하기를 클릭하면 작성자가 아닌 유저가 팀원이 된다!!</h2>
    <div v-if="isWriter">
      <button @click="gotoUpdate">수정</button>
      <button @click="gotoDelete">삭제</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useRecruitStore } from "@/stores/recruit";

const route = useRoute();
const router = useRouter();
const isWriter = ref(false);
const store = useRecruitStore();
const recruitItem = computed(() => store.recruitItem);

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
  router.push({name: 'clubBoard'});
}

onMounted(async() => {
  const recruitmentId = route.params.id;
  await store.fetchRcruitOneData(recruitmentId);
  if(sessionStorage.getItem("nickName") === recruitItem.value.authorName){
    isWriter.value = true;
  }
});
</script>

<style scoped>
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
</style>