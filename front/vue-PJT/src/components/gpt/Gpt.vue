<template>
  <div class="chat-container">
    <!-- Button trigger modal -->
    <div class="input-container">
      <button type="button" class="nav-button shoes-button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="shoesClick">👟</button>
      <button type="button" class="nav-button stretch-button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="stretchClick">🤸🏼‍♂️</button>
      <button type="button" class="nav-button etiquette-button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="etiquetteClick">🙅🏻‍♂️</button>
    </div>
    
    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">🤖</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" v-if="answer !== ''">
            {{ answer }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
import { ref } from 'vue';

const question = ref('');
const answer = ref('');
const apiKey = import.meta.env.VITE_GPT_API_KEY;

const resetAnswer = () => {
  answer.value = '';
};

const shoesClick = async () => {
  resetAnswer();
  try {
    const response = await axios.post("/gpt/v1/chat/completions", {
      model: "gpt-3.5-turbo",
      messages: [{
        role: "user",
        content: "20,30대가 쓰기 좋은 풋살화 추천해줘. 반말모드로 해줘. 내가 뭔가를 물어볼때마다 처음 답변하는 것처럼 대답해줘. 제품 이름을 꼭 알려줘."
      }],
      max_tokens: 1000
    }, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${apiKey}`
      }
    });
    answer.value = response.data.choices[0].message.content;
  } catch (error) {
    console.error(error);
  }
}

const stretchClick = async () => {
  resetAnswer();
  try {
    const response = await axios.post("/gpt/v1/chat/completions", {
      model: "gpt-3.5-turbo",
      messages: [{
        role: "user",
        content: "내가 질문할때마다 처음 질문 받은 것처럼 반말로 친구처럼 대답해. 풋살하기 전에 할 스트레칭 추천해줘."
      }],
      max_tokens: 1000
    }, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${apiKey}`
      }
    });
    answer.value = response.data.choices[0].message.content;
  } catch (error) {
    console.error(error);
  }
}

const etiquetteClick = async () => {
  resetAnswer();
  try {
    const response = await axios.post("/gpt/v1/chat/completions", {
      model: "gpt-3.5-turbo",
      messages: [{
        role: "user",
        content: "내가 질문할때마다 처음 질문 받은 것처럼 반말로 친구처럼 대답해. 풋살하면서 지켜야 할 에티켓 알려줘."
      }],
      max_tokens: 1000
    }, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${apiKey}`
      }
    });
    answer.value = response.data.choices[0].message.content;
  } catch (error) {
    console.error(error);
  }
}
</script> 

<style scoped>
.nav-button {
  position: fixed;
  right: 0;
  z-index: 1000; /* 다른 요소 위에 표시되도록 설정 */
  border: none;
  color: white;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px 0 0 5px;
}

.shoes-button {
  top: 40%; /* 상단에서부터의 위치 */
  margin-right: 60px;
}

.stretch-button {
  top: 45%; /* 상단에서부터의 위치 */
  margin-right: 60px;
}

.etiquette-button {
  top: 50%;
  margin-right: 60px;
}

.nav-button:hover {
  filter: brightness(85%); /* 버튼 호버 시 색상 변경 */
}
</style>
