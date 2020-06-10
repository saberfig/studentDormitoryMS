<template>
    <div class="change-psw">
      <div class="row">
        <el-form :model="ruleForm"  status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="width: 400px">
          <el-form-item  label="旧密码" prop="oldpass">
            <el-input type="password" v-model="ruleForm.oldpass"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>


    </div>
</template>

<script>
    export default {
      name: "changepsw",
      data() {
        var checkOldpsw = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('原密码不能为空'));
          }
          callback();

        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm.checkPass !== '') {
              this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            pass: '',
            checkPass: '',
            oldpass: ''
          },
          rules: {
            pass: [
              {validator: validatePass, trigger: 'blur'}
            ],
            checkPass: [
              {validator: validatePass2, trigger: 'blur'}
            ],
            oldpass: [
              {validator: checkOldpsw, trigger: 'blur'}
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          // this.$axios
          //   .post('/changepsw', {
          //
          //   id: "111",
          //   psw: "111",
          //   status: '1'
          //
          //
          //   })
          //   //成功响应的情况？会返回一个数据类型successResponse
          //   //successResponse是服务器响应内容，其中包含json数据。是HttpServletResponse对象
          //   .then(successResponse => {
          //     if (successResponse.data.code === 200) {
          //       console.log("1111");
          //     }
          //     if (successResponse.data.code === 400) {
          //       //this.$router.replace({path: '/StudentLogin'})
          //       this.$alert("用户名或密码错误！")
          //     }
          //   })
          //   //不成功响应的情况？
          //   .catch(failResponse => {
          //   })

          console.log('id:'+this.COMMON.id),
          console.log('oldpsw:'+this.ruleForm.oldpass),
          console.log('newpsw'+this.ruleForm.pass),
          this.$refs[formName].validate((valid) => {
            if (valid) {
              //alert('submit!');
            } else {
              console.log('error submit!!');
              return false;
            }
          });
          this.$axios
            .post('/changepsw', {
              id:this.COMMON.id,
              oldpsw:this.ruleForm.oldpass,
              newpsw:this.ruleForm.pass,
            })
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                alert("修改成功！")
              }
              if (successResponse.data.code === 400) {
                alert("修改失败！请检查旧密码是否正确")
              }
            })
            //不成功响应的情况？
            .catch(failResponse => {
            })
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>
.change-psw{
  margin-top: 20px;
  margin-left: 20px;
}
</style>
