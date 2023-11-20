<template>
  <div class="div-groups">
    <div style="margin-left: 30%">
      <span>待选择数据</span>
      <ul class="chDiv">
        <li
          :class="{ sel: index === selIndex }"
          v-for="(item, index) in unCh"
          :key="index"
          @click="cli(index)"
        >
          {{ item?.name }}
        </li>
      </ul>
    </div>
    <div class="choosebutton">
      <el-row>
        <el-col>
          <el-button type="primary" @click="choose()">&gt;</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-button type="primary" @click="unChoose()">&lt;</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-button type="primary" @click="chooseAll()">&gt;&gt;</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-button type="primary" @click="unChooseAll()">&lt;&lt;</el-button>
        </el-col>
      </el-row>
    </div>
    <div>
      <span>已选择数据</span>
      <ul class="chDiv">
        <li
          :class="{ sel: index === unSelIndex }"
          v-for="(item, index) in ch"
          @click="cliUnChoose(index)"
          :key="index"
        >
          {{ item?.name }}
        </li>
      </ul>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import type { Item } from './Item'

const props = defineProps({ unChoosedData: Array<Item>, choosedData: Array<Item> })

let selIndex = ref(-1)
const cli = (index: number) => {
  selIndex.value = index
  unSelIndex.value = -1
}
let unSelIndex = ref(-1)
const cliUnChoose = (index: number) => {
  unSelIndex.value = index
  selIndex.value = -1
}
watch(
  [() => props.unChoosedData, () => props.choosedData],
  (newVal) => {
    unCh.splice(0, unCh.length)
    newVal[0]?.forEach((t: any) => {
      unCh.push(t)
    })
    ch.splice(0, ch.length)
    newVal[1]?.forEach((t) => {
      ch.push(t)
    })
  },
  {
    deep: true
  }
)

const unCh: Array<Item> = reactive<Array<Item>>([])
const ch: Array<Item> = reactive<Array<Item>>([])
props.unChoosedData?.forEach((t) => {
  unCh.push(t)
})

props.choosedData?.forEach((t) => {
  ch.push(t)
})

const choose = () => {
  if (selIndex.value > -1) {
    ch.push(unCh[selIndex.value])
    unCh.splice(selIndex.value, 1)
    selIndex.value = -1
  }
}
const chooseAll = () => {
  ch.push(...unCh)
  unCh.splice(0, unCh.length)
}
const unChoose = () => {
  if (unSelIndex.value > -1) {
    unCh.push(ch[unSelIndex.value])
    ch.splice(unSelIndex.value, 1)
    unSelIndex.value = -1
  }
}
const unChooseAll = () => {
  unCh.push(...ch)
  ch.splice(0, ch.length)
}

const getData = () => {
  return ch
}

defineExpose({
  getData
})
</script>

<style scoped>
.div-groups {
  display: flex;
}

.el-button {
  width: 100%;
}
ul li {
  list-style: none;
  padding: 0;
  margin: 0;
  padding-left: 5px;
}

ul {
  padding-left: 0px;
}

li:hover {
  background-color: aliceblue;
  color: black;
}
.sel {
  background-color: aliceblue;
  color: black;
}

.choosebutton {
  top: 50px;
  width: 100px;
}
.chDiv {
  width: 250px;
  height: 200px;
  border: 1px solid white;
}
</style>
