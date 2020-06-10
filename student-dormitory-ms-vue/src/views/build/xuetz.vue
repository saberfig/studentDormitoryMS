<template>
  <div>
    <div>
    <section v-for="item in notice" :key="item.id">
      <!--公告区域1-->
      <div class="timeline-block">
        <!--标题-->
        <div class="timeline-title-info">
          <div class="timeline-title">公告</div>
          <div class="timeline-time">{{$moment(item.time).format('YYYY-MM-DD MM:SS')}}</div>
        </div>
        <!--内容-->
        <div class="timeline-info">
          <div class="timeline-content">{{item.description}}</div>
        </div>
      </div>
      <!--end 公告区域-->
    </section>
    </div>


    </div>
</template>

<script>
export default {
  name: "xuetz",
  data() {
    return{
      notice:[
      {
      id:'',
      time:'',
      description:'',
      dormManagerId:'',
      }
    ]
  }
},
   methods:{
  },
mounted:function(){
        this.$axios
          .post("/nav_build/xsxuetz", {
            id: this.COMMON.id,
                 })
                 .then(successResponse => {
                   this.notice = successResponse.data
                 })
                 .catch(failResponse => {
                 });
             }

};
</script>

<style scoped>
</style>
