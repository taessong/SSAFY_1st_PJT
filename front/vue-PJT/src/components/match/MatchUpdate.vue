<template>
  <div :class="['container mt-5']">
    <h2 class="text-center mb-4">수정 페이지</h2>

    <fieldset class="p-4 border rounded shadow">
      <legend class="w-auto px-2">수정</legend>
      <div class="mb-3">
        <label for="content" class="form-label">매치 내용:</label>
        <input type="text" id="content" v-model="detail.content" class="form-control">
      </div>

      <div class="mb-3">
        <label for="matchDate" class="form-label">경기 날짜 선택:</label>
        <input type="date" id="matchDate" v-model="detail.matchDate" class="form-control">
      </div>

      <div class="mb-3">
        <label for="matchTime" class="form-label">경기 시간 입력:</label>
        <input type="time" id="matchTime" v-model="detail.matchTime" class="form-control">
      </div>

      <div class="mb-3">
        <label for="stadiumId" class="form-label">경기장 ID 입력:</label>
        <input type="text" id="stadiumId" v-model="detail.stadiumId" class="form-control">
      </div>
    </fieldset>

    <div class="d-flex justify-content-center mt-4">
      <button @click="updateMatch" :class="['btn', 'btn-outline-primary', ]">등록하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { useMatchStore } from '@/stores/match';
import { getTeamColorClass } from '@/utils/teamColors';

const store = useMatchStore();
const route = useRoute();
const detail = ref({});
const teamColorClass = ref('');

onMounted(async () => {
  await store.getBoard(route.params.id);
  detail.value = { ...store.match };
  
  const favoriteTeam = sessionStorage.getItem('favoriteTeam');
  if (favoriteTeam) {
    teamColorClass.value = getTeamColorClass(favoriteTeam, 'color');
  }
});

const updateMatch = () => {
  store.updateMatch(route.params.id, detail.value);
};
</script>

<style scoped>
.container {
  width: 40%;
  margin-left: auto;
  margin-right: auto;
  padding: 20px;
  border-radius: 8px;
  background-color: #f8f9fa;
}

fieldset {
  margin-bottom: 20px;
}

legend {
  font-size: 1.2rem;
  font-weight: bold;
}

.text-center {
  text-align: center;
}

.form-label {
  font-weight: bold;
}

.btn-primary {
  width: 200px;
}

/* 팀별 배경색 */
.mancity-color {
  background-color: #e3f2fd;
}

.manutd-color {
  background-color: #f8d7da;
}

.liverpool-color {
  background-color: #f5c6cb;
}

.chelsea-color {
  background-color: #d6e9f8;
}

.arsenal-color {
  background-color: #f1d4d4;
}

.tottenham-color {
  background-color: #e0e0e0;
}
</style>
