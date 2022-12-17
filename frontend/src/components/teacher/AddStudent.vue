<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>
      <el-form-item label="学院">
        <el-input v-model="form.institute"></el-input>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.major"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade"></el-input>
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="form.clazz"></el-input>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.cardId"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.passwordHash"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSubmit">立即创建</el-button>
        <el-button type="text" @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {insertStudent} from "@/api";
import {Md5} from "ts-md5";
import {useRouter} from "vue-router";

const router = useRouter()

const defaultForm = {
  name: "",
  grade: "",
  major: "",
  clazz: "",
  institute: "",
  telephone: "",
  email: "",
  passwordHash: "",
  cardId: "",
  sex: "",
}

const form = ref(defaultForm)

async function handleSubmit() {
  let passwordHash = Md5.hashStr(form.value.passwordHash)
  await insertStudent({...form.value, passwordHash})
  await router.replace("/studentManage")
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