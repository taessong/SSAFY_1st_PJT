<script setup>
import HeaderBar from '@/components/nav/HeaderBar.vue';
import { RouterView, useRoute } from 'vue-router';
import { watch } from 'vue';
import { ref } from 'vue';
import Gpt from './components/gpt/Gpt.vue';

const showHeader = ref(true);
const route = useRoute();

const updateHeaderVisibility = () => {
  console.log(route.path)
  showHeader.value = route.path !== '/' && route.path !== '/signup';
};

// 초기화
updateHeaderVisibility();

// 라우트가 변경될 때마다 실행
watch(() => route.path, updateHeaderVisibility);
</script>

<template>
  <div>
    <HeaderBar v-if="showHeader"/>
    <RouterView/>
    <Gpt />
  </div>
</template>

<style>

</style>
