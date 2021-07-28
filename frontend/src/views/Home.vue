
<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');

.nanumBrush {
  font-family: 'Nanum Pen Script', cursive;
}

/* 맘에 드는 간격 */
.margin-auto {
  margin: 10px auto;
}

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
.j-right{
  justify-content: right;
}
.j-left{
  justify-content: left;
}

p{
  margin:0 !important;
  padding:0 !important;
}

.main-bg{
  background: rgb(218, 199, 177);
  height:auto;
  width:auto;
  font-family:'Nanum Pen Script', cursive;
  padding:10px;
}
</style>

<template>
    <v-container class="flex-column j-center main-bg">
      <v-row 
        class="text-center flex-column j-center" 
        :style="{
          'margin':`10px auto`,
          'height': `80%`,
          'width': `100%`,
        }"
      >
        <v-col
          class="flex-column j-center"
          :style="{
            'height':`30%`,
          }"
        >
          <h2> [ vue.js & Spring ] </h2>
          <h1 
            class=""
            :style="{
              'text-decoration':`overline`,
              'font-size':`50px`,
            }" 
          >
            연   습   공   간
          </h1>
        </v-col>
        <v-col
          class="flex-column text-center"
          :style="{
              'height':`70%`,
              'font-size':`30px`,
              'white-space': `normal` 
          }"
        >
          <p :style="{'font-size':`35px`}">개린이 <span class="font-weight-bold">김원석</span></p>
          <p>(Kim Won Seok)</p>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
  import HelloWorld from '../components/HelloWorld'
  
  export default {
    name: 'Home',

    components: {
      HelloWorld,
    },
    computed :{
      
    },
    data(){
      return{
        cssProps: {
          backgroundImage: `url(${require('@/assets/img/home/back1.jpg')})`
        }
      }
    },
    created() {
        console.log('created')
        
        let token = this.$store.state.loginData

        console.log('token 있나/.????? ',token)

    },
  }
</script>



