<template>
    <div>
      생성페이지에요~
      <form @submit.prevent="submitForm">
        <div>
          <label for="content">내용</label>
          <input type="text" id="content" name="content" v-model="form.content" placeholder="모집 내용"/>
        </div>
        <button type="submit">등록</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from '@/api/axios';
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  
  const form = ref({
    content: ""
  });
  
  const submitForm = async () => {
    const recruitForm = {
      content: form.value.content
    };
  
    try {
      const response = await axios.post('/futsal/board', recruitForm, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      alert("작성이 완료 되었습니다.");
      // 글 등록 후 필요한 동작 수행 (예: 페이지 이동)
      router.go(-1);
    } catch (error) {
      console.error('글 등록 실패:', error);
    }
  };
  </script>
  
  <style scoped>
  </style>
  