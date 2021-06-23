<template>
<div id="app">
  <v-app id="inspire">
    <v-app id="keep">
      <v-app-bar
        app
        clipped-left
        color="deep-purple"
      >
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <span class="title ml-3 mr-5">Title&nbsp;<span class="font-weight-light">Keep</span></span>
        <v-text-field
          solo-inverted
          flat
          hide-details
          label="Search"
          prepend-inner-icon="search"
        ></v-text-field>
  
        <v-spacer></v-spacer>
        <v-menu
          left
          bottom
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              icon
              v-bind="attrs"
              v-on="on"
            >
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item
              v-if="!$store.state.loginData.authtoken"
              router :to="{name:'login'}"
              dense
            >
              <v-list-item-title>Login</v-list-item-title>
            </v-list-item>
            <v-list-item
              v-if="$store.state.loginData.authtoken"
              @click="logout()"
              dense
            >
              <v-list-item-title>Logout</v-list-item-title>
            </v-list-item>
            <!--  오른쪽 상단 리스트 템플릿
            <v-list-item
              v-for="n in 5"
              :key="n"
              @click="() => {}"
            >
              <v-list-item-title>Option {{ n }}</v-list-item-title>
            </v-list-item> -->
          </v-list>
        </v-menu>
      </v-app-bar>
  
      <v-navigation-drawer
        v-model="drawer"
        app
        clipped
        color="grey lighten-4"
      >
        <v-list
          dense
          class="grey lighten-4"
        >
          <template>
            <!-- <v-row
              v-if="item.heading"
              :key="i"
              align="center"
            >
              <v-col cols="6">
                <v-subheader v-if="item.heading">
                  {{ item.heading }}
                </v-subheader>
              </v-col>
              <v-col
                cols="6"
                class="text-right"
              >
                <v-btn
                  small
                  text
                >edit</v-btn>
              </v-col>
            </v-row>
            <v-divider
              v-else-if="item.divider"
              :key="i"
              dark
              class="my-4"
            ></v-divider> -->
            <v-list-item
              link
              router :to="{name:'home'}"
            >
              <v-list-item-action>
                <v-icon>home</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title class="grey--text">
                  Home
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-list-item
              link
              router :to="{name:'users'}"
            >
              <v-list-item-action>
                <v-icon>people</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title class="grey--text">
                  Users
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list>
      </v-navigation-drawer>
  
      <v-main>
        <v-container
          fluid
          class="grey lighten-4 "
        >
          <router-view></router-view>
        </v-container>
      </v-main>
    </v-app>
  </v-app>
</div>
</template>

<script>


export default {
  name: 'App',
  
  data: () => ({
    drawer: null
  }),
  props: {
      source: String
  },
  created() {
    console.log('created');
    console.log(' APP 궁금하네 이게 먼저 켜지는지')
  },
  methods:{
    logout(){
      alert("로그아웃");

      // store에 토큰 및 유저정보 삭제
      this.$store.state.loginData = {};
      // token 삭제
      localStorage.removeItem('authtoken');

      this.$router.push({
        name: "home",
      })
    }
  },
}
</script>