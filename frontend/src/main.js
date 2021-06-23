import '@babel/polyfill'
import Vue from 'vue'
import vuetify from './plugins/vuetify'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from './plugins/interceptor'

Vue.prototype.$http = axios;

Vue.config.productionTip = false

export const EventBus = new Vue() // eventBus

new Vue({
  router,
  store,
  beforeCreate() {
    
  },
  vuetify,
  render: h => h(App)
}).$mount('#app')
