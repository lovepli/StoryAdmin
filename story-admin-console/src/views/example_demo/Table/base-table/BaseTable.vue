<template>
  <div>
     <el-row>
    <el-tag :key="tag" v-for="tag in dynamicTags" type="danger"  style="width:100%" closable :disable-transitions="false" @close="handleClose(tag)">{{tag}}</el-tag>
    </el-row>
    <el-table :data="tableData" border highlight-current-row @header-contextmenu="contextmenu">
      <el-table-column v-if="colData[0].istrue" type="index" label="序号" width="80px"/>
      <el-table-column v-if="colData[1].istrue" prop="date" label="日期"/>
      <el-table-column v-if="colData[2].istrue" prop="name" label="姓名"/>
      <el-table-column v-if="colData[3].istrue" prop="age" label="年龄" sortable/>
      <!-- <el-table-column
      prop="gender"
      label="性别"
      :filters="tableMng.formatTable('gender', 'value', 'text')"
      :filter-method="handleFilter">
      <template slot-scope="scope">
        <span>{{tableMng.getNameById('gender',scope.row.gender)}}</span>
      </template>
    </el-table-column> -->

      <el-table-column
        v-if="colData[4].istrue"
        :filters="[{ text: '男', value: '1' }, { text: '女', value: '2' }]"
        :filter-method="handleFilter"
        prop="gender"
        label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.gender === '1'">{{ '男' }}</span>
          <span v-if="scope.row.gender === '2'">{{ '女' }}</span>
          <!-- <el-tag type="danger" v-if="scope.row.gender === '1'">男</el-tag>
      <el-tag type="success" v-if="scope.row.gender === '2'">女</el-tag> -->
        </template>
      </el-table-column>
    </el-table>
    <!--右键弹出的菜单内容-->
    <!--动态计算菜单出现的位置-->
    <div v-show="menuVisible" :style="{top:top+ &quot;px&quot;,left:left+ &quot;px&quot;}" class="menu1">
      <el-checkbox-group v-model="colOptions">
        <el-checkbox v-for="item in colSelect" :key="item" :label="item" />
      </el-checkbox-group>
    </div>
  </div>
</template>

<script>
import Mock from 'mockjs'; // 模拟生成表格数据
// import tableMng from '@/utils/wangluyao/tableMng';
const data = Mock.mock({
  'tableData|10': [{
    id: '@lower(@guid)',
    date: '@datetime("yyyy-MM-dd HH:mm:ss")',
    name: '@cname',
    age: '@natural(20,40)',
    gender: '@pick(["1", "2"])'
  }]
})

export default {
  name: 'BaseTable',
  data() {
    return {
      // tableMng,
      tableData: [],
     dynamicTags: ['!提示：鼠标放在表格栏右键单击可以对列表数据进行筛选显示'], // 动态标签
      // vue——动态控制表格列的显示和隐藏 参考：https://blog.csdn.net/qq_44450612/article/details/99715181
      menuVisible: false, // 右键菜单的显示与隐藏
      top: 0,		// 右键菜单的位置
      left: 0,
      colOptions: ['序号', '日期', '姓名', '年龄', '性别'], // 多选框的选择项
      colSelect: ['序号', '日期', '姓名', '年龄', '性别'], 	// 多选框已选择的内容，即表格中显示的列
      // istrue属性存放列的状态
      colData: [
    	  { title: '序号', istrue: true },
        { title: '日期', istrue: true },
        { title: '姓名', istrue: true },
        { title: '年龄', istrue: true },
        { title: '性别', istrue: true }
      ]
    }
  },
  // Vue 提供了一种更通用的方式来观察和响应 Vue 实例上的数据变动：侦听属性。当你有一些数据需要随着其它数据变动而变动时，你很容易滥用 watch——特别是如果你之前使用过 AngularJS。然而，通常更好的做法是使用计算属性computed而不是命令式的 watch 回调
  // 虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的。
  watch: {
    // 观察 Vue 实例上的一个表达式或者一个函数计算结果的变化。回调函数得到的参数为新值和旧值。表达式只接受简单的键路径。对于更复杂的表达式，用一个函数取代。
    // 注意：在变更 (不是替换) 对象或数组时，旧值将与新值相同，因为它们的引用指向同一个对象/数组。Vue 不会保留变更之前值的副本。
    colOptions(newVal, oldVal) {
      if (newVal) { // 如果有值发生变化，即多选框的已选项变化
        var arr = this.colSelect.filter(i => newVal.indexOf(i) < 0) 	// 未选中
        this.colData.filter(i => {
          if (arr.indexOf(i.title) !== -1) {
            i.istrue = false
          } else {
            i.istrue = true
          }
        })
      }
    }
  },
  created() {
    this.tableData = data.tableData;
    console.log('表格数：' + JSON.stringify(this.tableData))
  },
  methods: {
     /**
     * 关闭提示标签
     */
    handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },
    contextmenu(row, event) {
      // 先把菜单关闭，目的是第二次或者第n次右键鼠标的时候 它默认的是true
      this.menuVisible = false
      // 显示菜单
      this.menuVisible = true
      window.event.returnValue = false // 阻止浏览器自带的右键菜单弹出
      // 给整个document绑定click监听事件， 左键单击任何位置执行foo方法
      document.addEventListener('click', this.foo)
      // event对应的是鼠标事件，找到鼠标点击位置的坐标，给菜单定位
      this.top = event.clientY
      this.left = event.clientX
    },
    foo() {
      this.menuVisible = false // 关闭菜单栏
      document.removeEventListener('click', this.foo) // 解绑click监听，很重要，具体原因可以看另外一篇博文
    },
    /** 前端根据字段类别过滤，不分页过滤 */
    handleFilter(value, row, column) {
      const property = column.property;
      return row[property] === value;
    }
  }
}
</script>

<style scoped>
.menu1{
  position:fixed;
  height:auto;
  width:231px;
  border-radius: 3px;
  border: 1px solid #999999;
  background-color: #f4f4f4;
  padding: 10px;
   z-index: 1000
}
.el-checkbox{
  display:block;
  height:20px;
  line-height:20px;
  padding:0 5px;
  margin-right:0;
  font-size:12px;
  border: 1px solid transparent;
}
.el-checkbox:hover{
  border-radius: 3px;
  border: 1px solid #999999;
}
</style>
