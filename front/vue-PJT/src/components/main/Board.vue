<template>
  <div class="container py-4">
    <div class="club-header d-flex justify-content-between align-items-end mb-3">
      <h2 :class="computedFavoriteTeamColorClass" v-if="showChat">수다 게시판</h2>
      <h2 :class="computedFavoriteTeamColorClass" v-if="showRecruit">모집 게시판</h2>
      <button @click="gotoClubBoard" :class="['btn', computedFavoriteTeamButtonClass, 'btn-sm']">+</button>
    </div>
    <div class="club-board border rounded p-3">
      <div class="board-header mb-3 btn-group">
        <button
          class="btn"
          @click="showChatBoard"
          :class="[showChat ? 'tab-active' : 'btn-inactive', computedFavoriteTeamButtonClass]"
        >
          수다
        </button>
        <button
          class="btn"
          @click="showRecruitBoard"
          :class="[showRecruit ? 'tab-active' : 'btn-inactive', computedFavoriteTeamButtonClass]"
        >
          모집
        </button>
      </div>
      <div class="board-content-wrapper" v-if="showChat">
        <div class="board-content table-responsive">
          <table class="table mb-0 custom-table">
            <thead>
              <tr>
                <th scope="col">분류</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in store.chatItems" :key="index">
                <td>[수다]</td>
                <td class="click" @click="selectPostAndNavigate(item)">{{ item.title }}</td>
                <td>{{ item.authorName }}</td>
                <td>{{ item.regDate }}</td>
                <td>{{ item.viewCnt }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="board-content-wrapper" v-else-if="showRecruit">
        <div class="board-content table-responsive">
          <table class="table mb-0 custom-table">
            <thead>
              <tr>
                <th scope="col">분류</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일</th>
                <th scope="col">조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in recruitStore.recruitItems" :key="index">
                <td>[모집]</td>
                <td class="click" @click="goToDetail(item.recruitmentId)">{{ item.content }}</td>
                <td>{{ item.authorName }}</td>
                <td>{{ item.regDate }}</td>
                <td>{{ item.viewCnt }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import { useClubStore } from "@/stores/club";
import { useRecruitStore } from "@/stores/recruit";
import { useRouter } from "vue-router";
import { getTeamColorClass } from "@/utils/teamColors";
axios.defaults.withCredentials = true;

const store = useClubStore();
const recruitStore = useRecruitStore();
const router = useRouter();

const showChat = ref(true);
const showRecruit = ref(false);

const favoriteTeam = ref(sessionStorage.getItem("favoriteTeam"));
const computedFavoriteTeamColorClass = computed(() => getTeamColorClass(favoriteTeam.value));
const computedFavoriteTeamButtonClass = computed(() => getTeamColorClass(favoriteTeam.value, 'btn'));

// Debugging: Log the computed classes
console.log("Favorite Team:", favoriteTeam.value);
console.log("Computed Color Class:", computedFavoriteTeamColorClass.value);
console.log("Computed Button Class:", computedFavoriteTeamButtonClass.value);

const gotoClubBoard = () => {
  router.push({ name: 'clubBoard' });
};

const goToDetail = (id) => {
  router.push({ name: "recruitDetail", params: { id } });
};

const showChatBoard = () => {
  if (!showChat.value) {
    showChat.value = true;
    showRecruit.value = false;
  }
};

const showRecruitBoard = () => {
  if (!showRecruit.value) {
    showRecruit.value = true;
    showChat.value = false;
  }
};

const selectPostAndNavigate = (item) => {
  router.push({ name: "clubDetail", params: { id: item.postId } });
};

onMounted(() => {
  store.fetchChatDataSummary();
  recruitStore.fetchRecruitDataSummary();
});
</script>
<style scoped>
* {
  font-weight: bold;
}

.click {
  cursor: pointer;
}

.btn-inactive {
  background-color: #e2e1e1 !important; /* 밝은 회색 배경색 */
  color: #000 !important; /* 어두운 회색 텍스트 색상 */
}

.btn-group > .btn {
  border-radius: 0;
  border: 1px solid #6c757d !important;
}

.btn-group > .btn:first-child {
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}

.btn-group > .btn:last-child {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}

.table-hover tbody tr:hover {
  background-color: #f1f1f1 !important;
}

.custom-table tbody tr {
  border-top: none !important;
}

.custom-table th,
.custom-table td {
  text-align: center !important; /* 중앙 정렬 */
  vertical-align: middle !important; /* 세로 중앙 정렬 */
  border-top: none !important; /* 행 사이의 줄 제거 */
  color: var(--team-bg) !important; /* 팀 색상에 따른 동적 텍스트 색상 */
}

/* Define color variables for different teams */
.mancity-btn, .mancity-color {
  --team-bg: #6CABDD !important;
  --team-text: #fff !important;
}

.manutd-btn, .manutd-color {
  --team-bg: #DA291C !important;
  --team-text: #fff !important;
}

.liverpool-btn, .liverpool-color {
  --team-bg: #C8102E !important;
  --team-text: #fff !important;
}

.chelsea-btn, .chelsea-color {
  --team-bg: #034694 !important;
  --team-text: #fff !important;
}

.arsenal-btn, .arsenal-color {
  --team-bg: #EF0107 !important;
  --team-text: #fff !important;
}

.tottenham-btn, .tottenham-color {
  --team-bg: #132257 !important;
  --team-text: #fff !important;
}

/* General button styles */
.btn.active {
  background-color: var(--team-bg) !important;
  color: var(--team-text) !important;
}

.btn {
  transition: background-color 0.3s, color 0.3s;
}

/* Active tab style */
.tab-active {
  background-color: var(--team-bg) !important;
  color: var(--team-text) !important;
}
</style>
