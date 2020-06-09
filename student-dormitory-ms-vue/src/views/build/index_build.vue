<template>
  <div>
    <div>
      <table width="100%" class="templatemo-table-fj">
        <caption>
          <h2>寝室楼信息</h2>
        </caption>
        <thead>
          <tr>
            <th>校区</th>
            <th>寝室楼</th>
            <th>学生人数</th>
            <th>管理员姓名</th>
          </tr>
        </thead>
        <tr>
          <td>{{name.campusName}}</td>
          <td>{{name.dormName}}</td>
          <td>{{info.length}}</td>
          <td>{{name.maDormName}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "index_build",
  data() {
    return{
      name:
      {
      dormName:'',
      campusName:'',
      maDormName:'',
      },
      info:[
      {
      dormName:'',
      campusName:'',
      maDormName:'',
      }
      ]
    }
  },
  mounted: function() {
    this.$axios
      .post("/nav_build/index", {
        id: this.COMMON.id,
              })
              .then(successResponse => {
                this.info = successResponse.data
                this.name = this.info[0]
              })
              .catch(failResponse  => {
              });
  }
};
</script>

<style scoped>
</style>
