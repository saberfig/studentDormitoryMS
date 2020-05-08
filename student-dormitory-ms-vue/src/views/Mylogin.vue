<template>
  <div class="all">


    <div class="box">
      <div class="row">
        <div class="font">大学生宿舍管理系统</div>
      </div>
      <div class="row">
        <div class="font">用户名</div>
      </div>
      <div class="row">
        <el-input v-model="username" placeholder="请输入用户名"></el-input>
      </div>
      <div class="row">
        <div class="font">密码</div>
      </div>
      <div class="row">
        <el-input v-model="password" placeholder="请输入密码"></el-input>
      </div>
      <div class="row">
        <el-button @click="changeword">登录</el-button>
      </div>
      <div class="row">
        <div class="font">忘记密码？</div>
      </div>
    </div>


  </div>
</template>

<script>

  export default {
    name: 'Mylogin',
    data () {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        responseResult: []
      }
    },
    methods: {
      login () {
        this.$axios
          .post('/login', {
            id: this.loginForm.username,
            psw: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/index'})
            }
            if (successResponse.data.code === 400) {
              this.$router.replace({path: '/failed'})
            }

          })
          .catch(failResponse => {
          })
      }
    }
  }
</script>

<style scoped>
.all{
  width:100%;
  height: 100%;

  background-repeat: no-repeat;
  background-size:100% 100%;
  background-attachment:fixed;
}
.box{

  background:#F7F7F7;
  margin-top: 5%;
  height: 360px;
  margin-left: 40%;
  padding: 2%;
  text-align:center;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0,0,0,0.3);

  width: 300px;

  padding: 25px;

}

  .row{
    height: 50px;
  }

  .font{
    width: 100%;
    height: 45px;
    font-size: 1.3em;
    font-weight: bold;
    text-align: center;

    border: 0 none;
  }
</style>
