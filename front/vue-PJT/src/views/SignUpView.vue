<template>
  <div>
    <div class="signup">회원 가입</div>
    <fieldset class="signup-form">
        <FileUpload/>
    </fieldset>
  </div>
</template>

<script setup>
import FileUpload from '../components/FileUpload.vue';
</script>

<style scoped>
.signup {
    text-align: center;
    font-size: 32px;
    font-weight: 800;
    padding: 20px;
}

.signup-form{
    align-items: center;
  width: 65%;
  margin-left: auto;
  margin-right: auto;
  border-radius: 6px;
  height: fit-content;
  margin-bottom: 30px;
  border: 2px solid black;
}


</style>
