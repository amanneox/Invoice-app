// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import axios from 'axios'
import {store} from './store'
import VueAxios from 'vue-axios'
import Vuex from 'vuex'
// index.js or main.js
import VeeValidate from 'vee-validate'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
Vue.use(Vuetify, {
  theme: {
    primary: '#1C2541',
    secondary: '#b0bec5',
    accent: '#FFB300'
  }
})
library.add(fas.faCoffee, fas.faUserCircle, fas.faCompass, fas.faBoxOpen, fas.faQuestion, fas.faMobileAlt, fas.faTruck
  , fas.faMobile, fas.faArrowAltCircleRight, fab.faFacebook, fab.faGooglePlus, fab.faGithub
  , fas.faHome, fas.faCaretSquareDown, fas.faBars, fas.faKey, fas.faHeart
  , fas.faUser, fas.faFileContract, fas.faQuestionCircle, fas.faTimes, fas.faFilter, fas.faSortAmountDown,
  fas.faSearch, fas.faArrowRight,
  fas.faShare, fas.faTrash, fas.faPlusSquare, fas.faMinusSquare, fas.faEdit, fas.faSignOutAlt, fas.faPlug, fas.faSlidersH,
  fas.faFile, fas.faUser, fas.faPowerOff, fas.faPlus, fas.faAngleLeft, fas.faBell, fas.faComments, fas.faDatabase,
  fas.faTrash
)
Vue.use(VeeValidate)
Vue.use(VueAxios, axios)
Vue.use(Vuex)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
