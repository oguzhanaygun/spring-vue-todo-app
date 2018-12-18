<template>
    <div class="todoList">
        <div class="right">
             <b-btn  @click="showModal(todoList.id)">Create item</b-btn>
        </div>
        <create-todo-form ref="myform" :items="todoList.items" :listid="todoList.id"></create-todo-form>
        <h5>{{todoList.name}}</h5>
        <b-list-group class ="wrap">
            <b-list-group-item class="wrap" :key="todo.id" v-for="todo in todoList.items">
                <todoItem :todo="todo">
                </todoItem>
          </b-list-group-item>
        </b-list-group>

    </div>
</template>

<script>
    import todoItem from '@/components/TodoItem';
     import bButton from 'bootstrap-vue/es/components/button/button';
     import createTodoForm from '@/components/CreateTodoForm';
    export default {
        data () {
            return {
                
            }
        },
        components: {
            todoItem,
            'b-btn':bButton,
            'create-todo-form':createTodoForm
        },
        props: {
            todoList: Object
        },
        methods:{
            showModal(){
                this.$refs.myform.$refs.modal.show();
            }
        }
    }
</script>
    <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .todoList{
    border: 1px solid black;
    display: inline-block;
    position: relative;
    margin: 1%;
    float: left;
    width: 31%;
    height: 400px;
    background-color: lightgrey;
    overflow-y: scroll
    }
    .wrap{
        width: 100%;
    }
    ::-webkit-scrollbar {
    width: 0px;  /* remove scrollbar space */
    background: transparent;  /* optional: just make scrollbar invisible */
}
/* optional: show position indicator in red */
::-webkit-scrollbar-thumb {
    background: #FF0000;
}
    
</style>