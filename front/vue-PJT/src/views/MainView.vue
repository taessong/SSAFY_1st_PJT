<template>
    <h2>커뮤니티</h2>
    <h2>매칭 게시판 <button @click="gotoMatchBoard()">+</button></h2>
    <h3>왼쪽은 커뮤니티, 오른쪽은 매칭 게시판으로</h3>
    <BoardVue/>
</template> 

<script>
import BoardVue from "../components/main/Board.vue";

import { useRouter } from "vue-router";


export default {
  setup() {
    const router = useRouter();

    const gotoMatchBoard = () => {
      router.push({ name: 'matchboard' });
    };

    return {
      gotoMatchBoard
    };
  }
};



</script>

<style scoped>

</style>