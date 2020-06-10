<template>
  <div class="all">
    <div class="box1">
      <div class="box2">
        <div class="title">大学生宿舍管理系统</div>
        <el-form :rules="loginFormRules" label-width="100px" class="form" :model="loginForm">
            <el-form-item label="活动区域" prop="region">
              <el-select v-model="loginForm.status" placeholder="请选择用户类型">
                <el-option label="学生" value="1"></el-option>
                <el-option label="宿舍管理员" value="2"></el-option>
                <el-option label="宿管中心" value="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="用户名" prop="username" class="">
              <el-input  v-model="loginForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password" class="">
              <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div class="login-button">
          <el-button type="primary" @click="login">提交</el-button>
        </div>
        <div class="forget-psw">

          <div class="" align="center" @click="forget">忘记密码？</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  export default {
    name: 'NewLogin',
    data () {
      var checkUserName = (rule, value, callback) => {
        console.log(value);
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }
        callback();
      };
      var checkUserPsw = (rule, value, callback) => {
        console.log(value);
        if (!value) {
          return callback(new Error('密码不能为空'));
        }
        callback();
      };
      return {
        loginForm: {
          username: '',
          password: '',
          status: ''
        },
        loginFormRules: {
          username: [{ validator: checkUserName, trigger: 'blur' }],
          password: [{ validator: checkUserPsw, trigger: 'blur'}]
        },
        options: [{
          value: '1',
          label: '学生'
        }, {
          value: '2',
          label: '宿舍管理员'
        }, {
          value: '3',
          label: '宿舍管理中心'
        }],
        responseResult: [],
        value:''
      }
    },
    methods: {
      //methods 中定义了登录按钮的点击方法，即向后端 /login 接口发送数据，获得成功的响应后，
      //页面跳转到 /index。因为之前我们设置了默认的 URL，所以请求实际上发到了 http://localhost:8443/api/login。
      login () {
        console.log(this.loginForm),
        this.$axios
          .post('/login', {
            id: this.loginForm.username,
            psw: this.loginForm.password,
            status: this.loginForm.status.toString(),
          })
          //成功响应的情况？会返回一个数据类型successResponse
          //successResponse是服务器响应内容，其中包含json数据。是HttpServletResponse对象
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.COMMON.id=this.loginForm.username,
                this.COMMON.status=this.loginForm.status.toString()
                if(this.loginForm.status.toString() === '1'){
                  this.$router.replace({path: '/nav_student/info'})//切换页面
                }
                else if(this.loginForm.status.toString() === '2'){
                  this.$router.replace({path: '/nav_build/index_build'})//切换页面
                }
                else if(this.loginForm.status.toString() === '3'){
                  this.$router.replace({path: '/nav_manager/index'})//切换页面
                }
                else{

                }
            }
            if (successResponse.data.code === 400) {
              //this.$router.replace({path: '/StudentLogin'})
              this.$alert("用户名或密码错误！")
            }
          })
          //不成功响应的情况？
          .catch(failResponse => {
          })
      },
      forget(){
        alert("请联系宿舍管理员或宿管中心重置密码！")
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
    display: flex;
    flex-direction: column;
    align-items: center;
    //justify-content:center;
  }
  .title{
    //border:1px solid green;
    font-size: 24px;
    margin-top: 60px;
    //margin:0 auto;
  }
  .form{
    margin-top: 30px;
  }
  .login-button{
    margin-top: 0px;
  }
  .forget-psw{
    margin-top: 20px;
  }

</style>
