<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between mb-4">
      <button
        @click="goBack"
        :class="['btn', computedFavoriteTeamButtonClass]"
      >
        ◀️ 뒤로가기
      </button>
    </div>
    <div class="mb-4">
      <div class="d-flex justify-content-between align-items-center">
        <h2>{{ chatItem.title }}</h2>
        <div class="author">
          <p class="fw-bold mb-0">작성자 : {{ chatItem.authorName }}</p>
          <p class="fw-bold mb-0">작성 일자 : {{ chatItem.regDate }}</p>
        </div>
      </div>
    </div>
    <div class="mb-4 border rounded p-3">
      <p class="content">{{ chatItem.content }}</p>
      <div v-if="chatItem.postImgPath" class="text-center mt-3">
        <img :src="chatItem.postImgPath" alt="Post Image" class="img-fluid" />
      </div>
    </div>
    <div class="bottom">
      <p class="fw-bold">조회수: {{ chatItem.viewCnt }}</p>
      <div v-if="isWriter">
        <button
          @click="goUpdate()"
          :class="['btn', 'btn-warning', computedFavoriteTeamButtonClass, 'me-2']"
        >
          수정
        </button>
        <button
          @click="confirmDelete(chatItem.postId)"
          :class="['btn', 'btn-danger', computedFavoriteTeamButtonClass]"
        >
          삭제
        </button>
      </div>
    </div>
    <div class="comments mt-4">
      <h3>댓글 {{ comments.length }}개</h3>
      <div
        v-for="comment in comments"
        :key="comment.commentId"
        class="card mb-3 p-3"
      >
        <div v-if="modifyMode[comment.commentId]">
          <input
            type="text"
            v-model="comment.content"
            class="form-control mb-2"
          />
          <button @click="updateComment(comment)" class="btn btn-primary me-2">
            저장
          </button>
          <button
            @click="toggleModifyMode(comment.commentId)"
            class="btn btn-secondary"
          >
            취소
          </button>
        </div>
        <div v-else class="d-flex justify-content-between align-items-center">
          <div>
            <p class="fw-bold mb-1">{{ comment.authorName }}</p>
            <p class="mb-1">{{ comment.content }}</p>
          </div>
          <div class="d-flex align-items-center">
            <button
              class="btn me-2"
              @click="recommend(comment.commentId)"
              :disabled="hasRecommended(comment.commentId)"
            >
              👍 {{ comment.recommend }}
            </button>
            <div v-if="comment.authorName === currentNickName">
              <button
                @click="toggleModifyMode(comment.commentId)"
                :class="['btn', 'btn-warning', computedFavoriteTeamButtonClass, 'me-2']"
              >
                수정
              </button>
              <button
                @click="confirmCommentDelete(comment.commentId)"
                :class="['btn', 'btn-warning', computedFavoriteTeamButtonClass, 'me-2']"
              >
                삭제
              </button>
            </div>
          </div>
        </div>
      </div>
      <form @submit.prevent="handleSubmitComment" class="mt-3">
        <div class="input-group">
          <input
            type="text"
            class="form-control"
            placeholder="댓글을 입력하세요."
            id="content"
            name="content"
            v-model="comment.content"
          />
          <button :class="['btn', 'btn-primary', computedFavoriteTeamButtonClass, 'me-2']" type="submit">등록</button>
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
import { getTeamColorClass } from "@/utils/teamColors";

const comment = ref({
  content: "",
});

const modifyMode = ref({});
const recommendedComments = ref([]);

const store = useClubStore();
const storeDetail = useClubStore();
const route = useRoute();
const router = useRouter();
const isWriter = ref(false);

const currentNickName = sessionStorage.getItem("nickName");

const favoriteTeam = ref(sessionStorage.getItem("favoriteTeam"));

const computedFavoriteTeamColorClass = computed(() => {
  return getTeamColorClass(favoriteTeam.value);
});

const computedFavoriteTeamButtonClass = computed(() => {
  return getTeamColorClass(favoriteTeam.value, 'btn');
});

const goBack = () => {
  router.back();
};

const recommendCount = async (commentId) => {
  const postId = route.params.id;
  try {
    await axios.put(
      `/community/board/${postId}/comment/${commentId}/recommend`
    );
    alert("댓글을 추천하였습니다");
    router.go(0);
  } catch (error) {
    console.log("에러가 났어용", error);
  }
};

const recommend = async (commentId) => {
  const postId = route.params.id;
  try {
    const response = await axios.post("/recommendation", {
      userNickName: sessionStorage.getItem("nickName"), // 사용자 ID를 세션 또는 다른 저장소에서 가져옴
      postId: postId.toString(), // postId를 문자열로 변환
      commentId: commentId.toString(), // commentId를 문자열로 변환
    });
    recommendedComments.value.push(commentId); // 사용자 추천 리스트에 추가
    recommendCount(commentId);
  } catch (error) {
    if (error.response && error.response.status === 409) {
      alert("이미 추천하셨습니다.");
    } else {
      console.error("추천 실패", error);
    }
  }
};

const hasRecommended = (commentId) => {
  return recommendedComments.value.includes(commentId);
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
.card {
  border-radius: 8px;
  padding: 10px;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}

.bottom {
  display: flex;
  justify-content: space-between;
}

.input-group {
  margin-bottom: 150px;
}

.form-control {
  padding: 15px;
}

h2 {
  font-weight: bolder;
  margin-left: 10px;
}

.img-fluid {
  width: 1280px;
  height: 860px;
}

.content {
  font-size: 20px;
}

.author {
  text-align: end;
}

/* 팀 컬러 스타일 추가 */
.mancity-color, .mancity-btn {
  background-color: #6CABDD;
  color: white;
}

.manutd-color, .manutd-btn {
  background-color: #DA291C;
  color: white;
}

.liverpool-color, .liverpool-btn {
  background-color: #C8102E;
  color: white;
}

.chelsea-color, .chelsea-btn {
  background-color: #034694;
  color: white;
}

.arsenal-color, .arsenal-btn {
  background-color: #EF0107;
  color: white;
}

.tottenham-color, .tottenham-btn {
  background-color: #132257;
  color: white;
}

/* 클래스 우선순위를 높이기 위해 구체적인 선택자 사용 */
.btn-warning.mancity-btn {
  background-color: #6CABDD;
}

.btn-danger.mancity-btn {
  background-color: #6CABDD;
}

.btn-warning.manutd-btn {
  background-color: #DA291C;
}

.btn-danger.manutd-btn {
  background-color: #DA291C;
}

.btn-warning.liverpool-btn {
  background-color: #C8102E;
}

.btn-danger.liverpool-btn {
  background-color: #C8102E;
}

.btn-warning.chelsea-btn {
  background-color: #034694;
}

.btn-danger.chelsea-btn {
  background-color: #034694;
}

.btn-warning.arsenal-btn {
  background-color: #EF0107;
}

.btn-danger.arsenal-btn {
  background-color: #EF0107;
}

.btn-warning.tottenham-btn {
  background-color: #132257;
}

.btn-danger.tottenham-btn {
  background-color: #132257;
}
</style>
