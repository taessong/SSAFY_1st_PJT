import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/api/axios";

export const useClubStore = defineStore("club", () => {
  const chatItems = ref([]);
  const comments = ref([]);
  const chatItem = ref({});

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");

    return `${year}/${month}/${day} ${hours}:${minutes}`;
  };

  const fetchChatDataSummary = async () => {
    try {
      const response = await axios.get(`/community/board`);
      chatItems.value = response.data.slice(0, 7).map((item) => {
        return {
          ...item,
          regDate: formatDate(item.regDate),
        };
      });
      console.log(response.data);
    } catch (error) {
      console.log("Failed to fetch data:", error);
    }
  };

  const fetchChatData = async () => {
    try {
      const response = await axios.get(`/community/board`);
      chatItems.value = response.data;
      console.log(response.data);
    } catch (error) {
      console.log("Failed to fetch data:", error);
    }
  };

  const fetchComments = async (postId) => {
    try {
        const response = await axios.get(`/community/board/${postId}/comment`);
        console.log(response.data);
        comments.value = response.data;
    } catch (error) {
        console.log("failed to fetch comments", error)
    }
  }

  const fetchOneChatData = async (postId) => {
    try {
      const response = await axios.get(`/community/board/${postId}`)
      chatItem.value = response.data;
      chatItem.value.regDate = formatDate(chatItem.value.regDate);
    } catch (error) {
      console.log("failed to fetch oneChatData", error)
    }
  }

  const recommendComment = async(postId, commentId) => {
    try {
      await axios.put(`/community/board/${postId}/comment/${commentId}/recommend`);
      await fetchComments(postId);
    } catch (error) {
      console.log("에러다!!")
    }
  }



  return {
    chatItems,
    fetchChatData,
    fetchChatDataSummary,
    formatDate,
    fetchComments,
    comments,
    chatItem,
    fetchOneChatData,
    recommendComment,
  };
});
