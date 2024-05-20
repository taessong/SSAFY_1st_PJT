<template>
    <h2>매치 디테일</h2>
    <div>
        <table>
        <thead>
            <tr>
                <th>분류</th>
                <th>내용</th>
                <th>팀</th>
                <th>작성자</th>
                <th>경기일자</th>
            </tr>
        </thead>
        <tbody>
            <tr v-if="detail">
            <td>{{ detail.teamBId ? '[마감]' : '[모집]' }}</td>
            <td>{{ detail.content }}</td>
            <td>{{ detail.authorName }}</td>
            <td>{{ detail.regDate }}</td>
            <td>{{ detail.matchDate }}</td>
            </tr>
        </tbody>
        </table>
        <div v-if="detail.stadiumImg">
            <p>경기장</p>
            <img src="@/asset/stadium/가오풋살장/가오풋살장(1).png">
            <img :src="getStadiumImage(detail.stadiumImg)">
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";

const detail = ref([]);
const route = useRoute();

const detailPost = async (id) => {
    try {
    const response = await axios.get(
        `http://localhost:8080/futsal/match/${id}`
    );
    detail.value = response.data;
    console.log(response.data);
    } catch (error) {
    console.error(error);
    }
};

const getStadiumImage = (imageName) => {
    console.log(imageName);
    return new URL(`@/asset/stadium/${imageName}`, import.meta.url).href;
};

onMounted(() => {
    detailPost(route.params.id)
});
</script>

<style>
img {
  max-width: 100%;
  height: auto;
}
</style>