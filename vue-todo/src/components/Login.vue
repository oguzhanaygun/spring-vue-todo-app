<template>
    <div>
        <h4>Login</h4>
        <form>
            <label for="email" >Username or E-Mail Address</label>
            <div>
                <input id="usernameOrEmail" type="username" v-model="usernameOrEmail" required autofocus>
            </div>
            <div>
                <label for="password" >Password</label>
                <div>
                    <input id="password" type="password" v-model="password" required>
                </div>
            </div>
            <div>
                <button type="submit" @click="handleSubmit">
                    Login
                </button>
            </div>
        </form>
    </div>
</template>

<script>
    export default {
        data(){
            return {
                usernameOrEmail : "",
                password : ""
            }
        },
        methods : {
            handleSubmit(e){
                e.preventDefault();
                if (this.password.length > 0) {
                    this.$http.post('http://localhost:5000/api/auth/signin', {
                        usernameOrEmail: this.usernameOrEmail,
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
                                location.reload();
                            } else {
                                this.$router.push('dashboard')
                                location.reload();
                            }
                        }
                    })
                    .catch(function (error) {
                        console.error(error.response);
                    });
                }
            }
        }
    }
</script>