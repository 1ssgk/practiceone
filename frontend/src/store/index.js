import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { //data
    allUsers:[
      {userId: 'hoza123', password: '123', name: 'Hoza', address: 'Seoul', src:'https://cdn.vuetifyjs.com/images/lists/1.jpg'},
      {userId: 'max123', password: '456', name: 'Max', address: 'Berlin', src:'https://cdn.vuetifyjs.com/images/lists/2.jpg'},
      {userId: 'lego123', password: '789', name: 'Lego', address: 'Busan', src:'https://cdn.vuetifyjs.com/images/lists/3.jpg'}
    ],
    loginData:{}
  },
  getters:{ // computed와 비슷한
    allUsersCount: state =>{
      return state.allUsers.length
    },
    countOfSeoul : state =>{
      let count = 0
      state.allUsers.forEach(user =>{
        if(user.address === 'Seoul'){
          count++
        } 
      })
      return count
    },
    percentOfSeoul : (state, getters) =>{
      return Math.round(getters.countOfSeoul / getters.allUsersCount *100)
    },
  },
  mutations: { // 행위
    addUsers: (state, payload) => {
      console.log('mu adduser')

      state.allUsers.push(payload)
    },
    resetLoginData:(state) => {
      state.loginData = {}
    },
  },
  // mutation 에서 데이터를 바꾸기 전에
  // 서버에서 중복제거 같은 
  actions: {   // mutations에 접근하기위한 
    addUsers: ({ commit }, payload) => {
      console.log('ac adduser')

      commit('addUsers', payload)
    },
    getLoginData: (commit) => {
      let config = {
        headers : {
          "authtoken": localStorage.getItem('authtoken')
        }
      }
      console.log('getLoginData')
      // axios
      // .get('',config)
      // .then(response =>{
      //   console.log('성공했다')
      // })
      // .catch(()=>{
      //   alert("에러에러에러")
      // })
    },
    resetLoginData:({commit}) => {
      commit('resetLoginData')
    },
    uploadFiles:({},payload)=>{
      console.log('업로드 Files',payload)
      let elem = payload

      // 이미지 파일 업로드 / 동시에 여러 파일 업로드
      elem.id = 'image'
      elem.type = 'file'
      elem.accept = 'image/*'
      elem.multiple = true
      // 클릭
      elem.click();

      // 이벤트 감지
      elem.onchange = function() {
        const formData = new FormData()
        for (var index = 0; index < this.files.length; index++) {
          formData.append('fileList', this.files[index])
        }
        axios
        .post('/api/file/upload', 
                   formData, 
                   { headers: { 'Content-Type': 'multipart/form-data' } })
        .then(response => {
          console.log('response',response)
        })
        .catch(error => {
          console.log('error',error.message)
        })
      }
    },
    downloadFiles:({},payload)=>{

      let params = payload

      axios
      .post('/api/file/download', 
                  params, 
                  { headers: { 'Content-Type': 'application/javascript' }, responseType:'blob' })
      .then(response => {
        console.log('response',response)
        var fileURL = window.URL.createObjectURL(new Blob([response.data]));
        var fileLink = document.createElement('a');

        fileLink.href = fileURL;
        fileLink.setAttribute('download', '고양이.jpg');
        document.body.appendChild(fileLink);
        fileLink.click();

      })
      .catch(error => {
        console.log('error',error.message)
      })
    }
  },
  modules: {
  }
})
