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
            <b-btn v-on:click="logOut">Logout</b-btn>
             <b-btn v-b-modal.item-list-create-modal>Create List</b-btn>

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
        <create-Item-Form></create-Item-Form>
         <create-todo-form></create-todo-form>
    </div>

</div>

</template>

<script>
    import router from '../router';
    import bButton from 'bootstrap-vue/es/components/button/button';
    import createItemForm from '@/components/CreateListForm';
    import createTodoForm from '@/components/CreateTodoForm';
    export default {
        data(){
            return {
                isLoggedIn : false,
                routes : []
            }
        },
        computed:{
            user(){
                return JSON.parse(localStorage.getItem("user"));
            },
            username(){
                return this.isLoggedIn ? this.user.username : "";
            }
        },
        beforeMount() {
              this.routes = router.options.routes;  
              this.isLoggedIn = this.isUserLoggedin();   
        },
         components: {
            'b-btn' : bButton,
            'create-Item-Form' : createItemForm,
            'create-todo-form':createTodoForm
        },
        methods : {
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
<style >
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