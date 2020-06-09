<template>
  <div class="resetpsw">
    <div>
      <label>需要互换的学生1学号：</label>
      <input type="text" v-model="id_1">
    </div>
    <div>
      <label>需要互换的学生2学号：</label>
      <input type="text" v-model="id_2">
    </div>
    <button type="reset" class="btn btn-success" v-on:click="exchange">确认互换</button>
  </div>
</template>

<script>
    export default {
        name: "dorm_exchange",
      data(){
          return{
            id_1:"",
            id_2:"",
          }
      },
      methods:{
        exchange(){
          this.$axios
            .post("/manager/dorm_exchange",[{studentId:this.id_1},{studentId:this.id_2}])
            .then(successResponse => {
              if (successResponse != null) {
                alert("宿舍交换成功");
              }else{
                alert("宿舍交换失败");
              }
            })
            .catch(failResponse => {});
        }
      }
    }
</script>

<style scoped>

</style>
