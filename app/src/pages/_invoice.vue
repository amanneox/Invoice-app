<template lang="html">
  <div class="">
    <layoutheader/>
     <v-content>
       <v-container>
          <v-card>
            <v-container>
              <v-layout row wrap>
                  <v-flex class="form-item" xs6>
                      <v-text-field v-model="invoice.current.cname" label="Seller/Company Name"  dark flat solo></v-text-field>
                  </v-flex>
                  <v-flex class="form-item" xs6>
                      <v-text-field v-model="invoice.current.number" label="Your Number"  dark flat solo></v-text-field>
                  </v-flex>
                  <v-flex class="form-item" xs6>
                      <v-text-field v-model="invoice.current.email" label="Your Email"  dark flat solo></v-text-field>
                  </v-flex>
                  <v-flex class="form-item" xs6>
                      <v-text-field v-model="invoice.current.address" label="Your Address"  dark flat solo></v-text-field>
                  </v-flex>
                  <v-flex class="form-item" xs6>
                      <v-text-field v-model="invoice.current.logo" label="Company Logo/URL "  dark flat solo></v-text-field>
                  </v-flex>
                  <v-flex class="form-item" xs6>
                      <v-textarea v-model="invoice.current.terms" label="Terms and conditions"  dark flat solo></v-textarea>
                  </v-flex>
              </v-layout>
              <v-layout>
                <v-btn class="white--text" @click="dialog = false" color="red">Cancel</v-btn>
                <v-btn class="white--text" :loading="loading" :disabled="loading" @click.prevent="$_submit" @click.native="loader = 'loading'" color="green">Save</v-btn>
              </v-layout>
            </v-container>
          </v-card>
        </v-container>
      </v-content>
  </div>
</template>

<script>
/* eslint-disable */
import { mapState, mapActions } from 'vuex'
import layoutheader from '@/components/LayoutHeader'
export default {
  data () {
    return {
      loader: null,
     loading: false,
      form:{
        cname:'',
        logo:'',
        number:'',
        email:'',
        terms:'',
        address:''
      }
    }
  },
  watch: {
 loader () {
   const l = this.loader
   this[l] = !this[l]
   setTimeout(() => (this[l] = false), 3000)
   this.loader = null
 },

},
  components: {
    layoutheader
  },
methods:{
  $_submit(){
    const invoice = this.form
   this.update(this.invoice.current)
  },
     ...mapActions('invoice', ['getById','update']),
   },
   mounted(){
   this.getById(this.$route.params.id)
 },
 computed:{
      ...mapState({invoice: 'invoice'}),
 }
}
</script>

<style lang="css">
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
.form-item{
  padding: 12px;
}
</style>
