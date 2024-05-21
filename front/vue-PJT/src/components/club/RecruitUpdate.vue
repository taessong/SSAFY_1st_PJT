<template>
    <div v-if="recruitItem">
      업데이트 해야지요~
  
      <form @submit.prevent="updateRecruitBoard">
        <div>
          <label for="content">내용</label>
          <input type="text" id="content" v-model="recruitItem.content" />
        </div>
        <div>
          <label for="maxMembers">최대인원</label>
          <input type="number" id="maxMembers" v-model="recruitItem.maxMembers" />
        </div>
        <button type="submit">제출해!!!!!!!!!!!!!</button>
      </form>
    </div>
  </template>  
  
  <script setup>
  import { ref, onMounted } from "vue";
  import { useRoute, useRouter } from "vue-router";
  import { useRecruitStore } from '@/stores/recruit';
  
  const route = useRoute();
  const router = useRouter();
  const store = useRecruitStore();
  const recruitItem = ref(null);
  
  onMounted(async () => {
    const recruitId = route.params.id;
    await store.fetchRcruitOneData(recruitId);
    recruitItem.value = store.recruitItem;
  });
  
  const updateRecruitBoard = async () => {
    const recruitId = route.params.id;
    await store.updateRecruitBoard(recruitId, recruitItem.value);
    router.back();
  };
  </script>
  
  
  <style scoped>
 
  </style>
  