<template>
  <div style="padding:30px;">
    <h2>可选择表格(翻页保存上一页选中表格,点击右侧删除可以控制左侧表格选中)</h2>
    <p>博客连接:<a href="https://www.cnblogs.com/steamed-twisted-roll/p/10451549.html" target="_blank">https://www.cnblogs.com/steamed-twisted-roll/p/10451549.html</a></p>
    <div class="contanier">
      <div class="table-contanier">
        <el-table
          ref="checkTable"
          :data="tableData"
          border
          style="width: 100%"
          row-key="id"
          @selection-change="handleSelectionChange"
        >
          <el-table-column
            type="selection"
            reserve-selection
            width="55"
          />
          <el-table-column
            label="姓名"
            prop="name"
          />
          <el-table-column
            label="年龄"
            prop="age"
          />
          <el-table-column
            label="班级"
            prop="class"
          />
        </el-table>
        <el-pagination
          :total="30"
          layout="prev, pager, next"
          @current-change="changePage"
        />
      </div>
      <ul class="list">
        <p>已选中列表</p>
        <li v-for="(item, index) in list" :key="index" class="item">
          {{ item.name }} <el-button type="text" @click="removeName(item)">删除</el-button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import { getCheckTaleList } from '@/service/list'

export default {
  data() {
    return {
      tableData: [],
      list: [],
      selectedTable: [],
      page: 1
    }
  },
  created() {
    this.getTableData()
  },
  mounted() {
  },
  methods: {
    // 处理表格数据默认展示
    handleTable() {
      const ids = this.list.map(item => { return item.id })
      this.tableData.forEach(item => {
        // console.log(item, this.$refs.checkTable)
        if (item.age < 10 && !ids.includes(item.id)) { // 展示判断条件
          this.$refs.checkTable && this.$refs.checkTable.toggleRowSelection(item, true)
        }
      })
    },
    // 获取表格数据
    getTableData() {
      this.tableData = [{ 'id': 0, 'name': '黎明0', 'age': 18, 'class': 3 }, { 'id': 1, 'name': '黎明1', 'age': 3, 'class': 5 }, { 'id': 2, 'name': '黎明2', 'age': 7, 'class': 3 }, { 'id': 3, 'name': '黎明3', 'age': 2, 'class': 3 }, { 'id': 4, 'name': '黎明4', 'age': 9, 'class': 5 }, { 'id': 5, 'name': '黎明5', 'age': 7, 'class': 2 }, { 'id': 6, 'name': '黎明6', 'age': 7, 'class': 3 }, { 'id': 7, 'name': '黎明7', 'age': 12, 'class': 5 }, { 'id': 8, 'name': '黎明8', 'age': 4, 'class': 4 }, { 'id': 9, 'name': '黎明9', 'age': 14, 'class': 5 }];
      // getCheckTaleList({page: this.page}).then(res => {
      //   // console.log(res)
      //   this.tableData = res.data
      //   // 默认选中方法
      //   // this.handleTable()
      // })
      console.log(this.tableData)
    },

    // 点击表格多选框
    handleSelectionChange(val) {
      console.log('选中的表格', val)
      this.list = val
    },
    // 删除,取消表格选中
    removeName(item) {
      const arr = this.tableData.filter(sub => { return sub.id === item.id })
      const row = arr.length ? arr[0] : item
      // console.log(item, row)
      this.$refs.checkTable && this.$refs.checkTable.toggleRowSelection(row, false)
    },
    // 翻页
    changePage(page) {
      this.page = page
      this.getTableData()
    }
  }
}

</script>

<style scoped>
.contanier {
  display: flex;
}
.table-contanier {
  flex: 1;
}
.list {
  flex: 1;
  border-left: 1px solid #ccc;
  padding: 10px;
  margin-left: 30px;
}
.item {
  padding: 5px 0;
}
</style>
