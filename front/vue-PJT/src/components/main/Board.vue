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
          :class="[showChat ? computedFavoriteTeamButtonClass : 'btn-inactive']"
        >
          수다
        </button>
        <button
          class="btn"
          @click="showRecruitBoard"
          :class="[showRecruit ? computedFavoriteTeamButtonClass : 'btn-inactive']"
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

//store를 사용해서 불러오기
const store = useClubStore();
const recruitStore = useRecruitStore();

//router 사용
const router = useRouter();

// 수다, 모집게시판 분류
const showChat = ref(true);
const showRecruit = ref(false);

// 좋아하는 팀 색상 클래스 설정
const favoriteTeam = ref(sessionStorage.getItem("favoriteTeam"));
const computedFavoriteTeamColorClass = computed(() => getTeamColorClass(favoriteTeam.value));
const computedFavoriteTeamButtonClass = computed(() => `${getTeamColorClass(favoriteTeam.value)}-btn`);

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
.click {
  cursor: pointer;
}

.btn-inactive {
  background-color: #6c757d !important;
  color: white !important;
}

.btn-group > .btn {
  border-radius: 0;
  border: 1px solid #6c757d;
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
  background-color: #f1f1f1;
}

.custom-table tbody tr {
  border-top: none;
}

.custom-table th,
.custom-table td {
  text-align: center; /* 중앙 정렬 */
  vertical-align: middle; /* 세로 중앙 정렬 */
  border-top: none; /* 행 사이의 줄 제거 */
}
</style>
