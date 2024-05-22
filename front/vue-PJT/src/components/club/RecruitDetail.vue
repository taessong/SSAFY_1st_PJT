<template>
  <div>
    <h2>상세페이지</h2>
    <button @click="goBack" class="gobackButton">◀️ 뒤로가기</button>
    <table>
      <thead>
        <tr>
          <th>마감여부</th>
          <th>내용</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>최대인원</th>
        </tr>
      </thead>
      <tbody> 
        <tr>
          <td>{{ recruitItem.full }}</td>
          <td>{{ recruitItem.content }}</td>
          <td>{{ recruitItem.authorName }}</td>
          <td>{{ recruitItem.regDate }}</td>
          <td><span>{{ futsalMember.length + 1 }} / </span> {{ recruitItem.maxMembers }}</td>
        </tr>
      </tbody>
    </table>
    <h2>신청하기를 클릭하면 작성자가 아닌 유저가 팀원이 된다!!</h2>
    <button @click="applyTeam">신청하기</button>
    <div v-if="isWriter">
      <button @click="gotoUpdate">수정</button>
      <button @click="gotoDelete">삭제</button>
    </div>

    <div v-for="member in futsalMember" :key="member.id">
      {{ member.memberName }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useRecruitStore } from "@/stores/recruit";
import axios from "@/api/axios";

const route = useRoute();
const router = useRouter();
const isWriter = ref(false);
const store = useRecruitStore();
const recruitItem = computed(() => store.recruitItem);
const futsalMember = computed(() => store.futsalMember);

const goBack = () => {
  router.back();
};

const gotoUpdate = () => {
  const recruitId = route.params.id;
  router.push({name: 'recruitUpdate', params: {id : recruitId}})
}

const gotoDelete = async () => {
  const recruitId = route.params.id;
  await store.deleteRecruitBoard(recruitId);
  //팀 삭제까지 동시에 수행하기
  await axios.delete('/futsal/board/team');
  console.log("삭제완료");
  router.push({name: 'clubBoard'});
}

const applyTeam = async () => {
  if(sessionStorage.getItem("nickName") === recruitItem.value.authorName){
    alert("작성자는 팀 리더이기 때문에 신청을 할 수 없습니다.");
  }
  else{
    try {
      await store.isTeamMember();
      router.go(0);
    } catch (error) {
      console.log("에러!!!", error)
    }
  }
}

onMounted(async() => {
  const recruitmentId = route.params.id;
  // await store.fetchRcruitOneData(recruitmentId);
  await store.fetchTeamData(recruitmentId);
  if(sessionStorage.getItem("nickName") === recruitItem.value.authorName){
    isWriter.value = true;
  }

  if(futsalMember.value.length + 1 === 6){
    recruitItem.value.full = true;
  }
});
</script>

<style scoped>
.gobackButton {
  border: none;
  padding: 6px;
  margin-top: 25px;
  border-radius: 8px;
  background-color: darkcyan;
  color: white;
  font-weight: 800;
  cursor: pointer;
}
</style>