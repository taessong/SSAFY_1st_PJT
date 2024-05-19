<template>
  <div class="container">
    <h2>매칭 게시판 <button @click="gotoMatchBoard">+</button></h2>
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
</style>