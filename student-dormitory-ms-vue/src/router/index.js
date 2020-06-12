import Vue from 'vue'
import Router from 'vue-router'
import debug from "../views/debug";

import Login from '../views/Login'
import AppFailed from "../views/AppFailed"
import Mylogin from "../views/Mylogin"
import nav_student from "../views/nav_student"
import nav_manager from "../views/nav_manager"
import nav_build from "../views/nav_build"

import index from '../views/manager/index'
import xiaoqu from "../views/manager/dorm_info/xiaoqu"
import sushe from "../views/manager/dorm_info/sushe"
import fangjian from "../views/manager/dorm_info/fangjian"
import student_account from "../views/manager/student_info/student_account";
import build_account from "../views/manager/student_info/build_account";
import student_in from "../views/manager/ruzhu/student_in";
import student_out from "../views/manager/ruzhu/student_out";

import chuangwei_build from "../views/build/dorm_info/chuangwei_build"
import fangjian_build from "../views/build/dorm_info/fangjian_build"
import fangkexinxi from "../views/build/fangkexinxi"
import jlfangkexinxi from "../views/build/jlfangkexinxi"
import index_build from "../views/build/index_build"
import jiluweiji from "../views/build/jiluweiji"
import jlweiji from "../views/build/jlweiji"
import xiaotz from "../views/build/xiaotz"
import xuetz from "../views/build/xuetz"
import fbxuetz from "../views/build/fbxuetz"
//学生页面部分
import notification from "../views/student/notification";
import info from "../views/student/info";
import changepsw from "../views/student/changepsw";
//登陆页面部分
import StudentLogin from "../views/logins/StudentLogin";
import DormManagerLogin from "../views/logins/DormManagerLogin";
import SuperManagerLogin from "../views/logins/SuperManagerLogin";
import NewLogin from "../views/logins/NewLogin";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'debug',
      component: debug
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/mylogin',
      name: 'Mylogin',
      component: Mylogin
    },
    {
      path: '/failed',
      name: 'AppFailed',
      component: AppFailed
    },

    {
      path: '/nav_student',
      name: 'nav_student',
      component: nav_student
    },
    //宿管中心组件部分
    {
      path: '/nav_manager',
      name: 'nav_manager',
      component: nav_manager,
      children: [
        {
          path: 'index',
          name: 'index',
          component: index,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'xiaoqu',
          component: xiaoqu,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'sushe',
          component: sushe ,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'fangjian',
          component: fangjian,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'student_account',
          component: student_account,
          meta: {
            requireAuth: true
          }
        },
        { path: 'build_account',
          component: build_account,
          meta: {
            requireAuth: true
          }
        },
        {
          path:'student_in',
          component:student_in,
          meta:{
            requireAuth: true
          }
        },
        {
          path:'student_out',
          component:student_out,
          meta:{
            requireAuth: true
          }
        },
      ]
    },
    //管理员组件部分
    {
      path: '/nav_build',
      name: 'nav_build',
      component: nav_build,
      children: [
        {
          path: 'index_build',
          name: 'index_build',
          component:index_build,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'fangjian_build',
          component: fangjian_build,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'chuangwei_build',
          component: chuangwei_build ,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'fangkexinxi',
          component: fangkexinxi,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'jlfangkexinxi',
          component: jlfangkexinxi,
          meta: {
            requireAuth: true
          }
        },
        { path: 'jiluweiji',
          component: jiluweiji,
          meta: {
            requireAuth: true
          }
        },
        { path: 'jlweiji',
          component: jlweiji,
          meta: {
            requireAuth: true
          }
        },
        { path: 'xiaotz',
          component: xiaotz,
          meta: {
            requireAuth: true
          }
        },
        { path: 'xuetz',
          component: xuetz,
          meta: {
            requireAuth: true
          }
        },
        { path: 'fbxuetz',
          component: fbxuetz,
          meta: {
            requireAuth: true
          }
        },
      ]
    },
    //学生个人主页部分
    {
      path: '/nav_student',
      name: 'nav_student',
      component: nav_student,
      children: [
        {
          path: 'info',
          name: 'info',
          component:info,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'notification',
          component: notification,
          meta: {
            requireAuth: true
          },
        },
        {
          path: 'changepsw',
          component: changepsw,
          meta: {
            requireAuth: true
          },
        },

      ]
    },
    //学生登陆部分页面
    {
      path: '/StudentLogin',
      name: 'StudentLogin',
      component: StudentLogin,
    },
    //宿舍管理员登陆部分页面
    {
      path: '/DormManagerLogin',
      name: 'DormManagerLogin',
      component: DormManagerLogin,
    },
    //宿管中心登陆部分页面
    {
      path: '/SuperManagerLogin',
      name: 'SuperManagerLogin',
      component: SuperManagerLogin,
    },
    //三用户结合登陆部分页面
    {
      path: '/NewLogin',
      name: 'NewLogin',
      component: NewLogin,
    },
  ],
  mode:'history'
})
