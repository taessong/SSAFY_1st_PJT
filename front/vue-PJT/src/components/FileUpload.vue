<template>
    <div class="file-upload-container">
      <input type="file" @change="handleFileUpload" />
      <p v-if="fileName">선호하는 팀 : {{ fileName }}</p>
      <img v-if="filePath" :src="filePath" alt="Uploaded Image" @load="resizeImage" ref="image" />
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const fileName = ref('');
  const filePath = ref('');
  const image = ref(null);
  
  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      fileName.value = file.name;
      filePath.value = getFilePath(file.name);
      console.log('Selected file:', file);
    }
  };
  
  const getFilePath = (name) => {
    // 확장자를 포함한 파일 경로를 생성합니다.
    // 예를 들어, `example.png` 같은 파일 이름을 생성합니다.
    return new URL(`../asset/logo/${name}`, import.meta.url).href;
  };
  
  const resizeImage = () => {
    if (image.value) {
      const maxDimension = 200;
      if (image.value.width > image.value.height) {
        image.value.style.width = `${maxDimension}px`;
        image.value.style.height = 'auto';
      } else {
        image.value.style.height = `${maxDimension}px`;
        image.value.style.width = 'auto';
      }
    }
  };
  </script>
  
  <style scoped>
  .file-upload-container {
    display: flex;
    flex-direction: column-reverse;
    align-items: center;
  }
  
  input[type="file"] {
    display: block;
    margin: 20px 0;
  }
  
  img {
    margin-top: 20px;
    max-width: 100%; /* 원래 비율을 유지하면서 최대 너비를 100%로 설정 */
    height: auto; /* 자동 높이 설정 */
  }

  p {
    font-size: 15px;
    font-weight: 800;
  }
  </style>
  