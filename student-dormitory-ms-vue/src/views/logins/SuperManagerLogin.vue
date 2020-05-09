<template>
  <div class="all">
    <div class="box1">
      <div class="box2">
        <div class="row">
          <div class="font">大学生宿舍管理系统</div>
        </div>
        <div class="row">
          <div class="font">宿管中心登陆</div>
        </div>
        <div class="row">
          <div class="font">用户名</div>
        </div>
        <div class="row">
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </div>
        <div class="row">
          <div class="font">密码</div>
        </div>
        <div class="row">
          <el-input v-model="loginForm.password" placeholder="请输入密码"></el-input>
        </div>
        <div class="row" >
          <div id="loginbutton" align="center">
            <el-button @click="login">登录</el-button>
          </div>
        </div>
        <div class="row">
          <div class="font">忘记密码？</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "SuperManagerLogin",
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
                this.COMMON.id=this.loginForm.username,
                this.COMMON.status=1,
                this.$router.replace({path: '/nav_manager/index'})
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

    position: absolute;
    min-height: 100%;
    height: auto;
    overflow:hidden;
    width:100%;

    background-repeat: no-repeat;
    background-size:100% 100%;
    background-attachment:fixed;
    background-image:url(https://www.bupt.edu.cn/images/18/05/09/1mu9mjraxf/xyfg3.jpg);
  }
  .box1{
  //border: 1px solid #00ee00;
    height: 300px;
    position:relative;
  }
  .box2{
    border-radius:20px;
    position: absolute;
    width: 400px;
    height:450px;
  //border: 1px solid red;
    background-color: #F7F7F7;
    left:50%;
    margin-left: -200px;
    margin-top: 170px;
  }
  .row{
    margin: 5px;
    height: 45px;

  }
  .font{
    width: 100%;
    height: 45px;
    font-size: 1.3em;
    font-weight: bold;
    text-align: center;
    border: 0 none;
  }
  #loginbutton{
    margin-left:auto;
    margin-right:auto;
  }
</style>
