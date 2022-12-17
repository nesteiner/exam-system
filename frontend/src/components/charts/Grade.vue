<template>
  <div id="grade">
    <div ref="box" class="box"/>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {findAllScoreByStudentId} from "@/api";
import {useRoute} from "vue-router";
import * as echarts from "echarts"

const route = useRoute()

const tableDataX = ref([] as string[])
const tableDataY = ref([] as number[])

const box = ref<HTMLDivElement>()

onMounted(async () => {
  let studentId = parseInt(route.query["studentId"] as string)
  let scores = await findAllScoreByStudentId(studentId)
  scores.forEach((element: Score, index: number) => {
    tableDataX.value.push(`第${index + 1}次`)
    tableDataY.value.push(element.usualScore)
  })

  let scoreCharts = echarts.init(box.value!!)
  let option = {
    xAxis: {
      type: "category",
      data: tableDataX.value
    },

    yAxis: {
      type: "value"
    },

    series: [
      {
        data: tableDataY.value,
        type: "line",
        itemStyle: {normal: {label: {show: true}}}
      }
    ]
  }

  scoreCharts.setOption(option)
})
</script>

<style lang="scss" scoped>
#grade {
  position: relative;
  .box {
    width: 600px;
    height: 400px;
  }
}
</style>