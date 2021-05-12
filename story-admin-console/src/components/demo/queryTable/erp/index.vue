<template>
  <div class="app-container">
    <el-table 
    :data="tableDataList" v-loading="isLoading" border stripe>
      <el-table-column label="序号" fixed width="70px">
        <template slot-scope="scope">
          <span>{{
            (queryInfo.pageNumber - 1) * queryInfo.pageSize + scope.$index + 1
          }}</span>
        </template>
      </el-table-column>

      <el-table-column label="机构号" prop="orgNumber" fixed width="100">
      </el-table-column>

      <el-table-column
        label="机构名称"
        prop="orgName"
        show-overflow-tooltip
        width="200"
      >
      </el-table-column>

      <el-table-column label="操作" fixed="right" align="center"  min-width="10%">
             <template slot-scope="scope">
               <el-button @click="handleDelete(scope.$index,tableDataList,scope.row)" type="danger" size="mini" style="margin-left:0px;">
                 删除
               </el-button>
               <el-button @click="handleDetail(scope.$index,tableDataList)" type="info" size="mini" style="margin-left:0px;">
                 详情
               </el-button>
             </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-if="showPagination"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pageNumber"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="queryInfo.pageSize"
      layout="total, sizes, prev,pager,next,jumper"
      :total="total"
      background
    ></el-pagination>

    <div class="btnGroup">
      <el-row>
        <el-button size="small" @click="goBack" >返回</el-button>
      </el-row>
    </div>
  </div>
</template>

<style></style>

<script>
export default {
  name: "erp",
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
      tableDataList: [],
      queryInfo: {
        dateSelect: "",
        pageNumber: 1,
        pageSize: 10
      },
      total: 0,
      isLoading: false,
      showPagination: false
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
    // 删除
    handleDelete(index,rows,row){
      console.log("删除数据！")
      console.log(row.id);
      console.log(row);
      console.log(rows,index);
    },
    handleDetail(index,rows){

    },
    goBack(){
      // this.$router.push({path:""})
    },
    async getTableList() {
      console.log("查询表格！");
      this.isLoading = true; // 查询结果没有出来前进行转圈，查询结果出来后结束转圈
      this.queryInfo.dateSelect = this.selectDate;
      // 给后端传递json数据,调用后台接口
      // await dateNewCodeSearch(this.queryInfo)
      // .then(res => {
      //   console.log(res);
      //   this.isLoading = false;
      //   if(res.errCode===0){
      //     this.tableDataList = res.extend.pageData.records
      //     this.total = res.extend.pageData.total
      //     this.showPagination = this.tableDataList.length < 1 ? false:true;
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
      this.tableDataList = [
        {
          id:'001',
          orgNumber: "1",
          orgName: "机构名称1"
        },
        {
          id:'002',
          orgNumber: "2",
          orgName: "机构名称2"
        }
      ],
        this.total = 2;
        this.isLoading = false;
        this.showPagination = this.tableDataList.length < 1 ? false:true;
    },
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getTableList();
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNumber = newPage;
      this.getTableList();
    }
  }
};
</script>
<style scoped lang="scss">
.btnGroup{
  margin-top: 50px;
  .el-row{
    text-align: center;
  }
}
</style>
