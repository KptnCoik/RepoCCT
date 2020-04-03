import {HTTP} from '../http-constants'

HTTP.get(...).then(...).catch(...)

new Vue({
  el: '#app',
  data () {
    return {
      id:'',
      nom:''
    }
  },
  methods: {
      getGreetings: function () {
        HTTP.get('/Epreuve/Classement/42')
          .then(response => {
            this.greetings = response.data.message
          })
          .catch(e => {
            this.errors = e
          })
      }
    }
})
 