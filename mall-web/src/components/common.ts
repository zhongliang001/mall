import { ElMessageBox } from 'element-plus'

/**
 * 进行修改
 * @param zltable 表格信息
 * @param toMod 跳转方法
 */
const mod = (zltable: any, toMod: Function) => {
  if (zltable.currentRow) {
    toMod()
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toAdd = (page: string) => {
  page = 'add'
}

export { mod, toAdd }
