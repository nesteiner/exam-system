<template>
  <div class="all">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="institute" label="学院" width="200"></el-table-column>
      <el-table-column prop="major" label="专业" width="200"></el-table-column>
      <el-table-column prop="grade" label="年级" width="200"></el-table-column>
      <el-table-column prop="clazz" label="班级" width="100"></el-table-column>
      <el-table-column prop="sex" label="性别" width="120"></el-table-column>
      <el-table-column prop="telephone" label="联系方式" width="120"></el-table-column>
      <el-table-column fixed="right" label="查看成绩" width="150">
        <template #default="scope">
          <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">查看成绩</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.current"
        :page-sizes="[6, 10]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total"
        class="page"/>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {findAllStudents} from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()

const pagination = reactive({
  current: 1,
  total: -1,
  size: 6
})

const students = ref([] as Student[])

async function getStudentInfo() {
  let response = await findAllStudents(pagination.current - 1, pagination.size)
  pagination.total = response["totalPage"]
  students.value = response["contents"]
}

async function handleSizeChange(value: number) {
  pagination.size = value
  await getStudentInfo()
}

async function handleCurrentChange(value: number) {
  pagination.current = value
  await getStudentInfo()
}

function checkGrade(studentId: number) {
  router.replace({path: "/grade", query: {studentId}})
}

onMounted(async () => {
  await getStudentInfo()
})
</script>

<style lang="scss" scoped>
.all {
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

  .el-table{
    tr {
      background: #dd5863 !important;
    }

    .warning-row {
      background: #000 !important;
    }

    .success-row {
      background: #dd5862;
    }
  }
}
</style>