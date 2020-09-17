<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#95d1ff" text-color="#fff" active-text-color="#0099FF" style="border:none" @select="changeIndex">
    <el-menu-item index='4' class="bgc">
      <img src="../../static/img/logo.png" />
    </el-menu-item>
    <el-menu-item index="3" class="bgc item" @click="logout">
      <span>退出登录</span>
    </el-menu-item>
    <el-menu-item index="2" class="bgc item">
      <li>信息查询</li>
    </el-menu-item>
    <el-menu-item index="1" class="bgc item">
      <li>账号管理</li>
    </el-menu-item>
  </el-menu>
  <changepsw v-if="activeIndex === '1'"></changepsw> 
  <info v-else-if="activeIndex === '2'"></info>
  <logout v-if="dialogFormVisible == true" @cancel="handleCancel"></logout>
  </div>
</template>  

<script>
import changepsw from '@/components/student/changepsw'
import info from '@/components/student/info'
import logout from '@/components/logout'

export default {
  name: "nav_student",
  data() {
    return {
      id: this.COMMON.id,
      status: this.COMMON.status,
      dialogFormVisible: false,
      activeIndex: "1"
    };
  },
  components:{
    changepsw,
    info,
    logout
  },
  methods: {
    changeIndex(index){
      this.activeIndex = index;
    },
    logout(){
      this.dialogFormVisible = true;
    },
    handleCancel(index){
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
