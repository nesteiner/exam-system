<template>
  <div class="all-teachers">
    <el-table :data="pagination.content" border>
      <el-table-column fixed="left" prop="name" label="姓名" width="180"/>
      <el-table-column prop="institute" label="学院" width="200"/>
      <el-table-column prop="sex" label="性别" width="120"/>
      <el-table-column prop="telephone" label="电话号码" width="120"/>
      <el-table-column prop="email" label="邮箱" width="120"/>
      <el-table-column prop="password" label="密码" width="120"/>
      <el-table-column prop="cardId" label="身份证号" width="120"/>
      <el-table-column prop="type" label="职称"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button @click="checkGradge(scope.row.id)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteById(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6,10]"
      :page-size="pagination.size"
      layout="total,sizes,prev,pager,next,jumper"
      :page-count="pagination.total"
      class="page"/>

    <el-dialog
        title="编辑教师信息"
        v-model="dialogVisible"
        width="30%"
        :before-close="handleClose">

      <section class="update">
        <el-form form="form" :model="form" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="form.name"/>
          </el-form-item>

          <el-form-item label="学院">
            <el-input v-model="form.institute"/>
          </el-form-item>

          <el-form-item label="性别">
            <el-input v-model="form.sex"/>
          </el-form-item>

          <el-form-item label="电话号码">
            <el-input v-model="form.telephone"/>
          </el-form-item>

          <el-form-item label="邮箱">
            <el-input v-model="form.email"/>
          </el-form-item>

          <el-form-item label="密码">
            <el-input v-model="form.password"/>
          </el-form-item>

          <el-form-item label="身份证号">
            <el-input v-model="form.cardId"/>
          </el-form-item>

          <el-form-item label="职称">
            <el-input v-model="form.type"/>
          </el-form-item>

        </el-form>
      </section>

      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {deleteTeacher, findTeacher, findTeachers, updateTeacher} from "@/api";

const pagination = reactive({
  current: 1,
  total: -1,
  size: 6,
  content: [] as Teacher[]
})

const dialogVisible = ref(false)
const form = ref<UpdateTeacherRequest>({
  id: 0,
  name: "",
  institute: "",
  sex: "",
  telephone: "",
  email: "",
  cardId: "",
  type: "",
  password: ""
})

async function getTeacherInfo() {
  try {
    let response: {totalPages: number, content: Teacher[]} = await findTeachers(pagination.current - 1, pagination.size)
    pagination.total = response.totalPages
    pagination.content = response.content
  } catch (error: any) {
    alert(error.response.data["message"])
    console.error(error.response.data["message"])
  }
}

async function handleSizeChange(value: number) {
  pagination.size = value
  await getTeacherInfo()
}

function handleCurrentChange(value: number) {
  pagination.current = value
}

async function checkGradge(teacherId: number) {
  dialogVisible.value = true
  try {
    form.value = await findTeacher(teacherId)
  } catch (error: any) {
    alert(error.response.data["message"])
    console.error(error.response.data["message"])
  }
}

async function deleteById(teacherId: number) {
  let flag = confirm("确定删除当前教师吗")
  if (flag) {
    await deleteTeacher(teacherId)
  }
}

async function handleSubmit() {
  dialogVisible.value = false
  try {
    updateTeacher(form.value)
    getTeacherInfo()
  } catch (error: any) {
    alert(error.respones.data["message"])
    console.error(error.respones.data["message"])
  }
}

function handleClose() {
  confirm("确认关闭?")
}

onMounted(async () => {
  getTeacherInfo()
})
</script>

<style lang="scss" scoped>
.all-teachers {
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

  .el-table tr {
    background: #dd5862 !important;
  }

  .el-table .warning-row {
    background: #000 !important;
  }

  .el-table .success-row {
    background: #dd5862;
  }
}
</style>