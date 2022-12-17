<template>
  <div class="score">
    <div class="title">
      <p class="name">计算机网络</p>
      <p class="description">(总分：100分,限时：100分钟)</p>
      <p class="description">学生：大咸鱼</p>
    </div>

    <div class="total">
      <div class="look">
        本次考试成绩
      </div>

      <div class="show">
        <div class="img1" :class="{'img-transform': data.imageShow}">
          <img :src="data.imageSrcs.fail1" alt="哭了" v-if="score < 60"/>
          <img :src="data.imageSrcs.pass1" alt="过了" v-else/>
        </div>

        <div class="number" :class="{'border': data.isTransition}">
          <span>{{score}}</span>
          <span>分数</span>
        </div>

        <div class="img2" :class="{'img-transform': data.imageShow}">
          <img :src="data.imageSrcs.fail2" alt="哭了" v-if="score < 60"/>
          <img :src="data.imageSrcs.pass2" alt="过了" v-else/>
        </div>
      </div>

      <ul class="time">
        <li class="start">
          <span>开始时间</span>
          <span>{{data.startTime}}</span>
        </li>

        <li class="end">
          <span>结束时间</span>
          <span>{{data.endTime}}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {useRoute} from "vue-router";

const route = useRoute()
const data = reactive({
  isTransition: false,
  score: 0,
  imageShow: false,
  imageSrcs: {
    fail1: require("@/assets/image/cry1.gif"),
    fail2: require("@/assets/image/cry2.jpg"),
    pass1: require("@/assets/image/good1.jpg"),
    pass2: require("@/assets/image/good2.gif")
  },

  startTime: "",
  endTime: ""
})

onMounted(() => {
  setTimeout(() => {
    data.isTransition = true
    data.imageShow = true
  }, 1000)

  let score = parseInt(route.query["score"] as string)
  let startTime = route.query["startTime"] as string
  let endTime = route.query["endTime"] as string

  data.score = score
  data.startTime = startTime
  data.endTime = endTime
})
</script>

<style lang="scss" scoped>
.score {
  max-width: 800px;
  margin: 0 auto;
  .title {
    margin: 60px 0 30px 0;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    .name {
      font-size: 26px;
      color: inherit;
      font-weight: 500;
    }

    .description {
      font-size: 14px;
      color: #888;
    }
  }

  .total {
    border: 1px solid #dbdbdb;
    background: #fff;
    padding: 40px;
    .look {
      border-bottom: 1px solid #dbdbdb;
      padding: 0 0 14px 14px;
      color: #36aafd;
    }

    .number {
      opacity: 0;
      border: 6px solid #fff;
      transform: rotate(0deg);
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      margin: 0 auto;
      width: 160px;
      height: 160px;
      border-radius: 50%;
      margin-top: 80px;
      margin-bottom: 80px;
      transition: all 1s ease;

      span:nth-child(1) {
        font-size: 36px;
        font-weight: 600;
      }

      span:nth-child(2) {
        font-size: 14px;
      }
    }

    .number.border {
      border: 6px solid #36aafd !important;
      transition: all 2s ease;
      width: 160px !important;
      height: 160px !important;
      transform: rotate(360deg) !important;
      opacity: 1 !important;
    }
  }

  .show {
    display: flex;
    justify-content: center;
    align-items: center;

    img {
      width: 160px;
      height: 160px;
    }

    .img1.img-transform {
      opacity: 1 !important;
      transform: translateX(30px) !important;
      transition: all 0.6s ease !important;
    }

    .img2.img-transform {
      opacity: 1 !important;
      transform: translateX(-30px) !important;
      transition: all 0.6s ease !important;
    }

    .img1 {
      margin-top: 70px;
      opacity: 0;
      transform: translateX(0px);
      transition: all 0.6s ease;
    }

    .img2 {
      margin-top: 30px;
      opacity: 0;
      transform: translateX(0px);
      transition: all 0.6s ease;
    }


  }

  .time {
    padding: 0 70px;

    li {
      display: flex;
      justify-content: space-around;
      padding-left: 10px;
      margin: 20px 0;

      &:nth-child(1) {
        background: #fcf8e3;
      }

      &:nth-child(2) {
        background: #e9f5e9;
      }
    }
  }
}
</style>