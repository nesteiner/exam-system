<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
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

      <el-form-item label="密码">
        <el-input v-model="form.password"/>
      </el-form-item>

      <el-form-item label="身份证号">
        <el-input v-model="form.cardId"/>
      </el-form-item>

      <el-form-item label="职称">
        <el-input v-model="form.type"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleClick">立即创建</el-button>
        <el-button type="text" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {Md5} from "ts-md5";
import {insertTeacher} from "@/api";

const defaultForm = {
  name: "",
  grade: "",
  major: "",
  clazz: "",
  institute: "",
  telephone: "",
  email: "",
  password: "",
  cardId: "",
  sex: "",
  type: "",
  roles: [

  ]
}

const form = ref(defaultForm)

async function handleClick() {
  let passwordHash = Md5.hashStr(form.value.password)
  let request: RegisterTeacherRequest = {
    ...form.value,
    passwordHash
  }

  try {
    await insertTeacher(request)
  } catch(error: any) {
    alert(error.response.data["message"])
    console.error(error.response.data["message"])
  } finally {

  }
}

function cancel() {
  form.value = defaultForm
}
</script>

<style scoped>

</style>