<template>

  <div>
    <div>notice</div>
    <div>
      <section>
        <!--公告区域1-->
        <div class="timeline-block">
          <!--标题-->
          <div class="timeline-title-info">
            <div class="timeline-title">test停水公告</div>
            <div class="timeline-time">test2020-05-02</div>
          </div>
          <!--内容-->
          <div class="timeline-info">
            <div class="timeline-content">test学九公寓由于管道施工，将于今日中午12：00开始停水，恢复时间另行通知，望学生周知</div>
          </div>
        </div>
        <!--end 公告区域-->
      </section>
    </div>

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
        loginForm: {
          username: "2017211857",
          password: "2017211857"
        },
        responseResult: []
      };
    },
    methods: {
      login() {
        this.$axios
          .post("/login", {
            id: this.loginForm.username,
            psw: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({ path: "/nav_manager/index" });
            }
            if (successResponse.data.code === 400) {
              this.$router.replace({ path: "/failed" });
            }
          })
          .catch(failResponse => {});
      }
    },

  }
</script>

<style scoped>

</style>
