// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import './assets/font/iconfont/iconfont.css';

import Axios from 'axios';
import VueCookies from 'vue-cookies';

Vue.prototype.$axios = Axios;
Axios.defaults.headers.post['Content-Type'] = 'application/json';
Vue.use(VueCookies);
Vue.use(ElementUI);

// 引入全局CSS样式
import './assets/scss/main.css'

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '/login' || to.path === '/competition'
    || to.path === '/community' || to.path === '/wiki' || to.path === '/favicon.ico') {
    // 完全匹配
    next();
  } else if (to.path.indexOf('/competition/cm') === 0 && checkRate(to.path.substr(15))) {
    // 匹配 /competition/cm:id
    next();
  } else {
    next('/');
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});

function checkRate(number) {
  //判断正整数/[1−9]+[0−9]∗]∗/
  let re = /^[0-9]+.?[0-9]*/;
  return re.test(number);
}
