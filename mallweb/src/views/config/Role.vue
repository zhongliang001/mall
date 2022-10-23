<template>
    <div>
        <el-container>
            <el-main>
                <el-form labelWidth="100px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item
                                label="角色id"
                                :inline-message="true"
                                size="default"
                                prop="roleId"
                            >
                                <el-input
                                    name="el-input"
                                    :min-rows="2"
                                    type="text"
                                    v-model="queryData.condition.roleId"
                                    placeholder="角色id"
                                />
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item
                                label="角色名"
                                :inline-message="true"
                                size="default"
                                prop="roleName"
                            >
                                <el-input
                                    name="el-input"
                                    :min-rows="2"
                                    type="text"
                                    v-model="queryData.condition.roleName"
                                    placeholder="角色id"
                                />
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label-width="0" style="text-align: center">
                        <zl-button @click="query(true)">查询</zl-button>
                        <zl-button nativeType="reset">重置</zl-button>
                    </el-form-item>
                </el-form>
                <zl-table
                    :data="actions"
                    :loading="loading"
                    @current-change="selected"
                >
                    <el-table-column
                        label="角色id"
                        align="center"
                        prop="roleId"
                    ></el-table-column>
                    <el-table-column
                        label="角色名"
                        align="center"
                        prop="roleName"
                    ></el-table-column>
                </zl-table>
                <el-pagination
                    :total="total"
                    :page-size="queryData.pageSize"
                    :pager-count="5"
                    v-model:current-page="queryData.pageNum"
                    @current-change="query()"
                ></el-pagination>
            </el-main>
        </el-container>
    </div>
</template>
<script lang="ts">
import { reactive, toRefs, ref, getCurrentInstance } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'

export default {
    name: 'RoleInfo',
    setup() {
        const state = reactive({
            queryData: {
                condition: {
                    roleId: '',
                    roleName: ''
                },
                pageSize: 5,
                pageNum: 1
            },
            total: 0,
            actions: [],
            currentRow: {
                menuId: '',
                menuName: '',
                parentId: '',
                path: ''
            },
            loading: false
        })
        interface rep {
            data: any
            total: number
            pageNum: number
        }
        // @ts-ignore
        const { proxy } = getCurrentInstance()
        const service = proxy.$service
        const query = (flag?: Boolean) => {
            if (flag) {
                state.queryData.pageNum = 1
            }
            state.loading = true
            console.log(service)
            const url = service.userURL + '/role/'
            const queryData = state.queryData
            service.request({
                method: 'post',
                url: url,
                data: queryData,
                success: function (rep: rep) {
                    state.loading = false
                    state.actions = rep.data
                    state.total = rep.total
                    state.queryData.pageNum = rep.pageNum
                }
            })
        }
        const selected = (val: {
            menuId: ''
            menuName: ''
            parentId: ''
            path: ''
        }) => {
            state.currentRow = val
        }
        return {
            ...toRefs(state),
            query,
            selected
        }
    }
}
</script>
