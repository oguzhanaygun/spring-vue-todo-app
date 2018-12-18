<template>
   <div>
    <div class="left">
        <b-btn  @click="start()"  size="sm" variant="warning">start</b-btn>
        <b-btn  @click="finish()" size="sm" variant="success">Done</b-btn>
    </div>
    <b-badge pill :variant="status" class="right"> {{todo.status.name}} </b-badge> 
       <div> {{todo.name}} - {{todo.description}} - {{todo.deadline | formatDate}} </div>
   
   </div>
</template>

<script>
    import bButton from 'bootstrap-vue/es/components/button/button';
    import bBadge from 'bootstrap-vue/es/components/badge/badge';
    export default {
        data () {
            return {
                
            }
        },
        props: {
            todo: Object
        },
        components:{
            'b-btn' : bButton,
            'b-badge' : bBadge
        },
        methods:{
            start(){
                 let header = this.getAutHeader();
                 this.$http.post('http://localhost:5000/api/todo/item/'+ this.todo.id + '/start',{},{ 'headers':header })
                     .then(response => {
                        this.todo = response.data;
                    })
                    .catch(function (error) {
                        if(error.response.data != null ){
                            alert(error.response.data.message);
                        } else{
                            console.log(error)
                            alert('ups! something went wrong look at the console');
                        }
                    });
            },
            finish(){
                    let header = this.getAutHeader();
                    this.$http.post('http://localhost:5000/api/todo/item/'+ this.todo.id + '/done',{},{ 'headers':header })
                     .then(response => {
                        this.todo = response.data;
                    })
                    .catch(function (error) {
                         if(error.response.data != null ){
                            alert(error.response.data.message);
                        } else{
                            console.log(error)
                            alert('ups! something went wrong look at the console');
                        }
                    });
            }
        },
        computed:{
            status(){
                 let variant = ''
                 if(this.todo.status.name == 'DONE'){
                     variant = 'success';
                 } else if (this.todo.status.name == 'INPROGRESS'){
                     variant = 'warning';
                 } else if (this.todo.status.name == 'NEW'){
                     variant = 'primary';
                 }
                 return variant
             }
        }
    }
</script>
    <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.INPROGRESS{
background-color: orange;
height: 100%
}
.NEW{
background-color: white;
height: 100%
}
.DONE{
    background-color: green;
    height: 100%
}
</style>filters: {
         statusVariant(value) {
           
        }