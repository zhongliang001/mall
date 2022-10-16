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
                    @current-change="change"
                ></el-pagination>
            </el-main>
        </el-container>
    </div>
</template>
<script>
export default {
    name: 'RoleInfo',
    data: function () {
        return {
            queryData: {
                condition: {},
                pageSize: 5,
                pageNum: 1,
            },
            actions: [],
            currentRow: {},
            total: 0,
            loading: false,
        }
    },
    methods: {
        query: function (flag) {
            if (flag) {
                this.queryData.pageNum = 1
            }
            const _this = this
            _this.loading = true
            const url = this.service.userURL + '/role/'
            const queryData = this.queryData
            this.service.request({
                method: 'post',
                url: url,
                data: queryData,
                success: function (rep) {
                    _this.loading = false
                    _this.actions = rep.data
                    _this.total = rep.total
                    _this.queryData.pageNum = rep.pageNum
                },
            })
        },
        selected: function (val) {
            this.currentRow = val
        },
        change: function () {
            this.query()
        },
    },
}
</script>
