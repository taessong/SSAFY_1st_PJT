<template>
    <div>
        <h2>매치 등록 페이지입니당</h2>
        <fieldset>
            <legend>매치 정보 입력</legend>
            <input type="text" v-model="content" placeholder="매치 내용 입력">
            <input type="text" v-model="stadiumId" placeholder="경기장 ID 입력">
            <button @click="registMatch()">등록하기</button>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const content = ref('');
const stadiumId = ref('');
const router = useRouter();

const registMatch = async () => {
    try {
        const response = await axios.post('http://localhost:8080/futsal/match', {
            authorName: sessionStorage.getItem("nickName"),
            teamAName: sessionStorage.getItem("favoriteTeam"),
            content: content.value,
            stadiumId: stadiumId.value
        });
        console.log(response.data);
        router.push('/match');
    } catch (error) {
        console.error(error);
    }
}
</script>

<style scoped>

</style>
