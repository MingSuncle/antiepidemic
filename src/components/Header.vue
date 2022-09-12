<template>
    <div style="font-size: 12px;line-height:60px;display:flex;">
        <div style="flex: 1; font-size: 18px">
            <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
            <el-breadcrumb separator="/" style="display:inline-block;margin-left: 10px;" >
                <el-breadcrumb-item v-for="(item,index) in breadList" :key="index" :to="{path:getPath(item.path)}">
                    {{digestParams(item.meta.title,$route.params)}}
                </el-breadcrumb-item> 
            </el-breadcrumb>
        </div>
        <el-dropdown style="width:70px">
            <span style="margin-right: 5px;">小明</span><i class="el-icon-setting" style="margin-right: 5px"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item style="font-size:14px;padding: 5px 0;">个人信息</el-dropdown-item>
                <el-dropdown-item style="font-size:14px;padding: 5px 0;">
                <router-link to="/login" style="text-decoration: none">退出</router-link>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>

    </div>
</template>

<script>
export default{
    name:"Header",
    props:{
        collapseBtnClass:String,
        collapse:Function
    },
    data:function(){
        return{
            breadList:[],
        }
    },
    mounted:function(){
        this.getBreadList(this.$route);
    },
    watch:{
        $route(val){
            this.getBreadList(val);
        }
    },
    methods: {
        getBreadList(val) {
            if (val.matched) {
                let matched = val.matched.filter(
                    (item) => item.meta && item.meta.title
                );
                this.breadList = matched;
            }
        },
        getPath(path) {
            if (path === "") {
                return "/";
            }
            return this.digestParams(path, this.$route.params);
        },
        digestParams(breadcrumbName, params) {
            if (!breadcrumbName) {
                return null;
            }
            var paramsKeys = Object.keys(params).join("|");
            return breadcrumbName.replace(
                new RegExp(":(".concat(paramsKeys, ")"), "g"),
                function (replacement, key) {
                    return params[key] || replacement;
                }
            );
        },
    },
    
    }



</script>

<style scoped>
</style>