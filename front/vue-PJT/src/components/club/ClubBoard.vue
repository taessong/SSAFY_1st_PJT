<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 :class="favoriteTeamColorClass" v-if="showChat">수다 게시판</h2>
      <h2 :class="favoriteTeamColorClass" v-if="showRecruit">모집 게시판</h2>
      <button
        v-if="showChat"
        @click="gotoChatRegist"
        :class="['btn', favoriteTeamButtonClass]"
      >
        수다 글 등록하기
      </button>
      <button
        v-if="showRecruit"
        @click="gotoRecriutRegist"
        :class="['btn', favoriteTeamButtonClass]"
      >
        모집 글 등록하기
      </button>
    </div>
    <div>
      <div class="btn-group mb-4">
        <button
          @click="showChatBoard"
          :class="['btn', 'btn-secondary', { active: showChat }]"
        >
          수다
        </button>
        <button
          @click="showRecruitBoard"
          :class="['btn', 'btn-secondary', { active: showRecruit }]"
        >
          모집
        </button>
      </div>
      <div v-if="showChat">
        <div class="table-responsive">
          <table class="table table-hover text-center">
            <thead>
              <tr>
                <th :class="favoriteTeamColorClass">분류</th>
                <th :class="favoriteTeamColorClass">제목</th>
                <th :class="favoriteTeamColorClass">작성자</th>
                <th :class="favoriteTeamColorClass">작성일</th>
                <th :class="favoriteTeamColorClass">조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in store.chatItems" :key="index">
                <td>[수다]</td>
                <td 
                  @click="selectPostAndNavigate(item)" 
                  class="click"
                >
                  {{ item.title }}
                </td>
                <td>{{ item.authorName }}</td>
                <td>{{ item.regDate }}</td>
                <td>{{ item.viewCnt }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-else-if="showRecruit">
        <div class="table-responsive">
          <table class="table table-hover text-center">
            <thead>
              <tr>
                <th>분류</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>총 인원</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in recruitStore.recruitItems" :key="index">
                <td>[모집]</td>
                <td 
                  @click="goToDetail(item.recruitmentId)" 
                  class="click"
                  :class="item.colorClass"
                >
                  {{ item.content }}
                </td>
                <td>{{ item.authorName }}</td>
                <td>{{ item.regDate }}</td>
                <td>{{ item.maxMembers }}인</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useClubStore } from "@/stores/club";
import { useRecruitStore } from "@/stores/recruit";
import { useRouter, useRoute } from "vue-router";
import { getTeamColorClass } from "@/utils/teamColors";

//store를 사용해서 불러오기
const store = useClubStore();
const recruitStore = useRecruitStore();

//router 사용
const router = useRouter();
const route = useRoute();

// 세션 스토리지에서 favoriteTeam 가져오기
const favoriteTeam = sessionStorage.getItem('favoriteTeam');

// 수다, 모집게시판 분류
const showChat = ref(true);
const showRecruit = ref(false);

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamButtonClass:', favoriteTeamButtonClass.value);

const showChatBoard = () => {
  if (!showChat.value) {
    showChat.value = true;
    showRecruit.value = false;
  }
};

const showRecruitBoard = () => {
  if (!showRecruit.value) {
    showRecruit.value = true;
    showChat.value = false;
  }
};

const gotoChatRegist = () => {
  router.push({ name: 'clubRegist' });
}

const gotoRecriutRegist = () => {
  router.push({ name: 'recruitRegist' });
}

const selectPostAndNavigate = (item) => {
  router.push({ name: "clubDetail", params: { id: item.postId } });
};

const goToDetail = (id) => {
  router.push({ name: "recruitDetail", params: { id } });
};

const applyTeamColorClasses = (items) => {
  return items.map(item => {
    item.colorClass = (item.teamName === favoriteTeam) ? getTeamColorClass(item.teamName) : '';
    console.log(`Team: ${item.teamName}, Color Class: ${item.colorClass}`);
    return item;
  });
};

onMounted(() => {
  store.fetchChatData().then(() => {
    store.chatItems = applyTeamColorClasses(store.chatItems);
  });
  recruitStore.fetchRecruitData().then(() => {
    recruitStore.recruitItems = applyTeamColorClasses(recruitStore.recruitItems);
  });
  
  // URL 매개변수에 따라 초기 상태 설정
  if (route.query.tab === 'chat') {
    showChatBoard();
  } else if (route.query.tab === 'recruit') {
    showRecruitBoard();
  }
});
</script>

<style scoped>
.click {
  cursor: pointer;
}

h2 {
  font-weight: bolder;
}

.active {
  background-color: #ccc !important;
  box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.3) !important;
}

td {
  font-weight: bold;
}

th {
  font-weight: bolder !important;
}

.btn.active {
  background-color: #6CABDD !important; /* 기본 색상을 맨시티 색상으로 설정 */
  color: #fff !important;
}

.mancity-btn.active {
  background-color: #6CABDD !important;
}

.manutd-btn.active {
  background-color: #DA291C !important;
}

.liverpool-btn.active {
  background-color: #C8102E !important;
}

.chelsea-btn.active {
  background-color: #034694 !important;
}

.arsenal-btn.active {
  background-color: #EF0107 !important;
}

.tottenham-btn.active {
  background-color: #132257 !important;
}
</style>
