<template>
    <div>
        <h4>Register</h4>
        <form>
            <label for="name">Name</label>
            <div>
                <input id="name" type="text" v-model="name" required autofocus>
            </div>

            <label for="name">userame</label>
            <div>
                <input id="username" type="text" v-model="username" required>
            </div>


            <label for="email" >E-Mail Address</label>
            <div>
                <input id="email" type="email" v-model="email" required>
            </div>

            <label for="password">Password</label>
            <div>
                <input id="password" type="password" v-model="password" required>
            </div>

            <label for="password-confirm">Confirm Password</label>
            <div>
                <input id="password-confirm" type="password" v-model="password_confirmation" required>
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
            }
        },
        methods : {
            handleSubmit(e) {
                e.preventDefault()

                if (this.password !== this.password_confirmation ){
                    this.password = ""
                    this.passwordConfirm = ""

                    return alert("Passwords do not match")
                
                } else if(this.password.length < 6){
                    return alert("Passwords must be at least 6 character")
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
                       this.$http.post('http://localhost:5000/api/auth/signin', {
                        usernameOrEmail: this.username,
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
                            } else {
                                this.$router.push('dashboard')
                            }
                        }
                    })
                    .catch(function (error) {
                        console.error(error.response);
                    });
                    })
                    .catch(error => {
                        alert(error.response.data.message + ". look console for details");
                        console.log(error.response.data);
                    });
                } 
            }
        }
    }
</script>