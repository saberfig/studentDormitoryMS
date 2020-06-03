<template>
  <div class="resetps addpadding">
    <div>
      <label>重置密码目标的学号：</label>
      <input type="text" v-model="id">
    </div>
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
            if (successResponse != null) {
              alert("重置成功"+" 学号："+successResponse.data.id+" 密码："+successResponse.data.psw);
            }else{
              alert("重置失败");
            }
          })
          .catch(failResponse => {});
      },
    }
  }
</script>

<style scoped>
</style>
