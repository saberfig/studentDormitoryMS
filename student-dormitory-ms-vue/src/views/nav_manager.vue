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
        <a href="javascript:;" @click="dialogFormVisible1=true">
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
          <li class="sub">
            <a href="javascript:;">
              <i class="iconfont icon-xue"></i>账号管理
              <div class="pull-right">
                <span class="caret"></span>
              </div>
            </a>
            <ul class="templatemo-submenu">
              <li>
                <a @click="dialogFormVisible = true">重置密码</a>
              </li>
              <li>
                <a @click="dialogFormVisible2 = true">添加账号</a>
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

      <el-dialog title :visible.sync="dialogFormVisible1" width="20%">
        <el-form class="dialog">
          <span style="margin-left:80px;font-size:20px;font-weight:500">退出登录？</span>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="clearUser"  >确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="重置密码" :visible.sync="dialogFormVisible" width="40%">
        <el-form class="dialog">
          <span>重置密码目标的学号:</span>
          <input type="text" autocomplete="off" v-model="studentId" class="el-input__inner addinput" />
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="resetpsw">确 定</el-button>
        </div>
      </el-dialog>

      <el-dialog title="添加账号" :visible.sync="dialogFormVisible2" width="30%">
        <el-form class="dialog">
          <el-select v-model="addType" placeholder="添加账号类别">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form>
        <el-form class="dialog">
          <input type="text" autocomplete="off" v-model="addedId" class="el-input__inner addinput"  placeholder="要添加的账号 "/>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="addId">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "nav_manager",
  num: 0,
  data() {
    return {
      id: this.COMMON.id,
      studentId: "",
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      addedId: "",
      addType: "",
      options: [
        {
          value: "学生",
          label: "学生"
        },
        {
          value: "管理员",
          label: "管理员"
        }
      ]
    };
  },
  methods: {
    addId() {},
    clearUser() {
      this.COMMON.clearUser();
      this.$router.replace('/NewLogin')
    },
    resetpsw() {
      this.$axios
        .post("/manager/reset_psw", { id: this.studentId })
        .then(successResponse => {
          if (successResponse != null) {
            alert(
              "重置成功" +
                " 学号：" +
                successResponse.data.id +
                " 密码：" +
                successResponse.data.psw
            );
          } else {
            alert("重置失败");
          }
        })
        .catch(failResponse => {});
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
.templatemo-submenu li a:hover {
  cursor: pointer;
}
.addinput{
  width: 222px;
  margin-top:20px;
}
.width{
  width: 500px
}
</style>
