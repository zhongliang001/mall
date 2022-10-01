<template>
  <el-container>
    <el-main>
      <el-form  labelWidth="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="菜单id" :inline-message="true" size="medium" props="menuId">
              <el-input name="el-input" :min-rows="2" type="text" v-model="queryData.condition.menuId" placeholder="菜单id" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名" size="medium" props="menuName">
              <el-input name="el-input" :min-rows="2" type="text" v-model="queryData.condition.menuName" placeholder="菜单名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label-width="0" style="text-align:center">
          <el-button type="primary" @click="query">查询</el-button>
          <el-button type="primary" nativeType="reset">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="menus" :border="true" stripe header-row-class-name="header">
        <el-table-column label="菜单id" align="center" prop="menuId"></el-table-column>
        <el-table-column label="菜单名" align="center" prop="menuName"></el-table-column>
      </el-table>
      <el-pagination :total="total" :page-size="queryData.pageSize" :pager-count="5" :current-page="queryData.pageNum" @current-change="change"></el-pagination>
    </el-main>
  </el-container>
</template>
<script>
export default {
  name: 'MenuConfig',
  data: function () {
    return {
      queryData: {
        condition: {
        },
        pageSize: 1,
        pageNum: 1
      },
      menus: [
        {
          menuId: '1',
          menuName: 'hi'
        }
      ],
      total: 0
    }
  },
  methods: {
    query: function () {
      const _this = this
      const url = this.service.userURL + '/menu/'
      const queryData = this.queryData
      this.service.request({
        method: 'post',
        url: url,
        data: queryData,
        success: function (rep) {
          _this.menus = rep.data
          _this.total = rep.total
          _this.queryData.pageNum = rep.pageNum
        }
      })
    },
    change: function () {
      this.query()
    }
  }
}
</script>
<style>
.header {
  font-size: 15px;
  color: black;
}
</style>
