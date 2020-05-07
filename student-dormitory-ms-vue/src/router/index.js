import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../views/Index'
import Login from '../views/Login'
import AppFailed from "../views/AppFailed"
import xiaoqu from "../views/xiaoqu"
import sushe from "../views/sushe"
import fangjian from "../views/fangjian"
import resetpsw from "../views/resetpsw";
import Mylogin from "../views/Mylogin"
import StudentPage from "../views/StudentPage"
import nav_manager from "../views/nav_manager"
import chuangwei_build from "../views/chuangwei_build"
import fangjian_build from "../views/fangjian_build"
import fangkexinxi from "../views/fangkexinxi"
import index_build from "../views/index_build"
import jiluweiji from "../views/jiluweiji"
import nav_build from "../views/nav_build"
import xiaotz from "../views/xiaotz"
import xuetz from "../views/xuetz";

Vue.use(Router)

export default new Router({
  routes: [
    //宿管中心组件部分
    {
      path: '/nav_manager',
      name: 'nav_manager',
      component: nav_manager,
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/xiaoqu', 
          component: xiaoqu,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/sushe', 
          component: sushe ,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/fangjian',
          component: fangjian,
          meta: {
            requireAuth: true
          }
        },
        { path: '/resetpsw', 
          component: resetpsw,
          meta: {
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
          path: '/index_build',
          name: 'index_build',
          component:index_build,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/fangjian_build', 
          component: fangjian_build,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/chuangwei_build', 
          component: chuangwei_build ,
          meta: {
            requireAuth: true
          }
        },
        { 
          path: '/fangkexinxi',
          component: fangkexinxi,
          meta: {
            requireAuth: true
          }
        },
        { path: '/jiluweiji', 
          component: jiluweiji,
          meta: {
            requireAuth: true
          }
        },
        { path: '/xiaotz', 
          component: xiaotz,
          meta: {
            requireAuth: true
          }
        },
        { path: '/xuetz', 
          component: xuetz,
          meta: {
            requireAuth: true
          }
        },
      ]
    },
    {
      path: '/',
      name: 'Default',
      component: Login
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
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    },
    {
      path: '/failed',
      name: 'AppFailed',
      component: AppFailed
    },

    {
      path: '/studentpage',
      name: 'StudentPage',
      component: StudentPage
    }
  ],
  mode:'history'
})
