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
        <a href="javascript:;" data-toggle="modal" data-target="#confirmModal">
          <i class="iconfont icon-tuichudenglu"></i>退出
        </a>
      </div>
    </div>
    <div class="template-page-wrapper" id="box">
      <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
          <li>
            <router-link to="index">
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
                <router-link to="xiaoqu">校区</router-link>
              </li>
              <li>
                <router-link to="sushe">宿舍</router-link>
              </li>
              <li>
                <router-link to="fangjian">房间</router-link>
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
                <!-- <router-link to="resetpsw">重置密码</router-link> -->
                <a @click="dialogFormVisible = true">重置密码</a>
              </li>
            </ul>
          </li>
          <li class="sub">
            <a href="javascript:;">
              <i class="iconfont icon-ruzhu"></i>学生入住管理
              <div class="pull-right">
                <span class="caret"></span>
              </div>
            </a>
            <ul class="templatemo-submenu">
              <li>
                <router-link to="dorm_exchange">宿舍对调</router-link>
              </li>
              <li>
                <router-link to="student_in">学生入住</router-link>
              </li>
              <li>
                <router-link to="student_out">学生退宿</router-link>
              </li>
            </ul>
          </li>
          <li>
            <a href="javascript:;">
              <i class="iconfont icon-xitongrizhi"></i>系统日志
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
              <a href="/SuperManagerLogin" class="btn btn-primary" @click="clearUser">是</a>
              <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
            </div>
          </div>
        </div>
      </div>
      <el-dialog title="添加宿舍楼" :visible.sync="dialogFormVisible">
        <el-form class="dialog">
          <span>重置密码目标的学号:</span>
          <input type="text" autocomplete="off" v-model="studentId" class="el-input__inner">
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="resetpsw">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "nav_manager",
  num:0,
  data() {
    return {
      id: this.COMMON.id,
      studentId:"",
      dialogFormVisible:false,
    };
  },
  methods: {
    clearUser() {
      this.COMMON.clearUser();
    },
    resetpsw(){
        this.$axios
          .post("/manager/reset_psw",{id:this.studentId})
          .then(successResponse => {
            if (successResponse != null) {
              alert("重置成功"+" 学号："+successResponse.data.id+" 密码："+successResponse.data.psw);
            }else{
              alert("重置失败");
            }
          })
          .catch(failResponse => {});
      },
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
