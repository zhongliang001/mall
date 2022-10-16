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
        <zl-button @click="view">查看</zl-button>
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
          @current-change="change"
        ></el-pagination>
      </el-main>
    </el-container>
    <el-dialog ref="addDialog" v-model:visible="isAddShow" title="新增操作">
      <el-form
        labelWidth="100px"
        ref="addFrom"
        :model="addAction"
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
                v-model="addAction.menuId"
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
                v-model="addAction.menuName"
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
                v-model="addAction.oper"
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
                v-model="addAction.actionName"
                placeholder="操作名"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label-width="0" style="text-align: center">
          <zl-button @click="saveAdd">保存</zl-button>
          <zl-button @click="closeAdd">关闭</zl-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
      ref="updateDialog"
      v-model:visible="isUpdateShow"
      title="修改操作"
    >
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
    <el-dialog ref="viewDialog" v-model:visible="isViewShow" title="查看操作">
      <el-form
        labelWidth="100px"
        ref="viewForm"
        :model="viewAction"
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
<script>
export default {
  name: "ActionConfig",
  data: function () {
    return {
      queryData: {
        condition: {
          menuId: "",
          menuName: "",
        },
        pageSize: 5,
        pageNum: 1,
      },
      total: 0,
      currentRow: {},
      loading: false,
      actions: [],
      isAddShow: false,
      isUpdateShow: false,
      isViewShow: false,
      addAction: {},
      updateAction: {},
      viewAction: {},
      addRule: {
        oper: [
          {
            required: true,
            message: "操作编码必输",
            trigger: "blur",
          },
        ],
        actionName: [
          {
            required: true,
            message: "操作名必输",
            trigger: "blur",
          },
        ],
      },
      updateRule: {
        oper: [
          {
            required: true,
            message: "操作编码必输",
            trigger: "blur",
          },
        ],
        actionName: [
          {
            required: true,
            message: "操作名必输",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created: function () {
    this.queryData.condition.menuId = this.$route.params.menuId;
    this.queryData.condition.menuName = this.$route.params.menuName;
  },
  methods: {
    query: function (flag) {
      if (flag) {
        this.queryData.pageNum = 1;
      }
      const _this = this;
      _this.loading = true;
      const url = this.service.userURL + "/action/queryMenuAction";
      const queryData = this.queryData;
      this.service.request({
        method: "post",
        url: url,
        data: queryData,
        success: function (rep) {
          _this.loading = false;
          _this.actions = rep.data;
          _this.total = rep.total;
          _this.queryData.pageNum = rep.pageNum;
        },
        failed: function () {
          _this.loading = false;
        },
      });
    },
    add: function () {
      this.isAddShow = true;
      this.addAction = this.queryData.condition;
    },
    selected: function (val) {
      this.currentRow = val;
    },
    change: function (val) {
      this.query();
    },
    saveAdd: function () {
      const _this = this;
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          const url = this.service.userURL + "/action/add";
          const action = this.addAction;
          this.service.request({
            method: "post",
            url: url,
            data: action,
            success: function (rep) {
              _this.isAddShow = false;
              _this.action = {};
              _this.query();
            },
          });
        }
      });
    },
    closeAdd: function () {
      this.isAddShow = false;
      this.action = {};
    },
    update: function () {
      if (JSON.stringify(this.currentRow) !== "{}") {
        this.queryDetail(this.currentRow.actionId, "updateAction");
        this.isUpdateShow = true;
      } else {
        this.$message({
          message: "请选择一条数据",
          type: "warning",
        });
      }
    },
    queryDetail: function (actionId, data) {
      const url = this.service.userURL + "/action/detail?actionId=" + actionId;
      const _this = this;
      this.service.request({
        method: "post",
        url: url,
        success: function (rep) {
          _this[data] = rep.data;
        },
      });
    },
    saveUpdate: function () {
      const _this = this;
      this.$refs.updateForm.validate((valid) => {
        if (valid) {
          const url = this.service.userURL + "/action/update";
          const action = this.updateAction;
          this.service.request({
            method: "post",
            url: url,
            data: action,
            success: function (rep) {
              _this.isUpdateShow = false;
              _this.updateAction = {};
              _this.query();
            },
          });
        }
      });
    },
    closeUpdate: function () {
      this.isUpdateShow = false;
    },
    del: function () {
      if (JSON.stringify(this.currentRow) !== "{}") {
        const _this = this;
        this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            const url =
              this.service.userURL +
              "/action/delete?actionId=" +
              this.currentRow.actionId;
            this.service.request({
              method: "post",
              url: url,
              success: function (rep) {
                _this.query();
                _this.$message({
                  type: "success",
                  message: "删除成功!",
                });
              },
            });
          })
          .catch(() => {});
      } else {
        this.$message({
          message: "请选择一条数据",
          type: "warning",
        });
      }
    },
    view: function () {
      if (JSON.stringify(this.currentRow) !== "{}") {
        this.queryDetail(this.currentRow.actionId, "viewAction");
        this.isViewShow = true;
      } else {
        this.$message({
          message: "请选择一条数据",
          type: "warning",
        });
      }
    },
    closeView: function () {
      this.isViewShow = false;
      this.viewAction = {};
    },
  },
};
</script>
