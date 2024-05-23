<template>
  <div class="regist-container">
    <fieldset class="fieldset-border">
      <legend class="legend-text">매치 정보 입력</legend>
      <!--매칭정보입력-->
      <div class="row">
        <div class="col-md-8">
          <div class="form-group">
            <label for="content" class="form-label">매치 내용 :</label>
            <textarea id="content" v-model="match.content" class="form-control" rows="5"></textarea>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="matchDate" class="form-label">경기 날짜 선택 :</label>
            <input type="date" id="matchDate" v-model="match.matchDate" class="form-control date-input">
          </div>
          <div class="form-group">
            <label for="matchTime" class="form-label">경기 시간 입력 :</label>
            <input type="time" id="matchTime" v-model="match.matchTime" class="form-control time-input">
          </div>
        </div>
      </div>
      <!--경기장선택-->
      <div class="row mt-4">
        <div class="col-md-9">
          <div>
            <h3>경기장</h3>
          </div>
          <div class="row mt-3">
            <div class="col-md-6 carousel-container" style="width: 50%;">
              <div id="carouselExampleCaptions1" class="carousel slide">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/가오풋살장/가오풋살장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>가오풋살장</h6>
                      <h6>대전 동구 가오동 7-9</h6>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/가오풋살장/가오풋살장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>가오풋살장</h6>
                      <h6>대전 동구 가오동 7-9</h6>
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
              <div class="text-center mt-2">
                <button @click="selectStadium('가오풋살장')" class="btn btn-outline-primary">✅</button>
              </div>
            </div>
            <div class="col-md-6 carousel-container" style="width: 50%;">
              <div id="carouselExampleCaptions2" class="carousel slide">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>남선공원풋살장</h6>
                      <h6>대전 서구 남선로 66</h6>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/남선공원풋살장/남선공원풋살장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>남선공원풋살장</h6>
                      <h6>대전 서구 남선로 66</h6>
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
              <div class="text-center mt-2">
                <button @click="selectStadium('남선공원풋살장')" class="btn btn-outline-primary">✅</button>
              </div>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-md-6 carousel-container" style="width: 50%;">
              <div id="carouselExampleCaptions3" class="carousel slide">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>대전가장풋살존</h6>
                      <h6>대전 서구 괴정로198번길 43 21-3</h6>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/대전가장풋살존/대전가장풋살존(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>대전가장풋살존</h6>
                      <h6>대전 서구 괴정로198번길 43 21-3</h6>
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
              <div class="text-center mt-2">
                <button @click="selectStadium('대전가장풋살존')" class="btn btn-outline-primary">✅</button>
              </div>
            </div>
            <div class="col-md-6 carousel-container" style="width: 50%;">
              <div id="carouselExampleCaptions4" class="carousel slide">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img src="@/asset/stadium/유성풋살구장/유성풋살구장(1).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>유성풋살구장</h6>
                      <h6>대전 유성구 유성대로713번길 83</h6>
                    </div>
                  </div>
                  <div class="carousel-item">
                    <img src="@/asset/stadium/유성풋살구장/유성풋살구장(2).png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                      <h6>유성풋살구장</h6>
                      <h6>대전 유성구 유성대로713번길 83</h6>
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
              <div class="text-center mt-2">
                <button @click="selectStadium('유성풋살구장')" class="btn btn-outline-primary">✅</button>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div>
            <h3>선택된 경기장</h3>
          </div>
          <div class="selected-stadium mt-3 p-3">
            <div v-if="selectedStadiumName">
              <p><strong>선택된 경기장 :</strong> {{ selectedStadiumName }}</p>
              <p><strong>주소 :</strong> {{ selectedStadiumAddress }}</p>
            </div>
            <!--등록버튼-->
            <div class="btn-container mt-4">
              <button @click="createMatch" class="btn btn-outline-success w-100">등록하기</button>
            </div>
          </div>
        </div>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { useMatchStore } from '@/stores/match';
import { ref } from 'vue';

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
const store = useMatchStore();

const selectStadium = (stadiumName) => {
  if (stadiumName === '가오풋살장') {
    selectedStadiumName.value = '가오풋살장';
    selectedStadiumAddress.value = '대전 동구 가오동 7-9';
    selectedStadiumId.value = 1;
  } else if (stadiumName === '남선공원풋살장') {
    selectedStadiumName.value = '남선공원풋살장';
    selectedStadiumAddress.value = '대전 서구 남선로 66';
    selectedStadiumId.value = 2;
  } else if (stadiumName === '대전가장풋살존') {
    selectedStadiumName.value = '대전가장풋살존';
    selectedStadiumAddress.value = '대전 서구 괴정로198번길 43 21-3';
    selectedStadiumId.value = 3;
  } else if (stadiumName === '유성풋살구장') {
    selectedStadiumName.value = '유성풋살구장';
    selectedStadiumAddress.value = '대전 유성구 유성대로713번길 83';
    selectedStadiumId.value = 4;
  }
};

const createMatch = () => {
  match.value.stadiumId = selectedStadiumId.value;
  console.log('Match created:', match.value);
  store.createMatch(match);
};
</script>

<style scoped>
.carousel-container {
  padding: 15px;
}

.carousel-caption {
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  border-radius: 5px;
  padding: 5px;
  bottom: 10px;
}

.regist-container {
  padding: 50px 100px;
}

.fieldset-border {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.legend-text {
  font-weight: bold;
}

.date-input, .time-input {
  width: 100%;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 15px;
}

.selected-stadium {
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.btn-container .btn {
  width: 100%;
}

.form-group {
  margin-bottom: 20px;
}
</style>
