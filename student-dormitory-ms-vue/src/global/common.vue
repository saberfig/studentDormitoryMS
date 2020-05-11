<template>

</template>

<script>

    //在这个文件声明全局变量和函数
    var id="2017211857";//登入用户的ID
    var status;//登入用户的身份判断、0为学生、1为宿舍管理员、2为宿管中心
    function commonFun() {
      console.log("公共方法")
    }
    function clearUser(){
      id = undefined;
      status = undefined;
      console.log("clearUser finish");
    }
    // 暴露出这些属性和方法
    export default {
      name: "common",
      id,
      status,
      commonFun,
      clearUser
    }
</script>

<style scoped>

</style>
