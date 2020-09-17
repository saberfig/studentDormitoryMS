<template>
  <div>
    <div class="title">本楼违纪信息</div>
    <el-table :data="offeninfo" style="width: 80%; margin:auto;" border>
      <el-table-column label="学生姓名">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学号">
        <template slot-scope="scope">
          <p>{{ scope.row.studentId }}</p>
        </template>
      </el-table-column>
      <el-table-column label="详细信息">
        <template slot-scope="scope">{{ scope.row.description }}</template>
      </el-table-column>
    </el-table>

    <div class="title">添加违纪信息</div>
    <div style="width:30%;margin-left:10%">
      <el-date-picker v-model="form.time" type="datetime" placeholder="选择违纪时间"></el-date-picker>
      <br />
      <br />
      <el-input v-model="form.studentId" placeholder="违纪学生学号" style="width:47%;"></el-input>
      <br />
      <br />
      <el-input v-model="form.description" placeholder="违纪内容" style="width:47%;"></el-input>
      <el-button type="primary" @click="onSubmit" style="float:right;">提交违纪信息</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "jiluweiji",
  data() {
    return {
      offeninfo: [
        {
          id: "",
          name: "",
          studentId: "",
          description: ""
        }
      ],
      form: {
        studentId: "",
        description: "",
        time: ""
      }
    };
  },

  methods: {
    getVio() {
      this.$axios
        .post("/nav_build/xsjiluweiji", {
          id: this.COMMON.id
        })
        .then(successResponse => {
          this.offeninfo = successResponse.data;
        })
        .catch(failResponse => {});
    },
    onSubmit() {
      if (
        this.form.studentId === "" ||
        this.form.description === "" ||
        this.form.time === ""
      ) {
        this.$message({
          message: "发布失败，请检查要发布的内容",
          type: "error"
        });
      } else {
        this.$axios
          .post("/nav_build/jiluweiji", {
            studentId: this.form.studentId,
            time: this.form.time,
            description: this.form.description
          })
          .then(resp => {
            if (resp && resp.status === 200) {
              this.$emit("onSubmit");
              this.$message({
                message: "发布成功",
                type: "success"
              });
              this.getVio();
            }
          });
      }
    }
  },

  mounted() {
    this.getVio();
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
