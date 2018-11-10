<template lang="html">
  <v-layout class="login-form" justify-center>
   <v-flex xs10 sm6 md4>
     <v-card>
  <v-container>
  <v-text-field v-model="user.name" v-validate="'required'" name="name" dark flat clearable solo label="Name" required></v-text-field>
  <span>{{errors.first('name')}}</span>
  <v-text-field v-model="user.email" v-validate="'required|email'" name="email" dark flat clearable solo label="E-mail"></v-text-field>
  <span>{{ errors.first('email') }}</span>
  <v-text-field v-model="user.password" v-validate="'required'" type="password" name="password" dark flat clearable solo label="Password"></v-text-field>
  <span>{{ errors.first('password') }}</span>
  <v-text-field v-model="user.number" v-validate="'required|digits:10'" name="number" dark flat clearable solo label="Number" required></v-text-field>
  <span>{{errors.first('number')}}</span>
  <v-btn @click.prevent="$_emitData" :loading="loading" :disabled="loading" color="orange"  @click.native="loader = 'loading'" class="continue-btn white--text">Continue</v-btn>
  <v-layout justify-start>
    <v-flex>
      <span class="text-xs-left forgot">Need Help?</span>
    </v-flex>
    <v-flex>
      <router-link to="/login"><span class="text-xs-left signup">Login</span>
      </router-link>
    </v-flex>
  </v-layout>
</v-container>
     </v-card>
   </v-flex>
 </v-layout>
</template>
<script>
/* eslint-disable */
import { mapState, mapActions } from 'vuex'
export default {
  $_veeValidate: {
    validator: 'new'
  },
  data: () => ({
    user: {
    name: '',
    number: '',
    email: '',
    password: '',
    role:'0700'
  },
  loader: null,
  loading: false,
  drawer: null
}),
props: {
  source: String
},
computed: {
       ...mapState('account', ['status'])
   },
watch: {
     loader () {
       const l = this.loader
       this[l] = !this[l]

       setTimeout(() => (this[l] = false), 3000)

       this.loader = null
     }
   },
   methods:{
      ...mapActions('account', ['register']),
     $_emitData () {
       this.$validator.validateAll()
         if (this.errors.any()) {
           return
         }
         else {
            this.register(this.user);
         }

   }
 }
}
</script>

<style lang="css">
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
  color: #000;
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
</style>
