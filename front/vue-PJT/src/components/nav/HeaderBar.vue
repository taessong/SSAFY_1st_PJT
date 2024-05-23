<template>
  <header class="header-bar">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-md-2">
          <RouterLink :to="{ name: 'login' }"><h3>⚽</h3></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'clubBoard' }"><h3>📢</h3></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'matchboard' }"><h3>👊🏼</h3></RouterLink>
        </div>
        <div class="col-md-4" v-if="nickName" style="display: flex; align-items: center;">
          <img :src="logoSrc" alt="favoriteTeam" v-if="logoSrc" class="logoImg">
          <h3>{{ nickName }}님, 안녕하세요!</h3>
        </div>
        <div class="col-md-2">
          <RouterLink v-if="nickName" :to="{ name: 'login' }" @click="handlerLogout"><h3>📴</h3></RouterLink>
        </div>
      </div>
    </div>
    <div class="team-img">
      <img :src="teamImageSrc" alt="Team Image" ref="teamImage">
    </div>
  </header>
</template>
<script setup>
import { onMounted, ref, nextTick } from "vue";
import { useRouter } from 'vue-router';

// 모든 팀의 배경 이미지를 사전에 import
import Tottenham from '../../asset/teams/토트넘.jpg';
import Mancity from '../../asset/teams/맨시티.jpg';
import Manchester from '../../asset/teams/맨유.jpg';
import Arsenal from '../../asset/teams/아스날.jpg';
import Chelsea from '../../asset/teams/첼시.jpg';
import Liverpool from '../../asset/teams/리버풀.jpg';

const teamBackgrounds = {
  '토트넘': Tottenham,
  '맨시티': Mancity,
  '맨유': Manchester,
  '아스널': Arsenal,
  '첼시': Chelsea,
  '리버풀': Liverpool
};

const nickName = ref(null);
const logoSrc = ref('');
const headerStyle = ref({ backgroundColor: 'black' }); // 기본 배경색 설정
const teamImageSrc = ref('');
const router = useRouter();
const teamImage = ref(null);

const updateNickName = () => {
  nickName.value = sessionStorage.getItem("nickName");
}

onMounted(async () => {
  updateNickName();
  const favoriteTeam = sessionStorage.getItem("favoriteTeam");
  if (favoriteTeam) {
    // 배경 이미지 설정
    if (teamBackgrounds[favoriteTeam]) {
      headerStyle.value.backgroundImage = `url(${teamBackgrounds[favoriteTeam]})`;
    }
    // 동적 import를 사용하여 프로필 이미지 설정
    try {
      const module = await import(`../../asset/logo/${favoriteTeam}.png`);
      logoSrc.value = module.default;
    } catch (error) {
      console.error("Error loading logo:", error);
    }
    // 팀 이미지 설정
    teamImageSrc.value = teamBackgrounds[favoriteTeam];
    nextTick(() => {
      adjustImageSize();
    });
  }
});

const adjustImageSize = () => {
  if (teamImage.value) {
    const img = teamImage.value;
    img.style.width = 'auto';
    img.style.height = 'auto';
    img.style.maxWidth = '100%';
    img.style.maxHeight = '300px';
    img.style.objectFit = 'contain';
  }
}

window.addEventListener('storage', () => {
  updateNickName();
});

const handlerLogout = (event) => {
  event.preventDefault();
  sessionStorage.removeItem("nickName");
  sessionStorage.removeItem("favoriteTeam");
  nickName.value = null;
  alert("로그아웃 되었습니다.");
  router.go(0);
};
</script>
<style scoped>
.header-bar {
  padding: 10px 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.container {
  width: 100%;
  max-width: 1200px;
}

.row {
  width: 100%;
}

.header-sub {
  display: flex;
  margin-top: 15px;
  margin-right: auto;
  align-items: center;
}

.user-info {
  display: flex;
  align-items: center;
  margin-top: 15px;
}

.header-login {
  display: flex;
  align-items: center;
}

.logoImg {
  margin-right: 10px;
  border-radius: 50%;
  width: 40px;
  height: 40px;
}

h1 {
  color: white;
  margin-left: 5px;
  margin-right: 20px;
  margin-top: 0;
}

h3 {
  color: white;
  margin-left: 10px;
  margin-right: 10px;
  padding: 5px;
}

a {
  text-decoration: none;
  color: white;
  font-weight: 800;
  font-size: 16px;
}

.a {
  text-decoration: none;
  color: white;
}

a:hover, .a:hover, .header-sub h3:hover {
  color: lightgray;
}

img {
  width: 40px;
  height: 40px;
}

.team-img {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  overflow: hidden;
  margin-top: 20px;
}

.team-img img {
  max-width: 100%;
  max-height: 300px;
  object-fit: contain;
}
</style>
