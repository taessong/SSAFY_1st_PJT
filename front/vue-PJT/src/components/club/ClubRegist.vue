<template>
    <div>
      생성페이지에요~
      <form @submit.prevent="submitForm">
        <div>
          <label for="title">제목</label>
          <input type="text" id="title" name="title" v-model="form.title" />
        </div>
        <div>
          <label for="content">내용</label>
          <input type="text" id="content" name="content" v-model="form.content" />
        </div>
        <div>
          <label for="file">파일 첨부</label>
          <input type="file" id="postImgPath" name="postImgPath" @change="handleFileUpload" />
        </div>
        <div v-if="previewUrl">
          <p>선택한 파일 미리보기:</p>
          <img :src="previewUrl" alt="File Preview" class="file-preview" />
        </div>
        <button type="submit">제출해!!!!!!!!!!!!!</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from '@/api/axios';
  
  const form = ref({
    title: '',
    content: '',
    postImgPath: ''
  });
  
  const previewUrl = ref(null);
  
  const handleFileUpload = async (event) => {
    const file = event.target.files[0];
    const fileData = new FormData();
    fileData.append('postFile', file); // 필드 이름을 'postFile'로 지정
    
    // 선택한 파일 미리보기 URL 생성
    previewUrl.value = URL.createObjectURL(file);
  
    try {
      const response = await axios.post('/file/postfileUpload', fileData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      const postImgPath = response.data;
      form.value.postImgPath = postImgPath; // 업로드된 파일의 URL을 form에 저장
    } catch (error) {
      console.error('파일 업로드 실패:', error);
    }
  };
  
  const submitForm = async () => {
    try {
      const response = await axios.post('/community/board', form.value);
      console.log('글 등록 성공:', response.data);
      // 글 등록 후 필요한 동작 수행 (예: 페이지 이동)
    } catch (error) {
      console.error('글 등록 실패:', error);
    }
  };
  </script>
  
  <style scoped>
  /* 스타일을 여기에 추가하세요 */
  .file-preview {
    width: 100px; /* 원하는 크기로 조절하세요 */
    height: 100px; /* 원하는 크기로 조절하세요 */
    margin-top: 10px;
  }
  </style>
  