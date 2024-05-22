<template>
  <div class="notice-boards">
    <div class="left-panel">
      <article class="notice">
        <BoardVue :teamName="teamName"/>
      </article>
    </div>
    <div class="right-panel">
      <article class="notice">
        <YoutubeVideo />
      </article>
      <article class="notice">
        <MatchMainBoard />
      </article>
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
.notice-boards {
  display: flex;
  width: 100%;
  height: 100vh; /* 전체 높이 설정 */
}

.left-panel {
  flex: 1;
  margin-right: 10px; /* 오른쪽 여백 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
  display: flex;
  align-items: center;
  justify-content: center;
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
</style>
