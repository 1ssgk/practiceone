<template>
  <div>
    <h1>All Users({{ count }})</h1>
    <h3>서울에 사는 인구 분포 : {{ seouls }} ({{ percent }}%)</h3>
    <v-card
    max-width="450"
    class="mx-auto"
    >
      <v-list
      subheader
      two-line
    >
      <v-list-item
        v-for="(user,index) in $store.state.allUsers"
        :key="index"
      >
        <v-list-item-avatar>
          <v-img :src="user.src"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-text="user.name" class="pb-1"></v-list-item-title>

          <v-list-item-subtitle class="caption pb-1">id:#{{index}} / {{user.address}} 거주 </v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <v-btn icon>
            <v-icon color="grey lighten-1">mdi-information</v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>
    </v-list>
    </v-card>
    <div>
    <h3>파일 업로드 결과: </h3>
    <div>
      <button @click="selectUploadFile()">이미지 선택</button>
    </div>
    <div class="subheading">Matching</div>
        <v-img src="@/assets/img/고양이.jpg" aspect-ratio="1.7"></v-img>
        <v-btn >
          <v-icon color="grey lighten-1" @click="selectDownloadFile()">다운로드 버튼</v-icon>
        </v-btn>
  </div>
  </div>
</template>

<script>
import { EventBus } from '@/main.js'
import { mapState,mapGetters } from 'vuex'
import axios from 'axios'

  export default {
    data() {
      return {
        
      }
    },
    computed :{
      ...mapGetters({
        count: 'allUsersCount',
        seouls: 'countOfSeoul',
        percent: 'percentOfSeoul',
      }),
      ...mapState({
        allUsers : 'allUsers'
      })
    },
    mounted() {
      EventBus.$on('signUp', users => {
        this.$store.state.allUsers.push(users)
      })
    },
    methods :{
      //파일 업로드
      selectUploadFile() {
        let elem = document.createElement('input')
        //심심풀이로 다 가져가보기(성공)
        this.$store.dispatch('uploadFiles',elem)
      },
      // 선택한 파일 다운로드
      selectDownloadFile(){
        let params = {fileUrl:'url',fileName:'name'}

        this.$store.dispatch('downloadFiles',params)


        // axios
        // .post('/api/file/download', 
        //             params, 
        //             { headers: { 'Content-Type': 'application/javascript' }, responseType:'blob' })
        // .then(response => {
        //   console.log('response',response)
        //   var fileURL = window.URL.createObjectURL(new Blob([response.data]));
        //   var fileLink = document.createElement('a');

        //   fileLink.href = fileURL;
        //   fileLink.setAttribute('download', '고양이.jpg');
        //   document.body.appendChild(fileLink);
        //   fileLink.click();

        // })
        // .catch(error => {
        //   console.log('error',error.message)
        // })
      }



    }
  }
</script>
