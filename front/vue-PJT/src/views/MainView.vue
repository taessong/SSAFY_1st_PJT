<template>
  <div>
    <div class="notice-boards">
      <article class="notice">
        <BoardVue :teamName="teamName"/>
      </article>
      <article class="notice">
        <MatchMainBoard />
      </article>
    </div>
  </div>
</template> 

<script setup>
import BoardVue from "../components/main/Board.vue";
import MatchMainBoard from "@/components/main/MatchMainBoard.vue";

const teamName = sessionStorage.getItem("favoriteTeam");
</script>

<style scoped>
.notice-boards {
  display: flex;
  flex-direction: row;
  width: 100%;
}

.notice {
  flex: 1;
  margin: 0 10px; /* 양쪽 여백 설정 */
  box-sizing: border-box; /* padding과 border를 너비에 포함시킴 */
}
</style>
