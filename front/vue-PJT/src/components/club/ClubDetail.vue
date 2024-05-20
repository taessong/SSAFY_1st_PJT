<template>
    <div>
      <div>
        <h1>클럽 상세 페이지</h1>
        <p>클럽 ID: {{ id }}</p>
        <p>제목: {{ selectedPost?.title }}</p>
        <p>작성자: {{ selectedPost?.authorName }}</p>
        <p>작성 일: {{ selectedPost?.regDate }}</p>
        <p>조회수: {{ selectedPost?.viewCnt }}</p>
        <!-- 추가적인 게시글 정보 -->
      </div>
      <h2>댓글</h2>
      <ul>
        <li v-for="comment in comments" :key="comment.commentId">
          <p>{{ comment.content }}</p>
        </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { useClubStore } from "@/stores/club";
  import { onMounted, computed } from "vue";
  import { useRoute } from "vue-router";
  
  const props = defineProps({
    id: String,
  });
  
  const store = useClubStore();
  const route = useRoute();
  
  // selectedPost와 comments는 반응형으로 만들기 위해 computed로 정의
  const selectedPost = computed(() => store.selectedPost);
  const comments = computed(() => store.comments);
  
  onMounted(() => {
    const postId = route.params.id;
    store.fetchComments(postId);
  });
  </script>
  
  <style scoped>
  /* 스타일을 여기 추가하세요 */
  </style>
  