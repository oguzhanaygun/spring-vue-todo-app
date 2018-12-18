<template>
    <div class="hello">
        <h1>Welcome to regular users page</h1>
        <h2>{{msg}}</h2>
        <div clsas="todo-container">
            <div v-for="todos in todoLists">
                 <List :todoList="todos"></List>
            </div>
            </div>
            
    </div>
</template>

<script>
    import List from '@/components/TodoList'
    export default {
        data () {
            return {
                msg: 'This page must show the todo items of user',
                todoLists:[]
            }
        },
        components: {
            List
        },
        computed: {
            user(){
                return localStorage.getItem(user);
            }
        },
        beforeMount() {
                let header = this.getAutHeader();
                this.$http.get('http://localhost:5000/api/todo/itemList/', { 'headers': header})
                     .then(response => {
                       this.todoLists = response.data.data;
                    })
                    .catch(function (error) {
                        alert("ups something went wrong look at the console");
                        console.log(error);
                    });  
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h1, h2 {
        font-weight: normal;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
    .todo-container{
        height: 60%;
        width: 100%;
    }
</style>