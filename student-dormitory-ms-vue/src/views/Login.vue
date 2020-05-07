<template>
  <div class="temp">
    用户名:
    <input type="text" v-model="loginForm.username" placeholder="请输入用户名" />
    <br />
    <br />密码：
    <input type="password" v-model="loginForm.password" placeholder="请输入密码" />
    <br />
    <br />
    <button v-on:click="login">登录</button>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
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
  }
};
</script>

<style scoped>
.temp {

  width:100%;
  height: 100%;

  background-repeat: no-repeat;
  background-size:100% 100%;
  background-attachment:fixed;
  margin-top: 15%;

}
</style>
