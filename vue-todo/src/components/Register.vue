<template>
    <div>
        <h4>Register</h4>
        <form>
            <label for="name">Name</label>
            <div>
                <input id="name" type="text" v-model="name" required autofocus>
                <div class="validation" v-if="name_not_valid">{{name_not_valid}}</div>
            </div>

            <label for="name">userame</label>
            <div>
                <input id="username" type="text" v-model="username" required>
                <div class="validation" v-if="username_not_valid">{{username_not_valid}}</div>
            </div>


            <label for="email" >E-Mail Address</label>
            <div>
                <input id="email" type="email" v-model="email" required>
                <div class="validation" v-if="email_not_valid">{{email_not_valid}}</div>
            </div>

            <label for="password">Password</label>
            <div>
                <input id="password" type="password" v-model="password" required>
                <div class="validation" v-if="password_not_valid">{{password_not_valid}}</div>
            </div>

            <label for="password-confirm">Confirm Password</label>
            <div>
                
                <input id="password-confirm" type="password" v-model="password_confirmation" required>
                <div class="validation" v-if="re_pass_not_valid">
                {{re_pass_not_valid}}</div>
            </div>


            <div>
                <button type="submit" @click="handleSubmit">
                    Register
                </button>
            </div>
        </form>
    </div>
</template>

<script>
    export default {
        props : ["nextUrl"],
        data(){
            return {
                name : "",
                username:"",
                email : "",
                password : "",
                password_confirmation : "",
                re_pass_not_valid : "",
                password_not_valid : "",
                email_not_valid : "",
                username_not_valid : "",
                name_not_valid : ""
            }
        },
        methods : {
            handleSubmit(e) {
                e.preventDefault()

                this.re_pass_not_valid = "";
                this.password_not_valid = "";
                this.email_not_valid = "";
                this.username_not_valid = "";
                this.name_not_valid = "";

                if (this.password !== this.password_confirmation ){
                    this.password = ""
                    this.passwordConfirm = ""

                    this.re_pass_not_valid = "Passwords do not match";
                
                } else if(this.password.length < 6){
                    this.password_not_valid = "Passwords must be at least 6 character";
                }

                 else {
                    let url = "http://localhost:5000/api/auth/signup"
                    this.$http.post(url, {
                        name: this.name,
                        username: this.username,
                        email: this.email,
                        password: this.password
                    })
                     .then(response => {
                        console.log(response);
                        localStorage.setItem('isAdmin',response.data.admin);
                        localStorage.setItem('user',JSON.stringify(response.data.user))
                        localStorage.setItem('jwt',response.data.accessToken)

                        if (localStorage.getItem('jwt') != null){
                            this.$emit('loggedIn')
                            if(this.$route.params.nextUrl != null){
                                this.$router.push(this.$route.params.nextUrl)
                                 this.$router.go();
                            } else {
                                this.$router.push('dashboard')
                                 this.$router.go();
                            }
                        }
                    })
                    .catch(error => {
                        for(let i in error.response.data){
                            let fieldName = error.response.data.data[i].fieldName;
                            let message = error.response.data.data[i].message;
                            switch (fieldName){
                                case 'name':
                                    this.name_not_valid = message;
                                break;
                                case 'username':
                                    this.username_not_valid = message;
                                break;
                                case 'email':
                                    this.email_not_valid = message;
                                break;
                                case 'password': 
                                    this.password_not_valid = message;
                                break;
                            }
                        }
                    });
                } 
            }
        }
    }
</script>

<style type="text/css">
 
.validation {
  margin-right: .5em;
  color:red;
  font-size: x-small;
}
</style>