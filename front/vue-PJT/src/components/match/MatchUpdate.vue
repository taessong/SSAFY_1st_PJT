<template>
  <div>
    <h2>수정 페이지</h2>

    <fieldset>
      <legend>수정</legend>
      <div>
        <label for="content">매치 내용:</label>
        <input type="text" id="content" v-model="detail.content">
      </div>

      <div>
        <label for="matchDate">경기 날짜 선택:</label>
        <input type="date" id="matchDate" v-model="detail.matchDate">
      </div>

      <div>
        <label for="matchTime">경기 시간 입력:</label>
        <input type="time" id="matchTime" v-model="detail.matchTime">
      </div>

      <div>
        <label for="stadiumId">경기장 ID 입력:</label>
        <input type="text" id="stadiumId" v-model="detail.stadiumId">
      </div>
    </fieldset>

    <div>
      <button @click="updateMatch">등록하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useMatchStore } from '@/stores/match';

const store = useMatchStore();
const route = useRoute();
const detail = ref({});

onMounted(async () => {
  await store.getBoard(route.params.id);
  detail.value = { ...store.match };
});

const updateMatch = () => {
  store.updateMatch(route.params.id, detail.value);
};
</script>

<style scoped>
</style>
