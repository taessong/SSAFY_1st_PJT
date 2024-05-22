import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useYoutubeStore = defineStore('youtube', () => {
  const videos = ref([]);
  const selectedVideo = ref(null);
  const teamVideos = ref({
    '토트넘': 'k8_T2-G4lQc',
    '리버풀': 'c2z17aaEYVQ',
    '아스널': 'hsmCN488rSQ',
    '맨시티': 'XoQ4wlWIHJk',
    '맨유': 'vOvhAq_vZPM',
    '첼시': 'H5FM_SFq5BA'
  });

  const youtubeSearch = function(keyword) {
    const URL = 'https://www.googleapis.com/youtube/v3/search';
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

    axios({
      url: URL,
      method: 'GET',
      params: {
        key: API_KEY,
        part: 'snippet',
        maxResults: 10,
        q: keyword,
        type: 'video',
      },
    })
    .then((response) => {
      videos.value = response.data.items;
      console.log(response.data);
    })
    .catch(() => {});
  };

  const clickVideo = function(video) {
    selectedVideo.value = video;
  };

  const getVideoIdByTeam = function(teamName) {
    return teamVideos.value[teamName] || '';
  };

  const updateVideoIdForTeam = function(teamName, videoId) {
    teamVideos.value[teamName] = videoId;
  };

  return { youtubeSearch, videos, selectedVideo, clickVideo, getVideoIdByTeam, updateVideoIdForTeam };
});
