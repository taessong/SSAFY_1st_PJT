<template>
  <div v-if="chatItemReady" class="container mt-5">
    <h2 :class="favoriteTeamColorClass">게시글 수정</h2>
    <form @submit.prevent="updateBoard" class="form-group">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" id="title" name="title" v-model="form.title" :class="['form-control', favoriteTeamColorClass]" />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <input type="text" id="content" v-model="form.content" :class="['form-control', favoriteTeamColorClass]" />
      </div>
      <button type="submit" :class="['btn', favoriteTeamButtonClass]">수정</button>
    </form>
  </div>
</template>

<script setup>
import { useClubStore } from "@/stores/club";
import axios from "@/api/axios";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getTeamColorClass } from "@/utils/teamColors";

const route = useRoute();
const router = useRouter();
const storeDetail = useClubStore();

const form = ref({
  title: "",
  content: "",
});

const chatItemReady = ref(false);

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

onMounted(async () => {
  const postId = route.params.id;
  await storeDetail.fetchOneChatData(postId);
  const chatItem = storeDetail.chatItem;

  if (chatItem) {
    // chatItem의 데이터를 form에 복사
    form.value.title = chatItem.title;
    form.value.content = chatItem.content;
    chatItemReady.value = true;
  }
});

const updateBoard = async () => {
  try {
    const postId = route.params.id;
    console.log(form.value);
    await axios.put(`/community/board/${postId}`, form.value);
    console.log("수정 됐어요~");
    router.back();
  } catch (error) {
    console.log("수정 실패...", error);
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group .form-label {
  font-weight: bold;
}

.form-group .form-control {
  margin-bottom: 10px;
}

.form-group .btn {
  align-self: center;
  font-size: 1.2rem;
  padding: 10px 20px;
}

/* Define color variables for different teams */
.mancity-btn, .mancity-color {
  --team-bg: #6CABDD !important;
  --team-text: #6CABDD !important;
}

.manutd-btn, .manutd-color {
  --team-bg: #DA291C !important;
  --team-text: #DA291C !important;
}

.liverpool-btn, .liverpool-color {
  --team-bg: #C8102E !important;
  --team-text: #C8102E !important;
}

.chelsea-btn, .chelsea-color {
  --team-bg: #034694 !important;
  --team-text: #034694 !important;
}

.arsenal-btn, .arsenal-color {
  --team-bg: #EF0107 !important;
  --team-text: #EF0107 !important;
}

.tottenham-btn, .tottenham-color {
  --team-bg: #132257 !important;
  --team-text: #132257 !important;
}

.btn.active {
  background-color: var(--team-bg) !important;
  color: var(--team-text) !important;
}

.btn-light {
  background-color: #d6d6d6 !important; /* 밝은 회색 */
  color: #333 !important; /* 어두운 회색 */
}

.btn {
  transition: background-color 0.3s, color 0.3s;
}
</style>
