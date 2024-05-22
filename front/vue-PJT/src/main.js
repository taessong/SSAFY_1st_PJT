import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import "bootstrap/dist/css/bootstrap.min.css"
import './asset/main.css'

const app = createApp(App)

//모든 라우터 이동전에, 로그인 여부를 판단한다.
router.beforeEach((to, from, next) => {
  const nickName = sessionStorage.getItem("nickName");
  const favoriteTeam = sessionStorage.getItem("favoriteTeam");

  if (!nickName || !favoriteTeam) {
    if (to.path !== '/' && to.path !== '/signup') {
      // 로그인 페이지와 회원가입 페이지가 아니라면 로그인 페이지로 리다이렉트
      next('/');
    } else {
      next();
    }
  } else {
    if (to.path === '/' || to.path === '/signup') {
      // 이미 로그인한 상태에서 로그인 페이지나 회원가입 페이지로 가려는 경우 메인 페이지로 리다이렉트
      next('/main');
    } else {
      next();
    }
  }
});


app.use(createPinia())
app.use(router)
app.mount('#app')

import "bootstrap/dist/js/bootstrap.min.js"