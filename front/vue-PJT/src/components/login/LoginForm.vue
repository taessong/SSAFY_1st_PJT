<template>
  <div>
    <div class="head"> 로그인 </div>
    <fieldset class="login-form">
      <h1>
        Run! <br />
        On Ground
      </h1>
      <div class="form">
        <label for="id">아이디</label>
        <input type="text" id="userId" class="inputText" maxlength="20" placeholder="아이디를 입력하세요." />
      </div>

      <div class="form">
        <label for="passsword">비밀번호</label>
        <input type="text" id="userPassword" class="inputText" placeholder="비밀번호를 입력하세요." />
      </div>

      <div class="sign">
        <RouterLink :to="{name: 'main'}">로그인</RouterLink>
        <RouterLink :to="{name : 'signup'}">회원 가입</RouterLink>
      </div>
    </fieldset>
  </div>
</template>

<script>
</script>

<style scoped>
.head {
    text-align: center;
    font-size: 32px;
    font-weight: 800;
    padding: 20px;
}
.login-form {
  align-items: center;
  width: 50%;
  margin-left: auto;
  margin-right: auto;
  border-radius: 6px;
  height: fit-content;
  margin-bottom: 30px;
  border: 2px solid black;
}

h1 {
  text-align: center;
  font-size: 60px;
}

.form {
    display: flex;
    flex-direction: column;
    padding: 25px;
}

label {
    text-align: center;
    padding: 15px;
    font-size: x-large;
    color: #70ACE3;
    font-weight: 800;
}

.inputText {
    border-radius: 6px;
    padding: 10px;
    width: 300px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    border: 2px solid black;
}

a {
    text-decoration-line: none;
    border: 2px solid #70ACE3;
    border-radius: 6px;
    padding: 10px;
    text-align: center;
    width: 300px;
    margin: 8px;
    background-color: black;
    color: white;
    font-weight: 900;
}

.sign {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 60px;
    margin-bottom: 30px;
}


</style>