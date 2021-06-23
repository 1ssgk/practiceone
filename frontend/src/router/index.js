import Vue from 'vue'
import VueRouter from 'vue-router'
import axios from 'axios'
import store from '../store'

const beforeAuth = inAuth =>  (from, to,next) => {
  // token 유효성 검사
  const url = '/api/login/authChk';
  axios
  .post(url,"dd",{ headers: { 'authtoken': localStorage.getItem('authtoken') } })
  .then((response) => {
      let tokenChk = response.headers.authorization
      // 토큰이 없다면 Home 화면으로 이동
      if( tokenChk === 'notLogin'){
          alert('로그인이 필요합니다.');

          // token 삭제
          localStorage.removeItem('authtoken');
          next("/login")
        }
        else if( tokenChk === 'tokenIsNull' ){
          alert('인증이 만료되었습니다.');

          // token 삭제
          localStorage.removeItem('authtoken');
          next("/login")
        }else{
          // 개인정보 없을경우 새로 가져오기
          console.log("data",store.state.loginData);
          if(store.state.loginData.id == null){
            console.log('들어왔다고?')
            axios.get('/api/login/token', {
              headers: { 'authtoken': localStorage.getItem('authtoken') }
            })
            .then(function (response) {
              console.log(response);
            })
            .catch(function (error) {
              console.log(error);
            })
          }


          next();
      }
  })
  .catch(err =>{
      console.log(err)
      next('/');
  });
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue')
  },
  {
    path: '/users',
    name: 'users',
    
    component: () => import(/* webpackChunkName: "users" */ '../views/users/Users.vue'),
    beforeEnter: beforeAuth(true),
  },
  { // router에 설정되어있지 않은 경로라면 
    path: '/*',
    redirect: {name : 'home'}
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
/*
{
    path: '/users',
    name: 'users',
    
    component: () => import( webpackChunkName: "users"  '../views/users/Users.vue'),
    children:[
      {
        path: ':id',
        name: 'users-detail',
        component: UsersDetail
      },
      {
        path: ':id/edit',
        name: 'users-edit',
        component: UsersEdit
      }
    ]
  },
*/