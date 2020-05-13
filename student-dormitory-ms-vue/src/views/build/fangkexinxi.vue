<template>
  <div>
    <div>
      <table width="100%" class="templatemo-table-fj">
        <caption>
          <h2>访客信息</h2>
        </caption>
        <thead>
          <tr>
            <th>访客姓名</th>
            <th>访客身份</th>
            <th>相关学号</th>
            <th>来访时间</th>
            <th>理由</th>
          </tr>
        </thead>
        <tr>
          <td>张三</td>
          <td>11111111111111</td>
          <td>201789898</td>
          <td>2020.9.20 18：11</td>
          <td>XXXXXXXXXXXXXXXXXXX</td>
        </tr>
      </table>
    </div>
    <div>
      <button type="button">登记访客信息</button>
    </div>
    <div>
        <h2>登记访客信息</h2>
        <el-form  class="templatemo-form-weiji" ref="dataForm">
        <el-form-item label="来访时间"  prop="timeIn"><el-input v-model="form.timeIn" type="datetime-local"></el-input></el-form-item><br>
        <el-form-item label="访客姓名"  prop="vname"><el-input v-model="form.vname"></el-input></el-form-item><br>
        <el-form-item label="访客身份证号"  prop="videntity"><el-input v-model="form.videntity"></el-input></el-form-item><br>
        <el-form-item label="访客理由"  prop="reason"><el-input v-model="form.reason"></el-input></el-form-item><br>
        <el-form-item label="相关学号"  prop="studentId"><br><el-input v-model="form.studentId" class="templatemo-input-weiji"></el-input></el-form-item><br>
        <el-button type="primary" @click="onSubmit">发布公告</el-button><br>
        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "fangkexinxi",
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
      this.$axios
        .post("/nav_build/djfangkexinxi",{
          timeIn: this.form.timeIn,
          vname: this.form.vname,
          videntity: this.form.videntity,
          reason: this.form.reason,
          studentId: this.form.studentId
        }).then(resp=> {
        if (resp && resp.status === 200) {
          this.$emit('onSubmit')
        }
    })
   },
  }
};
</script>

<style scoped>
</style>
