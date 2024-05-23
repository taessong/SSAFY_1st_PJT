<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4" :class="favoriteTeamColorClass">팀 모집 페이지</h2>
    <form @submit.prevent="submitForm" class="form-container">
      <div class="form-group mb-3">
        <label for="content" class="form-label">내용</label>
        <input type="text" id="content" name="content" v-model="form.content" placeholder="모집 내용" class="form-control"/>
      </div>
      <button type="submit" :class="['btn', 'w-100', favoriteTeamButtonClass]">등록</button>
    </form>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import axios from '@/api/axios';
import { useRouter } from 'vue-router';
import { getTeamColorClass } from '@/utils/teamColors';

const router = useRouter();

const form = ref({
  content: ""
});

const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

const submitForm = async () => {
  const recruitForm = {
    content: form.value.content
  };

  const res = await axios.get('futsal/board/team');
  console.log(res.data);
  for(let i = 0; i < res.data.length; i++){
    if(res.data[i].leaderName === sessionStorage.getItem("nickName")){
      alert("이미 팀을 만드셨기 때문에, 팀 모집이 불가합니다.");
      router.push({ name: 'clubBoard', query: { tab: 'recruit' } });
      return;
    }
  }

  try {
    const response = await axios.post('/futsal/board', recruitForm, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
    alert("작성이 완료 되었습니다.");
    // 글 등록 후 필요한 동작 수행 (예: 페이지 이동)
    await axios.post(`/futsal/board/team`);
    console.log("생성완료.");
    router.push({ name: 'clubBoard', query: { tab: 'recruit' } });
  } catch (error) {
    console.error('글 등록 실패:', error);
  }
};
</script>
<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  font-weight: bolder;
}

.form-container {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 1rem;
}

.form-label {
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #495057;
}

.form-control {
  padding: 0.5rem;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #ced4da;
  transition: border-color 0.3s;
}

.form-control:focus {
  border-color: var(--team-color, #80bdff);
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.btn-primary {
  background-color: var(--team-color, #007bff);
  border-color: var(--team-color, #007bff);
  color: white;
}

.btn-primary:hover {
  background-color: var(--team-color-hover, #0056b3);
  border-color: var(--team-color-hover, #004085);
}

.w-100 {
  width: 100%;
}

/* 팀별로 동적으로 스타일 적용 */
.mancity-color {
  color: #6CABDD !important;
}

.manutd-color {
  color: #DA291C !important;
}

.liverpool-color {
  color: #C8102E !important;
}

.chelsea-color {
  color: #034694 !important;
}

.arsenal-color {
  color: #EF0107 !important;
}

.tottenham-color {
  color: #132257 !important;
}

.mancity-btn {
  background-color: #6CABDD !important;
  border-color: #6CABDD !important;
}

.manutd-btn {
  background-color: #DA291C !important;
  border-color: #DA291C !important;
}

.liverpool-btn {
  background-color: #C8102E !important;
  border-color: #C8102E !important;
}

.chelsea-btn {
  background-color: #034694 !important;
  border-color: #034694 !important;
}

.arsenal-btn {
  background-color: #EF0107 !important;
  border-color: #EF0107 !important;
}

.tottenham-btn {
  background-color: #132257 !important;
  border-color: #132257 !important;
}
</style>
