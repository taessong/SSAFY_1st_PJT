<template>
    <div class="container">
        <h2>보드야보드~</h2>
        <div class="club-board">
            <div class="board-header">
                <button class="filter-btn">수다</button>
                <button class="filter-option">모집</button>
            </div>
            <div class="board-content-wrapper">
                <div class="board-content">
                    <table>
                        <thead>
                            <tr>
                                <th>분류</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성 일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="a in 3" :key="a">
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
axios.defaults.withCredentials = true;

const items = ref([]);

const fetchData = async () => {
    try {
        console.log(sessionStorage.getItem("favoriteTeam"))
        const response = await axios.get('http://localhost:8080/community/board');
        items.value = response.data;
        console.log(response.data)
    } catch (error) {
        console.error('Failed to fetch data:', error);
    }
};

onMounted(() => {
    fetchData();
});
</script>

<style scoped>
.container {
    padding: 20px;
}

h2 {
    margin-bottom: 20px;
}

.club-board {
    width: 100%;
    border: 1px solid black;
    border-radius: 6px;
    padding: 10px;
    box-sizing: border-box;
}

.board-header {
    display: flex;
    justify-content: flex-start;
    margin-bottom: 10px;
}

.filter-btn,
.filter-option {
    padding: 5px 10px;
    border: none;
    background-color: #eee;
    border-radius: 4px;
    cursor: pointer;
    margin-right: 5px;
}

.board-content-wrapper {
    overflow: hidden;
    border-radius: 6px;
}

.board-content {
    border-collapse: collapse;
    width: 100%;
}

.board-content th,
.board-content td {
    padding: 10px;
    text-align: center;
}

.board-content th {
    background-color: #f9f9f9;
}

.board-content tr {
    background-color: #fff;
    transition: background-color 0.3s;
}

.board-content tr:hover {
    background-color: #f1f1f1;
}

.board-content-wrapper table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
}

.board-content-wrapper th:first-child,
.board-content-wrapper td:first-child {
    border-left: none;
}

.board-content-wrapper th:last-child,
.board-content-wrapper td:last-child {
    border-right: none;
}

.board-content-wrapper th {
    border-top: none;
}

.board-content-wrapper td {
    border-bottom: none;
}

.board-content-wrapper th:first-child {
    border-top-left-radius: 6px;
}

.board-content-wrapper th:last-child {
    border-top-right-radius: 6px;
}

.board-content-wrapper tr:last-child td:first-child {
    border-bottom-left-radius: 6px;
}

.board-content-wrapper tr:last-child td:last-child {
    border-bottom-right-radius: 6px;
}
</style>
