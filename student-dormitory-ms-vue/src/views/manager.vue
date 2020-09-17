<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#95d1ff"
      text-color="#fff"
      active-text-color="#0099FF"
      style="border:none;"
      @select="changeIndex"
    >
      <el-menu-item index="5" class="bgc">
        <img src="../../static/img/logo.png" />
      </el-menu-item>
      <el-menu-item index="4" class="bgc item" @click="logout">
        <span>退出登录</span>
      </el-menu-item>
      <el-submenu index="3" class="bgc item">
        <template slot="title">宿舍信息管理</template>
        <el-menu-item index="3-1">校区管理</el-menu-item>
        <el-menu-item index="3-2">宿舍楼管理</el-menu-item>
        <el-menu-item index="3-3">房间管理</el-menu-item>
      </el-submenu>
      <el-submenu index="2" class="bgc item">
        <template slot="title">学生入住管理</template>
        <el-menu-item index="2-1">学生入住</el-menu-item>
        <el-menu-item index="2-2">学生退宿</el-menu-item>
      </el-submenu>

      <el-menu-item index="1" class="bgc item">
        <li>学生账号管理</li>
      </el-menu-item>
    </el-menu>
    <campus v-if="activeIndex === '3-1'"></campus>
    <dorm v-if="activeIndex === '3-2'"></dorm>
    <room v-if="activeIndex === '3-3'"></room>
    <student_in v-if="activeIndex === '2-1'"></student_in>
    <student_out v-if="activeIndex === '2-2'"></student_out>
    <student_account v-if="activeIndex === '1'"></student_account>
    <logout v-if="dialogFormVisible === true" @cancel="handleCancel"></logout>
  </div>
</template>

<script>
import campus from "@/components/center/campus";
import dorm from "@/components/center/dorm";
import room from "@/components/center/room";
import student_in from "@/components/center/student_in";
import student_out from "@/components/center/student_out";
import student_account from "@/components/center/student_account";
// import build_account from '@/components/center/build_account'
import logout from "@/components/logout";
export default {
  name: "nav_manager",
  num: 0,
  data() {
    return {
      id: this.COMMON.id,
      studentId: "",
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      activeIndex: "1",
      addedId: "",
      addType: "",
      options: [
        {
          value: "学生",
          label: "学生"
        },
        {
          value: "管理员",
          label: "管理员"
        }
      ]
    };
  },
  methods: {
    resetpsw() {
      this.$axios
        .post("/manager/reset_psw", { id: this.studentId })
        .then(successResponse => {
          if (successResponse != null) {
            this.$message({
              message: `重置成功 学号：${successResponse.data.id} 密码：${successResponse.data.psw}`,
              type: "success"
            });
          } else {
            this.$message({
              message: "重置失败",
              type: "error"
            });
          }
        })
        .catch(failResponse => {});
    },
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
  },
  components: {
    campus,
    dorm,
    room,
    student_in,
    student_out,
    student_account,
    logout
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
