<template>
  <div class="container py-4">
    <div class="club-header d-flex justify-content-between align-items-end mb-3">
      <h2 v-if="showChat">수다 게시판</h2>
      <h2 v-if="showRecruit">모집 게시판</h2>
      <button @click="gotoClubBoard" class="btn btn-secondary btn-sm">+</button>
    </div>
    <div class="club-board border rounded p-3">
      <div class="board-header mb-3">
        <button
          class="btn btn-outline-secondary me-2"
          @click="showChatBoard"
          :class="{ active: showChat }"
        >
          수다
        </button>
        <button
          class="btn btn-outline-secondary"
          @click="showRecruitBoard"
          :class="{ active: showRecruit }"
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
import { ref, onMounted } from "vue";
import axios from "axios";
import { useClubStore } from "@/stores/club";
import { useRecruitStore } from "@/stores/recruit";
import { useRouter } from "vue-router";
axios.defaults.withCredentials = true;

//store를 사용해서 불러오기
const store = useClubStore();
const recruitStore = useRecruitStore();

//router 사용
const router = useRouter();

// 수다, 모집게시판 분류
const showChat = ref(true);
const showRecruit = ref(false);

const gotoClubBoard = () => {
  router.push({ name: 'clubBoard' });
};

const goToDetail = (id) => {
  router.push({ name: "recruitDetail", params: { id } });
};

const showChatBoard = () => {
  if (showChat.value != true) {
    showChat.value = true;
    showRecruit.value = false;
    console.log("수다로 전환됐지롱~");
  }
};

const showRecruitBoard = () => {
  if (showRecruit.value != true) {
    showRecruit.value = true;
    showChat.value = false;
    console.log("모집으로 전환됐지롱~");
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

.btn.active {
  background-color: #ccc !important;
  box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.3);
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
