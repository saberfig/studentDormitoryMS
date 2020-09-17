<template>

</template>

<script>

    //在这个文件声明全局变量和函数
    var id="2";//登入用户的ID
    var status = 1;//登入用户的身份判断、0为学生、1为宿舍管理员、2为宿管中心
    function commonFun() {
    }
    function clearUser(){
      id = '';
      status = '';
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
