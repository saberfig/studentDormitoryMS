<template>
  <div class="all">
    <div class="box1">
      <div class="box2">
        <div class="row">
          <div class="font">大学生宿舍管理系统</div>
        </div>
        <div class="row">
          <div class="font">学生登陆</div>
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
          <el-input v-model="loginForm.password" placeholder="请输入密码" type="password"></el-input>
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
    name: 'StudentLogin',
    data () {
      return {
        loginForm: {
          username: '',
          password: '',
          status: '1'
        },
        responseResult: []
      }
    },
    methods: {
      //methods 中定义了登录按钮的点击方法，即向后端 /login 接口发送数据，获得成功的响应后，
      //页面跳转到 /index。因为之前我们设置了默认的 URL，所以请求实际上发到了 http://localhost:8443/api/login。
      login () {
        this.$axios
          .post('/login', {
            id: this.loginForm.username,
            psw: this.loginForm.password,
            status: '1'
          })
          //成功响应的情况？会返回一个数据类型successResponse
          //successResponse是服务器响应内容，其中包含json数据。是HttpServletResponse对象
          .then(successResponse => {
            if (successResponse.data.code === 200) {
                this.COMMON.id=this.loginForm.username,
                this.COMMON.status=1,
              this.$router.replace({path: '/nav_student/info'})//切换页面
            }
            if (successResponse.data.code === 400) {
              //this.$router.replace({path: '/StudentLogin'})
              this.$alert("用户名或密码错误！")
            }
          })
          //不成功响应的情况？
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
