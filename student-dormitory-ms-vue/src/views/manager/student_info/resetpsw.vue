<template>
  <div class="resetpsw">
    <div>
      <label>重置密码目标的学号：</label>
      <input type="text" v-model="id">
    </div>
    <!--<button type="reset" class="btn btn-success" data-toggle="modal" data-target="#reset" v-on:click="resetpsw">修改</button>-->
    <button type="reset" class="btn btn-success" v-on:click="resetpsw">修改</button>
  </div>
</template>

<script>
  export default {
    name: 'resetpsw',
    data(){
      return{
        id: "",
      }
    },
    methods:{
      resetpsw(){
        this.$axios
          .post("/manager/reset_psw",{id:this.id})
          .then(successResponse => {
            console.log("0000");
            if (successResponse != null) {
              console.log(1111);
              alert("重置成功"+" 学号："+successResponse.data.id+" 密码："+successResponse.data.psw);
            }else{
              console.log(2222);
              alert("重置失败");
            }
            //this.get_student_bed();
          })
          .catch(failResponse => {});
      },
    }
  }
</script>

<style scoped>
</style>
