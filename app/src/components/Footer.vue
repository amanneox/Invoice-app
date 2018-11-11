<template lang="html">
  <v-layout class="footer">
    <v-flex>
      <span class="ver black--text">Version 1.0 &nbsp; contribute@github&nbsp;<font-awesome-icon class="git-icon" :icon="['fab', 'github']" /></span>
    </v-flex>
</v-layout>
</template>

<script>
/* eslint-disable */
export default {
}
</script>

<style lang="css">
.ver{
    color: #909399;
}
.footer{
  margin: 12px;
  position: absolute;
  bottom: 16px;
  height: auto !important
}
</style>
