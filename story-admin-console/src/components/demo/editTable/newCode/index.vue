<template>
  <div class="app-container">
    <el-table
      v-loading="isLoading"
      ref="multipleTable"
      :data="tableData"
      border
      stripe
      tooltip-effect="dark"
      height="400px"
      @selection-change="handleSelectionChange"
    >
      <el-table-column :selectable="checkStatus" type="selection" width="40px"/>
      <el-table-column label="序号" fixed width="70px">
        <template slot-scope="scope">
          <span>{{
            (queryInfo.pageNumber - 1) * queryInfo.pageSize + scope.$index + 1
          }}</span>
        </template>
      </el-table-column>

      <el-table-column
        :sortable="true"
        :sort-method="sortName"
        label="币别名称"
        min-width="30%"
      >
        <template slot-scope="{ row, $index }">
          <span v-if="drawRates.showEdit[$index]">
            <el-input
              v-model="row.currencyName"
              size="mini"
              placeholder="请输入内容"
            />
          </span>
          <span v-else>{{ row.currencyName }}</span>
        </template>
      </el-table-column>

      <el-table-column
        :default-sort="{ prop: 'currencyNumber' }"
        :sortable="true"
        :sort-method="sortName"
        label="币别号"
        min-width="30%"
      >
        <template slot-scope="{ row, $index }">
          <span v-if="drawRates.showEdit[$index]">
            <el-input
              v-model="row.currencyNumber"
              size="mini"
              placeholder="请输入内容"
            />
          </span>
          <span v-else>{{ row.currencyNumber }}</span>
        </template>
      </el-table-column>

      <el-table-column label="上传时间" min-width="30%">
        <template slot-scope="scope">
          <span>{{ scope.row.uploadDate }}</span>
        </template>
      </el-table-column>

      <el-table-column label="状态" min-width="30%">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '0'" type="danger">待分配</el-tag>
          <el-tag v-if="scope.row.status === '1'" type="success">已分配</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        fixed="right"
        align="center"
        min-width="40%"
      >
        <template slot-scope="{ row, $index }">
          <el-button
            v-if="drawRates.showBtn[$index]"
            type="success"
            size="mini"
            @click="handleUpdate($index, row)"
          >
            更新
          </el-button>
          <el-button
            v-if="drawRates.showBtn[$index]"
            type="button"
            size="mini"
            @click="handleCancle($index, row)"
          >
            取消
          </el-button>
          <el-button
            v-if="!drawRates.showBtn[$index]"
            type="primary"
            size="mini"
            @click="handleEdit($index, row)"
          >
            编辑
          </el-button>
          <el-button
            v-if="!drawRates.showBtn[$index]"
            type="warning"
            size="mini"
            @click="handleCopy($index, row)"
          >
            复制
          </el-button>
          <el-button
            v-if="!drawRates.showBtn[$index]"
            type="danger"
            size="mini"
            @click="handleDelete($index, row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-if="showPagination"
      :current-page="queryInfo.pageNumber"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="queryInfo.pageSize"
      :total="total"
      layout="total, sizes, prev,pager,next,jumper"
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <div class="btnGroup">
      <el-row>
        <el-button size="small" type="primary" @click="save">保存</el-button>
        <el-button size="small" type="primary" @click="addData">新增</el-button>
        <el-button
          size="small"
          type="primary"
          @click="addAll"
        >批量复制</el-button
        >
        <el-button
          size="small"
          type="primary"
          @click="deleteAll"
        >批量删除</el-button
        >
      </el-row>
    </div>
  </div>
</template>

<style></style>

