<template lang="html">
  <div class="main">
    <brand/>
    <v-layout class="login-form" justify-center>
     <v-flex xs10 sm6 md4>
       <v-card>
    <v-container>
    <v-text-field v-model="email" v-validate="'required|email'" name="email" dark flat clearable solo label="E-mail"></v-text-field>
       <span>{{ errors.first('email') }}</span>
    <v-text-field v-model="password" v-validate="'required'" type="password" name="password" dark flat clearable solo label="Password"></v-text-field>
     <span>{{ errors.first('password') }}</span>
     <br>
     <v-btn  @click.prevent="$_emitData()" :loading="loading" :disabled="loading" color="orange"  @click.native="loader = 'loading'" class="continue continue-btn white--text">Continue</v-btn>
     <v-layout justify-start>
       <v-flex>
         <span class="text-xs-left forgot">Forgot Password?</span>
       </v-flex>
       <v-flex>
         <router-link to="/register"><span class="text-xs-left signup">Sign Up</span>
         </router-link>
       </v-flex>
     </v-layout>
  </v-container>
       </v-card>
     </v-flex>
   </v-layout>
    <version/>
  </div>
</template>

<script>
/* eslint-disable */
import brand from '@/components/Brand.vue'
import version from '@/components/Footer.vue'
import { mapState, mapActions ,mapGetters} from 'vuex'
  export default {
    components:{
      brand,
      version
    },
    computed: {
   ...mapState({account:'account', status:['status'],alert:'alert'}),
},
mounted(){
  this.$store.dispatch('alert/clear',{ root: true })
},
methods:{
  ...mapActions('account', ['login', 'logout']),
  $_emitData () {

    const { email, password } = this

    this.$validator.validateAll()
      if (this.errors.any()) {
        return
      }
      else {
        if (email && password) {

            this.login({ email,password })
        }
      }
}
},
data: () => ({
email: '',
password: '',
loader: null,
loading: false,
drawer: null
}),
props: {
source: String
},
watch: {
   loader () {
     const l = this.loader
     this[l] = !this[l]

     setTimeout(() => (this[l] = false), 3000)

     this.loader = null
   }
 }
  }
</script>
<style lang="css">
.login-form{
  margin-top: 22px;
}
.continue-btn{
  background: #00c6ff;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #0072ff, #00c6ff);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #0072ff, #00c6ff); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.forgot{
  text-transform: capitalize;
  float: left;
}
.signup{
  text-transform: capitalize;
  float: right;
  color: black ;
}
a{
  text-decoration: none !important;
}
.v-text-field__slot,.v-input__slot{
background-color: #E0E0E0 !important;
}
input{
  color: black !important;
}
label{
  color: black !important;
}
.custom-loader {
   animation: loader 1s infinite;
   display: flex;
 }
 @-moz-keyframes loader {
   from {
     transform: rotate(0);
   }
   to {
     transform: rotate(360deg);
   }
 }
 @-webkit-keyframes loader {
   from {
     transform: rotate(0);
   }
   to {
     transform: rotate(360deg);
   }
 }
 @-o-keyframes loader {
   from {
     transform: rotate(0);
   }
   to {
     transform: rotate(360deg);
   }
 }
 @keyframes loader {
   from {
     transform: rotate(0);
   }
   to {
     transform: rotate(360deg);
   }
 }
</style>
