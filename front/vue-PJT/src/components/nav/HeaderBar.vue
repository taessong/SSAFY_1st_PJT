<template>
  <header class="header-bar">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-md-2">
          <RouterLink :to="{ name: 'login' }"><h4>⚽홈</h4></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'clubBoard' }"><h4>📢커뮤니티</h4></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'matchboard' }"><h4>👊🏼매칭</h4></RouterLink>
        </div>
        <div class="col-md-4 user" v-if="nickName">
          <img :src="logoSrc" alt="favoriteTeam" v-if="logoSrc" class="logoImg">
          <h4>{{ nickName }}님, 안녕하세요!</h4>
        </div>
        <div class="col-md-2">
          <RouterLink v-if="nickName" :to="{ name: 'login' }" @click="handlerLogout"><h4>📴로그아웃</h4></RouterLink>
        </div>
      </div>
    </div>
  </header>
</template>
<script setup>
import { onMounted, ref, nextTick } from "vue";
import { useRouter } from 'vue-router';

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
  padding: 70px 50px;
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

a {
  text-decoration: none;
  color: black;
  font-size: 16px;
}

.user {
  display: flex;
  align-items: center;
}
</style>
