// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import element from './component'
import global from './global/common.vue';

import 'element-ui/lib/theme-chalk/index.css';
// import XLSX from 'xlsx';


const zce = '112.124.20.192';
const wx = '123.56.240.164';
const lzg = '47.94.147.140';
//设置反向代理，前端请求默认发送到http://localhost:8443/api
var axios = require('axios')
axios.defaults.baseURL = `http://${lzg}:8443/api`
//全局注册，之后可在其他组件通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(element);

/* eslint-disable no-new */

Vue.prototype.COMMON = global

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
