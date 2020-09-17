<template>
  <div class="content">
    <div class="title">公告详情</div>
    <div class="timeline">
      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in noticeList"
          :key="index"
          :icon="activity.icon"
          :type="activity.type"
          :color="activity.color"
          :size="activity.size"
          :timestamp="activity.time"
        >{{activity.description}}</el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: "xiaotz",
  data() {
    return {
      noticeList: ""
    };
  },
  mounted() {
    this.getNotice();
  },

  methods: {
    getDate(time) {
      var tt = time.replace(/T/g, " ").replace(/:00\.000\+0000/, "");
      return tt;
    },
    getNotice() {
      this.$axios.get("/nav_build/xiaotz").then(resp => {
        if (resp && resp.status === 200) {
          this.noticeList = resp.data;
          let a;
          for (a in this.noticeList) {
            this.noticeList[a].time = this.getDate(this.noticeList[a].time);
            this.noticeList[a].type = "primary";
            this.noticeList[a].color = "##409EFF";
          }
        }
      });
    }
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
