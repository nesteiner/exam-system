<template>
  <div id="left">
    <el-menu active-text-color="#dd5862"
             text-color="#000"
             :default-active="route.path"
             class="el-menu-vertical-demo"
             :collapse="globalstate.flag"
             background-color="#124280"
             menu-trigger="click" router>
      <el-sub-menu v-for="(item, index) in globalstate.menus" :index="item.index.toString()" :key="index">
        <template #title>
          <div class="left-width">
            <i class="iconfont" :class="item.icon"/>
            <span class="title">{{item.title}}</span>
          </div>
        </template>

        <el-menu-item-group v-for="(list, index1) in item.content" :key="index1">
          <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item != null">{{list.item}}</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script lang="ts" setup>
import {useGlobalState} from "@/store";
import {useRoute} from "vue-router";
import {onMounted} from "vue";

const globalstate = useGlobalState()
const emits = defineEmits(["send-index"])
const route = useRoute()

function handleTitle(index: number) {
  emits("send-index", index)
}

function addData() {
  let roles = localStorage.getItem("userroles") || "student"
  if(roles.split(",").findIndex(x => x == "admin") != -1) {
    globalstate.menus.push({
      index: 5,
      title: "教师管理",
      icon: "icon-Userselect",
      content:[{item:"教师管理",path:"/teacherManage"},{item: "添加教师",path: "/addTeacher"}],
    })
  }
}

onMounted(() => {
  addData()
})
</script>

<style lang="scss" scoped>
#left {
  height: 900px;
  background-color: #124280;
  z-index: 0;


  .el-menu-vertical-demo {
    .title {
      color: #fff;
      font-size: 16px;
      font-weight: bold;
      margin-left: 14px;
    }

    .el-submenu {
      border-bottom: 1px solid #eeeeee0f !important;
    }
    .el-submenu__title {
      overflow: hidden;

      &:not(.el-menu--collapse) {
        min-height: 900px;
      }

      &:hover {
        background: #fff;
      }

      i {
        color: #fbfbfc !important;
      }
    }

    .left-width {
      width: 213px;

      .iconfont {
        font-size: 18px;
        color: #fff;
      }
    }
  }

}
</style>