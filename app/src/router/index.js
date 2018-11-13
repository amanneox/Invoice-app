import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/home'
import SignUp from '@/pages/signUp'
import Login from '@/pages/login'
import InvoiceBuilder from '@/pages/invoicebuilder'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/register',
      name: 'Register',
      component: SignUp
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/build',
      name: 'InvoiceBuilder',
      component: InvoiceBuilder
    }
  ]
})
