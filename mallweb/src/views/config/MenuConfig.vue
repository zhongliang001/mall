<template>
  <div>
    <el-container>
      <el-main>
        <el-form labelWidth="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="菜单id" :inline-message="true" size="medium" prop="menuId">
                <el-input name="el-input" :min-rows="2" type="text" v-model="queryData.condition.menuId"
                  placeholder="菜单id" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名" size="medium" prop="menuName">
                <el-input name="el-input" :min-rows="2" type="text" v-model="queryData.condition.menuName"
                  placeholder="菜单名" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label-width="0" style="text-align:center">
            <zl-button @click="query(true)">查询</zl-button>
            <zl-button nativeType="reset">重置</zl-button>
          </el-form-item>
        </el-form>
        <zl-button @click="add">新增</zl-button>
        <zl-button @click="update">修改</zl-button>
        <zl-button @click="del">删除</zl-button>
        <zl-button @click="viewDetail">查看</zl-button>
        <!-- <el-table :data="menus" v-loading="loading" element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)" :border="true"
          highlight-current-row stripe @current-change="handleCurrentChange" header-row-class-name="header"> -->
        <zl-table :data="menus" :loading="loading"  @current-change="handleCurrentChange" >
          <el-table-column label="菜单id" align="center" prop="menuId"></el-table-column>
          <el-table-column label="菜单名" align="center" prop="menuName"></el-table-column>
          <el-table-column label="父菜单id" align="center" prop="parentId"></el-table-column>
          <el-table-column label="菜单路径" align="center" prop="path"></el-table-column>
        </zl-table>
        <el-pagination :total="total" :page-size="queryData.pageSize" :pager-count="5"
          :current-page.sync="queryData.pageNum" @current-change="change"></el-pagination>
      </el-main>
    </el-container>
    <el-dialog ref="addDialog" :visible.sync="isAddShow" title="新增菜单">
      <el-container>
        <el-main>
          <el-form :model="menu" ref="addForm" labelWidth="100px" :rules="addRule">
            <el-row>
              <el-col :span="12">
                <el-form-item label="菜单名" size="medium" prop="menuName">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="menu.menuName" placeholder="菜单名" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="父菜单id" size="medium" prop="parentId">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="menu.parentId" placeholder="父菜单id"
                    @blur="checkParentId(menu, 'addDialog', 'saveBtn')" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="菜单路径" size="medium" prop="path">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="menu.path" placeholder="菜单路径" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label-width="0" style="text-align:center">
              <zl-button ref="saveBtn" @click="save" :disabled="false">保存</zl-button>
              <zl-button @click="close">关闭</zl-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-Dialog>
    <el-Dialog ref="updateDialog" :visible.sync="isUpateShow" title="修改菜单">
      <el-container>
        <el-main>
          <el-form :model="updateMenu" ref="updateForm" labelWidth="100px" :rules="updateRule">
            <el-row>
              <el-col :span="12">
                <el-form-item label="菜单id" size="medium" prop="menuId">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="updateMenu.menuId" :readonly="true"
                    placeholder="菜单id" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="菜单名" size="medium" prop="menuName">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="updateMenu.menuName" placeholder="菜单名" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="父菜单id" size="medium" prop="parentId">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="updateMenu.parentId" placeholder="父菜单id"
                    @blur="checkParentId(updateMenu, 'updateDialog','updateBtn')" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="菜单路径" size="medium" prop="path">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="updateMenu.path" placeholder="菜单路径" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label-width="0" style="text-align:center">
              <zl-button ref="updateBtn" :disabled="false" @click="saveUpdate">保存</zl-button>
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
                <el-form-item label="菜单id" size="medium" prop="menuId">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="viewMenu.menuId" :readonly="true" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="菜单名" size="medium" prop="menuName">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="viewMenu.menuName" :readonly="true" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="父菜单id" size="medium" prop="parentId">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="viewMenu.parentId" :readonly="true" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="菜单路径" size="medium" prop="path">
                  <el-input name="el-input" :min-rows="2" type="text" v-model="viewMenu.path" :readonly="true" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label-width="0" style="text-align:center">
              <zl-button @click="closeView">关闭</zl-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-Dialog>
  </div>
