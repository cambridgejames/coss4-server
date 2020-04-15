// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

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
    || to.path === '/community' || to.path === '/wiki'
    || localStorage.getItem("isLogin") === 'true') {
    next();
  } else {
    next('/login');
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
