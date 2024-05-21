<template>
  <div>
    <div class="head">로그인</div>
    <form @submit.prevent="handleSubmit">
      <fieldset class="login-form">
        <h1>
          Run! <br />
          On Ground
        </h1>
        <div class="form">
          <label for="userId">아이디</label>
          <input
            type="text"
            id="userId"
            name="userId"
            v-model="form.userId"
            class="inputText"
            maxlength="20"
            placeholder="아이디를 입력하세요."
          />
        </div>
        <div class="form">
          <label for="passsword">비밀번호</label>
          <input
            type="text"
            id="password"
            name="password"
            v-model="form.password"
            class="inputText"
            placeholder="비밀번호를 입력하세요."
          />
        </div>

        <div class="sign">
          <button type="submit">로그인</button>
          <button type="button" @click="gotoSignupPage">회원가입</button>
        </div>
      </fieldset>
    </form>
  </div>
</template>

<script setup>
import { nextTick, onMounted, ref, watch } from "vue";
import axios from "@/api/axios";
import { useRouter } from "vue-router";

//axios를 통해 데이터를 내보낼 폼
const form = ref({
  //로그인의 경우 아이디, 비밀번호만.
  userId: "",
  password: "",
});

const nickName = ref(null);
const router = useRouter();

//눌렀을 때 회원가입 페이지로 이동 시키기
const gotoSignupPage = async () => {
  router.push("/signup");
};

//로그인을 누르면 비교하여 성공시에 메인 페이지로 이동시키기
const handleSubmit = async () => {
  //로그인 유저 폼
  const loginUser = {
    userId: form.value.userId,
    password: form.value.password,
  };

  // 아이디나 비밀번호가 비어 있는지 확인
  if (!loginUser.userId && !loginUser.password) {
    alert("아이디와 비밀번호를 입력해주세요.");
    return;
  } else if (!loginUser.userId) {
    alert("아이디를 입력해주세요.");
    return;
  } else if (!loginUser.password) {
    alert("비밀번호를 입력해주세요.");
    return;
  }

  try {
    const response = await axios.post("/user/", loginUser);
    //성공시 세션에 닉네임과 선호팀을 저장
    if (response.status === 200) {
      sessionStorage.setItem("nickName", response.data.nickName);
      sessionStorage.setItem("favoriteTeam", response.data.favoriteTeam);
      //메인페이지로...
      getData();
      alert("로그인 성공! 환영합니다!");
      await nextTick();
      router.push("/main").then(() => {
        window.location.reload();
      });
    }
  } catch (error) {
    alert("아이디 혹은 비밀번호가 틀렸습니다. 다시 입력해주세요.");

    console.log(error);
  }
};

async function getData() {
  try {
    const res = await axios.get('/user/sessionCheck')
    console.log("Data", res.data);
  } catch (error) {
    console.log("에러당", error)
  }
}
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
  color: #70ace3;
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
  font-size: 16px;
  font-weight: 800;
}

button {
  border: 2px solid white;
  border-radius: 6px;
  padding: 10px;
  text-align: center;
  width: 325px;
  margin: 3px;
  background-color: black;
  color: white;
  font-weight: 900;
  cursor: pointer;
}

.sign {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 60px;
  margin-bottom: 30px;
}
</style>