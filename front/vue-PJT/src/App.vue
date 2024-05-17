<script setup>
import HeaderBar from '@/components/nav/HeaderBar.vue';
import { RouterView } from 'vue-router';
</script>

<template>
  <div>
    <HeaderBar/>
    <RouterView/>
  </div>
</template>

<style>
html, body {
        width: auto;
        margin : 0;
    }
</style>
