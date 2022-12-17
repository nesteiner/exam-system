<template>
  <section class="index">
    <div class="hello">
      <i class="iconfont icon-xihuan"></i><span>很高兴遇见你,{{user.name}}老师。</span>
    </div>
  </section>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";

const user = reactive({
  name: "",
  id: 0
})

onMounted(() => {
  user.name = localStorage.getItem("username") || "no such user"
  user.id = parseInt(localStorage.getItem("userid") || "0")
})
</script>

<style lang="scss" scoped>
.index {
  margin-left: 70px;
  .hello {
    font-size: 20px;
    color: #726f70;
    .icon-xihuan {
      font-size: 30px;
      color: #dd6572;
    }
  }
}
</style>