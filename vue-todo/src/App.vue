
<template>
  <div id="app">
    <header-view></header-view>
  <div class="test"><router-view/></div>
    
  </div>
</template>

<script>
  import header from '@/components/Header'
export default {
  name: 'App',
  components: {
          'header-view': header
      }
}
</script>

<style>
.test {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

