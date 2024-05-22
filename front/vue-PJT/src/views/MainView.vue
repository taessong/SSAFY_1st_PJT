<template>
  <div class="main-container">
    <div class="notice-boards">
      <div class="left-panel">
        <article class="notice">
          <BoardVue :teamName="teamName" />
        </article>
      </div>
      <div class="right-panel">
        <article class="notice">
          <MatchMainBoard />
        </article>
        <article class="video">
          <YoutubeVideo />
        </article>
      </div>
    </div>
  </div>
</template>

<script setup>
import BoardVue from "../components/main/Board.vue";
import MatchMainBoard from "@/components/main/MatchMainBoard.vue";
import YoutubeVideo from "@/components/youtube/YoutubeVideo.vue";

const teamName = sessionStorage.getItem("favoriteTeam");
</script>

<style scoped>
html, body {
  margin: 0;
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  justify-content: center;
}

.main-container {
  width: 80%;
  height: 80%;
  background-color: #f9f9f9; /* 배경색 설정 */
  padding: 20px; /* 내부 여백 설정 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
  margin: auto; /* 중앙에 배치 */
}

.notice-boards {
  display: flex;
  width: 100%;
  height: 100%;
}

.left-panel {
  flex: 1;
  margin-right: 10px; /* 오른쪽 여백 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
  display: flex;
  flex-direction: column;
}

.right-panel {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px; /* 요소 간격 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
}

.notice {
  flex: 1;
  margin: 0 10px; /* 양쪽 여백 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
}

.video {
  flex: 1;
  margin: 0 10px; /* 양쪽 여백 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
}
</style>
