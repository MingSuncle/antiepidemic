<template>
    <el-menu :default-openeds="['2']" style="min-height:100%;overflow-x: hidden;" background-color="rgb(48,65,86)"
        text-color='#fff' active-text-color="#2990d6" :collapse-transition="true" :collapse="isCollapse" router>
        <div style="height:60px;line-height: 60px;text-align: center;">
            <img src="../assets/logoa.png" alt="" style="width:20px;position:relative;top:5px;margin-right: 5px;">
            <b style="color:white " v-show="logoTextShow">企业防疫官</b>
        </div>
        <el-menu-item index="/">
            <template slot="title">
            <i class="el-icon-house"></i>
            <span slot="title">主页</span>
        </template>
        </el-menu-item>
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">系统管理</span>
            </template>
            <el-menu-item index="/user">用户管理</el-menu-item>
        </el-submenu>

    </el-menu>
</template>

<script>
    export default{
        name:"Aside",
        props:{
            isCollapse:Boolean,
            logoTextShow:Boolean
        }
    }
</script>

<style scoped>
</style>