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
/* 전체 화면 스타일 설정 */
html, body {
  margin: 0;
  height: 100%;
}

/* body 스타일 설정 */
body {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 메인 컨테이너 스타일 설정 */
.main-container {
  width: 80%;
  height: 80%;
  background-color: #f9f9f9;
  padding: 30px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  margin: auto;
}

/* 공지사항 보드 스타일 설정 */
.notice-boards {
  display: flex;
  width: 100%;
  height: 100%;
}

/* 좌측 패널 스타일 설정 */
.left-panel {
  flex: 1;
  margin-right: 10px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}

/* 우측 패널 스타일 설정 */
.right-panel {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
  box-sizing: border-box;
}

/* 공지사항 스타일 설정 */
.notice {
  flex: 1;
  margin: 0 10px;
  box-sizing: border-box;
}

/* 비디오 스타일 설정 */
.video {
  flex: 1;
  margin: 0 10px;
  box-sizing: border-box;
}

/* 캐러셀 아이템 스타일 설정 */
.carousel-inner {
  width: 90%;
  height: 400px; /* 고정된 높이 설정 */
  display: flex;
  align-content: center;
  margin-left: 30px;
}

/* 이미지 스타일 설정 */
.carousel-item img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지가 컨테이너를 완전히 덮도록 설정 */
}
</style>
