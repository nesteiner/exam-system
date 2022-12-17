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
          <el-button @click="toPart(scope.row.examCode,scope.row.source)" type="primary" size="small">查看分段</el-button>
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
        :page-count="pagination.total" class="page"/>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {findAllExamsByPage} from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()

const pagination = reactive({
  current: 1,
  total: -1,
  size: 4
})
const exams = ref([] as ExamManage[])

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

function toPart(examCode: number, source: string) {
  router.replace({path: "/scorePart", query: {examCode, source}})
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

  .edit {
    margin-left: 20px;
  }
}
</style>