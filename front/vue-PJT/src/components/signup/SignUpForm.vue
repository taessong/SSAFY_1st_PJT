<template>
    <div>
      <div class="head">회원가입</div>
      <fieldset class="signup-form">
        <div class="imgninput">
          <div class="file-upload-container">
            <input
              type="file"
              @change="handleFileUpload"
              ref="fileInput"
              style="display: none"
            />
            <button @click="triggerFileInput">파일 선택</button>
            <img
              :src="filePath || defaultImagePath"
              alt="Profile Image"
              @load="resizeImage"
              ref="image"
            />
            <p v-if="fileName">{{ fileName }}</p>
          </div>
          <div class="inputForms">
            <div class="inputForm">
              <label for="id">아이디</label>
              <input
                type="text"
                id="id"
                placeholder="아이디를 입력하세요(~20자 이내)"
                size="25"
              />
            </div>
            <div class="inputForm">
              <label for="password">비밀 번호</label>
              <input
                type="text"
                id="password"
                placeholder="비밀 번호를 입력하세요"
              />
            </div>
            <div class="inputForm">
              <label for="nickName">닉네임</label>
              <input
                type="text"
                id="nickName"
                placeholder="닉네임을 입력하세요."
              />
            </div>
            <div class="inputForm">
              <label for="realName">본 명</label>
              <input type="text" id="realName" placeholder="본명을 입력하세요." />
            </div>
            <div class="inputForm">
              <label for="favoriteTeam">선호 팀</label>
              <select name="favorite" id="favoriteTeam" @change="handleTeamChange">
                <option value="">-- 선택하세요 --</option>
                <option value="ManchesterCity">맨체스터시티</option>
                <option value="ManchesterUnited">맨체스터유나이티드</option>
                <option value="TottenhamHotspur">토트넘홋스퍼</option>
                <option value="Liverpool">리버풀</option>
                <option value="Arsenal">아스널</option>
                <option value="Chelsea">첼시</option>
              </select>
            </div>
          </div>
        </div>
        <div v-if="teamLogoPath" class="team-logo-container">
          <img :src="teamLogoPath" alt="Team Logo" />
        </div>
      </fieldset>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  
  // 기본 프로필 이미지 경로
  import defaultImageImg from "@/asset/loginImage/profileImg.png"; // 여기에 기본 이미지 경로를 넣으세요.
  
  const defaultImagePath = defaultImageImg;
  const fileName = ref("");
  const filePath = ref("");
  const fileInput = ref(null);
  const image = ref(null);
  
  // 팀 로고 경로
  const teamLogoPath = ref("");
  
  // 팀 로고 경로를 설정하는 함수
  const getTeamLogoPath = (team) => {
    // 팀 이름에 따라 해당 로고 파일 경로를 반환합니다.
    console.log(team);
    return new URL(`../asset/logo/${team}.png`, import.meta.url).href;
  };
  
  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      fileName.value = removeFileExtension(file.name);
      filePath.value = URL.createObjectURL(file);
      console.log("Selected file:", file);
    } else {
      fileName.value = "";
      filePath.value = "";
    }
  };
  
  const removeFileExtension = (name) => {
    return name.split(".").slice(0, -1).join(".");
  };
  
  const triggerFileInput = () => {
    fileInput.value.click();
  };
  
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
    const selectedTeam = event.target.value;
    if (selectedTeam) {
      teamLogoPath.value = getTeamLogoPath(selectedTeam);
    } else {
      teamLogoPath.value = "";
    }
  };
  </script>
  
  <style scoped>
  .head {
    text-align: center;
    font-size: 32px;
    font-weight: 800;
    padding: 20px;
  }
  
  .signup-form {
    align-items: center;
    width: 75%;
    margin-left: auto;
    margin-right: auto;
    border-radius: 6px;
    height: fit-content;
    margin-bottom: 30px;
    border: 2px solid black;
  }
  
  .imgninput {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
  }
  
  .file-upload-container {
    display: flex;
    flex-direction: column-reverse;
    align-items: center;
    flex: 1;
  }
  
  .inputForms {
    padding: 20px;
    display: flex;
    flex-direction: column;
    flex: 2;
  }
  
  .inputForm {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }
  
  label {
    width: 100px;
    text-align: center;
    margin-right: 10px;
    border: 2px solid black;
    border-radius: 15px;
  }
  
  input[type="text"],
  select {
    flex: 1;
    padding: 5px;
    border: 2px solid black;
    border-radius: 15px;
  }
  
  button {
    padding: 5px 10px;
    font-size: 16px;
    cursor: pointer;
  }
  
  img {
    margin-top: 20px;
    max-width: 100%;
    height: auto;
  }
  
  .team-logo-container {
    text-align: center;
    margin-top: 20px;
  }
  
  .team-logo-container img {
    max-width: 150px;
    max-height: 150px;
  }
  
  p {
    font-size: 15px;
    font-weight: 800;
  }
  </style>
  