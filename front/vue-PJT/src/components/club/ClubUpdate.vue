<template>
  <div v-if="chatItemReady">
    업데이트페이지에요~
    <form @submit.prevent="updateBoard">
      <div>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" v-model="form.title" />
      </div>
      <div>
        <label for="content">내용</label>
        <input type="text" id="content" v-model="form.content" />
      </div>
      <button type="submit">제출해!!!!!!!!!!!!!</button>
    </form>
  </div>
</template>
  
  <script setup>
import { useClubStore } from "@/stores/club";
import axios from "@/api/axios";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const storeDetail = useClubStore();

const form = ref({
  title: "",
  content: "",
});

const chatItemReady = ref(false);

onMounted(async () => {
  const postId = route.params.id;
  await storeDetail.fetchOneChatData(postId);
  const chatItem = storeDetail.chatItem;

  if (chatItem) {
    // chatItem의 데이터를 form에 복사
    form.value.title = chatItem.title;
    form.value.content = chatItem.content;
    chatItemReady.value = true;
  }
});

const updateBoard = async () => {
  try {
    const postId = route.params.id;
    console.log(form.value);
    await axios.put(`/community/board/${postId}`, form.value);
    console.log("수정 됐어요~");
    router.back();
  } catch (error) {
    console.log("수정 실패...", error);
  }
};
</script>
  
<style scoped>
</style>
  