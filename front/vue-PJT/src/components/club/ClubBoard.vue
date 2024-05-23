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
          :class="['btn', showChat ? favoriteTeamButtonClass : 'btn-light']"
        >
          수다
        </button>
        <button
          @click="showRecruitBoard"
          :class="['btn', showRecruit ? favoriteTeamButtonClass : 'btn-light']"
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
              <tr v-for="(item, index) in paginatedChatItems" :key="index">
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
        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1" class="btn btn-sm btn-secondary">이전</button>
          <button
            v-for="page in totalPages"
            :key="page"
            @click="goToPage(page)"
            :class="['btn btn-sm', page === currentPage ? favoriteTeamButtonClass : 'btn-light']"
          >
            {{ page }}
          </button>
          <button @click="nextPage" :disabled="currentPage === totalPages" class="btn btn-sm btn-secondary">다음</button>
        </div>
      </div>
      <div v-else-if="showRecruit">
        <div class="table-responsive">
          <table class="table table-hover text-center">
            <thead>
              <tr>
                <th :class="favoriteTeamColorClass">분류</th>
                <th :class="favoriteTeamColorClass">제목</th>
                <th :class="favoriteTeamColorClass">작성자</th>
                <th :class="favoriteTeamColorClass">작성일</th>
                <th :class="favoriteTeamColorClass">총 인원</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in paginatedRecruitItems" :key="index">
                <td>[모집]</td>
                <td 
                  @click="goToDetail(item.recruitmentId)" 
                  class="click"
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
        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1" class="btn btn-sm btn-secondary">이전</button>
          <button
            v-for="page in totalPages"
            :key="page"
            @click="goToPage(page)"
            :class="['btn btn-sm', page === currentPage ? favoriteTeamButtonClass : 'btn-light']"
          >
            {{ page }}
          </button>
          <button @click="nextPage" :disabled="currentPage === totalPages" class="btn btn-sm btn-secondary">다음</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from "vue";
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

// 페이지네이션 변수 설정
const currentPage = ref(1);
const itemsPerPage = 10;

// 좋아하는 팀 색상 클래스 설정
const favoriteTeamColorClass = ref(getTeamColorClass(favoriteTeam));
const favoriteTeamButtonClass = ref(getTeamColorClass(favoriteTeam, 'btn'));

// 클래스 값 확인을 위한 콘솔 로그
console.log('favoriteTeamColorClass:', favoriteTeamColorClass.value);
console.log('favoriteTeamButtonClass:', favoriteTeamButtonClass.value);

// 페이지네이션 계산
const totalPages = computed(() => {
  if (showChat.value) {
    return Math.ceil(store.chatItems.length / itemsPerPage);
  } else if (showRecruit.value) {
    return Math.ceil(recruitStore.recruitItems.length / itemsPerPage);
  }
  return 1;
});

const paginatedChatItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return store.chatItems.slice(start, end);
});

const paginatedRecruitItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  return recruitStore.recruitItems.slice(start, end);
});

const showChatBoard = () => {
  if (!showChat.value) {
    showChat.value = true;
    showRecruit.value = false;
    currentPage.value = 1; // 페이지 초기화
  }
};

const showRecruitBoard = () => {
  if (!showRecruit.value) {
    showRecruit.value = true;
    showChat.value = false;
    currentPage.value = 1; // 페이지 초기화
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

// 페이지네이션 함수
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

const goToPage = (page) => {
  currentPage.value = page;
};

// Apply dynamic styles function
const applyDynamicStyles = () => {
  const style = document.createElement('style');
  style.type = 'text/css';
  const colorClass = getTeamColorClass(favoriteTeam).replace('.', '');
  style.innerHTML = `
    .btn.active {
      background-color: var(--${colorClass}-bg) !important;
      color: var(--${colorClass}-text) !important;
    }
    .btn-secondary.active {
      background-color: var(--${colorClass}-bg) !important;
      color: var(--${colorClass}-text) !important;
    }
  `;
  document.head.appendChild(style);
};

// Apply team color classes to items
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

  // Apply dynamic styles
  applyDynamicStyles();
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
  box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.3) !important;
}

td {
  font-weight: bold;
}

th {
  font-weight: bolder !important;
}

/* 팀별로 동적으로 스타일 적용 */
.mancity-btn.active {
  --mancity-bg: #6CABDD;
  --mancity-text: #fff;
}

.manutd-btn.active {
  --manutd-bg: #DA291C;
  --manutd-text: #fff;
}

.liverpool-btn.active {
  --liverpool-bg: #C8102E;
  --liverpool-text: #fff;
}

.chelsea-btn.active {
  --chelsea-bg: #034694;
  --chelsea-text: #fff;
}

.arsenal-btn.active {
  --arsenal-bg: #EF0107;
  --arsenal-text: #fff;
}

.tottenham-btn.active {
  --tottenham-bg: #132257;
  --tottenham-text: #fff;
}

/* 팀 색상 반영 */
.team-bg-active {
  background-color: var(--mancity-bg) !important;
  color: var(--mancity-text) !important;
}

/* 비활성화된 버튼의 기본 색상 */
.btn-light {
  background-color: #d6d6d6 !important; /* 밝은 회색 */
  color: #333 !important; /* 어두운 회색 */
}

.btn {
  transition: background-color 0.3s, color 0.3s;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 1rem;
}

.pagination .btn {
  margin: 0 0.25rem;
}
</style>
