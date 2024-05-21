<template>
  <header class="header-bar" :style="headerStyle">
    <RouterLink :to="{ name: 'login' }"><h1>RunOnGround</h1></RouterLink>
    <div class="header-sub">
      <RouterLink :to="{ name: 'main' }"><h3>MainPage</h3></RouterLink>
      <RouterLink :to="{ name: 'matchboard' }"><h3>Match</h3></RouterLink>
      <a href="https://www.naver.com" class="a"><h3>QnA With GPT</h3></a>
    </div>
    <div class="header-login">
      <div v-if="nickName" class="user-info">
        <img :src="logoSrc" alt="favoriteTeam" v-if="logoSrc">
        <h3>{{ nickName }}님, 안녕하세요!</h3>
      </div>
      <RouterLink v-if="nickName" :to="{ name: 'login' }" @click="handlerLogout">
        <h3>Log Out</h3>
      </RouterLink>
    </div>
  </header>
</template>


<!-- 여기에 나중에 로그인 여부 확인해서, 로그인 x일시 로고만, 아닐 시 전부 띄우게 해야함-->

<script setup>
import { onMounted, ref } from "vue";
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
const router = useRouter();

const updateNickName = () => {
  nickName.value = sessionStorage.getItem("nickName");
}

onMounted(() => {
  updateNickName();
  const favoriteTeam = sessionStorage.getItem("favoriteTeam");
  if (favoriteTeam) {
    // 배경 이미지 설정
    if (teamBackgrounds[favoriteTeam]) {
      headerStyle.value = { backgroundImage: `url(${teamBackgrounds[favoriteTeam]})`, backgroundSize: 'cover', backgroundPosition: 'center', backgroundColor: 'black' };
    }
    // 동적 import를 사용하여 프로필 이미지 설정
    import(`../../asset/logo/${favoriteTeam}.png`)
      .then(module => {
        logoSrc.value = module.default;
      })
      .catch(error => {
        console.error("Error loading logo:", error);
      });
  }
});

window.addEventListener('storage', () => {
  updateNickName();
});

const handlerLogout = (event) => {
  event.preventDefault();
  sessionStorage.removeItem("nickName");
  sessionStorage.removeItem("favoriteTeam");
  nickName.value = null;
  alert("로그아웃 되었습니다.");
  router.push({ name: 'login' });
};
</script>




<style scoped>
.header-bar {
  background-color: black;
  padding: 1px;
  display: flex;
  justify-content: space-between;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 300px;
}

.header-sub {
  display: flex;
  margin-top: 9px;
  margin-right: auto;
}

.user-info {
  display: flex;
  align-items: center;
}

.header-login {
  display: flex;
  margin-top: 8px;
}

h1 {
  color: white;
  margin-left: 5px;
  margin-right: 10px;
}

h3 {
  color: white;
  margin-left: 10px;
  margin-right: 10px;
  padding: 5px;
}

a {
  text-decoration-line: none;
}

.a {
  text-decoration-line: none;
  color: white;
  font-weight: 800;
  font-size: 16px;
}

img {
  width: 40px;
  height: 40px;
}
</style>