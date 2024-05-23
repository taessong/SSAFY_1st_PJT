<template>
  <div class="container mt-5">
    <div class="head text-center mb-4">회원가입</div>
    <form @submit.prevent="handleSubmit">
      <fieldset class="signup-form p-4">
        <div class="row top-signup mb-4 mt-5">
          <div class="col-md-4 text-center file-upload-container">
            <input
              type="file"
              name="userImgPath"
              @change="handleFileUpload"
              ref="fileInput"
              class="d-none"
            />
            <button @click="triggerFileInput" type="button" class="btn btn-primary mb-3">Upload</button>
            <img
              :src="filePath || defaultImagePath"
              alt="Profile Image"
              @load="resizeImage"
              ref="image"
              class="img-fluid rounded-circle profile-image mb-3"
            />
            <div v-if="teamLogoPath" class="team-logo-container mb-3">
              <img :src="teamLogoPath" alt="Team Logo" @load="resizeImage" class="img-fluid" />
              <p>선택된 팀 : {{ selectedTeam }}</p>
            </div>
            <div v-else class="team-logo-container">아직 선택 안함</div>
          </div>
          <div class="col-md-8 inputForms">
            <div class="form-group mb-3">
              <label for="userId" class="form-label">아이디</label>
              <input
                type="text"
                id="userId"
                v-model="form.userId"
                name="userId"
                class="form-control"
                placeholder="아이디를 입력하세요(~20자 이내)"
              />
            </div>
            <div class="form-group mb-3">
              <label for="password" class="form-label">비밀 번호</label>
              <input
                type="password"
                id="password"
                v-model="form.password"
                name="password"
                class="form-control"
                placeholder="비밀 번호를 입력하세요"
              />
            </div>
            <div class="form-group mb-3">
              <label for="nickName" class="form-label">닉네임</label>
              <input
                type="text"
                id="nickName"
                v-model="form.nickName"
                name="nickName"
                class="form-control"
                placeholder="닉네임을 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="realName" class="form-label">본 명</label>
              <input
                type="text"
                id="realName"
                v-model="form.realName"
                name="realName"
                class="form-control"
                placeholder="본명을 입력하세요."
              />
            </div>
            <div class="form-group mb-3">
              <label for="teamName" class="form-label">선호 팀</label>
              <select
                name="teamName"
                id="teamName"
                v-model="form.teamName"
                class="form-select"
                @change="handleTeamChange"
              >
                <option value="">선호하는 팀을 선택하세요.</option>
                <option value="맨시티">맨체스터시티</option>
                <option value="맨유">맨체스터유나이티드</option>
                <option value="토트넘">토트넘홋스퍼</option>
                <option value="리버풀">리버풀</option>
                <option value="아스널">아스널</option>
                <option value="첼시">첼시</option>
              </select>
            </div>
          </div>
        </div>
        <div class="bottom-signup">
          <div class="d-flex justify-content-center">
            <button @click="resetForm" type="button" class="btn btn-secondary me-2">초기화</button>
            <button type="submit" class="btn btn-primary">가입</button>
          </div>
        </div>
      </fieldset>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "@/api/axios";
import router from "@/router";

// 기본 프로필 이미지 경로
import defaultImageImg from "@/asset/loginImage/profileImg.png"; // 여기에 기본 이미지 경로를 넣으세요.

const defaultImagePath = defaultImageImg;
const filePath = ref("");
const fileInput = ref(null);
const image = ref(null);
const selectedTeam = ref("");

// axios를 통해 내보낼 데이터 폼
const form = ref({
  userId: "",
  password: "",
  nickName: "",
  realName: "",
  teamName: "",
  userImgPath: "", // URL을 저장할 필드
});

// 폼을 초기화하는 함수
const resetForm = () => {
  form.value = {
    userId: "",
    password: "",
    nickName: "",
    realName: "",
    teamName: "",
    userImgPath: "",
  };
  filePath.value = "";
  selectedTeam.value = "";
  teamLogoPath.value = "";
  
  // 파일 입력 요소 초기화
  if (fileInput.value) {
    fileInput.value.value = "";
  }

  // 이미지 src 초기화
  if (image.value) {
    image.value.src = defaultImagePath;
  }
};

// 팀 로고 경로
const teamLogoPath = ref("");

// 팀 로고 경로를 설정하는 함수
const getTeamLogoPath = (team) => {
  const a = new URL(`../../asset/logo/${team}.png`, import.meta.url).href;
  return a;
};

// 파일을 선택하면 해당 파일을 브라우저에서 사용할 수 있도록 하는 함수
const handleFileUpload = async (event) => {
  const file = event.target.files[0];
  const fileData = new FormData();
  fileData.append('file', file);

  try {
    const response = await axios.post('/file/profileUpload', fileData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
    const fileUrl = response.data;
    filePath.value = fileUrl; // 업로드된 파일의 URL을 form에 저장
    form.value.userImgPath = fileUrl; // 업로드된 파일의 URL을 form에 저장
  } catch (error) {
    console.error('파일 업로드 실패:', error);
  }
};

const triggerFileInput = () => {
  fileInput.value.click();
};

// 브라우저 이미지에 맞게 해당 이미지를 조절하는 함수
const resizeImage = () => {
  if (image.value) {
    const maxDimension = 200;
    if (image.value.width > image.value.height) {
      image.value.style.width = `${maxDimension}px`;
      image.value.style.height = "auto";
    } else {
      image.value.style.height = `${maxDimension}px`;
      image.value.style.width = "auto";
    }
  }
};

// 팀 선택 시 팀 로고 경로를 설정하는 함수
const handleTeamChange = (event) => {
  selectedTeam.value = event.target.value;
  if (selectedTeam.value) {
    teamLogoPath.value = getTeamLogoPath(selectedTeam.value);
  } else {
    teamLogoPath.value = "";
  }
};

// 폼 제출 처리 함수
const handleSubmit = async () => {
  const user = {
    userId: form.value.userId,
    password: form.value.password,
    nickName: form.value.nickName,
    realName: form.value.realName,
    teamName: form.value.teamName,
    userImgPath: form.value.userImgPath, // URL을 사용자 데이터에 포함
  };

  try {
    const response = await axios.post('/user/signup', user);
    alert('회원가입 성공!');
    router.push('/');
  } catch (error) {
    console.error('회원가입 실패:', error);
    alert('회원가입 실패!');
  }
};
</script>

<style scoped>
.head {
  font-size: 32px;
  font-weight: 800;
}

.signup-form {
  border-radius: 6px;
  border: 2px solid black;
}

.file-upload-container {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  margin-bottom: 20px;
}

.profile-image {
  margin-top: 20px;
  width: auto;
  height: auto;
  max-width: 150px;
  max-height: 150px;
  object-fit: cover;
  border-radius: 50%;
}

.team-logo-container img {
  width: auto;
  height: auto;
  max-width: 150px;
  max-height: 150px;
  object-fit: cover;
}

.team-logo-container p {
  font-size: 15px;
  font-weight: 800;
}

.bottom-signup {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
