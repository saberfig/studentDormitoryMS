import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../views/Index'
import Login from '../views/Login'
import AppFailed from "../views/AppFailed"
import xiaoqu from "../views/xiaoqu"
import sushe from "../views/sushe"
import fangjian from "../views/fangjian"
import resetpsw from "../views/resetpsw";

Vue.use(Router)

export default new Router({
  routes: [
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
      path: '/xiaoqu', 
      component: xiaoqu 
    },
		{ 
      path: '/sushe', 
      component: sushe 
    },
		{ 
      path: '/fangjian',
      component: fangjian 
    },
    { path: '/resetpsw', 
      component: resetpsw 
    },
  ],
  mode:'history'
})
