<template>
    <div>
        <div style="padding:10px 0">
            <el-input style="width:200px" placeholder="输入电话" suffix-icon="el-icon-search" v-model="phone">
            </el-input>
            <el-button class="ml-5" type="primary" @click="search" v-model="phone">搜索
            </el-button>
        </div>

        <div style="margin:10px 0">
            <el-upload action="http://47.100.71.212:8877/Excel/import" :data="{cid:2}" :show-file-list="false" :on-success="handleImportSuccess" style="display:inline-block">
                <el-button type="primary" @click="importStaff">导入员工 <i class="el-icon-upload2"></i></el-button>
            </el-upload>

            <el-button type="primary" @click="downloadExcel" class="ml-5">模板下载 <i class="el-icon-download"></i></el-button>
            <el-button type="primary" @click="handleApp">增加 <i class="el-icon-circle-plus-outline"></i></el-button>
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
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='返回' icon='el-icon-info'
                        icon-color="red" title="确定删除嘛" @confirm="handleDelete(scope.row.uid)">
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-delete"></i>
                        </el-button>
                    </el-popconfirm>

                </template>
            </el-table-column>
        </el-table>
        <div style="padding:10px 0">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="current_page" :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

        <el-dialog title="员工信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px">
                <el-form-item label="员工名称">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工电话">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工部门">
                    <el-input v-model="form.department" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工职称">
                    <el-input v-model="form.position" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工地址">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>


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
            this.request.get("/Staff/staffList", {
                params: {
                    cid: this.cid,
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