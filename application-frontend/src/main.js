import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './views/router';
import store from './data/store';

import '@mdi/font/css/materialdesignicons.css'
require('@/assets/scss/style.scss')

Vue.config.productionTip = false

new Vue({
  debug: true,
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
