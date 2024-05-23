<template>
  <div class="container">
    <div class="match-header">
      <h2>매칭 게시판</h2>
      <button @click="gotoMatchBoard" class="button">+</button>
    </div>
    <div>
      <div>
        <table>
          <thead>
            <tr>
              <th>분류</th>
              <th>내용</th>
              <th>작성자</th>
              <th>경기일자</th>
              <th>경기시간</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="match in store.matchList" :key="match.id">
              <td>{{ match.teamBId ? '[마감]' : '[모집]' }}</td>
              <td>{{ match.content }}</td>
              <td>{{ match.authorName }}</td>
              <td>{{ match.matchDate }}</td>
              <td>{{ match.matchTime }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useMatchStore } from "@/stores/match";
import { onMounted } from "vue";

const router = useRouter();
const store = useMatchStore();

const gotoMatchBoard = () => {
  router.push({ name: "matchboard" });
};

const getBoardList = () => {
  store.getBoardList();
  for(let i=0; i<store.matchList.length; i++){
    console.log(store.matchList[i]);
  }
}

onMounted(() => {
  getBoardList();
});
</script>

<style scoped> 
.container {
  padding: 15px;
  width: 100%;
}

.match-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.button {
  width: 20px;
  height: 20px;
  border: none;
  background-color: gainsboro;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 5px;
  font-weight: 900;
  margin-bottom: 3px;
}
</style>