
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import moment from 'moment'



Vue.use(BootstrapVue);
Vue.prototype.$http = Axios;

Vue.config.productionTip = false

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('MM/DD/YYYY hh:mm');
  }
});

Vue.mixin({
  methods: {
    isAdmin() {
      let isAdmin = localStorage.getItem('isAdmin') && localStorage.getItem('jwt');
      return isAdmin
    },
    isUserLoggedin(){
       let jwt = localStorage.getItem('jwt');
       return jwt != null && jwt != ""; 
    },
    getAutHeader(){
        let jwt = localStorage.getItem('jwt');
        let auth = "Bearer " + jwt;
        let header = {"Authorization" : auth};
        return header;
    }
  }
})

new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})
