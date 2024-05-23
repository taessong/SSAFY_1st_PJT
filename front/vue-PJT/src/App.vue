<script setup>
import HeaderBar from '@/components/nav/HeaderBar.vue';
import { RouterView } from 'vue-router';
import Gpt from './components/gpt/Gpt.vue';
</script>

<template>
  <div>
    <HeaderBar/>
    <RouterView/>
    <Gpt />
  </div>
</template>

<style>

</style>
