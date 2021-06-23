<template>
  <hello-world />
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
    created() {
        console.log('created')
        
        let token = this.$store.state.loginData

        console.log('token 있나/.????? ',token)

    },
  }
</script>
