<template>
  <div>
    <div class="title">来访记录</div>
    <el-table :data="vinfo" style="width: 80%; margin:auto;" border>
      <el-table-column label="访客姓名">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.vname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="访客身份证号">
        <template slot-scope="scope">
          <p>{{ scope.row.videntity }}</p>
        </template>
      </el-table-column>
      <el-table-column label="相关学号">
        <template slot-scope="scope">{{ scope.row.studentId }}</template>
      </el-table-column>
      <el-table-column label="来访时间">
        <template slot-scope="scope">
          <p>{{ scope.row.timeIn }}</p>
        </template>
      </el-table-column>
      <el-table-column label="理由">
        <template slot-scope="scope">
          <p>{{ scope.row.reason }}</p>
        </template>
      </el-table-column>
    </el-table>

    <div class="title">登记访客信息</div>
    <el-date-picker v-model="form.timeIn" type="datetime" placeholder="来访时间"></el-date-picker>
    <el-input v-model="form.vname" placeholder="访客姓名"></el-input>
    <el-input v-model="form.videntity" placeholder="身份证号"></el-input>
    <el-input v-model="form.reason" placeholder="来访理由"></el-input>
    <el-input v-model="form.studentId" placeholder="相关学号"></el-input>
    <el-button type="primary" @click="onSubmit" style="margin-left:31%">提交访客信息</el-button>
  </div>
</template>

<script>
export default {
  name: "fangkexinxi",
  data() {
    return {
      vinfo: [
        {
          id: "",
          timeIn: "",
          vname: "",
          videntity: "",
          reason: "",
          studentId: ""
        }
      ],
      form: {
        timeIn: "",
        vname: "",
        videntity: "",
        reason: "",
        studentId: ""
      }
    };
  },
  methods: {
    getGuest(){
      this.$axios
      .post("/nav_build/xsfangkexinxi", {
        id: this.COMMON.id
      })
      .then(successResponse => {
        this.vinfo = successResponse.data;
        let a;
        for (a in this.vinfo) {
          this.vinfo[a].timeIn = this.getDate(this.vinfo[a].timeIn);
        }
      })
      .catch(failResponse => {});
    },
    getDate(time) {
      var tt = time.replace(/T/g, " ").replace(/\.000\+0000/, "");
      return tt;
    },
    onSubmit() {
      if (
        this.form.timeIn === "" ||
        this.form.vname === "" ||
        this.form.videntity == "" ||
        this.form.reason === "" ||
        this.form.studentId === ""
      ) {
        this.$message({
          message: "发布失败，请检查要发布的内容",
          type: "error"
        });
      } else {
        this.$axios
          .post("/nav_build/djfangkexinxi", {
            timeIn: this.form.timeIn,
            vname: this.form.vname,
            videntity: this.form.videntity,
            reason: this.form.reason,
            studentId: this.form.studentId
          })
          .then(resp => {
            if (resp && resp.status === 200) {
              this.$emit("onSubmit");
              this.$message({
                message: "发布成功",
                type: "success"
              });
              this.getGuest();
            }
          });
      }
    }
  },
  mounted() {
    this.getGuest();
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
.el-input{
  width: 30%;
  display: block;
  margin: 1rem 10rem;
}
</style>
