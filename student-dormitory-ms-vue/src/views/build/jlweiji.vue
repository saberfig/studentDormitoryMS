<template>
  <div>
			<div>
			</div>
			<div>
					<h2>
      		违纪信息登记</h2>
				  <el-form  class="templatemo-form-weiji" ref="dataForm">
					<el-form-item label="记录日期"  prop="time">
					<br><el-input v-model="form.time" type="date"></el-input></el-form-item><br>
					<el-form-item label="学号"  prop="studentId">
					<br><el-input v-model="form.studentId" class="templatemo-input-weiji"></el-input></el-form-item><br>
					<el-form-item label="描述"  prop="description"><br>
					<br><el-input v-model="form.description" class="templatemo-input-weiji"></el-input></el-form-item><br>
					<el-button type="primary" @click="onSubmit">提交违纪信息</el-button><br>
				</el-form>
			</div>
		</div>
</template>

<script>
export default {
  name: "jlweiji",
  data() {
    return{
      form: {
        studentId:'',
        description:'',
        time:'',
      },
    }
  },

  methods:{
    onSubmit(){
      if(this.form.studentId===""||this.form.description===""||this.form.time===''){
      alert("请确保没有漏填的内容")
  }
  else{
  this.$axios
          .post("/nav_build/jiluweiji",{
            studentId: this.form.studentId,
            time: this.form.time,
            description: this.form.description
          }).then(resp=> {
          if (resp && resp.status === 200) {
            this.$emit('onSubmit');
            alert("发布成功");
          }
      })}
  }
 }


};
</script>

<style scoped>
</style>
