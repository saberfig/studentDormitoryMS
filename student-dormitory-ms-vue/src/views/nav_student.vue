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
            <i>{{id}}</i>
          </li>
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
            <a href="javascript:;" data-toggle="modal" data-target="#confirmModal" onclick="">
              <i class="iconfont icon-tuichudenglu"></i>退出登录
            </a>
          </li>
        </ul>
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
              <a  href="/StudentLogin" class="btn btn-primary" @click="clearUser">是</a>
              <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
            </div>
          </div>
        </div>
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
        id="reset"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel"
        aria-hidden="true"
      >
      </div>
    </div>
  </div>
</template>

<script>


  export default {
    name: 'nav_student',
    data () {
      return {
        id:this.COMMON.id,
      }
    },
    methods:{
      clearUser(){
        this.COMMON.clearUser()
      }
    },
    mounted:function(){
      $('.templatemo-sidebar-menu li.sub a').click(function(){
        if($(this).parent().hasClass('open')) {
          console.log("aaaaa");
          $(this).parent().removeClass('open');
        } else {
          console.log("bbbb");
          $(this).parent().addClass('open');
        }
      });
    }
  }
</script>

<style scoped>
  .temp{
    margin-top: 15%;
  }

</style>
