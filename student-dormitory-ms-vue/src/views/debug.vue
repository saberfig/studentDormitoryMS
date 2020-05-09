<template>
  <div class="debug">
    <div><router-link to="/login"><h1>登录</h1></router-link></div>
    <div><router-link to="/nav_manager/index"><h1>宿管中心</h1></router-link></div>
    <div><router-link to="/nav_build/index_build"><h1>宿舍管理员</h1></router-link></div>
    <div><router-link to="/nav_student/info"><h1>学生</h1></router-link></div>
  </div>
</template>

<script>
    export default {
        name: "debug"
    }
</script>

<style scoped>
.debug{
  text-align: center; /*让div内部文字居中*/
  background-color: #fff;
  border-radius: 20px;
  width: 300px;
  height: 350px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
