<template>
  <div>
    <el-container>
      <el-main>
        <el-form labelWidth="100px">
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
                :readonly="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名" size="default" prop="menuName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="queryData.condition.menuName"
                placeholder="菜单名"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
          <el-form-item label-width="0" style="text-align: center">
            <zl-button @click="query(true)">查询</zl-button>
            <zl-button nativeType="reset">重置</zl-button>
          </el-form-item>
        </el-form>
        <zl-button @click="add">新增</zl-button>
        <zl-button @click="update">修改</zl-button>
        <zl-button @click="del">删除</zl-button>
        <zl-button @click="viewDetail">查看</zl-button>
        <zl-table :loading="loading" :data="actions" @current-change="selected">
          <el-table-column
            label="操作id"
            align="center"
            prop="actionId"
          ></el-table-column>
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
            label="操作编码"
            align="center"
            prop="oper"
          ></el-table-column>
          <el-table-column
            label="操作名"
            align="center"
            prop="actionName"
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
    <el-dialog ref="addDialog" v-model:visible="isAddShow" title="新增操作">
      <el-form
        labelWidth="100px"
        ref="addFrom"
        :model="action"
        :rules="addRule"
      >
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
                v-model="action.menuId"
                placeholder="菜单id"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名" size="default" prop="menuName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="action.menuName"
                placeholder="菜单名"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="操作编码"
              :inline-message="true"
              size="default"
              prop="oper"
            >
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="action.oper"
                placeholder="操作编码"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作名" size="default" prop="actionName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="action.actionName"
                placeholder="操作名"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label-width="0" style="text-align: center">
          <zl-button @click="save(addForm)">保存</zl-button>
          <zl-button @click="close">关闭</zl-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog ref="updateDialog" v-model="isUpdateShow" title="修改操作">
      <el-form
        labelWidth="100px"
        ref="updateForm"
        :model="updateAction"
        :rules="addRule"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="操作id"
              :inline-message="true"
              size="default"
              prop="actionId"
            >
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="updateAction.actionId"
                placeholder="操作id"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
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
                v-model="updateAction.menuId"
                placeholder="菜单id"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="菜单名" size="default" prop="menuName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="updateAction.menuName"
                placeholder="菜单名"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="操作编码"
              :inline-message="true"
              size="default"
              prop="oper"
            >
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="updateAction.oper"
                placeholder="操作编码"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="操作名" size="default" prop="actionName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="updateAction.actionName"
                placeholder="操作名"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label-width="0" style="text-align: center">
          <zl-button @click="saveUpdate">保存</zl-button>
          <zl-button @click="closeUpdate">关闭</zl-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog ref="viewDialog" v-model="isViewShow" title="查看操作">
      <el-form labelWidth="100px" ref="viewForm" :model="viewAction">
        <el-row>
          <el-col :span="12">
            <el-form-item
              label="操作id"
              :inline-message="true"
              size="default"
              prop="actionId"
            >
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="viewAction.actionId"
                placeholder="操作id"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
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
                v-model="viewAction.menuId"
                placeholder="菜单id"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="菜单名" size="default" prop="menuName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="viewAction.menuName"
                placeholder="菜单名"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="操作编码"
              :inline-message="true"
              size="default"
              prop="oper"
            >
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="viewAction.oper"
                placeholder="操作编码"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="操作名" size="default" prop="actionName">
              <el-input
                name="el-input"
                :min-rows="2"
                type="text"
                v-model="viewAction.actionName"
                placeholder="操作名"
                :readonly="true"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label-width="0" style="text-align: center">
          <zl-button @click="closeView">关闭</zl-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import { reactive, toRefs, ref, getCurrentInstance } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { menuActionStore } from '@/stores/menuAction'
export default {
  name: 'ActionConfig',
  setup() {
    const store = menuActionStore()
    const state = reactive({
      queryData: {
        condition: {
          menuId: store.menuId,
          menuName: store.menuName
        },
        pageSize: 5,
        pageNum: 1
      },
      loading: false,
      actions: [],
      total: 0,
      currentRow: {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      },
      isAddShow: false,
      action: {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      },
      isUpdateShow: false,
      updateAction: {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      },
      isViewShow: false,
      viewAction: {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      }
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
      const url = service.userURL + '/action/'
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
      actionId: ''
      menuId: ''
      menuName: ''
      oper: ''
      actionName: ''
    }) => {
      state.currentRow = val
    }

    const add = () => {
      state.isAddShow = true
    }
    const addRule = reactive<FormRules>({
      oper: [
        {
          required: true,
          message: '操作编码必输',
          trigger: 'blur'
        }
      ],
      actionName: [
        {
          required: true,
          message: '操作名必输',
          trigger: 'blur'
        }
      ]
    })
    const addForm = ref<FormInstance>()
    const save = async (formEl: FormInstance | undefined) => {
      if (!formEl) return
      await formEl.validate((valid: any, fields: any) => {
        if (valid) {
          const url = service.userURL + '/action/add'
          const action = state.action
          service.request({
            method: 'post',
            url: url,
            data: action,
            success: function (rep: rep) {
              state.isAddShow = false
              state.action = {
                actionId: '',
                menuId: '',
                menuName: '',
                oper: '',
                actionName: ''
              }
              query()
            }
          })
        }
      })
    }

    const close = () => {
      state.isAddShow = false
      state.action = {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      }
    }

    const updateDialog = ref<FormInstance>()
    const update = () => {
      if (state.currentRow.actionId !== '') {
        state.isUpdateShow = true
        state.updateAction = state.currentRow
      } else {
        ElMessage({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    }

    const saveUpdate = async (formEl: FormInstance | undefined) => {
      if (!formEl) {
        return
      }
      await formEl.validate((valid) => {
        if (valid) {
          const url = service.userURL + '/action/update'
          const updateAction = state.updateAction
          service.request({
            method: 'post',
            url: url,
            data: updateAction,
            success: function (rep: rep) {
              state.isUpdateShow = false
              state.updateAction = {
                actionId: '',
                menuId: '',
                menuName: '',
                oper: '',
                actionName: ''
              }
              query()
            }
          })
        }
      })
    }
    const closeUpdate = () => {
      state.isUpdateShow = false
      state.updateAction = {
        actionId: '',
        menuId: '',
        menuName: '',
        oper: '',
        actionName: ''
      }
    }

    const del = () => {
      if (state.currentRow.actionId !== '') {
        const _this = this
        ElMessageBox.confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            const url =
              service.userURL +
              '/action/delete?actionId=' +
              state.currentRow.actionId
            service.request({
              method: 'post',
              url: url,
              success: function (rep: rep) {
                query()
                ElMessage({
                  type: 'success',
                  message: '删除成功!'
                })
              }
            })
          })
          .catch(() => {})
      } else {
        ElMessage({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    }

    const viewDetail = () => {
      if (state.currentRow.menuId !== '') {
        state.isViewShow = true
        state.viewAction = state.currentRow
      } else {
        ElMessage({
          message: '请选择一条数据',
          type: 'warning'
        })
      }
    }

    const closeView = () => {
      state.isViewShow = false
    }

    return {
      ...toRefs(state),
      query,
      selected,
      add,
      save,
      addRule,
      addForm,
      close,
      update,
      saveUpdate,
      closeUpdate,
      del,
      viewDetail,
      closeView
    }
  }
}
</script>
