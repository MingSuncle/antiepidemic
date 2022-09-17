import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage.vue'),
    redirect: "/home",
    meta:{
      title:'首页'
    },
    children: [{
      path: 'user',
      name: 'User',
      meta:{
        title:'员工管理'
      },
      component: () => import('../views/User.vue'),
    },
    {
      path: 'home',
      name: 'Home',
      component: () => import('../views/Home.vue'),
    },
    {
      path: '/dataManage',
      name: 'dataManage',
      meta:{
        title:'数据管理'
      },
      component: () => import('../views/dataManage/data.vue')
    }
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/antiepidemic',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
