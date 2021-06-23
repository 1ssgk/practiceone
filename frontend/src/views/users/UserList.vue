<template>
    
</template>

<script>
export default {
    components:{
        AllUsers,
        SignUp
    },
    data(){
        return{
        }
    },
    computed : {
        
    },
    created() {
        console.log('created')
    },
    destroyed(){
        console.log('destroyed')
    },
    // beforeRouteEnter (to,from,next) { // 페이지 오픈전에 보안
    //     console.log('befroe Enter')

    //     // next()
    // },
    // beforeRouteLeave (to,from,next) { // route 가 이 페이지를 떠나기 전에
    //     console.log('leave')

    //     //next()
    // },
}
</script>