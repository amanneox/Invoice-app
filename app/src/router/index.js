import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/home'
import SignUp from '@/pages/signUp'
import Login from '@/pages/login'
import InvoiceBuilder from '@/pages/invoicebuilder'
import Invoice from '@/pages/_invoice'
import InvoiceManager from '@/pages/InvoiceManager'
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
    },
    {
      path: '/manager',
      name: 'InvoiceManager',
      component: InvoiceManager
    },
    {
      path: '/_invoice/:id',
      name: 'Invoice',
      component: Invoice
    }
  ]
})
