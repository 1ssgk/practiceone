<template>
<v-app>
    <v-container grid-list-xl>
        <v-layout row wrap>
            <v-flex xs6>
                <AllUsers></AllUsers>
            </v-flex>
            <v-flex xs6>
                <SignUp></SignUp>
            </v-flex>
        </v-layout>
    </v-container>
</v-app>
</template>

<script>
import AllUsers from '@/components/users/AllUsers'
import SignUp from '@/components/users/SignUp'

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
    // beforeRouteEnter (to,from,next) { // 페이지 오픈전에 보안
    //     console.log('befroe Enter')

    //     // next()
    // },
    // beforeRouteLeave (to,from,next) { // route 가 이 페이지를 떠나기 전에
    //     console.log('leave')

    //     //next()
    // },
    created() {
        console.log('created')
        console.log(' users 궁금하네 이게 먼저 켜지는지')
        
    },
    destroyed(){
        console.log('destroyed')
    }
}
</script>