</template>
<script>
export default {
  name: 'MenuConfig',
  data: function () {
    return {
      queryData: {
        condition: {},
        pageSize: 5,
        pageNum: 1
      },
      menus: [],
      menu: {},
      currentRow: {},
      total: 0,
      releaseGoods: false,
      addRule: {
        menuName: [{ required: true, message: '请输入菜单名', trigger: 'blur' }]
      },
      isAddShow: false,
      isUpateShow: false,
      updateMenu: {},
      updateRule: {
        menuName: [{ required: true, message: '请输入菜单名', trigger: 'blur' }]
      },
      loading: false,
      isViewShow: false,
      viewMenu: {}
    }
  },
  methods: {
    query: function (flag) {
      if (flag) {
        this.queryData.pageNum = 1
      }
      const _this = this
      _this.loading = true
      const url = this.service.userURL + '/menu/'
      const queryData = this.queryData
      this.service.request({
        method: 'post',
        url: url,
        data: queryData,
        success: function (rep) {
          _this.loading = false
          _this.menus = rep.data
          _this.total = rep.total
          _this.queryData.pageNum = rep.pageNum
        }
      })
    },
    handleCurrentChange: function (val) {
      this.currentRow = val
    },
    change: function () {
      this.query()
    },
    add: function () {
      this.isAddShow = true
    },
    save: function () {
      const _this = this
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          const url = this.service.userURL + '/menu/add'
          const menu = this.menu
          this.service.request({
            method: 'post',
            url: url,
            data: menu,
            success: function (rep) {
              _this.isAddShow = false
              _this.menu = {}
              _this.query()
            }
          })
        }
      })
    },
    checkParentId: function (menu, dialog, btn) {
      const _this = this
      if (_this.$refs[dialog].visible) {
        _this.$refs[btn].disabled = true
        const url = this.service.userURL + '/menu/checkParentId?menuId=' + menu.parentId
        this.service.request({
          method: 'post',
          url: url,
          success: function (rep) {
            _this.$refs[btn].disabled = false
            if (rep.data === false) {
              if (_this.$refs[dialog].visible) {
                _this.$message.error('父菜单id不存在')
              }
              menu.parentId = null
            }
          },
          failed: function (rep) {
            _this.$refs[btn].disabled = false
          }
        })
      }
    },
    close: function () {
      this.isAddShow = false
      this.menu = {}
    },
    update: function () {
      if (JSON.stringify(this.currentRow) !== '{}') {
        this.isUpateShow = true
        this.updateMenu = this.currentRow
      } else {
        this.$message({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    },
    saveUpdate: function () {
      const _this = this
      this.$refs.updateForm.validate((valid) => {
        if (valid) {
          const url = this.service.userURL + '/menu/update'
          const menu = this.updateMenu
          this.service.request({
            method: 'post',
            url: url,
            data: menu,
            success: function (rep) {
              _this.isUpateShow = false
              _this.updateMenu = {}
              _this.query()
            }
          })
        }
      })
    },
    closeUpdate: function () {
      this.isUpateShow = false
    },
    del: function () {
      if (JSON.stringify(this.currentRow) !== '{}') {
        const _this = this
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const url = this.service.userURL + '/menu/delete?menuId=' + this.currentRow.menuId
          this.service.request({
            method: 'post',
            url: url,
            success: function (rep) {
              _this.query()
              _this.$message({
                type: 'success',
                message: '删除成功!'
              })
            }
          })
        }).catch(() => {
        })
      } else {
        this.$message({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    },
    viewDetail: function () {
      if (JSON.stringify(this.currentRow) !== '{}') {
        this.isViewShow = true
        this.viewMenu = this.currentRow
      } else {
        this.$message({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    },
    closeView: function () {
      this.isViewShow = false
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
