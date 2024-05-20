<template>
  <div class="detailBoard">
    <div class="detailPage">
      <div class="top-button">
        <button @click="goBack" class="gobackButton">◀️ 뒤로가기</button>
        <div v-if="isWriter" class="putNdelete">
          <button class="put">수정</button>
          <button @click="confirmDelete(chatItem.postId)" class="delete">
            삭제
          </button>
        </div>
      </div>
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
        <div class="recommendNput">
          <button class="recommend" @click="recommend(comment.commentId)">
            👍 {{ comment.recommend }}
          </button>
          <div v-if="isCommentWriter" class="commentPutNdelete">
            <button class="put">수정</button>
            <button class="delete" @click="confirmCommentDelete(comment.commentId)">삭제</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script setup>
import { useClubStore } from "@/stores/club";
import { onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";
import { ref } from "vue";
import axios from "axios";

const props = defineProps({
  id: String,
});

const store = useClubStore();
const storeDetail = useClubStore();
const route = useRoute();
const router = useRouter();
const isWriter = ref(false);
const isCommentWriter = ref(false);

const goBack = () => {
  router.back();
};

const recommend = (commentId) => {
  const postId = route.params.id;
  store.recommendComment(postId, commentId);
};

const confirmDelete = (postId) => {
  if (window.confirm("정말 삭제하시겠습니까?")) {
    deleteBoard(postId);
  }
};

const deleteBoard = async (postId) => {
  try {
    await axios.delete(`http://localhost:8080/community/board/${postId}`);
    router.back();
  } catch (error) {
    console.log("실패용", error);
  }
};

const confirmCommentDelete = (commentId) => {
  if( window.confirm("정말 댓글을 삭제하시겠습니까?")){
    commentDelete(commentId);
  }
}

const commentDelete = async (commentId) => {
  const postId = route.params.id;
  try{
    await axios.delete(`http://localhost:8080/community/board/${postId}/comment/${commentId}`);
  }catch(error){
    console.log("에러다!!", error);
  }
}

const comments = computed(() => store.comments);
const chatItem = computed(() => storeDetail.chatItem);

onMounted(async () => {
  const postId = route.params.id;
  await storeDetail.fetchOneChatData(postId);
  await store.fetchComments(postId);
  if (sessionStorage.getItem("nickName") === chatItem.value.authorName) {
    isWriter.value = true;
  }
  if (sessionStorage.getItem("nickName") === comments.value.authorName){
    isCommentWriter.value = true;
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

.top-button {
  display: flex;
  justify-content: space-between;
}

.putNdelete {
  margin-top: 25px;
}

.put,
.delete {
  margin-right: 10px;
  border: none;
  padding: 6px;
  border-radius: 8px;
  background-color: darkcyan;
  color: white;
  font-weight: 800;
  cursor: pointer;
}

.recommendNput {
  display: flex;
}

.commentPutNdelete {
  margin-top: 12px;
}
</style>
  