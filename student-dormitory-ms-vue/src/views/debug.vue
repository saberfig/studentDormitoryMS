<template>
  <div class="debug">
    <div><router-link to="/NewLogin"><h1>统一登录</h1></router-link></div>
    <div><router-link to="/StudentLogin"><h1>学生登录</h1></router-link></div>
    <div><router-link to="/DormManagerLogin"><h1>宿舍管理员登录</h1></router-link></div>
    <div><router-link to="/SuperManagerLogin"><h1>宿管中心登录</h1></router-link></div>
    <div><router-link to="/student"><h1>学生</h1></router-link></div>
    <div><router-link to="/build"><h1>宿舍管理员</h1></router-link></div>
    <div><router-link to="/manager"><h1>宿管中心</h1></router-link></div>
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
