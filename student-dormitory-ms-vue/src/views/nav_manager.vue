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
          <li class="welcomeBack">
            欢迎回来
            <i>XXX</i>
          </li>
          <li>
            <router-link to="/Index">
              <i class="iconfont icon-zhuye"></i>主页
            </router-link>
          </li>
          <li class="sub">
            <a href="javascript:;">
              <i class="iconfont icon-sushe"></i>宿舍信息管理
              <div class="pull-right">
                <span class="caret"></span>
              </div>
            </a>
            <ul class="templatemo-submenu">
              <li>
                <router-link to="/xiaoqu">校区</router-link>
              </li>
              <li>
                <router-link to="/sushe">宿舍</router-link>
              </li>
              <li>
                <router-link to="/fangjian">房间</router-link>
              </li>
            </ul>
          </li>
          <li class="sub">
            <a href="javascript:;">
              <i class="iconfont icon-xue"></i>学生信息管理
              <div class="pull-right">
                <span class="caret"></span>
              </div>
            </a>
            <ul class="templatemo-submenu">
              <li>
                <router-link to="/resetpsw">重置密码</router-link>
              </li>
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
          <transition enter-active-class="animated fadeIn">
            <router-view></router-view>
          </transition>
        </div>
      </div>
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
              <a href="#" class="btn btn-primary">是</a>
              <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
            </div>
          </div>
        </div>
      </div>
      <div
        class="modal fade"
        id="reset"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">重置密码成功！</h4>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'nav_manager'
  }
</script>

<style scoped>
  .temp{
    margin-top: 15%;
  }
</style>
