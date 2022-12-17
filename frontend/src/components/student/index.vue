<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <ul class="list">
          <li class="logo"><i class="iconfont icon-kaoshi"></i><span>Exam-Online</span></li>
          <li><a href="javascript:" @click="exam">我的试卷</a></li>
          <li><a href="javascript:" @click="practise">我的练习</a></li>
          <li><router-link to="/scoreTable">我的分数</router-link></li>
          <li><router-link to="/message">给我留言</router-link></li>
          <li><a href="javascript:">待定</a></li>
          <li class="right" @mouseenter="globalstate.flag = !globalstate.flag" @mouseleave="globalstate.flag = !globalstate.flag">
            <a href="javascript:"><i class="iconfont icon-Userselect icon"></i>{{data.user.name}}</a>
            <div class="msg" v-if="globalstate.flag">
              <p>管理中心</p>
              <p class="exit" @click="exit">退出</p>
            </div>
          </li>
        </ul>
      </el-col>
    </el-row>

    <div class="main">
      <router-view/>
    </div>

    <Footer/>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {logout} from "@/api";
import {useRouter} from "vue-router";
import {useGlobalState} from "@/store";
import Footer from "@/components/student/Footer.vue";

const router = useRouter()
const data = reactive({
  flag: false,
  user: {} as Student
})

const globalstate = useGlobalState()

function exit() {
  logout()
  router.replace({path: "/"})
}

function practise() {
  globalstate.isPractice = true
  router.replace({path: "/startExam"})
}

function exam() {
  globalstate.isPractice = false
  router.replace({path: "/student"})
}

onMounted(() => {
  data.user.name = localStorage.getItem("username") || "no such user"
  data.user.id = parseInt(localStorage.getItem("userid") || "0")
})
</script>

<style lang="scss" scoped>
#student {
  .padding-50 {
    margin: 0 auto;
    padding: 0 50px;
    box-shadow: 0 0 10px 4px rgba(1,149,255,0.1);
    background-color: #fff;
  }

  .list {
    display: flex;

    a {
      text-decoration: none;
      color: #334046;
    }

    li {
      list-style: none;
      height: 60px;
      line-height: 60px;
      padding: 0 20px;
      cursor: pointer;

      &:hover {
        background: #0195ff;
        transition: all 2s ease;

        a {
          color: #fff;
        }
      }
    }

    .logo {
      display: flex;
      font-weight: bold;
      color: #2f6c9f;

      i {
        font-size: 50px;
      }
    }

    .right {
      margin-left: auto;
      position: relative;

      .icon {
        margin-right: 6px;
      }

      .msg {
        text-align: center;
        position: absolute;
        top: 60px;
        left: 0;
        display: flex;
        flex-direction: column;
        border-radius: 2px;
        border-bottom: 3px solid #0195ff;
        background: #fff;

        .msg {
          p {
            height: 40px;
            line-height: 40px;
            width: 105px;

            &:hover {
              background: #0195ff;
            }
          }
        }
      }
    }
  }

}
</style>