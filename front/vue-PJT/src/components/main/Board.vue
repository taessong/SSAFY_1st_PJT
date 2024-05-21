<template>
    <div class="container">
      <div class="club-header">
        <h2 v-if="showChat">수다 게시판</h2>
        <h2 v-if="showRecruit">모집 게시판</h2>
        <button @click="gotoClubBoard" class="button">+</button>
      </div>
      <div class="club-board">
        <div class="board-header">
          <button
            class="filter-btn"
            @click="showChatBoard"
            :class="{ active: showChat }"
          >
            수다
          </button>
          <button
            class="filter-option"
            @click="showRecruitBoard"
            :class="{ active: showRecruit }"
          >
            모집
          </button>
        </div>
        <div class="board-content-wrapper" v-if="showChat">
          <div class="board-content">
            <table>
              <thead>
                <tr>
                  <th>분류</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th>조회수</th>
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
        <div v-else-if="showRecruit">
          <div class="board-content">
            <table>
              <thead>
                <tr>
                  <th>분류</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th>조회수</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(item, index) in recruitStore.recruitItems"
                  :key="index"
                >
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
  .container {
    padding: 20px;
  }
  
  h2 {
    margin-bottom: 20px;
  }

  .click {
    cursor: pointer;
  }
  
  .club-header {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
  }
  
  .button {
    width: 20px;
    height: 20px;
    border: none;
    background-color: gainsboro;
    border-radius: 4px;
    cursor: pointer;
    margin-right: 5px;
    font-weight: 900;
    margin-bottom: 3px;
  }
  
  .button:hover {
    background-color: #ccc;
  }
  
  .club-board {
    width: 100%;
    border: 1px solid black;
    border-radius: 6px;
    padding: 10px;
    box-sizing: border-box;
  }
  
  .board-header {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 10px;
  }
  
  .filter-btn,
  .filter-option {
    padding: 5px 10px;
    border: none;
    background-color: #eee;
    border-radius: 4px;
    cursor: pointer;
    margin-right: 5px;
  }
  
  .filter-btn.active,
  .filter-option.active {
    background-color: #ccc;
    box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.3);
  }
  
  .board-content-wrapper {
    overflow: hidden;
    border-radius: 6px;
  }
  
  .board-content {
    border-collapse: collapse;
    width: 100%;
  }
  
  .board-content th,
  .board-content td {
    padding: 10px;
    text-align: center;
  }
  
  .board-content th {
    background-color: #f9f9f9;
  }
  
  .board-content tr {
    background-color: #fff;
    transition: background-color 0.3s;
  }
  
  .board-content tr:hover {
    background-color: #f1f1f1;
  }
  
  .board-content-wrapper table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
  }
  
  .board-content-wrapper th:first-child,
  .board-content-wrapper td:first-child {
    border-left: none;
  }
  
  .board-content-wrapper th:last-child,
  .board-content-wrapper td:last-child {
    border-right: none;
  }
  
  .board-content-wrapper th {
    border-top: none;
  }
  
  .board-content-wrapper td {
    border-bottom: none;
  }
  
  .board-content-wrapper th:first-child {
    border-top-left-radius: 6px;
  }
  
  .board-content-wrapper th:last-child {
    border-top-right-radius: 6px;
  }
  
  .board-content-wrapper tr:last-child td:first-child {
    border-bottom-left-radius: 6px;
  }
  
  .board-content-wrapper tr:last-child td:last-child {
    border-bottom-right-radius: 6px;
  }
  </style>