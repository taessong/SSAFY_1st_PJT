<template>
  <div class="regist-container">
    <fieldset class="fieldset-border">
      <legend>매치 정보 입력</legend>
      <!--매칭정보입력-->
      <div class="row">
        <div class="col-md-8">
          <div>
            <label for="content">매치 내용 :</label>
            <textarea id="content" v-model="match.content"></textarea>
          </div>
        </div>
        <div class="col-md-4">
          <div>
            <label for="matchDate" class="input-label">경기 날짜 선택 :</label>
            <input type="date" id="matchDate" v-model="match.matchDate" class="date-input">
          </div>
          <div>
            <label for="matchTime" class="input-label">경기 시간 입력 :</label>
            <input type="time" id="matchTime" v-model="match.matchTime" class="time-input">
          </div>
        </div>
      </div>
      <!--경기장선택-->
      <div class="row">
        <div class="col-md-9">
          <div>
            <h3>경기장</h3>
          </div>
          <div>
            <div class="row">
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
            <div class="row">
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
        </div>
        <div class="col-md-3">
          <div>
            <h3>선택된 경기장</h3>
          </div>
          <div>
            <div v-if="selectedStadiumName">
              <p>선택된 경기장 : {{ selectedStadiumName }}</p>
              <p>주소 : {{ selectedStadiumAddress }}</p>
            </div>
            <!--등록버튼-->
            <div>
              <div class="btn-container">
                <button @click="createMatch" class="btn btn-outline-success">등록하기</button>
              </div>
            </div>
          </div>
        </div>
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
.carousel-container {
  padding: 30px;
}

.carousel-caption {
  background-color: rgba(0, 0, 0, 0.2);
  color: white;
  border-radius: 5px;
  padding: 3px;
  bottom: 10px;
}

.regist-container {
  padding-top: 50px;
  padding-left: 300px;
  padding-right: 300px;
}

.fieldset-border {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
}

.date-input, .time-input {
  width: 100%;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 15px;
}
</style>