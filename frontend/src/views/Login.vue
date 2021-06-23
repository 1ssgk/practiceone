<template>
    <div id="app">
    <v-app id="inspire">
        <v-container
          class="fill-height"
          fluid
        >
          <v-row
            align="center"
            justify="center"
          >
            <v-col
              cols="10"
              sm="8"
              md="4"
            >
              <v-card class="elevation-12">
                <v-toolbar
                  color="deep-purple"
                  dark
                  flat
                >
                  <v-toolbar-title>Login form</v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      label="Login"
                      name="login"
                      prepend-icon="mdi-account"
                      type="text"
                      v-model="user.userId"
                    ></v-text-field>
  
                    <v-text-field
                      id="password"
                      label="Password"
                      name="password"
                      prepend-icon="mdi-lock"
                      type="password"
                      v-model="user.password"
                    ></v-text-field>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn @click="loginBtnClick()" color="white">Login</v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
    </v-app>
</div>
</template>


<script>
import axios from 'axios'
import { registerLogin } from '@/api/login'


export default {
  data(){
      return{
          user:{}
      }
  },
  methods:{
    loginBtnClick(){
      if( this.user.userId == null || this.user.password == null ){
          alert('ID 또는 Password를 확인해 주세요.')
          return;
      };
      // 서버로 보낼 ID, Password
      let jsonData = {"id": this.user.userId,"password": this.user.password};

      // 로그인 체크
      registerLogin(JSON.stringify(jsonData))
      .then((response) => {
        console.log('response',response)
        if(response.headers.authorization == "noId"){
            alert('존재하지 않는 계정입니다.')
        }else if(response.headers.authorization == "wrongPwd"){
            alert('Password가 틀렸습니다.')
        }else if(response.headers.authorization == "correctUser"){
            console.log('로그인 성공.')
            console.log("로그인 성공 시 토큰 : "+response.headers.authtoken)

            console.log('성공 데이터 : ',response.data)

            // store에 토큰 및 유저정보 저장
            this.$store.state.loginData = {
              sysId  : response.data.sys_id,
              id     : response.data.id,
              userNo : response.data.userNo,
              name   : response.data.name
            }
            // localStorage에 토큰 저장
            localStorage.setItem('authtoken',response.headers.authtoken)

        }
      })
      .catch(err =>{
        console.log(err)
      })
    }
  }
}
</script>