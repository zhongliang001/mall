<template>
    <div>
        <el-container>
            <el-main>
                <el-form labelWidth="100px">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item
                                label="菜单id"
                                :inline-message="true"
                                size="default"
                                prop="menuId"
                            >
                                <el-input
                                    name="el-input"
                                    :min-rows="2"
                                    type="text"
                                    v-model="queryData.condition.menuId"
                                    placeholder="菜单id"
                                />
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item
                                label="菜单名"
                                size="default"
                                prop="menuName"
                            >
                                <el-input
                                    name="el-input"
                                    :min-rows="2"
                                    type="text"
                                    v-model="queryData.condition.menuName"
                                    placeholder="菜单名"
                                />
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label-width="0" style="text-align: center">
                        <zl-button @zl-click="query(true)">查询</zl-button>
                        <zl-button nativeType="reset">重置</zl-button>
                    </el-form-item>
                </el-form>
                <zl-button @click="add">新增</zl-button>
                <zl-button @click="update">修改</zl-button>
                <zl-button @click="del">删除</zl-button>
                <zl-button @click="viewDetail">查看</zl-button>
                <zl-button @click="configAction">配置操作</zl-button>
                <zl-table
                    :data="menus"
                    :loading="loading"
                    @current-change="selected"
                >
                    <el-table-column
                        label="菜单id"
                        align="center"
                        prop="menuId"
                    ></el-table-column>
                    <el-table-column
                        label="菜单名"
                        align="center"
                        prop="menuName"
                    ></el-table-column>
                    <el-table-column
                        label="父菜单id"
                        align="center"
                        prop="parentId"
                    ></el-table-column>
                    <el-table-column
                        label="菜单路径"
                        align="center"
                        prop="path"
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
        <el-dialog ref="addDialog" v-model="isAddShow" title="新增菜单">
            <el-container>
                <el-main>
                    <el-form
                        :model="menu"
                        ref="addForm"
                        labelWidth="100px"
                        :rules="addRule"
                    >
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单名"
                                    size="default"
                                    prop="menuName"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="menu.menuName"
                                        placeholder="菜单名"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item
                                    label="父菜单id"
                                    size="default"
                                    prop="parentId"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="menu.parentId"
                                        placeholder="父菜单id"
                                        @blur="checkParentId(menu, addDialog)"
                                    />
                                    <!-- @blur="checkParentId(menu, 'addDialog', 'saveBtn')" /> -->
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单路径"
                                    size="default"
                                    prop="path"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="menu.path"
                                        placeholder="菜单路径"
                                    />
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <div>
                            <zl-button
                                ref="saveBtn"
                                @click="save(addForm)"
                                :disabled="addDisabled"
                                >保存</zl-button
                            >
                            <zl-button @click="close">关闭</zl-button>
                        </div>
                    </el-form>
                </el-main>
            </el-container>
        </el-dialog>
        <el-Dialog
            ref="updateDialog"
            :visible.sync="isUpateShow"
            title="修改菜单"
        >
            <el-container>
                <el-main>
                    <el-form
                        :model="updateMenu"
                        ref="updateForm"
                        labelWidth="100px"
                        :rules="updateRule"
                    >
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单id"
                                    size="default"
                                    prop="menuId"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="updateMenu.menuId"
                                        :readonly="true"
                                        placeholder="菜单id"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单名"
                                    size="default"
                                    prop="menuName"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="updateMenu.menuName"
                                        placeholder="菜单名"
                                    />
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="父菜单id"
                                    size="default"
                                    prop="parentId"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="updateMenu.parentId"
                                        placeholder="父菜单id"
                                        @blur="
                                            checkParentId(
                                                updateMenu,
                                                updateDialog
                                            )
                                        "
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单路径"
                                    size="default"
                                    prop="path"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="updateMenu.path"
                                        placeholder="菜单路径"
                                    />
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item
                            label-width="0"
                            style="text-align: center"
                        >
                            <zl-button
                                ref="updateBtn"
                                :disabled="false"
                                @click="saveUpdate"
                                >保存</zl-button
                            >
                            <zl-button @click="closeUpdate">关闭</zl-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-Dialog>
        <el-Dialog ref="viewDialog" :visible.sync="isViewShow" title="查看菜单">
            <el-container>
                <el-main>
                    <el-form labelWidth="100px" :model="viewMenu">
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单id"
                                    size="default"
                                    prop="menuId"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="viewMenu.menuId"
                                        :readonly="true"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单名"
                                    size="default"
                                    prop="menuName"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="viewMenu.menuName"
                                        :readonly="true"
                                    />
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item
                                    label="父菜单id"
                                    size="default"
                                    prop="parentId"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="viewMenu.parentId"
                                        :readonly="true"
                                    />
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item
                                    label="菜单路径"
                                    size="default"
                                    prop="path"
                                >
                                    <el-input
                                        name="el-input"
                                        :min-rows="2"
                                        type="text"
                                        v-model="viewMenu.path"
                                        :readonly="true"
                                    />
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item
                            label-width="0"
                            style="text-align: center"
                        >
                            <zl-button @click="closeView">关闭</zl-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-Dialog>
    </div>
</template>
<script lang="ts">
import { reactive, toRefs, ref, getCurrentInstance } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'

