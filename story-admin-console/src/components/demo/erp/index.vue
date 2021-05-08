<template>
  <div class="app-container">
    <el-table
    :data="tableDataList"
    v-loading="isLoading"
    border
    stripe
    >

<el-table-column label="序号" fixed width="70px" >
  <template slot-scope="scope">
    <span>{{(queryInfo.pageNumber - 1) * queryInfo.pageSize +scope.$index +1}}</span>
  </template>
</el-table-column>

<el-table-column label="机构号" prop="orgNumber" fixed width="100">
</el-table-column>
<el-table-column label="机构名称" prop="orgName" show-overflow-tooltip width="200">
</el-table-column>
    </el-table>

    <el-pagination
    v-if="showPagination"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="queryInfo.pageNumber"
    :page-sizes="[10,20,50,100]"
    :page-size="queryInfo.pageSize"
    layout="total, sizes, prev,pager,next,jumper"
    :total="total"
    background
    ></el-pagination>


  </div>
</template>

<style>

</style>

<script>

export default {

  name: 'erp',
  // 定义本地过滤器
  filters: {

  },
  // data中存放的是el中需要的数据
  data() {
    return {
      // 接受父组件传递的参数
     props:{
       selectDate:String,
       tableId:String
     },
     tableDataList:[],
     queryInfo:{
       dateSelect:"",
       pageNumber:1,
       pageSize:10,
     },
     total:0,
     isLoading:false,
     showPagination:false

    };
  },
  // 侦听属性
  watch: {


  },
  mounted(){
    this.getTableList();
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  created() {},
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
 async getTableList(){
    this.isLoading = true;
    console.log("查询表格！")
    this.queryInfo.dateSelect = this.selectDate
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


  

  },
  handleSizeChange(newSize){
    this.queryInfo.pageSize = newSize;
    this.getTableList();
  },
  handleCurrentChange(newPage){
  this.queryInfo.pageNumber = newPage;
  this.getTableList();
  }

  }
};
</script>
