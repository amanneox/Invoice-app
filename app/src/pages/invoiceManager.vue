<template lang="html">
  <div class="">
    <layoutheader/>
    <v-content>
    <v-container>
      <v-card>
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
                <p class="layout-name subheading">{{i.user_name}}</p>
             </v-flex>
             <v-flex  xs12>
             <p class="layout-name">{{i.total_value}}</p>
             </v-flex>
             <v-flex xs12>
                <p class="layout-name">Generated ID&nbsp;{{i.id}}</p>
             </v-flex>

            </v-layout>
            <router-link :to="`${i.invoice_url}`" target="_blank">
              <v-btn icon class="download-icon" small fab color="blue">
               <v-icon class="download-icon-img" large color="white darken-2">cloud_download</v-icon>
              </v-btn>
            </router-link>
            <v-btn icon class="mail-icon" small fab color="primary">
             <v-icon class="mail-icon-img" large color="white darken-2">email</v-icon>
            </v-btn>
          </v-container>
        </v-card>
      </v-flex>
        </v-layout>
      </v-card>
      <div class="text-xs-right pagination-holder">
        <v-pagination dark v-model="currentPage" :length="totalPages"></v-pagination>
      </div>
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
      page: 1,
      currentPage: 1,
      itemsPerPage: 4,
      resultCount: 0,
      maxVisibleButtons: 4
    }
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]
      setTimeout(() => (this[l] = false), 3000)
      this.loader = null
    }
  },
  components: {
    layoutheader
  },
  methods: {
    $_remove (id) {
      this._delete(id)
    },
    $_download(id){
      this.download(id)
    },
    $_submit () {
      const userinvoice = this.form
      this.create(userinvoice)
    },
    ...mapActions('userinvoice', ['create', 'get_All_userinvoice', '_delete','download'])
  },
  mounted () {
    this.get_All_userinvoice()
  },
  computed: {
    ...mapState({userinvoice: 'userinvoice'}),
    pages () {
      const range = []
      for (let i = this.startPage;
        i < Math.min(this.startPage + this.itemsPerPage);
        i += 1) {
        const item = this.userinvoice.items.content[i]
        if (typeof item !== 'undefined') {
          range.push({
            ...item
          })
        }
      }
      return range
    },
    totalPages: function () {
      this.resultCount = this.userinvoice.items.content.length
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
.download-icon{
  float: left !important;
}
.download-icon-img{
  font-size: 28px !important;
}
.mail-icon{
  float: right !important;
  background: green !important;
}
.mail-icon-img{
font-size: 28px !important;
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
