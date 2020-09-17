<template>
  <div>
    <div>
      <div class="title">本楼基本信息</div>
      <el-table :data="info" style="width: 80%; margin:auto;" border>
        <el-table-column label="校区">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.campusName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="寝室楼">
          <template slot-scope="scope">
            <p>{{ scope.row.dormName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="学生人数">
          <template slot-scope="scope">{{ info.length }}</template>
        </el-table-column>
        <el-table-column label="管理员姓名">
          <template slot-scope="scope">
            <p>{{ scope.row.maDormName }}</p>
          </template>
        </el-table-column>
      </el-table>

      <div class="title">本楼学生</div>
      <el-table :data="mastudentinfo" style="width: 80%; margin:auto;" border>
        <el-table-column label="寝室号">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.roomId }}</span>
          </template>
        </el-table-column>
        <el-table-column label="学生姓名">
          <template slot-scope="scope">
            <p>{{ scope.row.name }}</p>
          </template>
        </el-table-column>
        <el-table-column label="学生性别">
          <template slot-scope="scope">{{ scope.row.sex }}</template>
        </el-table-column>
        <el-table-column label="学号">
          <template slot-scope="scope">
            <p>{{ scope.row.id }}</p>
          </template>
        </el-table-column>
        <el-table-column label="专业">
          <template slot-scope="scope">
            <p>{{ scope.row.major }}</p>
          </template>
        </el-table-column>
        <el-table-column label="联系方式">
          <template slot-scope="scope">
            <p>{{ scope.row.tel }}</p>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "index_build",
  data() {
    return {
      info: [
        {
          dormName: "",
          campusName: "",
          maDormName: ""
        }
      ],
      mastudentinfo: [
        {
          id: "",
          name: "",
          roomId: "",
          sex: "",
          major: "",
          tel: ""
        }
      ]
    };
  },
  methods: {
    getDormInfo() {
      this.$axios
        .post("/nav_build/index", {
          id: this.COMMON.id
        })
        .then(successResponse => {
          this.info = successResponse.data;
        })
        .catch(failResponse => {});
    },
    getStudentInfo() {
      this.$axios
        .post("/nav_build/chuangwei_build", {
          id: this.COMMON.id
        })
        .then(successResponse => {
          this.mastudentinfo = successResponse.data;
        })
        .catch(failResponse => {});
    }
  },
  mounted() {
    this.getDormInfo();
    this.getStudentInfo();
  }
};
</script>

<style scoped>
.title {
  font-size: 2rem;
  margin: 2rem;
}
.el-table th,
.el-table td {
  text-align: center;
}
</style>
