<template>
    <div class="hello">
        <h1>Welcome to regular users page</h1>
        <h2>{{msg}}</h2>
        <h5>{{msg2}}</h5>
        <ul id="example-1">
            <li v-for="val in services">
                {{val}}
            </li>
            </ul>

    </div>
</template>

<script>
    export default {
        data () {
            return {
                msg: 'This page must show the todo items of user',
                msg2: 'I couldn\'t finish due to circumstances beyond my control.but backend services are ready',
                services:{
                    todoItem : 'http://localhost:5000/api/todo/item',
                    todoList:'http://localhost:5000/api/todo/itemList',
                    user:'http://localhost:5000/api/todo/user'
                }
            }
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
</style>