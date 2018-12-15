<template >
 <div class="header">
        <div class="left">
         <ul>
            <li v-if="showLink(route.meta)" v-for="route in routes">
         <router-link    :to="route.path" >{{route.name}}</router-link>
            </li>
           </ul> 
        </div>

        <div  class ="right" v-if="isLoggedIn">
           <h5 >{{username}}</h5>
            <button v-on:click="logOut">Logout</button>
        </div>
        <div class ="right" v-else>
         <ul>
            <li>
                <router-link  to="/login" >Login</router-link>
            </li>
            <li>
            <router-link   to="/register" >Register</router-link>
            </li>
        </ul>
        </div>
        
    </div>
</div>
</template>

<script>
    import router from '../router'
    export default {
        data(){
            return {
                isLoggedIn : false,
                routes : []
            }
        },
        computed:{
            user(){
                return JSON.parse(localStorage.getItem("user")).username;
            },
            username(){
                return this.isLoggedIn ? this.user:"";
            }
        },
        mounted() {
              this.routes = router.options.routes;  
              this.isLoggedIn = this.isUserLoggedin();   
        },
        methods : {
            isUserLoggedin(){
                let jwt = localStorage.getItem('jwt');
                return jwt != null && jwt != ""; 
            },
            showLink(meta){
                let isshow = false;
                if(meta.show){
                    isshow = true;
                }else if (meta.isAdmin) {
                    isshow = this.isLoggedIn && this.isAdmin(); 
                } else if (meta.requiresAuth){
                    isshow = this.isLoggedIn; 
                } 
                return isshow;
            },
            logOut(){
                localStorage.removeItem('isAdmin',null);
                localStorage.removeItem('user',null)
                localStorage.removeItem('jwt',null)
                this.isLoggedIn = false;
                this.$router.push("Login")
            }
        }
    }
</script>
    <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header{
    padding-right: 10px;
    padding-left: 10px;
    position: relative;
    top: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 50px;
}
.left{
    float: left;
}
.right{
    float: right;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

li {
  float: left;
}

li a {
  display: block;
  text-align: center;
  padding: 16px;
  text-decoration: none;
}


</style>