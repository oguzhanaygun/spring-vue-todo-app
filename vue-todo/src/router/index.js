import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import Register from '@/components/Register'
import UserBoard from '@/components/UserBoard'
import Admin from '@/components/Admin'
import Header from '@/components/Header'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HelloWorld,
      meta: {
        requiresAuth: false,
        isAdmin: false,
        show : true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        requiresAuth: false,
        isAdmin: false,
        show : false
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register,
      meta: {
        requiresAuth: false,
        isAdmin: false,
        show : false
      }
    },
    {
      path: '/dashboard',
      name: 'DashBoard',
      component: UserBoard,
      meta: {
        requiresAuth: true,
        isAdmin: false,
        show : true
      }
    },
    {
      path: '/admin',
      name: 'Admin',
      component: Admin,
      meta: {
        requiresAuth: true,
        isAdmin: true,
        show : false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  let jwt = localStorage.getItem('jwt');
  let isAdmin = JSON.parse(localStorage.getItem('isAdmin'))
 
  if(to.matched.some(record => record.meta.isAdmin)) {
      if (isAdmin) {
          next();
      } else {
          alert("you are not authorized");
      }
  } else if (to.matched.some(record => record.meta.requiresAuth)) {
      if (jwt == null) {
          next({
            path: '/login',
            params: { nextUrl: to.fullPath }
          });
      } else {
           next();
      }
  } else {
      next()
  }
})

export default router
