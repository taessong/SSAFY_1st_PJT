<template>
  <div class="main-container">
    <div class="notice-boards">
      <div class="left-panel">
        <article class="notice">
          <BoardVue :teamName="teamName" />
        </article>
        <article class="video">
          <YoutubeVideo />
        </article>
      </div>
      <div class="right-panel">
        <article class="notice">
          <MatchMainBoard />
        </article>
        <article>
          <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item" :class="{ active: index === 0 }" v-for="(image, index) in teamImages" :key="index">
                <img :src="image" class="d-block w-100" alt="...">
              </div>
            </div>
          </div>
        </article>
      </div>
    </div>
  </div>
</template>

<script setup>
import BoardVue from "../components/main/Board.vue";
import MatchMainBoard from "@/components/main/MatchMainBoard.vue";
import YoutubeVideo from "@/components/youtube/YoutubeVideo.vue";
import { computed } from 'vue';

const teamName = sessionStorage.getItem("favoriteTeam");

const teamBackgrounds = {
  '토트넘': ['src/asset/teams/토트넘1.jpg', 'src/asset/teams/토트넘2.jpg', 'src/asset/teams/토트넘3.jpg'],
  '맨시티': ['src/asset/teams/맨시티1.jpg', 'src/asset/teams/맨시티2.jpg', 'src/asset/teams/맨시티3.jpg'],
  '맨유': ['src/asset/teams/맨유1.jpg', 'src/asset/teams/맨유2.jpg', 'src/asset/teams/맨유3.jpg'],
  '아스널': ['src/asset/teams/아스날1.jpg', 'src/asset/teams/아스날2.jpg', 'src/asset/teams/아스날3.jpg'],
  '첼시': ['src/asset/teams/첼시1.jpg', 'src/asset/teams/첼시2.jpg', 'src/asset/teams/첼시3.jpg'],
  '리버풀': ['src/asset/teams/리버풀1.jpg', 'src/asset/teams/리버풀2.jpg', 'src/asset/teams/리버풀3.jpg']
};

const teamImages = computed(() => teamBackgrounds[teamName] || []); 

</script>

<style scoped>
img {
  width: 550px;
  height: auto;
}

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
  padding: 30px; /* 내부 여백 설정 */
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
