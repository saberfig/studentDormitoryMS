<template>
  <div>
    <div class="title">我的信息</div>
    <el-table :data="responsestudentinfo" style="width: 80%; margin:auto;" border>
      <el-table-column label="校区">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.campusname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="寝室楼">
        <template slot-scope="scope">
          <p>{{ scope.row.dormnum }}号公寓</p>
        </template>
      </el-table-column>
      <el-table-column label="房间号">
        <template slot-scope="scope">{{ scope.row.roomnum }}</template>
      </el-table-column>
      <el-table-column label="床号">
        <template slot-scope="scope">
          <p>{{ scope.row.bednum }}</p>
        </template>
      </el-table-column>
    </el-table>

    <notification></notification>
  </div>
</template>

<script>
import notification from "./notification";
export default {
  name: "info",
  data() {
    return {
      request: {
        userid: this.COMMON.id
      },
      responsestudentinfo: [
        {
          campusname: "",
          dormnum: "",
          roomnum: "",
          bednum: ""
        }
      ]
    };
  },
  components: {
    notification
  },
  mounted: function() {
    this.$axios
      .post("/studentinfo", {
        id: this.COMMON.id
      })
      .then(successResponse => {
        (this.responsestudentinfo[0].campusname =
          successResponse.data.campusname),
          (this.responsestudentinfo[0].dormnum = successResponse.data.dormnum),
          (this.responsestudentinfo[0].roomnum = successResponse.data.roomnum),
          (this.responsestudentinfo[0].bednum = successResponse.data.bednum);
      })
      .catch(failResponse => {});
  }
};
</script>

<style scoped>
.title {
  font-size: 2rem;
  margin: 2rem;
}
</style>
