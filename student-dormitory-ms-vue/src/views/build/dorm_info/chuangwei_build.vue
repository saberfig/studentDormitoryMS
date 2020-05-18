<template>
  <div>
    <div>
      <table width="100%" class="templatemo-table-fj">
        <caption>
          <h2>学生信息</h2>
        </caption>
        <thead>
          <tr>
            <th>寝室号</th>
            <th>学生姓名</th>
            <th>学生性别</th>
            <th>学号</th>
            <th>专业</th>
            <th>联系方式</th>
          </tr>
        </thead>
        <tr v-for="item in mastudentinfo" :key="item.id">
          <td>{{item.roomId}}</td>
          <td>{{item.name}}</td>
          <td>{{item.sex}}</td>
          <td>{{item.id}}</td>
          <td>{{item.major}}</td>
          <td>{{item.tel}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "chuangwei_build",
  data() {
    return{
      mastudentinfo:[
        {
          id:'',
          name:'',
          roomId:'',
          sex:'',
          major:'',
          tel:'',
        }
      ]
    }
  },
  mounted: function() {
    this.$axios
      .post("/nav_build/chuangwei_build", {
        id: this.COMMON.id,
              })
              .then(successResponse => {
                this.mastudentinfo = successResponse.data
              })
              .catch(failResponse  => {
              });
  }
};
</script>

<style scoped>
</style>
