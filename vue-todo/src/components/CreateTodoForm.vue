<template>
    <div>
        <b-modal id="item-create-modal"
             ref="modal"
             title="Create List"
             shown="getOtherItems"
             :lazy="true"
             @ok="createNewItem">

            <b-form-input id="name"
                      type="text"
                      v-model="name"
                      required
                      placeholder="name">
            </b-form-input>
            <b-form-input id="desc"
                      type="text"
                      v-model="description"
                      placeholder="description">
            </b-form-input>            
            <b-form-input id="date"
                      type="date"
                      v-model="deadline"
                      placeholder="deadline">
            </b-form-input>
             <b-form-select v-model="dependent" :options="dependentOptions" class="mb-3" />
        </b-modal>
    </div>
</template>

<script>
    import bForm from 'bootstrap-vue/es/components/form/form';
    import bModal from 'bootstrap-vue/es/components/modal/modal';
    export default {
        data () {
            return {
                name:'',
                description:'',
                deadline:null,
                dependent:null,
                dependentOptions:null
            }
        },
        mounted(){
            if(this.items != null){
                this.dependentOptions = this.items.map( function(item) {
                            item.value = item.id;
                            item.text = item.name;
                            return item;
                            });
            }
                
        },
        props: {
            listid:Number,
            items:Array
        },
        components:{
            'b-form' : bForm,
            'b-modal':bModal
        },
        methods:{
            createNewItem(e){
                e.preventDefault();
                let header = this.getAutHeader();
                let data = {'name':this.name,'description':this.description,'deadline':this.deadline,'itemList':this.listid,'dependentItems':[this.dependent]}; 
                this.$http.post('http://localhost:5000/api/todo/item/'+ this.listid ,data ,{ 'headers':header })
                .then(response => {
                    this.$refs.modal.hide();
                    this.$router.go();
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