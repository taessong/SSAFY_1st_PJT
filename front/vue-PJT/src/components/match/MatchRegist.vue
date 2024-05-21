<template>
    <div>
      <h2>매치 등록 페이지</h2>
      <fieldset>
        <legend>매치 정보 입력</legend>
  
        <div>
          <label for="content">매치 내용 : </label>
          <textarea id="content" v-model="match.content"></textarea>
        </div>
        <div>
          <label for="matchDate">경기 날짜 선택 : </label>
          <input type="date" id="matchDate" v-model="match.matchDate">
        </div>
        <div>
          <label for="matchTime">경기 시간 입력 : </label>
          <input type="time" id="matchTime" v-model="match.matchTime">
        </div>
        <div>
          <label for="stadium">경기장 선택 : </label>
          <select id="stadium" v-model="selectedStadium" @change="updateStadiumImage">
            <option v-for="stadium in stadiums" :key="stadium.stadium_id" :value="stadium.stadium_id">
              {{ stadium.stadium_name }}
            </option>
          </select>
          <div v-if="selectedStadium !== null">
            <img :src="stadiumImage" alt="Stadium Image">
          </div>
        </div>
        <div>
          <button @click="createMatch">등록하기</button>
        </div>
      </fieldset>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive, onMounted } from 'vue';
  import { useMatchStore } from '@/stores/match';
  
  const store = useMatchStore();
  
  const match = reactive({
      authorName: sessionStorage.getItem("nickName"),
      teamAName: sessionStorage.getItem("favoriteTeam"),
      content: '',
      matchDate: '',
      matchTime: '',
      stadiumId: ''
  });
  
  const selectedStadium = ref(null);
  const stadiums = ref([]);
  const stadiumImage = ref('');
  
  const fetchStadiums = async () => {
    // API를 통해 경기장 목록 가져오기
    // 예시로 하드코딩한 경기장 목록 사용
    stadiums.value = [
      { stadium_id: 1, stadium_name: '유성풋살구장', stadium_img: '@/asset/stadium/유성풋살구장/유성풋살구장(1).png' },
      { stadium_id: 2, stadium_name: '삼정풋살파크', stadium_img: '@/asset/stadium/삼정풋살파크/삼정풋살파크(1).png' },
      { stadium_id: 3, stadium_name: '위너스풋살파크', stadium_img: '@/asset/stadium/위너스풋살파크/위너스풋살파크(1).png' },
      { stadium_id: 4, stadium_name: '대전가장풋살존', stadium_img: '@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png' },
      { stadium_id: 5, stadium_name: '남선공원풋살장', stadium_img: '@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png' },
      { stadium_id: 6, stadium_name: '가오풋살장', stadium_img: '@/asset/stadium/가오풋살장/가오풋살장(1).png' }
    ];
    console.log(stadiums.value);
  };
  
  const updateStadiumImage = () => {
    // 선택한 경기장에 대한 이미지 업데이트
    if (selectedStadium.value !== null) {
      const stadium = stadiums.value.find(stadium => stadium.stadium_id === selectedStadium.value);
      stadiumImage.value = stadium ? stadium.stadium_img : '';
    }
  };
  
  const createMatch = () => {
    match.stadiumId = selectedStadium.value;
    store.createMatch(match);
  };
  
  onMounted(fetchStadiums);
  </script>
  
  <style scoped>
  </style>
  