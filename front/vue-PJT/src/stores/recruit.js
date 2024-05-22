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
      recruitItems.value = response.data.map((item) => {
        return {
          ...item,
          regDate: formatDate(item.regDate),
        };
      });;
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
      const response = await axios.get(`/futsal/board/${id}`);
      recruitItem.value = response.data;
      //console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  const updateRecruitBoard = async (id, formData) => {
    try {
      const response = await axios.put(`/futsal/board/${id}`, formData);
      console.log("됐다!", response.data);
      alert("글이 수정되었습니다.");
    } catch (error) {
      console.error("안돼~~", error);
    }
  };

  const deleteRecruitBoard = async (id) => {
    try {
      await axios.delete(`/futsal/board/${id}`);
      console.log("삭제돼썽요");
      alert("삭제 되었습니다.");
    } catch (error) {
      console.log("어라 삭제가..안되넹");
    }
  };


  const futsalTeamId = ref(0);
  const futsalMember = ref([]);

  const fetchTeamData = async (id) => {
    const response = await axios.get("/futsal/board/team");
    // console.log(response.data);
    await fetchRcruitOneData(id);
    for (let i = 0; i < response.data.length; i++) {
      //리더랑 작성자랑 이름이 똑같아야함!
      if (response.data[i].leaderName === recruitItem.value.authorName) {
        futsalTeamId.value = response.data[i].futsalTeamId;
        console.log(futsalTeamId.value);
        const teamData = await axios.get(`futsal/board/team/${futsalTeamId.value}`);
        futsalMember.value = teamData.data;
        // console.log(futsalMember.value);
        return;
      }
    }
  };

  const isTeamMember = async () => {
    try {

      if (futsalMember.value.length >= 5) {
        alert("꽉 차버렸쥐롱~~~~~~~~~");
        return;
      }

      for (let i = 0; i < futsalMember.value.length; i++) {
        //만약에 자기가 이미 신청한 상태라면??
        if (
          futsalMember.value[i].memberName === sessionStorage.getItem("nickName")
        ) {
          alert("이미 신청하셨잖아요?");

          return;
        }
      }

      const formData = {
        futsalTeamId: futsalTeamId.value,
        memberName: sessionStorage.getItem("nickName"),
      };

      try {
        await axios.post(`futsal/board/team/${futsalTeamId.value}`, formData, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        alert("팀 등록이 완료되었습니다.");
      } catch (error) {
        console.log("어 씌 등록안됐따.");
      }

      //applyTeamMember(futsalTeamId);
    } catch (error) {
      console.log("에러에용에러~~", error);
    }
  };

  //const applyTeamMember = async (futsalTeamId) => {

  //}

  return {
    recruitItems,
    formatDate,
    fetchRecruitData,
    fetchRecruitDataSummary,
    recruitItem,
    fetchRcruitOneData,
    updateRecruitBoard,
    deleteRecruitBoard,
    isTeamMember,
    fetchTeamData,
    futsalMember,
    futsalTeamId,
  };
});