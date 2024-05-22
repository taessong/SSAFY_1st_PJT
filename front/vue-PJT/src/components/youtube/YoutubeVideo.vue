<template>
    <div v-if="videoURL">
      <h2>최근 경기 하이라이트</h2>
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
  import { computed } from 'vue';
  
  const store = useYoutubeStore();
  const teamName = sessionStorage.getItem('favoriteTeam');
  
  const videoURL = computed(() => {
    const videoId = store.getVideoIdByTeam(teamName);
    return videoId ? `https://www.youtube.com/embed/${videoId}` : '';
  });
  </script>
  
  <style scoped></style>
  