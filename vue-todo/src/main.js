
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.prototype.$http = Axios;

Vue.config.productionTip = false

Vue.mixin({
  methods: {
    isAdmin: function () {
      let isAdmin = localStorage.getItem('isAdmin') && localStorage.getItem('jwt');
      return isAdmin
    }
  }
})

new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})
