import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../views/Index'
import Login from '../views/Login'
import AppFailed from "../views/AppFailed";


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
    }
  ],
  mode:'history'
})
