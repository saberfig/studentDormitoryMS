<template>
<div>
    <div>
    	<h2>发布通知</h2>
    	<el-form ref="dataForm">
    	<el-form-item label="时间"  prop="time">
    	<br><el-input v-model="form.time" type="datetime-local"></el-input></el-form-item><br>
    	<el-form-item label="描述"  prop="description"><br>
    	<br><el-input v-model="form.description"></el-input></el-form-item><br>
    	<el-button type="primary" @click="onSubmit">发布通知</el-button><br>
    	</el-form>
    	</div>
    	</div>
</template>

<script>
export default {
  name: "fbxuetz",
  data() {
    return{
      form: {
        dormManagerId:'',
        description:'',
        time:'',
      },
  }
},
   methods:{
     onSubmit(){
       if(this.form.time===""||this.form.description===""){
          alert("请确保没有漏填的内容")
       }
       else{
       this.$axios
                .post("/nav_build/xuetz",{
                  dormManagerId: this.COMMON.id,
                  time: this.form.time,
                  description: this.form.description
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
