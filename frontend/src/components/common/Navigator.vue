<template>
  <div id="nav">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item class="title">{{active.title}}</el-breadcrumb-item>
      <el-breadcrumb-item v-if="active.item != null">{{active.item}}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script lang="ts" setup>
import {useGlobalState} from "@/store";
import {onMounted, ref} from "vue";

const globalstate = useGlobalState()
const emits = defineEmits(["send-index"])

const index = ref(0)
const active = ref<any>([])

function getIndex() {
  emits("send-index", (data: number) => {
    index.value = data
    active.value = globalstate.menus[data - 1]
  })
}

onMounted(() => {
  getIndex()
})
</script>

<style lang="scss" scoped>
#nav {
  .el-breadcrumb {
    height: 60px;
    line-height: 60px;
    padding-left: 20px;

    .title {
      font-weight: bold;
    }
  }

}
</style>