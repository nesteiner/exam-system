<template>
  <div class="login">
    <input type="text" v-model="username" placeholder="input your username"/>
    <br/>
    <input type="password" v-model="password" placeholder="input your password"/>
    <br/>

    <input type="radio" v-model="role" value="student"> Student
    <input type="radio" v-model="role" value="teacher"> Teacher
    <input type="radio" v-model="role" value="admin"> Admin
    <br/>

    <button @click="handleLogin">Login</button>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import {login} from "@/api";

const router = useRouter()
const username = ref("")
const password = ref("")
const role = ref("student")
async function handleLogin() {
  let request = {
    username: username.value,
    passwordHash: password.value
  }

  try {
    await login(request, role.value)
    if (role.value == "student") {
      router.replace({path: "/student"})
    } else if (role.value == "teacher") {
      router.replace({path: "/index"})
    } else if (role.value == "admin") {
      router.replace({path: "/index"})
    }
  } catch (error: any) {
    alert(error)
    console.error(error)
  }
}
</script>

<style scoped>

</style>