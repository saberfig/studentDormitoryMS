import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../views/Index'
import Login from '../views/Login'
import AppFailed from "../views/AppFailed";
import Mylogin from "../views/Mylogin"
import StudentPage from "../views/StudentPage";

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
