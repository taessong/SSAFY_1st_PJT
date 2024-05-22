<template>
  <div class="container mt-4">
    <h2 class="mb-4" :class="favoriteTeamColorClass">게시글 등록</h2>
    <form @submit.prevent="submitForm">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" id="title" name="title" v-model="form.title" class="form-control" />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea id="content" name="content" v-model="form.content" class="form-control"></textarea>
      </div>
      <div class="mb-3">
        <label for="file" class="form-label">파일 첨부</label>
        <input type="file" id="postImgPath" name="postImgPath" @change="handleFileUpload" class="form-control" />
      </div>
      <div v-if="previewUrl" class="mb-3">
        <p>선택한 파일 미리보기:</p>
        <img :src="previewUrl" alt="File Preview" class="file-preview" />
      </div>
      <button type="submit" :class="['btn', favoriteTeamButtonClass]">글 등록</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from '@/api/axios';
import { useRouter } from 'vue-router';
import { getTeamColorClass } from '@/utils/teamColors';

const router = useRouter();

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
    alert("작성이 완료 되었습니다.")
    // 글 등록 후 필요한 동작 수행 (예: 페이지 이동)
    router.push({ name: 'clubBoard', query: { tab: 'chat' } });
  } catch (error) {
    console.error('글 등록 실패:', error);
  }
};

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

</script>

<style scoped>
.file-preview {
  width: fit-content; /* 원하는 크기로 조절하세요 */
  height: fit-content; /* 원하는 크기로 조절하세요 */
  margin-top: 15px;
  align-content: center;
}

h2 {
  font-weight: bolder;
}
</style>
