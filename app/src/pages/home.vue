<template lang="html">
  <div class="">
    <layoutheader/>
   <v-content>
     <v-container>
       <v-layout row wrap>
        <span class="title text-capitalize">Welcome, {{account.user.name}}</span>
       </v-layout>
       <v-layout row wrap>
       <span  v-if="`${account.user.role}`== 700">(Admin User)</span>
       <span  v-if="`${account.user.role}`== 600">(Write & Read)</span>
       <span  v-if="`${account.user.role}`== 400">(Read Only)</span>
     </v-layout>
     <v-layout row wrap v-if="invoice.request.loading == false">
      <v-flex xs6 md4>
        <v-card class="item-card color-0">
          <v-container>
            <span class="white--text title">{{invoice.items.content.length}}&nbsp;Invoice Templates</span>
          </v-container>
        </v-card>
      </v-flex>
      <v-flex xs6 md4>
        <v-card class="item-card color-1">
          <v-container>
            <span class="white--text title">{{userinvoice.items.content.length}}&nbsp;User Invoices</span>
          </v-container>
        </v-card>
      </v-flex>
      <v-flex xs6 md4>
        <v-card class="item-card color-2">
          <v-container>
              <span class="white--text title">{{users.all.content.length}}&nbsp;Users</span>
          </v-container>
        </v-card>
      </v-flex>
    </v-layout>
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
    }
  },
  components: {
    layoutheader
  },
  created () {
    const loggedIn = localStorage.getItem('user')
    if (!loggedIn) {
      this.$router.push('/login')
    }
  },
  methods: {
    ...mapActions('invoice', ['get_All_invoice']),
    ...mapActions('userinvoice', ['get_All_userinvoice']),
    ...mapActions('users', ['get_All'])
  },
  computed: {
    ...mapState({account: 'account', status: ['status'], user: ['user']}),
    ...mapState({userinvoice: 'userinvoice'}),
    ...mapState({invoice: 'invoice'}),
    ...mapState({users: 'users'})
  },
  mounted () {
    this.get_All(),
    this.get_All_invoice(),
    this.get_All_userinvoice()
  }
}
</script>

<style lang="css">
.item-card{
  margin: 12px;
}
.color-0{
  background: #8E2DE2;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #4A00E0, #8E2DE2);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #4A00E0, #8E2DE2); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.color-1{
  background: #FF512F;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #F09819, #FF512F);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #F09819, #FF512F); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.color-2{
  background: #FF416C;  /* fallback for old browsers */
background: -webkit-linear-gradient(to top, #FF4B2B, #FF416C);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to top, #FF4B2B, #FF416C); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
</style>
