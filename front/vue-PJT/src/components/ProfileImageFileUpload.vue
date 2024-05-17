<template>
  <div class="file-upload-container">
    <input type="file" @change="handleFileUpload" ref="fileInput" style="display: none;" />
    <button @click="triggerFileInput">파일 선택</button>
    <img :src="filePath || defaultImagePath" alt="Profile Image" @load="resizeImage" ref="image" />
    <p v-if="fileName">{{ fileName }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 기본 프로필 이미지 경로
import defaultImageImg from '@/asset/loginImage/profileImg.png' // 여기에 기본 이미지 경로를 넣으세요.

const defaultImagePath = defaultImageImg;
const fileName = ref('');
const filePath = ref('');
const fileInput = ref(null);
const image = ref(null);

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    fileName.value = removeFileExtension(file.name);
    filePath.value = URL.createObjectURL(file);
    console.log('Selected file:', file);
  } else {
    fileName.value = '';
    filePath.value = '';
  }
};

const removeFileExtension = (name) => {
  return name.split('.').slice(0, -1).join('.');
};

const triggerFileInput = () => {
  fileInput.value.click();
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
  display: none;
}

button {
  margin: 20px 0;
  padding: 5px 10px;
  font-size: 16px;
  cursor: pointer;
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
