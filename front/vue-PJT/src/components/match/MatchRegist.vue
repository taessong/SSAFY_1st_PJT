<template>
    <div>
        <h2>매치 등록 페이지입니당</h2>
        <fieldset>
            <legend>매치 정보 입력</legend>
            <input type="text" v-model="content" placeholder="매치 내용 입력">
            <input type="date" v-model="matchDate" placeholder="경기 날짜 선택">
            <input type="time" v-model="matchTime" placeholder="경기 시간 입력">
            <input type="text" v-model="stadiumId" placeholder="경기장 ID 입력">
            <select name="" id=""></select>
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
const matchDate = ref('');
const matchTime = ref('');
const router = useRouter();

const registMatch = async () => {
    try {
        console.log(matchDate.value);
        console.log(matchTime.value);

        const response = await axios.post('/futsal/match', {
            authorName: sessionStorage.getItem("nickName"),
            teamAName: sessionStorage.getItem("favoriteTeam"),
            content: content.value,
            matchDate: matchDate.value,
            matchTime: matchTime.value,
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