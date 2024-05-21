import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/api/axios";
import { useRouter } from "vue-router";


export const useRecruitStore = defineStore("recruit", () => {
  const recruitItems = ref([]);

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");

    return `${year}/${month}/${day} ${hours}:${minutes}`;
  };

    //모집게시판에있는 글을 들고오는것. (팀이름일치해야돼!!)
    const fetchRecruitData = async () => {
        try {
          const response = await axios.get(`/futsal/board`);
          recruitItems.value = response.data;
        } catch (error) {
          console.log("Failed to fetch RecruitData", error);
        }
      };
    

  //모집게시판에있는 글을 요약하여 들고오는것. (팀이름일치해야돼!!)
  const fetchRecruitDataSummary = async () => {
    try {
      const response = await axios.get(`/futsal/board`);
      recruitItems.value = response.data.slice(0, 7).map((item) => {
        return {
          ...item,
          regDate: formatDate(item.regDate),
        };
      });
    } catch (error) {
      console.log("Failed to fetch RecruitData", error);
    }
  };

  const recruitItem = ref({});

  const fetchRcruitOneData = async (id) => {
    try {
      const response = await axios.get(
        `/futsal/board/${id}`
      );
      recruitItem.value = response.data;
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  }

  const updateRecruitBoard = async (id, formData) => {
    try {
      const response = await axios.put(`/futsal/board/${id}`, formData);
      console.log('됐다!', response.data);
      alert("글이 수정되었습니다.");
    } catch (error) {
      console.error('안돼~~', error);
    }
  };

  const deleteRecruitBoard = async (id) =>{
    try {
      await axios.delete(`/futsal/board/${id}`);
      console.log("삭제돼썽요");
      alert("삭제 되었습니다.");
    } catch (error) {
      console.log("어라 삭제가..안되넹")
    }
  }

  return {
    recruitItems,
    formatDate,
    fetchRecruitData,
    fetchRecruitDataSummary,
    recruitItem,
    fetchRcruitOneData,
    updateRecruitBoard,
    deleteRecruitBoard,
  };
});
