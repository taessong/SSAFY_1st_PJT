<template>
  <div class="container py-4" v-if="videoURL" :class="favoriteTeamColorClassContainer">
    <h2 :class="favoriteTeamColorClass">최근 경기 하이라이트</h2>
    <iframe
      width="560"
      height="315"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen
    ></iframe>
  </div>
</template>
<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { computed, ref } from 'vue';
import { getTeamColorClass } from '@/utils/teamColors';

const store = useYoutubeStore();
const teamName = sessionStorage.getItem('favoriteTeam');

const videoURL = computed(() => {
  const videoId = store.getVideoIdByTeam(teamName);
  return videoId ? `https://www.youtube.com/embed/${videoId}` : '';
});

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(teamName));
const favoriteTeamColorClassContainer = computed(() => {
  return `${getTeamColorClass(teamName)}-container`;
});

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamColorClassContainer:', favoriteTeamColorClassContainer.value);
</script>
<style scoped>
.mancity-container {
  --team-bg: #6CABDD;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

.manutd-container {
  --team-bg: #DA291C;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

.liverpool-container {
  --team-bg: #C8102E;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

.chelsea-container {
  --team-bg: #034694;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

.arsenal-container {
  --team-bg: #EF0107;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

.tottenham-container {
  --team-bg: #132257;
  --team-text: #fff;
  background-color: var(--team-bg);
  color: var(--team-text);
  padding: 15px;
  border-radius: 8px;
}

h2 {
  font-weight: bolder;
  margin-bottom: 20px;
}
</style>
