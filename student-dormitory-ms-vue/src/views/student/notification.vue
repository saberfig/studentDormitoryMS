<template>

  <div class="notice">
<!--    <div>notice</div>-->
<!--    <div>-->
<!--      &lt;!&ndash;-->
<!--      <section>-->
<!--        &lt;!&ndash;公告区域1&ndash;&gt;-->
<!--        <div class="timeline-block">-->
<!--          &lt;!&ndash;标题&ndash;&gt;-->
<!--          <div class="timeline-title-info">-->
<!--            <div class="timeline-title">test停水公告</div>-->
<!--            <div class="timeline-time">test2020-05-02</div>-->
<!--          </div>-->
<!--          &lt;!&ndash;内容&ndash;&gt;-->
<!--          <div class="timeline-info">-->
<!--            <div class="timeline-content">test学九公寓由于管道施工，将于今日中午12：00开始停水，恢复时间另行通知，望学生周知</div>-->
<!--          </div>-->
<!--        </div>-->
<!--        &lt;!&ndash;end 公告区域&ndash;&gt;-->
<!--      </section>-->
<!--    </div>-->
<!--    <ul id="example-1">-->
<!--        <div >公告</div>-->
<!--        <div class="timeline-info">-->
<!--          <li v-for="item in test" :key="item.content">-->
<!--            <div class="timeline-time">{{ item.date }}</div>-->
<!--            <div class="timeline-content">{{ item.content }}</div>-->
<!--          </li>-->
<!--        </div>-->
<!--    </ul>-->
<!--    <dvi>##############################################################</dvi>-->
<!--    -->
    <ul id="example-2">
      <div >公告</div>
      <div class="timeline-info">
        <li v-for="item in responseResult" :key="item.content">
          <div >{{ item.stringdate }}</div>
          <div class="timeline-content">{{ item.content }}</div>
        </li>
      </div>
    </ul>

  </div>
</template>

<script>
  export default {
    name: "notification",
    data() {
      return {
        request: {
          userid: this.COMMON.id,
        },
        responsestudentnotice:{
          date: "",
          content: "",
        },
        test: [{date:"2020-5-11",content:"公告1"},{date:"2020-5-12",content:"公告2"},{date:"2020-5-13",content:"公告3"}],
        responseResult: [
          {
            content:"",
            date:"",
            stringdate:"",
          }
          ],
      };
    },
    mounted:function(){
      this.$axios
        .post("/studentnotice", {
          id: this.COMMON.id,
        })
        .then(successResponse => {
          console.log("have success response")
          console.log(successResponse)
          this.responseResult = successResponse.data
          console.log(this.responseResult)
        })
        .catch(failResponse => {
          console.log("have fail response")
        });
    }


  }
</script>

<style scoped>
  .notice{
    margin-top: 20px;
    margin-left: 20px;
  }
</style>
