<template>
  <header class="header-bar">
    <RouterLink :to="{ name: 'login' }"><h1>RunOnGround</h1></RouterLink>
    <div class="header-sub">
      <RouterLink :to="{ name: 'main' }"><h3>MainPage</h3></RouterLink>
      <RouterLink :to="{ name: 'match' }"><h3>Match</h3></RouterLink>
      <a href="https://www.naver.com" class="a"><h3>QnA With GPT</h3></a>
    </div>
    <div class="header-login">
      <div v-if="nickName" class="user-info">
        <img :src="logoSrc" alt="favoriteTeam" v-if="logoSrc">
        <h3>{{ nickName }}님, 안녕하세요!</h3>
      </div>
      <RouterLink v-if="nickName" :to="{ name: 'login' }" @click="handlerLogout"
        ><h3>Log Out</h3></RouterLink
      >
    </div>
  </header>
</template>

<!-- 여기에 나중에 로그인 여부 확인해서, 로그인 x일시 로고만, 아닐 시 전부 띄우게 해야함-->

<script setup>
import { onMounted, ref } from "vue";
const nickName = ref(null);

const logoSrc= ref('');

const updateNickName = () => {
    nickName.value = sessionStorage.getItem("nickName");
}

//닉네임 가져오기
onMounted( async () => {
    updateNickName();
    const favoriteTeam = sessionStorage.getItem("favoriteTeam");
    if(favoriteTeam){
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
})


const handlerLogout = (event) => {
  //로그아웃을 누르면 세션에 있는 정보 삭제
  event.preventDefault();
  sessionStorage.removeItem("nickName");
  sessionStorage.removeItem("favoriteTeam");
  nickName.value = null;
  alert("로그아웃 되었습니다.");
  router.push({ name: "login" });
};
</script>

<style scoped>
.header-bar {
  background-color: black;
  padding: 1px;
  display: flex;
  justify-content: space-between;
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