<template>
  <div class="container mt-5">
    <div class="head text-center mb-4">로그인</div>
    <form @submit.prevent="handleSubmit" class="mx-auto" style="max-width: 500px;">
      <fieldset class="login-form p-4 border rounded shadow">
        <h1 class="text-center mb-4">Run! <br /> On Ground</h1>
        <div class="form-group mb-3">
          <label for="userId" class="form-label">아이디</label>
          <input
            type="text"
            id="userId"
            name="userId"
            v-model="form.userId"
            class="form-control"
            maxlength="20"
            placeholder="아이디를 입력하세요."
          />
        </div>
        <div class="form-group mb-4">
          <label for="password" class="form-label">비밀번호</label>
          <input
            type="password"
            id="password"
            name="password"
            v-model="form.password"
            class="form-control"
            placeholder="비밀번호를 입력하세요."
          />
        </div>

        <div class="sign d-flex justify-content-between">
          <button type="submit" class="btn btn-primary w-45">로그인</button>
          <button type="button" @click="gotoSignupPage" class="btn btn-secondary w-45">회원가입</button>
        </div>
      </fieldset>
    </form>
  </div>
</template>

<script setup>
import { nextTick, ref } from "vue";
import axios from "@/api/axios";
import { useRouter } from "vue-router";

//axios를 통해 데이터를 내보낼 폼
const form = ref({
  //로그인의 경우 아이디, 비밀번호만.
  userId: "",
  password: "",
});

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
  font-size: 32px;
  font-weight: 800;
}

.login-form h1 {
  font-size: 2rem;
  line-height: 1.2;
}

.sign button {
  width: 48%;
}
</style>
