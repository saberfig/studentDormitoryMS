<template>
  <div class="content">
    <div class="title">我发布的公告</div>
    <div class="timeline">
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in notice"
          :key="index"
          :icon="activity.icon"
          :type="activity.type"
          :color="activity.color"
          :size="activity.size"
          :timestamp="activity.time"
        >{{activity.description}}</el-timeline-item>
      </el-timeline>
    </div>
    <div class="title">发布新公告</div>
    <div class="timeline">
      <el-date-picker v-model="form.time" type="datetime" placeholder="选择发布时间"></el-date-picker>
      <el-input v-model="form.description" placeholder="请填写公告内容" style="width:40%"></el-input>
      <el-button type="primary" @click="onSubmit">发布</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "xuetz",
  data() {
    return {
      notice: [
        {
          id: "",
          time: "",
          description: "",
          dormManagerId: ""
        }
      ],
      form: {
        dormManagerId: "",
        description: "",
        time: ""
      }
    };
  },
  methods: {
    getNotice() {
      this.$axios
        .post("/nav_build/xsxuetz", {
          id: this.COMMON.id
        })
        .then(successResponse => {
          this.notice = successResponse.data;
          let a;
          for (a in this.notice) {
            this.notice[a].time = this.getDate(this.notice[a].time);
            this.notice[a].type = "primary";
            this.notice[a].color = "#409EFF";
          }
        })
        .catch(failResponse => {});
    },
    getDate(time) {
      var tt = time.replace(/T/g, " ").replace(/\.000\+0000/, "");
      return tt;
    },
    onSubmit() {
      if (this.form.time === "" || this.form.description === "") {
        this.$message({
          message: "发布失败，请检查要发布的内容",
          type: "error"
        });
      } else {
        this.$axios
          .post("/nav_build/xuetz", {
            dormManagerId: this.COMMON.id,
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
              this.getNotice();
            }
          });
      }
    }
  },
  mounted() {
    this.getNotice();
  }
};
</script>

<style scoped>
.content {
  margin: 2rem auto;
}
.title {
  font-size: 2rem;
  margin: 2rem;
}
.timeline {
  padding-left: 10rem;
}
.el-table th,
.el-table td {
  text-align: center;
}
</style>
