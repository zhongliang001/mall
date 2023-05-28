<template>
  <div>
    <el-container>
      <el-header>菜单查询</el-header>
      <el-main>
        <el-form>
          <el-row>
            <el-col :span="11">
              <el-form-item label="菜单名" prop="menuName">
                <el-input v-model="formdata.menuName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="菜单中文名" prop="menuCnName">
                <el-input v-model="formdata.menuCnName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="11" :offset="11">
            <el-button type="primary" @click="query()">查询</el-button>
          </el-col>
        </el-row>
        <zl-table ref="zltab" url="/user/menu/" :query-data="formdata">
          <el-table-column label="菜单名" prop="menuName" />
          <el-table-column label="菜单中文名" prop="menuCnName" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive } from 'vue'
const formdata = reactive({
  menuName: '',
  menuCnName: ''
})

const zltab: any = ref(null)

const query = () => {
  zltab.value?.query()
}
</script>
