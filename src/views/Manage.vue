<template>
  <div style="height:100%">
    <el-container style="min-height:100vh">
      <el-aside width="sideWidth + 'px'"
        style="background-color: rgb(238, 241, 246);min-height: 100%;box-shadow: 2px 0 6px rgb(0 21 41 /35%);">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"></Aside>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #000;">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"></Header>
        </el-header>

        <el-main>
          <router-view></router-view>

        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import { request } from 'http'
import Aside from '@/components/Aside.vue'
import Header from '@/components/Header.vue'

export default {
  name: 'HomeView', 
  components: {
    HelloWorld,
    Aside,
    Header
},
  data(){

      return {

        collapseBtnClass:'el-icon-s-fold',
        isCollapse:false,
        sideWidth:200,
        logoTextShow:true,
      
      }
  },

  comments:{
    Aside
  },
  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 64
        this.collapseBtnClass= 'el-icon-s-unfold'
        this.logoTextShow=false
      }
      else{
        this.sideWidth = 200
        this.collapseBtnClass= 'el-icon-s-fold'
        this.logoTextShow=true
      }
    },
    
  }
}
</script>
