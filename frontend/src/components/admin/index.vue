<template>
  <div id="index">
    <Header class="topbar"/>
    <section class="container">
      <div class="left-side">
        <MainLeft/>
      </div>

      <div class="main-wrapper">
        <Navigator class="navigator"/>
        <router-view/>
      </div>
    </section>
  </div>
</template>

<script lang="ts" setup>
import {Header, MainLeft, Navigator} from "@/components/common"
import {ref} from "vue";

const username = ref("")
</script>

<style lang="scss" scoped>
#index {
  .navigator {
    box-shadow: 1px 0 5px rgba(0, 0, 0, 0.1);
    margin-bottom: 30px;
  }

  .container {
    display: flex;
    background: #fff;
  }

  .main-wrapper {
    overflow: hidden;
    flex: 1;
    background: #fff;
  }
}
</style>