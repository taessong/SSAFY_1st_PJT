<template>
    <div class="chat-container">
        <!-- Button trigger modal -->
        <input type="text" v-model="question" placeholder="질문을 입력하세요...">
        <button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="handleButtonClick">📢</button>

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
  .chat-container {
    position: relative;
    width: 100%;
    height: 100vh;
    overflow-y: auto;
  }
  
  .input-container {
    position: fixed;
    bottom: 20px;
    right: 20px;
    display: flex;
    align-items: center;
  }
  
  .input-container input[type="text"] {
    width: calc(100% - 40px);
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
  
  .answer-container {
    position: fixed;
    bottom: 60px; /* 입력 창 위에 겹치지 않도록 위치 조정 */
    right: 20px;
    margin-top: 20px;
  }
  </style>
  