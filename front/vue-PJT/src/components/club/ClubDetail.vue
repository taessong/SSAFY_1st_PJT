<template>
  <div class="detailBoard">
    <div class="detailPage">
      <button @click="goBack" class="gobackButton">◀️ 뒤로가기</button>
      <div class="detailTitle">
        <p class="title">제목 : {{ chatItem.title }}</p>
        <div class="authornRegDate">
          <p class="author">작성자 : {{ chatItem.authorName }}</p>
          <p class="regDate">작성 일자 : {{ chatItem.regDate }}</p>
        </div>
      </div>
      <div class="content">
        {{ chatItem.content }}
      </div>
      <p class="viewCnt">조회수: {{ chatItem.viewCnt }}</p>
    </div>
    <div class="comments">
      <h1>댓글 {{ comments.length }}개</h1>
      <div v-for="comment in comments" :key="comment.commentId" class="comment">
        <div class="authornContent">
          <p>{{ comment.authorName }}</p>
          <p>{{ comment.content }}</p>
        </div>
          <button class="recommend" @click="recommend">👍 {{ comment.recommend }}</button>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
import { useClubStore } from "@/stores/club";
import { onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";

const props = defineProps({
  id: String,
});

const store = useClubStore();
const storeDetail = useClubStore();
const route = useRoute();
const router = useRouter();

const goBack = () => {
  router.back();
};

const recommend = () => {
  console.log("눌렀다!")
}

const comments = computed(() => store.comments);
const chatItem = computed(() => storeDetail.chatItem);

onMounted(() => {
  const postId = route.params.id;
  storeDetail.fetchOneChatData(postId);
  store.fetchComments(postId);
});
</script>
  
  <style scoped>
.gobackButton {
  border: none;
  padding: 6px;
  margin-bottom: 15px;
}

.detailPage {
  padding: 30px;
}

.detailTitle {
  display: flex;
  justify-content: space-between;
}

.title {
  margin-left: 5px;
  font-weight: 800;
}
.authornRegDate {
  display: flex;
  font-weight: 800;
}

.regDate {
  margin-left: 25px;
  margin-right: 10px;
}

.content {
  border: 1px solid black;
  border-radius: 8px;
  text-align: start;
  height: 360px;
  padding: 6px;
}

.viewCnt {
  text-align: end;
  font-weight: 800;
  margin-right: 10px;
}

.comments {
  padding: 30px;
}

.detailBoard {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.comment {
  border: 1px solid black;
  border-radius: 8px;
  margin-top: 10px;
  padding: 3px;
  display: flex;
  justify-content: space-between;
}

.comment p {
  margin-left: 15px;
}

.authornContent {
  display: flex;
}

.recommend {
  margin-right: 10px;
  border: none;
  background-color: white;
}
</style>
  