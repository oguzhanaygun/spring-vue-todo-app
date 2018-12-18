<template>
    <div>
          <b-modal id="item-list-create-modal"
             ref="modal"
             title="Create List"
             @ok="createNewList">

            <b-form-input id="name"
                      type="text"
                      v-model="name"
                      required
                      placeholder="name">
            </b-form-input>
        </b-modal>
    </div>
</template>

<script>
    import bForm from 'bootstrap-vue/es/components/form/form';
    import bModal from 'bootstrap-vue/es/components/modal/modal';
    export default {
        data () {
            return {
                name: 'new list!'
            }
        },
        components:{
            'b-form' : bForm,
            'b-modal':bModal
        },
        methods:{
            createNewList(e){
                e.preventDefault();
                let header = this.getAutHeader();
                let user = JSON.parse(localStorage.getItem('user'));
                let data = {'name':this.name,'createdBy':{'id':user.id}}; 
                this.$http.post('http://localhost:5000/api/todo/itemList/',data ,{ 'headers':header, })
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

</style>