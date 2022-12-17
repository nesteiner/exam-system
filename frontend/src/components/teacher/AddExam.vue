<template>
  <section class="add">
    <el-form label-width="80px">
      <el-form-item label="试卷名称">
        <el-input v-model="form.source"></el-input>
      </el-form-item>
      <el-form-item label="介绍">
        <el-input v-model="form.description"></el-input>
      </el-form-item>
      <el-form-item label="所属学院">
        <el-input v-model="form.institute"></el-input>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.major"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade"></el-input>
      </el-form-item>
      <el-form-item label="考试日期">
        <el-col :span="11">
          <el-date-picker placeholder="选择日期" v-model="form.examDate" style="width: 100%;"/>
        </el-col>
      </el-form-item>
      <el-form-item label="持续时间">
        <el-input v-model="form.totalTime"></el-input>
      </el-form-item>
      <el-form-item label="总分">
        <el-input v-model="form.totalScore"></el-input>
      </el-form-item>
      <el-form-item label="考试类型">
        <el-input v-model="form.type"></el-input>
      </el-form-item>
      <el-form-item label="考生提示">
        <el-input type="textarea" v-model="form.tips"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSubmit">立即创建</el-button>
        <el-button link @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {findExamsPaperId, insertExam} from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()

const defaultForm = {
  term: "",
  source: "",
  description: "",
  institute: "",
  major: "",
  grade: "",
  examDate: "",
  totalTime: "",
  totalScore: "",
  type: "",
  tips: "",
  paperId: 0
}
const form = ref<ExamManage>(defaultForm)

function formatTime(date: Date) {
  const zeroPadding = (x: number) => {
    if(x < 10) {
      return `0${x}`
    } else {
      return `${x}`
    }
  }

  let year = date.getFullYear()
  let month = zeroPadding(date.getMonth() + 1)
  let day = zeroPadding(date.getDate())
  let hours = zeroPadding(date.getHours())
  let minutes = zeroPadding(date.getMinutes())
  let seconds = zeroPadding(date.getSeconds())
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

async function handleSubmit() {
  let examDate = formatTime(new Date())
  form.value.examDate = examDate.substring(0, 10)
  form.value.paperId = await findExamsPaperId() + 1
  await insertExam(form.value)
  await router.replace({path: "/selectExam"})
}

function cancel() {
  form.value = defaultForm
}

</script>

<style lang="scss" scoped>
.add {
  padding: 0 40px;
  width: 400px;
}
</style>