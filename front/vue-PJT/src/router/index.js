import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'
import MainView from '@/views/MainView.vue'
import SignUpView from '@/views/SignUpView.vue'
import MatchView from '@/views/MatchView.vue'

import MatchBoard from '@/components/match/MatchBoard.vue'
import MatchDetail from '@/components/match/MatchDetail.vue'
import MatchRegist from '@/components/match/MatchRegist.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginView
    },
    {
      path: '/main',
      name: 'main',
      component: MainView
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUpView
    },
    {
      path: '/match',
      name: 'match',
      component: MatchView,
      children: [
        {
          path: "",
          name: "matchboard",
          component: MatchBoard
        },
        {
          path: ":id",
          name: "matchdetail",
          component: MatchDetail
        },
        {
          path: "regist",
          name: "matchregist",
          component: MatchRegist
        },
      ]
    },
  ]
})

export default router
