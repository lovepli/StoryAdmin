<template>
  <div>分类
    <h3 class="title">列动态展示table案例</h3>
    <el-popover
      placement="bottom"
      width="150"
      trigger="click">
        <el-checkbox-group v-model="checkList">
          <el-checkbox :label="item" v-for="(item, i) in checkLabels" :key="i" style="display: block;"></el-checkbox>
        </el-checkbox-group>
        <span v-show="canDo" class="filterFuncClass" @click="handlerFilterFunc('filter')">筛选</span>
        <span v-show="!canDo" class="disabledClass">筛选</span>
        <span v-show="canDo" class="filterFuncClass" @click="handlerFilterFunc('cancel')">取消</span>
        <span v-show="!canDo" class="disabledClass">取消</span>
        
        <span class="filterClass" slot="reference">筛选
          <!-- <i class="fa fa-filter"></i>  -->
           <i class="el-icon-s-tools" />
          <!-- 筛选的图标样式 -->
        </span>
    </el-popover>
    <el-table :data="tableData" border style="position: relative;">
      <el-table-column type="index" label="序号" width="100" align="center"></el-table-column>
      <el-table-column v-for="(col,index) in colums" :key="index" align="center"
        :prop="col"
        :label="col"
        >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

export default {
  name: 'BaseTable2',
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          salary: 10000
        },
        {
          date: '2016-05-04',
          name: '张小龙',
          address: '上海市普陀区金沙江路 1517 弄',
          salary: 12000
        },
        {
          date: '2016-05-01',
          name: '赵小牛',
          address: '上海市普陀区金沙江路 1519 弄',
          salary: 9000
        },
        {
          date: '2016-05-03',
          name: '李小狗',
          address: '上海市普陀区金沙江路 1516 弄',
          salary: 20000
        },
        {
          date: '2016-05-06',
          name: '孙小猪',
          address: '上海市普陀区金沙江路 1519 弄',
          salary: 5500
        }
      ],
      colums: [],
      checkLabels: [],
      checkList: []
    }
  },
  // 计算属性
  computed: {
    canDo () {
      return this.checkList.length > 0
    }
  },

  watch: {

  },
  created() {
    this.checkLabels = JSON.parse(JSON.stringify(Object.keys(this.tableData[0])))
    // 把colums处理成 ["name","date","address","salary"] 的数组形式
    this.colums = JSON.parse(JSON.stringify(Object.keys(this.tableData[0])))
  },
  methods: {
    handlerFilterFunc (type) {
      console.log(type)
      if (type === 'filter') {
        this.colums = this.checkList
      } else if (type === 'cancel') {
        this.checkList = []
        this.colums = JSON.parse(JSON.stringify(Object.keys(this.tableData[0])))
      }
    }
  }
}
</script>

<style scoped>

</style>
