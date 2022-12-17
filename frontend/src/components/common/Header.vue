<template>
  <header id="topbar">
    <el-row>
      <el-col :span="4" class="topbar-left">
        <i class="iconfont icon-exam"/>
        <span class="title" @click="index">Exam-System</span>
      </el-col>

      <el-col :span="20" class="topbar-right">
        <i class="el-icon-menu" @click="globalState.flag = !globalState.flag"/>
        <div class="user">
          <span>{{user.name}}</span>
          <img src="@/assets/image/userimg.png" alt="no such image" class="user-img" ref="img" @click="showSetting"/>
          <transition name="fade">
            <div class="out" ref="out" v-show="loginFlag">
              <ul>
                <li><a href="javascript:">用户信息</a></li>
                <li><a href="javascript:">设置</a></li>
                <li class="exit" @click="exit"><a href="javascript:">退出登录</a></li>
              </ul>
            </div>
          </transition>
        </div>
      </el-col>
    </el-row>
  </header>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {useGlobalState} from "@/store";
import {useRouter} from "vue-router";
import {logout} from "@/api";

const router = useRouter()
const loginFlag = ref(false)
const user = reactive({
  name: "",
  id: 0
})

const globalState = useGlobalState()

function showSetting() {
  loginFlag.value = !loginFlag.value
}

function getUserInfo() {
  let username = localStorage.getItem("username") || "no such user"
  let userid = localStorage.getItem("userid") || "0"

  user.name = username
  user.id = parseInt(userid)
}

function index() {
  router.replace({path: "/index"})
}

function exit() {
  logout()
  router.replace({path: "/"})
}

onMounted(() => {
  getUserInfo()
})
</script>

<style lang="scss" scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

#topbar {
  position: relative;
  z-index: 10;
  background: #124280;
  height: 80px;
  line-height: 80px;
  color: #fff;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.5);

  .topbar-left {
    height: 80px;
    display: flex;
    justify-content: center;
    background: rgba(0, 0, 0, 0.05);
    overflow: hidden;

    .icon-exam {
      font-size: 60px;
    }

    .title {
      font-size: 20px;
      cursor: pointer;
    }
  }

  .topbar-right {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .user-img {
      width: 50px;
      height: 50px;
      border-radius: 50%;
    }

    .el-icon-menu {
      font-size: 30px;
      margin-left: 20px;
    }

    .user {
      position: relative;
      margin-right: 40px;
      display: flex;

      .user-img {
        margin-top: 15px;
        margin-left: 10px;
        cursor: pointer;
      }

      .out {
        font-size: 14px;
        position: absolute;
        top: 80px;
        right: 0;
        background: #fff;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        padding: 12px;

        ul {
          list-style: none;

          li {
            height: 26px;
            line-height: 26px;
          }
        }

        a {
          text-decoration: none;
          color: #000000;
        }

        .exit {
          margin-top: 4px;
          padding-top: 4px;
          border-top: 1px solid #ccc;
        }
      }
    }
  }
}
</style>