<script>
export default {
  name: 'NewCode',
  // 定义本地过滤器
  filters: {},
  // 接受父组件传递的参数
  props: {
    selectDate: String,
    tableId: String
  },
  // data中存放的是el中需要的数据
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      queryInfo: {
        dateSelect: '',
        pageNumber: 1,
        pageSize: 10
      },
      total: 0,
      isLoading: false,
      showPagination: false,
      // 储存编辑数据
      drawRates: {
        showEdit: [],
        showBtn: [],
        editIndex: '',
        editData: ''
      }
    };
  },
  // 侦听属性
  watch: {},
  mounted() {
    this.getTableList(); // 页面未显示之前进行数据渲染
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  created() {},
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
    sortName() {},
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection, 'this.multipleSelection');
    },
    // 删除
    handleDelete(index, rows, row) {
      console.log('删除数据！');
      console.log(row.id);
      console.log(row);
      console.log(rows, index);
    },
    handleDetail(index, rows) {},
    goBack() {
      // this.$router.push({path:""})
    },
    save() {},
    addData() {
      const obj = {
        id: '',
        currencyNumber: '',
        currencyName: '',
        uploadDate: ''
      };
      this.tableData.push(obj);
      this.handleEdit(this.tableData.length - 1, obj);
    },
    handleEdit(index, row) {
      this.handleUpdate(this.drawRates.editIndex, this.drawRates.editData);
      this.drawRates.editIndex = index;
      const obj = JSON.parse(JSON.stringify(row));
      this.drawRates.editData = obj;
      this.drawRates.showEdit[index] = true;
      this.drawRates.showBtn[index] = true;
      this.$set(this.drawRates.showEdit, index, true);
      this.$set(this.drawRates.showBtn, index, true);
    },
    handleCancle(index, row) {
      this.drawRates.showEdit[index] = false;
      this.drawRates.showBtn[index] = false;
      this.$set(this.drawRates.showEdit, index, false);
      this.$set(this.drawRates.showBtn, index, false);
      this.tableData[index] = this.drawRates.editData;
      this.drawRates.editData = '';
    },
    handleUpdate(index, row) {
      this.drawRates.showEdit[index] = false;
      this.drawRates.showBtn[index] = false;
      this.$set(this.drawRates.showEdit, index, false);
      this.$set(this.drawRates.showBtn, index, false);
      const obj1 = JSON.parse(JSON.stringify(row));
      this.drawRates.editData = obj1;
    },

    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.tableData.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功'
          });
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
    },

    handleCopy(index, row) {
      this.tableData.splice(index, 0, JSON.parse(JSON.stringify(row)));
    },

    addAll() {
      if (this.multipleSelection.length == 0) {
        const list = {
          id: '',
          currencyNumber: '',
          currencyName: '',
          uploadDate: ''
        };
        this.tableData.push(list);
      } else {
        this.multipleSelection.forEach((val, index) => {
          this.tableData.splice(index, 0, JSON.parse(JSON.stringify(val)));
        });
      }
    },

    deleteAll() {
      for (let i = 0; i < this.tableData.length; i++) {
        const element = this.tableData[i];
        element.id = i;
      }
      if (this.multipleSelection.length == 0) { this.$message.error('请至少选择一项！'); }
      this.multipleSelection.forEach(element => {
        this.tableData.forEach((e, i) => {
          if (element.id == e.id) this.tableData.splice(i, 1);
        });
      });
    },

    async getTableList() {
      console.log('查询表格！');
      this.isLoading = true; // 查询结果没有出来前进行转圈，查询结果出来后结束转圈
      this.queryInfo.dateSelect = this.selectDate;
      // 给后端传递json数据,调用后台接口
      // await dateNewCodeSearch(this.queryInfo)
      // .then(res => {
      //   console.log(res);
      //   this.isLoading = false;
      //   if(res.errCode===0){
      //     this.tableData = res.extend.pageData.records
      //     this.total = res.extend.pageData.total
      //     this.showPagination = this.tableData.length < 1 ? false:true;
      //   }else{
      //      this.$message({
      //         message:"查询失败",
      //         type:"error"
      //        }
      //      );
      //   }

      //   })
      //   .catch(()=>{
      //     this.isLoading = false;
      //     console.log(res);
      //   })
      this.tableData = [
        {
          id: '001',
          currencyNumber: '1',
          currencyName: '机构名称1',
          uploadDate: '2021-05-12',
          status: '0'
        },
        {
          id: '002',
          currencyNumber: '2',
          currencyName: '机构名称2',
          uploadDate: '2021-05-12',
          status: '1'
        },
        {
          id: '003',
          currencyNumber: '3',
          currencyName: '机构名称3',
          uploadDate: '2021-05-12',
          status: '1'
        },
        {
          id: '004',
          currencyNumber: '4',
          currencyName: '机构名称4',
          uploadDate: '2021-05-12',
          status: '0'
        },
        {
          id: '005',
          currencyNumber: '5',
          currencyName: '机构名称5',
          uploadDate: '2021-05-12',
          status: '0'
        },
        {
          id: '006',
          currencyNumber: '6',
          currencyName: '机构名称6',
          uploadDate: '2021-05-12',
          status: '0'
        },
        {
          id: '007',
          currencyNumber: '7',
          currencyName: '机构名称7',
          uploadDate: '2021-05-12',
          status: '1'
        },
        {
          id: '008',
          currencyNumber: '8',
          currencyName: '机构名称8',
          uploadDate: '2021-05-12',
          status: '0'
        },
        {
          id: '009',
          currencyNumber: '9',
          currencyName: '机构名称9',
          uploadDate: '2021-05-12',
          status: '1'
        },
        {
          id: '010',
          currencyNumber: '10',
          currencyName: '机构名称10',
          uploadDate: '2021-05-12',
          status: '1'
        },
        {
          id: '011',
          currencyNumber: '11',
          currencyName: '机构名称11',
          uploadDate: '2021-05-12',
          status: '0'
        }
      ];
      this.total = this.tableData.length;
      this.isLoading = false;
      this.showPagination = !(this.tableData.length < 1);
    },
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getTableList();
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNumber = newPage;
      this.getTableList();
    },
    checkStatus(row, rowIndex) {
      return row.status === '0'; // 默认只能勾选待分配的行数
    }
  }
};
</script>
<style scoped lang="scss">
.btnGroup {
  margin-top: 50px;
  .el-row {
    text-align: center;
  }
}
</style>
