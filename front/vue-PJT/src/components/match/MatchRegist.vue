<template>
  <div>
    <h2 class="page-title">매치 등록 페이지</h2>
    <fieldset class="match-info">
      <legend class="legend-title">매치 정보 입력</legend>
      <div class="input-group">
        <label for="content" class="input-label">매치 내용 :</label>
        <textarea id="content" v-model="match.content" class="text-input"></textarea>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="input-group">
            <label for="matchDate" class="input-label">경기 날짜 선택 :</label>
            <input type="date" id="matchDate" v-model="match.matchDate" class="date-input">
          </div>
        </div>
        <div class="col-md-6">
          <div class="input-group">
            <label for="matchTime" class="input-label">경기 시간 입력 :</label>
            <input type="time" id="matchTime" v-model="match.matchTime" class="time-input">
          </div>
        </div>
      </div>

      <div class="stadium-container">
        <h2>경기장 선택</h2>
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <div id="carouselExampleCaptions1" class="carousel slide">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleCaptions1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleCaptions1" data-bs-slide-to="1" aria-label="Slide 2"></button>
                </div>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/가오풋살장/가오풋살장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>가오풋살장</h5>
                      <p>대전 동구 가오동 7-9</p>
                      <button @click="selectStadium('가오풋살장')">Select</button>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/가오풋살장/가오풋살장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>가오풋살장</h5>
                      <p>대전 동구 가오동 7-9</p>
                      <button @click="selectStadium('가오풋살장')">Select</button>
                    </div>
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions1" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions1" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>
            </div>
            <div class="col-md-6">
              <div id="carouselExampleCaptions2" class="carousel slide">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide-to="1" aria-label="Slide 2"></button>
                </div>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>남선공원풋살장</h5>
                      <p>대전 서구 남선로 66</p>
                      <button @click="selectStadium('남선공원풋살장')">Select</button>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>남선공원풋살장</h5>
                      <p>대전 서구 남선로 66</p>
                      <button @click="selectStadium('남선공원풋살장')">Select</button>
                    </div>
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions2" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div id="carouselExampleCaptions3" class="carousel slide">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide-to="1" aria-label="Slide 2"></button>
                </div>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>대전가장풋살존</h5>
                      <p>대전 서구 괴정로198번길 43 21-3</p>
                      <button @click="selectStadium('대전가장풋살존')">Select</button>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>대전가장풋살존</h5>
                      <p>대전 서구 괴정로198번길 43 21-3</p>
                      <button @click="selectStadium('대전가장풋살존')">Select</button>
                    </div>
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions3" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>
            </div>
            <div class="col-md-6">
              <div id="carouselExampleCaptions4" class="carousel slide">
                <div class="carousel-indicators">
                  <button type="button" data-bs-target="#carouselExampleCaptions4" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                  <button type="button" data-bs-target="#carouselExampleCaptions4" data-bs-slide-to="1" aria-label="Slide 2"></button>
                </div>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/유성풋살구장/유성풋살구장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>유성풋살구장</h5>
                      <p>대전 유성구 유성대로713번길 83</p>
                      <button @click="selectStadium('유성풋살구장')">Select</button>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/유성풋살구장/유성풋살구장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h5>유성풋살구장</h5>
                      <p>대전 유성구 유성대로713번길 83</p>
                      <button @click="selectStadium('유성풋살구장')">Select</button>
                    </div>
                  </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions4" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions4" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <div v-if="selectedStadiumName">
          <p>선택된 경기장 : {{ selectedStadiumName }}</p>
          <p>주소 : {{ selectedStadiumAddress }}</p>
        </div>
      </div>

      <div class="btn-container">
        <button @click="createMatch" class="btn btn-outline-success">등록하기</button>
      </div>

    </fieldset>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMatchStore } from '@/stores/match';

const store = useMatchStore();

const match = ref({
    authorName: sessionStorage.getItem("nickName"),
    teamAName: sessionStorage.getItem("favoriteTeam"),
    content: '',
    matchDate: '',
    matchTime: '',
    stadiumId: 0
});

const selectedStadiumName = ref('');
const selectedStadiumAddress = ref('');
const selectedStadiumId = ref(0);

// 경기장 이름을 선택했다? 그 경기장이 몇번째 인덱스인지 알아야 함.....
const selectStadium = (stadiumName) => {
  for(let i=0; i<store.stadiums.length; i++){
    if(store.stadiums[i].name === stadiumName) {
      selectedStadiumName.value = store.stadiums[i].name;
      selectedStadiumAddress.value = store.stadiums[i].address;
      selectedStadiumId.value = store.stadiums[i].id;
      break;
    }
  }
};

const createMatch = () => {
  console.log(match)
  match.value.stadiumId = parseInt(selectedStadiumId.value);
  store.createMatch(match);
};

</script>

<style scoped>
.btn-container {
  text-align: center; /* 수평 가운데 정렬 */
}

.btn-outline-success {
  width: 1000px;
  display: inline-block;
}

.carousel {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
}

/* 캐러셀 내부 이미지 크기 조정 */
.carousel-inner img {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
}

.row {
  padding: 20px;
}

.carousel-caption {
  background: rgba(0, 0, 0, 0.2); /* 캡션의 배경을 반투명하게 설정 */
  padding: 10px; /* 캡션의 패딩을 추가 */
  border-radius: 5px; /* 캡션의 모서리를 둥글게 설정 */
}

.stadium-container {
  border: 2px solid black; /* 테두리의 두께, 종류 및 색상을 지정합니다. */
}

.page-title {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
}

.match-info {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.input-group {
  margin-bottom: 15px;
}

.input-label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.text-input,
.date-input,
.time-input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.text-input {
  resize: vertical;
}

.date-input,
.time-input {
  font-size: 16px;
}

</style>