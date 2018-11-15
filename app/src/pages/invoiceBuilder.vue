<template lang="html">
  <div class="">
  <layoutheader/>
   <v-content>
     <v-container>
        <v-card>
       <v-layout row wrap>
         <v-flex>
           <v-btn dark color="#FFB300" @click="dialog = true" class="add-btn">
               <v-icon dark>add</v-icon> Create Template
           </v-btn>
         </v-flex>
       </v-layout>
       <v-layout row wrap>
         <v-flex v-for="i in pages" :key="`${i.id}-item`"  xs12 md4 class="content-box">
       <v-card class="layout-item item">
         <v-container>

           <v-layout row wrap>
             <v-flex xs12>
               <v-btn @click.prevent="$_remove(i.id)" class="trash-btn" icon>
                   <font-awesome-icon class="trash-icon" icon="trash" size="lg" />
                 </v-btn>
             </v-flex>
            <v-flex xs12>
               <p class="layout-name subheading">{{i.cname}}</p>
            </v-flex>
            <v-flex  xs12>
            <p class="layout-name">{{i.email}}</p>
            </v-flex>
            <v-flex xs12>
               <p class="layout-name">{{i.number}}</p>
            </v-flex>
            <v-flex xs12>
               <p class="layout-name">Generated ID&nbsp;{{i.id}}</p>
            </v-flex>

           </v-layout>
           <v-btn :to="`_invoice/${i.id}`" icon class="edit-icon" small fab dark color="primary">
            <v-icon dark>edit</v-icon>
           </v-btn>
         </v-container>
       </v-card>
     </v-flex>
       </v-layout>
       </v-card>
       <div class="text-xs-right pagination-holder">
         <v-pagination dark v-model="currentPage" :length="totalPages"></v-pagination>
       </div>
    <v-dialog v-model="dialog" width="600" class="diloag">
  <v-container>
    <v-layout row wrap>
        <v-flex class="dialog-item" xs6>
            <v-text-field v-model="form.cname" label="Seller/Company Name"  dark flat solo></v-text-field>
        </v-flex>
        <v-flex class="dialog-item" xs6>
            <v-text-field v-model="form.number" label="Your Number"  dark flat solo></v-text-field>
        </v-flex>
        <v-flex class="dialog-item" xs6>
            <v-text-field v-model="form.email" label="Your Email"  dark flat solo></v-text-field>
        </v-flex>
        <v-flex class="dialog-item" xs6>
            <v-text-field v-model="form.address" label="Your Address"  dark flat solo></v-text-field>
        </v-flex>
        <v-flex class="dialog-item" xs6>
            <v-text-field v-model="form.logo" label="Company Logo/URL "  dark flat solo></v-text-field>
        </v-flex>
        <v-flex class="dialog-item" xs6>
            <v-textarea v-model="form.terms" label="Terms and conditions"  dark flat solo></v-textarea>
        </v-flex>
    </v-layout>
    <v-layout>
      <v-btn class="white--text" @click="dialog = false" color="red">Cancel</v-btn>
      <v-btn class="white--text" :loading="loading" :disabled="loading" @click.prevent="$_submit" @click.native="loader = 'loading'" color="green">Save</v-btn>
    </v-layout>
  </v-container>
</v-dialog>
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
      dialog: false,
      user: '',
      page: 1,
      currentPage: 1,
      itemsPerPage: 4,
      resultCount: 0,
      maxVisibleButtons: 4,
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
  methods: {
    $_remove(id){
      this._delete(id)
    },
    $_submit(){
      const invoice = this.form
      this.create(invoice)
    },
    ...mapActions('invoice', ['create', 'get_All','_delete']),
  },
  mounted () {
    this.get_All()
  },
  computed: {
    ...mapState({invoice: 'invoice'}),
    pages () {
      const range = []
      for (let i = this.startPage;
        i < Math.min(this.startPage + this.itemsPerPage);
        i += 1) {
        const item = this.invoice.items.content[i]
        if (typeof item !== 'undefined') {
          range.push({
            ...item
          })
        }
      }
      return range
    },
    totalPages: function () {
      this.resultCount = this.invoice.items.content.length
      return Math.ceil(this.resultCount / this.itemsPerPage)
    },
    startPage () {
      // When on the first page
      if (this.currentPage === 1) {
        return 0
      }
      // When on the last page
      if (this.currentPage === this.totalPages) {
        return this.totalPages * this.itemsPerPage - this.itemsPerPage
      }
      // When in between
      return this.currentPage * this.itemsPerPage - this.itemsPerPage
    }
  }
}
</script>

<style lang="css">
.trash-btn{
  float: right;
}
.trash-btn:hover{
  color: red;
}
.v-dialog{
  background: white !important
}
.dialog-item{
  padding: 4px;
}
.pagination-holder{
  margin-top: 16px;
}
.content-box{
  padding: 12px;
}
.layout-item{
  background-color: #F5F5F5 !important;
  margin: 12px !important;
}
.layout-name{
  float: left;
  }
.name{
  padding: 12px;
  float: left;
}
.edit-icon{
  float: right !important;
  background: black;
}
 .item {
   margin-bottom: 18px;
 }
 .main-content{
   padding: 16px;
 }
 .add-btn{
   float: right;
   margin: 12px;
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
