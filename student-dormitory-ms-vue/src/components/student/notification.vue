<template>
  <div >
    <div class="title">我需留意</div>
    <div class="timeline">
      <el-timeline>
      <el-timeline-item
        v-for="(activity, index) in responseResult"
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
  name: "notification",
  data() {
    return {
      request: {
        userid: this.COMMON.id
      },
      responsestudentnotice: {
        date: "",
        content: ""
      },
      responseResult:'',
    };
  },
  methods:{
    getDate(time) {
      var tt = time.replace(/T/g, " ").replace(/\.000\+0000/, "");
      return tt;
    },
  },
  mounted: function() {
    this.$axios
      .post("/studentnotice", {
        id: this.COMMON.id
      })
      .then(successResponse => {
        this.responseResult = successResponse.data;
        let a;
        for(a in this.responseResult){
          this.responseResult[a].color = '#409EFF'
          this.responseResult[a].type = 'primary'
          this.responseResult[a].time = this.getDate(this.responseResult[a].time)
        }
      })
      .catch(failResponse => {
      });
  }
};
</script>

<style scoped>
.title {
  font-size: 2rem;
  margin: 2rem;
}
.timeline {
  padding-left: 10rem;
}
</style>
