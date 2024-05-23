import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_MATCH_API = `http://localhost:8080/futsal/match`;

export const useMatchStore = defineStore('match', () => {
  const matchList = ref([]);
  const match = ref({});
  const stadiums = ref([]);

  const stadiumData = [
    {
      id: 1,
      name: '유성풋살구장',
      address: '대전 유성구 유성대로713번길 83'
    },
    {
      id: 2,
      name: '대전가장풋살존',
      address: '대전 서구 괴정로198번길 43 21-3'
    },
    {
      id: 3,
      name: '남선공원풋살장',
      address: '대전 서구 남선로 66'
    },
    {
      id: 4,
      name: '가오풋살장',
      address: '대전 동구 가오동 7-9'
    },
  ]

  stadiumData.forEach(stadium => {
    stadiums.value.push(stadium);
  });

  const createMatch = (match) => {
    axios.post(REST_MATCH_API, match.value)
      .then(() => {
        router.push({ name: 'matchboard' });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getBoardList = () => {
    axios.get(REST_MATCH_API)
      .then((response) => {
        matchList.value = response.data;
        console.log(matchList.value[0].matchId);
      });
  };

  const getBoard = (id) => {
    return axios.get(`${REST_MATCH_API}/${id}`)
      .then((response) => {
        match.value = response.data;
      });
  };

  const updateMatch = (id, updatedMatch) => {
    axios.put(`${REST_MATCH_API}/${id}`, updatedMatch)
      .then(() => {
        console.log("어 수정됐어~");
        router.push({ name: 'matchdetail' });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return {
    createMatch,
    matchList,
    getBoardList,
    match,
    getBoard,
    updateMatch,
    stadiums
  };
});
