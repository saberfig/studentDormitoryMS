<template>
  <div>
    <div class="navbar" role="navigation">
      <div class="navbar-header">
        <div class="logo">
          <h1>学生宿舍管理系统</h1>
        </div>
      </div>
      <div class="pull-right nav-right">
        <label>
          欢迎回来
          <i>{{id}}</i>
        </label>
        <a href="javascript:;" data-toggle="modal" data-target="#confirmModal" @click="dialogFormVisible1=true">
          <i class="iconfont icon-tuichudenglu"></i>退出
        </a>
      </div>
    </div>
    <div class="template-page-wrapper" id="box">
      <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
          <li>
            <router-link to="info">
              <i class="iconfont icon-zhuye"></i>主页
            </router-link>
          </li>

          <li>
            <router-link to="notification">
              <i class="iconfont icon-zhuye"></i>通知
            </router-link>
          </li>

          <li>
            <router-link to="changepsw">
              <i class="iconfont icon-zhuye"></i>更改密码
            </router-link>
          </li>
        </ul>
      </div>
      <el-dialog title="" :visible.sync="dialogFormVisible1" width="20%">
        <el-form class="dialog">
          <span style="margin-left:80px;font-size:20px;font-weight:500">退出登录？</span>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="clearUser"  >确 定</el-button>
        </div>
      </el-dialog>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <transition enter-active-class="animated fadeIn">
            <router-view></router-view>
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "nav_student",
  data() {
    return {
      id: this.COMMON.id,
      dialogFormVisible1:false
    };
  },
  methods: {
    clearUser() {
      this.COMMON.clearUser();
      this.$router.replace('/NewLogin')
    }
  },
  mounted: function() {
    $(".templatemo-sidebar-menu li.sub a").click(function() {
      if (
        $(this)
          .parent()
          .hasClass("open")
      ) {
        $(this)
          .parent()
          .removeClass("open");
      } else {
        $(this)
          .parent()
          .addClass("open");
      }
    });
  }
};
</script>

<style scoped>
.temp {
  margin-top: 15%;
}
</style>
