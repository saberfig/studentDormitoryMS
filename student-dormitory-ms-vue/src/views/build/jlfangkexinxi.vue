<template>
  <div>
    <div>
        <h2>登记访客信息</h2>
        <el-form   ref="dataForm">
        <el-form-item label="来访时间"  prop="timeIn"><el-input v-model="form.timeIn" type="datetime-local"></el-input></el-form-item><br>
        <el-form-item label="访客姓名"  prop="vname"><el-input v-model="form.vname"></el-input></el-form-item><br>
        <el-form-item label="访客身份证号"  prop="videntity"><el-input v-model="form.videntity"></el-input></el-form-item><br>
        <el-form-item label="访客理由"  prop="reason"><el-input v-model="form.reason"></el-input></el-form-item><br>
        <el-form-item label="相关学号"  prop="studentId"><br><el-input v-model="form.studentId" ></el-input></el-form-item><br>
        <el-button type="primary" @click="onSubmit">提交访客信息</el-button><br>
        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "jlfangkexinxi",
  data() {
    return{
      form: {
        timeIn:'',
        vname:'',
        videntity:'',
        reason:'',
        studentId:'',
      },
    }
  },
  methods:{
    onSubmit(){
      if(this.form.timeIn===""||this.form.vname===""||this.form.videntity==""||this.form.reason===""||this.form.studentId===""){
      alert("请确保没有漏填的内容")
      }
      else{
            this.$axios
              .post("/nav_build/djfangkexinxi",{
                timeIn: this.form.timeIn,
                vname: this.form.vname,
                videntity: this.form.videntity,
                reason: this.form.reason,
                studentId: this.form.studentId
              }).then(resp=> {
              if (resp && resp.status === 200) {
                this.$emit('onSubmit');
                alert("发布成功");
              }
          })
      }
   },
  }
};
</script>

<style scoped>
</style>
