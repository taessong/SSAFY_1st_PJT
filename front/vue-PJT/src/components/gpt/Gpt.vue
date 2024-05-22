<template>
  <div class="chat-container">
    <!-- Button trigger modal -->
    <div class="input-container">
      <input type="text" v-model="question" placeholder="질문을 입력하세요...">
      <button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="handleButtonClick">📢</button>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" v-if="answer !== ''">
            {{ answer }}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Understood</button>
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

const handleButtonClick = async () => {
  try {
    const response = await axios.post("/gpt/v1/chat/completions", {
      model: "gpt-3.5-turbo",
      messages: [{
        role: "user",
        content: question.value
      }],
      max_tokens: 100
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
body {
  margin: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.chat-container {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.input-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50%;
}

.input-container input[type="text"] {
  width: 70%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.input-container button {
  margin-left: 10px;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
}
</style>
