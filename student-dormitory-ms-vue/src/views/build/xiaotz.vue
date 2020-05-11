<template>
  <div>
    <section v-for="item in noticekey" :key="item.id">
      <!--公告区域1-->
      <div class="timeline-block">
        <!--标题-->
        <div class="timeline-title-info">
          <div class="timeline-title">公告</div>
          <div class="timeline-time">{{$moment(item.time).format('YYYY-MM-DD')}}</div>
        </div>
        <!--内容-->
        <div class="timeline-info">
          <div class="timeline-content">{{item.description}}</div>
        </div>
      </div>
      <!--end 公告区域-->
    </section>
  </div>
</template>

<script>
export default {
  name: "xiaotz",
  data() {
    return{
      noticekey:[
        {
          id:'',
          time:'',
          description:''
        }
      ]
    }
  },
  mounted: function () {
    this.loadnoticekey()
  },

  methods: {
    loadnoticekey () {
      var _this = this
      this.$axios.get('/nav_build/xiaotz').then(resp => {
      if (resp && resp.status === 200) {
        _this.noticekey = resp.data
      }
    })
  }
},
};
</script>

<style scoped>
</style>
