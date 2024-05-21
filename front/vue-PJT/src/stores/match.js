import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_MATCH_API = `http://localhost:8080/futsal/match`;

export const useMatchStore = defineStore('match', () => {

  // 매칭글 등록
  const createMatch = function (match) {
    axios.post(REST_MATCH_API, match)
    .then(() => {
      router.push({name: 'matchboard'})
    })
    .catch((error) => {
      console.log(error)
    })
  }

  // 전체 매칭글
  const matchList = ref([])
  const getBoardList = function () {
    axios.get(REST_MATCH_API)
    .then((response) => {
      matchList.value = response.data
    })
  }

  // 상세 매칭글
  const match = ref({})
  const getBoard = function (id) {
    axios.get(`${REST_MATCH_API}/${id}`)
    .then((response) => {
      match.value = response.data
    })
  }

  // 매칭글 수정
  const updateMatch = function (id) {
    axios.put(`${REST_MATCH_API}/${id}`, match.value)
    .then(() => {
      router.push({name: 'matchboard'})
    })
    .catch((error) => {
      console.log(error)
    })
  }

  return {
    createMatch,
    matchList,
    getBoardList,
    match,
    getBoard,
    updateMatch,
  }
})