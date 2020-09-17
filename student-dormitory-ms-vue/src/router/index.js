import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'debug',
      component: resolve => require(['../views/debug'], resolve)
    },
    {
      path: '/failed',
      name: 'AppFailed',
      component: resolve => require(['../views/AppFailed'], resolve)
    },

    {
      path: '/student',
      name: 'student',
      component: resolve => require(['../views/student'], resolve)
    },
    //宿管中心组件部分
    {
      path: '/manager',
      name: 'manager',
      component: resolve => require(['../views/manager'], resolve)
    },
    //管理员组件部分
    {
      path: '/build',
      name: 'build',
      component: resolve => require(['../views/build'], resolve),
    },
    //三用户结合登陆部分页面
    {
      path: '/NewLogin',
      name: 'NewLogin',
      component: resolve => require(['../views/NewLogin'], resolve),
    },
  ],
  mode:'history'
})
