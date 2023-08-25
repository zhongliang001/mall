<template>
  <div>
    <ul>
      <li v-for="(item, index) in data">
        <span @click.stop="showSel(index)">
          {{ item?.menuCnName }}
          <el-icon :size="10">
            <ArrowRight v-if="!isshow[index]" />
            <ArrowDown v-if="isshow[index]" />
          </el-icon>
        </span>
        <zl-select-tree
          v-show="isshow[index]"
          ref="tree"
          v-if="item?.children?.length"
          :data="item.children"
          :selData="selData"
        ></zl-select-tree>
        <ul v-show="isshow[index]">
          <el-checkbox-group v-model="datas">
            <li v-for="i in item?.list">
              <el-checkbox :label="i.actionId">{{ i.actionName }}</el-checkbox>
            </li>
          </el-checkbox-group>
        </ul>
      </li>
    </ul>
  </div>
</template>
<script setup lang="ts">
import { ref, reactive, watch } from 'vue'

const props = defineProps(['data', 'selData'])

let datas: any = ref([])

const tree: any = ref(null)

let selected: any = ref([])

const isshow: boolean[] = reactive([])

watch(
  () => props.selData,
  (newVal, oldVal) => {
    if (newVal.length > 0) {
      newVal.forEach((element: any) => {
        if (props.data.length > 0) {
          props.data.forEach(
            (ds: {
              list: any
              length: number
              forEach: (arg0: (d: { index: (arg0: any) => any }) => void) => void
            }) => {
              if (ds.list && ds.list.length > 0) {
                ds.list.forEach((d: { actionId: any }) => {
                  if (d.actionId === element) {
                    datas.value.push(element)
                  }
                })
              }
            }
          )
        }
      })
    }
  },
  {
    deep: true
  }
)

const getData = () => {
  const tv = tree?.value
  if (tv?.length) {
    for (let i = 0; i < tv?.length; i++) {
      selected.value = datas.value.concat(tv[i].getData().value)
    }
    return selected
  } else {
    return datas
  }
}

const showSel = (index: number) => {
  isshow[index] = !isshow[index]
}

defineExpose({
  getData
})
</script>
<style scoped>
ul li {
  list-style: none;
  padding: 0;
  margin: 0;
  padding-left: 5px;
}
ul {
  padding-left: 5px;
}
</style>
