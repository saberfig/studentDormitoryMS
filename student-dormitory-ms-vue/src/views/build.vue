<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#95d1ff" text-color="#fff" active-text-color="#0099FF" style="border:none;" @select="changeIndex">
      <el-menu-item index="6" class="bgc">
        <img src="../../static/img/logo.png" />
      </el-menu-item>
      <el-menu-item index="5" class="bgc item" @click="logout">
        <span>退出登录</span>
      </el-menu-item>
      <el-submenu index="4" class="bgc item">
        <template slot="title">学生信息管理</template>
        <el-menu-item index="4-1">违纪信息管理</el-menu-item>
        <el-menu-item index="4-2">访客信息管理</el-menu-item>
      </el-submenu>
      <el-submenu index="3" class="bgc item">
        <template slot="title">通知</template>
        <el-menu-item index="3-1">校方通知</el-menu-item>
        <el-menu-item index="3-2">通知学生</el-menu-item>
      </el-submenu>
      <el-menu-item index="1" class="bgc item">
        <li>宿舍信息</li>
      </el-menu-item>
    </el-menu>
    <dorm_info v-if="activeIndex === '1'"></dorm_info>
    <campus_notice v-if="activeIndex === '3-1'"></campus_notice>
    <ext_notice v-if="activeIndex === '3-2'"></ext_notice>
    <vio v-if="activeIndex === '4-1'"></vio>
    <guest v-if="activeIndex === '4-2'"></guest>
    <logout v-if="dialogFormVisible == true" @cancel="handleCancel"></logout>
    
  </div>
</template>

<script>
import campus_notice from "@/components/manager/campus_notice"
import guest from "@/components/manager/guest"
import ext_notice from "@/components/manager/ext_notice"
import dorm_info from "@/components/manager/dorm_info"
import vio from "@/components/manager/vio"
import logout from '@/components/logout'
export default {
  name: "nav_build",
  data() {
    return {
      id: this.COMMON.id,
      dialogFormVisible: false,
      activeIndex: "1"
    };
  },
  components: {
    guest,
    campus_notice,
    ext_notice,
    vio,
    dorm_info,
    logout
  },
  methods: {
    changeIndex(index) {
      this.activeIndex = index;
    },
    logout() {
      this.dialogFormVisible = true;
    },
    handleCancel(index) {
      this.dialogFormVisible = false;
      this.activeIndex = index;
    }
  }
};
</script>

<style scoped>
.bgc {
  background-color: #95d1ff !important;
}
.item {
  float: right !important;
}
</style>
