<template>
    <header class="header-bar">
        <RouterLink :to="{name : 'login'}"><h3>RunOnGround</h3></RouterLink>
    </header>
</template>

<!-- 여기에 나중에 로그인 여부 확인해서, 로그인 x일시 로고만, 아닐 시 전부 띄우게 해야함-->

<script setup>
</script>

<style scoped>

    .header-bar {
        background-color: black;
        padding: 1px;
    }

    h3 {
        color: white;
        margin-left: 10px;
    }
    a {
        text-decoration-line: none;
    }
</style>