<template>
  <div class="exam">
    <el-table :data="answers" border :row-class-name="tableRowClassName">
      <el-table-column fixed="left" prop="subject" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="question" label="题目信息" width="490"></el-table-column>
      <el-table-column prop="section" label="所属章节" width="200"></el-table-column>
      <el-table-column prop="type" label="题目类型" width="200"></el-table-column>
      <el-table-column prop="score" label="试题分数" width="150"></el-table-column>
      <el-table-column prop="level" label="难度等级" width="133"></el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.current"
        :page-sizes="[6, 10]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :page-count="pagination.total"
        class="page"/>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {findAllAnswersByPage} from "@/api";

const pagination = reactive({
  current: 1,
  total: -1,
  size: 6
})

const answers = ref<Answer[]>([])

async function getAnswerInfo() {
  let response = await findAllAnswersByPage(pagination.current - 1, pagination.size)
  pagination.total = response["totalPages"]
  answers.value = response["content"]
}

async function handleSizeChange(value: number) {
  pagination.size = value
  await getAnswerInfo()
}

async function handleCurrentChange(value: number) {
  pagination.current = value
  await getAnswerInfo()
}

function tableRowClassName(x: {rowIndex: number}) {
  if(x.rowIndex % 2 == 0) {
    return "warning-row"
  } else {
    return "success-row"
  }
}

onMounted(async () => {
  await getAnswerInfo()
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
    margin-left: 20px
  }

  .el-table {
    tr {
      background: #DD5862 !important;
    }

    .warning-row {
      background: #000;
    }

    .success-row {
      background: #dd5862;
    }
  }
}
</style>