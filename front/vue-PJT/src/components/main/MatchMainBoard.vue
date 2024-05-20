<template>
  <div class="container">
    <div class="match-header">
      <h2>매칭 게시판</h2>
      <button @click="gotoMatchBoard" class="button">+</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const gotoMatchBoard = () => {
  router.push({ name: "matchboard" });
};
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