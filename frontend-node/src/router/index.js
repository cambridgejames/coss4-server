import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/',
      component: () => import('@/pages/index'),
      children: [
        {
          path: '/',
          component: () => import('@/pages/common/home')
        },
        {
          path: '/competition',
          component: () => import('@/pages/competition/competition')
        },
        {
          path: '/community',
          component: () => import('@/pages/community/community')
        },
        {
          path: '/wiki',
          component: () => import('@/pages/wiki/wiki')
        }
      ]
    },
    {
      path: '/login',
      component: () => import('@/pages/common/login')
    }
  ]
})
