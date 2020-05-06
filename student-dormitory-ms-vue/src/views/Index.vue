<template>
  <div>
    <div class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <div class="logo">
          <h1>学生宿舍管理系统</h1>
        </div>
      </div>
    </div>
    <div class="template-page-wrapper">
      <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
          <li class="welcomeBack">欢迎回来 <i>XXX</i></li>
          <li class="active"><a href="#"><i class="iconfont icon-zhuye"/>主页</a></li>
          <li class="sub">
            <a href="javascript:;">
              <i class="iconfont icon-sushe"/> 宿舍信息管理 <div class="pull-right"><span class="caret"/></div>
            </a>
            <ul class="templatemo-submenu">
              <li><a href="#">校区</a></li>
              <li><a href="#">宿舍</a></li>
              <li><a href="#">房间</a></li>
              <li><a href="#">床位</a></li>
            </ul>
          </li>
          <li class="sub" >
            <a href="javascript:;">
              <i class="iconfont icon-xue"/> 学生信息管理 <div class="pull-right"><span class="caret"/></div>
            </a>
            <ul class="templatemo-submenu">
              <li><a href="#">校区</a></li>
              <li><a href="#">宿舍</a></li>
              <li><a href="#">房间</a></li>
              <li><a href="#">床位</a></li>
            </ul>
          </li>
          <li>
            <a href="javascript:;" data-toggle="modal" data-target="#confirmModal">
              <i class="iconfont icon-tuichudenglu"/>退出登录
            </a>
          </li>
        </ul>
      </div>

      <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
          Hello World!
        </div>
      </div>
      <!-- Modal -->
      <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">确认退出？</h4>
            </div>
            <div class="modal-footer">
              <a href="#" class="btn btn-primary">Yes</a>
              <button type="button"  class="btn btn-default" data-dismiss="modal">No</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Index'
  }
</script>

<style scoped>

</style>
