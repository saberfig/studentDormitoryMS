<template>
  <div class="resetps addpadding">
<!--    <div>-->
<!--      <label>重置密码目标的学号：</label>-->
<!--      <input type="text" v-model="id">-->
<!--    </div>-->
<!--    <button type="reset" class="btn btn-success" @click="resetpsw">修改</button>-->

    <button @click="dialogFormVisible=true">aa</button>
    <el-dialog title="重置密码" :visible.sync="dialogFormVisible" width="40%">
      <el-form class="dialog">
        <span>重置密码目标的学号:</span>
        <input type="text" autocomplete="off" v-model="id" class="el-input__inner addinput" />
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="student_account">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: 'resetpsw',
    data(){
      return{
        id: "",
        dialogFormVisible: false,
      }
    },
    mounted(){
      //setTimeout(this.showDialog,500);
      //this.showDialog();
    },
    methods:{
      resetpsw(){
        this.$axios
          .post("/manager/reset_psw",{id:this.id})
          .then(successResponse => {
            if (successResponse != null) {
              alert("重置成功"+" 学号："+successResponse.data.id+" 密码："+successResponse.data.psw);
            }else{
              alert("重置失败");
            }
          })
          .catch(failResponse => {});
      },
      showDialog(){
        this.dialogFormVisible=true;
      },
    },
  }
</script>

<style scoped>
</style>
