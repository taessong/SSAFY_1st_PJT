<template>
  <div class="detailBoard">
    <div class="detailPage">
      <div class="top-button">
        <button @click="goBack" class="gobackButton">◀️ 뒤로가기</button>
        <div v-if="isWriter" class="putNdelete">
          <button @click="goUpdate()" class="put">수정</button>
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
        <div v-if="chatItem.postImgPath">
          <img
            :src="chatItem.postImgPath"
            alt="Post Image"
            class="post-image"
          />
        </div>
      </div>
      <p class="viewCnt">조회수: {{ chatItem.viewCnt }}</p>
    </div>
    <div class="comments">
      <h1>댓글 {{ comments.length }}개</h1>
      <div v-for="comment in comments" :key="comment.commentId" class="comment">
        <div v-if="modifyMode[comment.commentId]" class="isModify">
          <input type="text" v-model="comment.content" class="modifyBar" />
          <div class="saveButton">
            <button @click="updateComment(comment)">저장</button>
            <button @click="toggleModifyMode(comment.commentId)">취소</button>
          </div>
        </div>
        <div v-else>
          <div class="commentBar">
            <div class="authornContent">
              <p>{{ comment.authorName }}</p>
              <p>{{ comment.content }}</p>
            </div>
            <div class="recommendNput">
              <button class="recommend" @click="recommend(comment.commentId)">
                👍 {{ comment.recommend }}
              </button>
              <div
                v-if="comment.authorName === currentNickName"
                class="commentPutNdelete"
              >
                <button
                  class="put"
                  @click="toggleModifyMode(comment.commentId)"
                >
                  수정
                </button>
                <button
                  class="delete"
                  @click="confirmCommentDelete(comment.commentId)"
                >
                  삭제
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <form @submit.prevent="handleSubmitComment">
        <div class="createComment">
          <input
            type="text"
            class="commentInput"
            placeholder="댓글을 입력하세요."
            id="content"
            name="content"
            v-model="comment.content"
          />
          <button class="commentRegistButton" type="submit">등록</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { useClubStore } from "@/stores/club";
import { onMounted, computed, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "@/api/axios";

const props = defineProps({
  id: String,
});

const comment = ref({
  content: "",
});

const modifyMode = ref({});

const store = useClubStore();
const storeDetail = useClubStore();
const route = useRoute();
const router = useRouter();
const isWriter = ref(false);

const currentNickName = sessionStorage.getItem("nickName");

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

const toggleModifyMode = (commentId) => {
  modifyMode.value[commentId] = !modifyMode.value[commentId];
};

const updateComment = async (comment) => {
  const postId = route.params.id;
  try {
    await axios.put(`/community/board/${postId}/comment/${comment.commentId}`, {
      content: comment.content,
    });
    alert("댓글이 수정되었습니다.");
    modifyMode.value[comment.commentId] = false;
    await store.fetchComments(postId); // 업데이트된 댓글 목록을 가져옴
  } catch (error) {
    console.log("댓글 수정 실패", error);
  }
};

const goUpdate = () => {
  const postId = route.params.id;
  router.push({ name: "clubUpdate", params: { id: postId } });
};

const deleteBoard = async (postId) => {
  try {
    await axios.delete(`/community/board/${postId}`);
    router.back();
  } catch (error) {
    console.log("실패용", error);
  }
};

const confirmCommentDelete = (commentId) => {
  if (window.confirm("정말 댓글을 삭제하시겠습니까?")) {
    commentDelete(commentId);
  }
};

const commentDelete = async (commentId) => {
  const postId = route.params.id;
  try {
    await axios.delete(`/community/board/${postId}/comment/${commentId}`);
    await store.fetchComments(postId); // 업데이트된 댓글 목록을 가져옴
  } catch (error) {
    console.log("에러다!!", error);
  }
};

const handleSubmitComment = async () => {
  const postId = route.params.id;
  const commentData = {
    content: comment.value.content,
  };

  try {
    await axios.post(`/community/board/${postId}/comment`, commentData);
    alert("댓글이 작성되었습니다.");
    comment.value.content = ""; // 댓글 입력란 초기화
    await store.fetchComments(postId); // 새로운 댓글 리스트를 가져옴
  } catch (error) {
    console.log("더 성장해라", error);
  }
};

const comments = computed(() => store.comments);
const chatItem = computed(() => storeDetail.chatItem);

onMounted(async () => {
  const postId = route.params.id;
  await storeDetail.fetchOneChatData(postId);
  await store.fetchComments(postId);
  if (sessionStorage.getItem("nickName") === chatItem.value.authorName) {
    isWriter.value = true;
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
}

.commentBar {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.comment p {
  margin-left: 15px;
}

.authornContent {
  display: flex;
  align-items: center;
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
  align-items: center;
}

.commentPutNdelete {
  margin-top: 3px;

}

.post-image {
  max-width: 100%;
  height: auto;
  margin-top: 10px;
}

.createComment {
  display: flex;
  margin-top: 15px;
  justify-content: center;
}

.commentInput {
  border: 1px solid black;
  padding: 30px;
  width: 90%;
  border-radius: 8px;
}

.commentRegistButton {
  margin-left: 10px;
  border: none;
  background-color: darkcyan;
  color: white;
  font-weight: 800;
  padding: 20px 20px;
  border-radius: 8px;
  width: 10%;
}


.modifyBar {
  width: 80%;
  margin-left: 10px;
  border-radius: 8px;
  border: none;
  padding: 10px;
}

.saveButton button {
  padding: 5px 10px;
  margin: 5px;
  border: none;
  background-color: darkcyan;
  border-radius: 8px;
  font-weight: 800;
  color:white;
}

.isModify {
  display: flex;
  justify-content: space-between;
}
</style>
