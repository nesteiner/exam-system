<template>
  <div class="exam">
    <el-table :data="exams" border>
      <el-table-column fixed="left" prop="source" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="description" label="介绍" width="200"></el-table-column>
      <el-table-column prop="institute" label="所属学院" width="120"></el-table-column>
      <el-table-column prop="major" label="所属专业" width="200"></el-table-column>
      <el-table-column prop="grade" label="年级" width="100"></el-table-column>
      <el-table-column prop="examDate" label="考试日期" width="120"></el-table-column>
      <el-table-column prop="totalTime" label="持续时间" width="120"></el-table-column>
      <el-table-column prop="totalScore" label="总分" width="120"></el-table-column>
      <el-table-column prop="type" label="试卷类型" width="120"></el-table-column>
      <el-table-column prop="tips" label="考生提示" width="400"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button @click="edit(scope.row.examCode)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteRecord(scope.row.examCode)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.current"
        :page-sizes="[4, 8, 10, 20]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :page-count="pagination.total" class="page">
    </el-pagination>

    <el-dialog
        title="编辑试卷信息"
        v-model="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
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
              <el-date-picker type="date" placeholder="选择日期" v-model="form.examDate" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="持续时间">
            <el-input v-model="form.totalTime"></el-input>
          </el-form-item>
          <el-form-item label="总分">
            <el-input v-model="form.totalScore"></el-input>
          </el-form-item>
          <el-form-item label="试卷类型">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="考生提示">
            <el-input type="textarea" v-model="form.tips"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {deleteExam, findAllExamsByPage, findExam, updateExam} from "@/api";

const form = ref({} as ExamManage)
const pagination = reactive({
  current: 1,
  total: -1,
  size: 4
})
const dialogVisible = ref(false)
const exams = ref([] as ExamManage[])
async function edit(examCode: number) {
  dialogVisible.value = true
  form.value = await findExam(examCode)
}

function handleClose() {
  let flag = confirm("确认关闭?")
}

async function handleSubmit() {
  dialogVisible.value = false
  await updateExam(form.value)
  await getExamInfo()
}

async function deleteRecord(examCode: number) {
  let flag = confirm("确认删除该记录吗，该操作不可逆")
  if (flag) {
    await deleteExam(examCode)
    await getExamInfo()
  }
}

async function getExamInfo() {
  let response = await findAllExamsByPage(pagination.current - 1, pagination.size)
  pagination.total = response["totalPages"]
  exams.value = response["content"]
}

async function handleSizeChange(value: number) {
  pagination.size = value
  await getExamInfo()
}

async function handleCurrentChange(value: number) {
  pagination.current = value
  await getExamInfo()
}

onMounted(async () => {
  await getExamInfo()
})
</script>

<style lang="scss" scoped>
.exam {
  padding: 0 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
</style>