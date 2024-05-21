import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/api/axios";


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

  return {
    recruitItems,
    formatDate,
    fetchRecruitData,
    fetchRecruitDataSummary
  };
});
