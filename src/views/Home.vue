<template>
    <div>
        <div style="padding:10px 0">
            <el-input style="width:200px" placeholder="输入电话" suffix-icon="el-icon-search" v-model="phone">
            </el-input>
            <el-button class="ml-5" type="primary" @click="search" v-model="phone">搜索
            </el-button>
        </div>
        <b>今日未打卡</b>
        <div style="margin:10px 0">
            <el-button type="primary" >芝士雪豹 <i class="el-icon-download"></i></el-button>
            <el-button type="primary" >芝士按钮 <i class="el-icon-circle-plus-outline"></i></el-button>
        </div>

        <el-table :data="tableData" border>
            <el-table-column prop="name" label="姓名" width="140">
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column prop="department" label="部门">
            </el-table-column>
            <el-table-column prop="position" label="职位">
            </el-table-column>

        </el-table>
        <div style="padding:10px 0">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="current_page" :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

    


    </div>
</template>

<script>
import axios from 'axios';
import { METHODS } from 'http';

export default {
    name: "User",
    data() {
        return {
            cid: 1,
            total: 0,
            pageSize: 10,
            current_page: 1,
            tableData: [],
            phone: "",
            form: {},
            dialogFormVisible: false,
        }
    },
    props: {

    },
    created() {
        this.load()
    },
    methods: {

        search() {

        },
        handleApp() {
            this.dialogFormVisible = true
            this.form = {}
        },
        save() {
            this.form.cid = 1
            this.request.post("/Staff/addOrUpdateStaff", this.form).then(res => {
                if (res) {
                    this.$message.success("保存成功")
                    this.dialogFormVisible = false
                    this.load()
                } else {
                    this.$message.success("保存失败")
                }
            })
        },
        handleEdit(row) {
            this.form = row
            this.dialogFormVisible = true
        },
        handleDelete(id) {
            console.log(id)
            this.request.get("/Staff/deleteStaff",
                {
                    params: {
                        uid: id
                    }
                }).then(res => {
                    if (res) {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })
        },
        load() {
            this.request.get("/DataManage/currentUnfinished", {
                params: {
                    // cid: this.cid,
                    current_page: this.current_page,
                    pageSize: this.pageSize,
                }
            }).then(res => {
                this.tableData = res.result
                this.total = res.total

            })

        },
        handleImportSuccess(){
            this.$message.success("导入成功")
            this.load()
        },
        downloadExcel() {
            window.open("http://47.100.71.212:8877/Excel/download")

        },
        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(current_page) {
            this.current_page = current_page
            this.load()
        },
    }
}
</script>

<style scoped>

</style>