<template>
  <div>
      <div class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="logo">
				<h1>学生宿舍管理系统</h1>
			</div>
		</div>
	</div>
	<div class="template-page-wrapper" id="box">
		<div class="navbar-collapse collapse templatemo-sidebar">
			<ul class="templatemo-sidebar-menu">
				<li class="welcomeBack">欢迎回来 <i>{{id}}</i></li>
				<li class="active"><router-link to="index_build"><i class="iconfont icon-zhuye"></i>主页</router-link></li>
				<li class="sub">
					<a href="javascript:;">
						<i class="iconfont icon-sushe"></i> 宿舍信息管理 <div class="pull-right"><span class="caret"></span></div>
					</a>
					<ul class="templatemo-submenu">
						<!---<li><router-link to="fangjian_build">房间信息</router-link></li>--->
						<li><router-link to="chuangwei_build">学生信息</router-link></li>
					</ul>
				</li>
				<li class="sub" >
					<a href="javascript:;">
						<i class="iconfont icon-xue"></i> 学生信息管理 <div class="pull-right"><span class="caret"></span></div>
					</a>
					<ul class="templatemo-submenu">
						<li><router-link to="jiluweiji">查看学生违纪信息</router-link></li>
						<li><router-link to="jlweiji">记录学生违纪信息</router-link></li>
					</ul>
					<ul class="templatemo-submenu">
						<li><router-link to="fangkexinxi">查看访客信息</router-link></li>
						<li><router-link to="jlfangkexinxi">记录访客信息</router-link></li>
					</ul>
				</li>
				<li class="sub" >
					<a href="javascript:;">
						<i class="iconfont icon-xitongrizhi"></i> 日志信息 <div class="pull-right"><span class="caret"></span></div>
					</a>
					<ul class="templatemo-submenu">
						<li><router-link to="xiaotz">校方通知</router-link></li>
					</ul>
					<ul class="templatemo-submenu">
						<li><router-link to="xuetz">查看已发布学生通知</router-link></li>
						<li><router-link to="fbxuetz">发布学生通知</router-link></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" data-toggle="modal" data-target="#confirmModal">
						<i class="iconfont icon-tuichudenglu"></i>退出登录
					</a>
				</li>
			</ul>
		</div>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          <router-view></router-view>
        </div>
      </div>
      <!-- Modal -->
      <div
        class="modal fade"
        id="confirmModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">确认退出？</h4>
            </div>
            <div class="modal-footer">
              <a href="/DormManagerLogin" class="btn btn-primary" @click="clearUser">Yes</a>

              <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
              <el-button icon="el-icon-close" circle></el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "nav_build",
  data() {
    return {
      id: this.COMMON.id
    };
  },
  methods: {
    clearUser() {
      this.COMMON.clearUser();
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
