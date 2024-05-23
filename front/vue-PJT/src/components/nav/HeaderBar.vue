<template>
  <header class="header-bar" :class="[headerColorClass, { 'tottenham-special': isTottenham }]">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-md-2">
          <RouterLink :to="{ name: 'login' }"><h4 :class="{'text-black': isTottenham}">⚽홈</h4></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'clubBoard' }"><h4 :class="{'text-black': isTottenham}">📢커뮤니티</h4></RouterLink>
        </div>
        <div class="col-md-2">
          <RouterLink :to="{ name: 'matchboard' }"><h4 :class="{'text-black': isTottenham}">👊🏼매칭</h4></RouterLink>
        </div>
        <div class="col-md-4 user" v-if="nickName">
          <img :src="logoSrc" alt="favoriteTeam" v-if="logoSrc" class="logoImg">
          <h4 :class="{ 'text-white': !isTottenham, 'text-black': isTottenham }">{{ nickName }}님, 안녕하세요!</h4>
        </div>
        <div class="col-md-2">
          <RouterLink v-if="nickName" :to="{ name: 'login' }" @click="handlerLogout"><h4 :class="{'text-black': isTottenham}">📴로그아웃</h4></RouterLink>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from 'vue-router';
import { getTeamColorClass } from "@/utils/teamColors";

const nickName = ref(null);
const logoSrc = ref('');
const headerColorClass = ref('');
const isTottenham = ref(false);
const router = useRouter();

const updateNickName = () => {
  nickName.value = sessionStorage.getItem("nickName");
};

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

    // 헤더 배경색 설정
    headerColorClass.value = getTeamColorClass(favoriteTeam, 'color');
    isTottenham.value = favoriteTeam === '토트넘';
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
  router.go(0);
};
</script>

<style scoped>
.header-bar {
  padding: 70px 50px;
  color: white;
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
  color: white;
  font-size: 16px;
}

.user {
  display: flex;
  align-items: center;
  text-align: end;
}

/* 팀별 배경색 */
.mancity-color {
  background-color: #6CABDD !important;
}

.manutd-color {
  background-color: #DA291C !important;
}

.liverpool-color {
  background-color: #C8102E !important;
}

.chelsea-color {
  background-color: #034694 !important;
}

.arsenal-color {
  background-color: #EF0107 !important;
}

.tottenham-color {
  background-color: #132257 !important;
}

.tottenham-special {
  background-color: white !important;
}

.text-black {
  color: black !important;
}
</style>