export default {
    setup(props, ctx) {
        const state = reactive({
            queryData: {
                condition: {
                    menuId: '',
                    menuName: '',
                },
                pageSize: 5,
                pageNum: 1,
            },
            menus: [],
            menu: {
                menuId: '',
                menuName: '',
                parentId: '',
                path: '',
            },
            currentRow: { menuId: '', menuName: '', parentId: '', path: '' },
            total: 0,
            releaseGoods: false,
            isAddShow: false,
            isUpateShow: false,
            updateMenu: {
                menuId: '',
                menuName: '',
                parentId: '',
                path: '',
            },
            updateRule: {
                menuName: [
                    {
                        required: true,
                        message: '请输入菜单名',
                        trigger: 'blur',
                    },
                ],
            },
            loading: false,
            isViewShow: false,
            viewMenu: { menuId: '', menuName: '', parentId: '', path: '' },
            addDisabled: false,
        })
        const addRule = reactive<FormRules>({
            menuName: [
                { required: true, message: '请输入菜单名', trigger: 'blur' },
            ],
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
            console.log('hi2')
            if (flag) {
                state.queryData.pageNum = 1
            }
            state.loading = true
            console.log(service)
            const url = service.userURL + '/menu/'
            const queryData = state.queryData
            service.request({
                method: 'post',
                url: url,
                data: queryData,
                success: function (rep: rep) {
                    state.loading = false
                    state.menus = rep.data
                    state.total = rep.total
                    state.queryData.pageNum = rep.pageNum
                },
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

        const add = () => {
            state.isAddShow = true
        }

        const addForm = ref<FormInstance>()
        const save = async (formEl: FormInstance | undefined) => {
            if (!formEl) return
            await formEl.validate((valid: any, fields: any) => {
                if (valid) {
                    const url = service.userURL + '/menu/add'
                    const menu = state.menu
                    service.request({
                        method: 'post',
                        url: url,
                        data: menu,
                        success: function (rep: rep) {
                            state.isAddShow = false
                            state.menu = {
                                menuId: '',
                                menuName: '',
                                parentId: '',
                                path: '',
                            }
                            query()
                        },
                    })
                }
            })
        }
        const addDialog = ref()
        const checkParentId = (menu: any, addDialog: any) => {
            if (addDialog.visible) {
                state.addDisabled = true
                const url =
                    service.userURL +
                    '/menu/checkParentId?menuId=' +
                    menu.parentId
                service.request({
                    method: 'post',
                    url: url,
                    success: function (rep: rep) {
                        state.addDisabled = false
                        if (rep.data === false) {
                            if (addDialog.visible) {
                                ElMessage('父菜单id不存在')
                            }
                            menu.parentId = null
                        }
                    },
                    failed: function (rep: rep) {
                        state.addDisabled = false
                    },
                })
            }
        }

        const close = () => {
            state.isAddShow = false
            state.menu = {
                menuId: '',
                menuName: '',
                parentId: '',
                path: '',
            }
        }
        const updateDialog = ref<FormInstance>()
        const update = () => {
            if (JSON.stringify(state.currentRow) !== '{}') {
                state.isUpateShow = true
                state.updateMenu = state.currentRow
            } else {
                ElMessage({
                    message: '请选择一条数据',
                    type: 'warning',
                })
            }
        }

        const saveUpdate = async (formEl: FormInstance | undefined) => {
            if (!formEl) {
                return
            }
            await formEl.validate((valid) => {
                if (valid) {
                    const url = service.userURL + '/menu/update'
                    const menu = state.updateMenu
                    service.request({
                        method: 'post',
                        url: url,
                        data: menu,
                        success: function (rep: rep) {
                            state.isUpateShow = false
                            state.updateMenu = {
                                menuId: '',
                                menuName: '',
                                parentId: '',
                                path: '',
                            }
                            query()
                        },
                    })
                }
            })
        }

        const closeUpdate = () => {
            state.isUpateShow = false
            state.updateMenu = {
                menuId: '',
                menuName: '',
                parentId: '',
                path: '',
            }
        }

        const del = () => {
            if (JSON.stringify(state.currentRow) !== '{}') {
                const _this = this
                ElMessageBox.confirm(
                    '此操作将永久删除该数据, 是否继续?',
                    '提示',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                )
                    .then(() => {
                        const url =
                            service.userURL +
                            '/menu/delete?menuId=' +
                            state.currentRow.menuId
                        service.request({
                            method: 'post',
                            url: url,
                            success: function (rep: rep) {
                                query()
                                ElMessage({
                                    type: 'success',
                                    message: '删除成功!',
                                })
                            },
                        })
                    })
                    .catch(() => {})
            } else {
                ElMessage({
                    message: '请选择一条数据',
                    type: 'warning',
                })
            }
        }

        const viewDetail = () => {
            if (JSON.stringify(state.currentRow) !== '{}') {
                state.isViewShow = true
                state.viewMenu = state.currentRow
            } else {
                ElMessage({
                    message: '请选择一条数据',
                    type: 'warning',
                })
            }
        }

        const closeView = () => {
            state.isViewShow = false
        }

        const router = useRouter()
        const configAction = () => {
            if (JSON.stringify(state.currentRow) !== '{}') {
                router.push({
                    name: 'action',
                    params: {
                        menuId: state.currentRow.menuId,
                        menuName: state.currentRow.menuName,
                    },
                })
            } else {
                ElMessage({
                    message: '请选择一条数据',
                    type: 'warning',
                })
            }
        }

        return {
            ...toRefs(state),
            query,
            selected,
            save,
            add,
            addRule,
            addForm,
            checkParentId,
            addDialog,
            close,
            update,
            updateDialog,
            saveUpdate,
            closeUpdate,
            del,
            viewDetail,
            closeView,
            configAction,
        }
    },
}
</script>
<style>
.header {
    font-size: 15px;
    color: black;
}
</style>
