<style>
/* flex 가운데 정렬(수직) */
.flex-column{
  display: flex;
  flex-direction: column;
}
/* flex 가운데 정렬(수평) */
.flex-default{
  display: flex;
}

.j-center{
  justify-content: center;
}
v-text-field{
  width:200px;
  height:200px;
}
</style>
<!-- 화면 Start -->
<template>
  <div id="app" class="d-flex flex-column justify-center align-center">
    <v-form class="" style="width:400px">
      <v-text-field
        label="ID"
        name="login"
        type="text"
        outlined
        v-model="user.userId"
      ></v-text-field>

      <v-text-field
        id="password"
        label="Password"
        name="password"
        type="password"
        outlined
        v-model="user.password"
      ></v-text-field>
    </v-form>
    <v-btn @click="loginBtnClick()" color="" style="width:400px">
      로그인
    </v-btn>
  </div>
</template>
<!-- 화면 End -->


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
      // 빈값 체크
      if( !this.user.userId && !this.user.password ){
        alert(' ID와 Password를 확인해 주세요.');
          return;
      }else if(  !this.user.userId  ){
        alert('ID를 확인해 주세요.');
        return;
      }else if( this.user.userId && !this.user.password ){
        alert('Password를 확인해 주세요.');
        return;
      }
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
              sysId     : response.data.sys_id,
              id        : response.data.id,
              userNo    : response.data.userNo,
              name      : response.data.name,
              authtoken : response.headers.authtoken
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