<template>
  <div class="all">
    <el-table :data="students" border>
      <el-table-column fixed="left" prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="institute" label="学院" width="200"></el-table-column>
      <el-table-column prop="major" label="专业" width="200"></el-table-column>
      <el-table-column prop="grade" label="年级" width="200"></el-table-column>
      <el-table-column prop="clazz" label="班级" width="100"></el-table-column>
      <el-table-column prop="sex" label="性别" width="120"></el-table-column>
      <el-table-column prop="telephone" label="联系方式" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button @click="checkGrade(scope.row.id)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteById(scope.row.studentId)" type="danger" size="small">删除</el-button>
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
        :page-count="pagination.total"
        class="page">
    </el-pagination>

    <el-dialog
        title="编辑试卷信息"
        v-model="dialogVisible"
        width="30%"
        :before-close="handleClose">

      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="学院">
            <el-input v-model="form.institute"></el-input>
          </el-form-item>
          <el-form-item label="专业">
            <el-input v-model="form.major"></el-input>
          </el-form-item>
          <el-form-item label="年级">
            <el-input v-model="form.grade"></el-input>
          </el-form-item>
          <el-form-item label="班级">
            <el-input v-model="form.clazz"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.sex"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.telephone"></el-input>
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
import {deleteStudent, findAllStudents, findStudent, updateStudent} from "@/api";

const pagination = reactive({
  current: 1,
  total: -1,
  size: 6
})

const dialogVisible = ref(false)
const form = ref({} as Student)
const students = ref([] as Student[])

async function getStudentInfo() {
  let response = await findAllStudents(pagination.current - 1, pagination.size)
  pagination.total = response["totalPages"]
  students.value = response["content"]
}

async function handleSizeChange(value: number) {
  pagination.size = value
  await getStudentInfo()
}

async function handleCurrentChange(value: number) {
  pagination.current = value
  await getStudentInfo()
}

async function checkGrade(studentId: number) {
  dialogVisible.value = true
  form.value = await findStudent(studentId)
}

async function deleteById(studentId: number) {
  let flag = confirm("确定删除当前学生吗")
  if (flag) {
    await deleteStudent(studentId)
    await getStudentInfo()
  }
}

async function handleSubmit() {
  dialogVisible.value = false
  await updateStudent(form.value)
  await getStudentInfo()
}

function handleClose() {
  confirm("确认关闭")
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
  .el-table {
    tr {
      background-color: #dd5862 !important;
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