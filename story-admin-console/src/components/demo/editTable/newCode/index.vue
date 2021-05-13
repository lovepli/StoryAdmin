<template>
  <div class="app-container">
    <el-table 
    :data="tableData" 
    v-loading="isLoading"
     border 
     stripe
     @selection-change="handleSelectionChange"
     >
        <el-table-column
          type="selection"
          width="40px"
        ></el-table-column>
      <el-table-column label="序号" fixed width="70px">
        <template slot-scope="scope">
          <span>{{
            (queryInfo.pageNumber - 1) * queryInfo.pageSize + scope.$index + 1
          }}</span>
        </template>
      </el-table-column>
 
      <el-table-column label="币别名称" :sortable="true" :sort-method="sortName"  min-width="30%">
            <template slot-scope="{ row, $index }">
              <span v-if="drawRates.showEdit[$index]">
        <el-input v-model="row.currencyName" size="mini" placeholder="请输入内容"></el-input>
            </span>
            <span v-else>{{ row.currencyName }}</span>
        </template>
      </el-table-column>

            <el-table-column label="币别号" :default-sort="{prop:'currencyNumber'}" :sortable="true" :sort-method="sortName"  min-width="30%">
            <template slot-scope="{ row, $index }">
              <span v-if="drawRates.showEdit[$index]">
        <el-input v-model="row.currencyNumber" size="mini" placeholder="请输入内容"></el-input>
            </span>
            <span v-else>{{ row.currencyNumber }}</span>
        </template>
      </el-table-column>

     <el-table-column label="上传时间"  min-width="30%">
            <template slot-scope="scope">
            <span >{{ scope.row.uploadDate }}</span>
        </template>
      </el-table-column>


      <el-table-column label="操作" fixed="right" align="center"  min-width="40%">
              <template slot-scope="{ row, $index }">
               <el-button @click="handleUpdate($index,row)" type="success" size="mini" v-if="drawRates.showBtn[$index]">
               更新
               </el-button>
              <el-button @click="handleCancle($index,row)" type="button" size="mini" v-if="drawRates.showBtn[$index]">
               取消
               </el-button>
               <el-button @click="handleEdit($index,row)" type="primary" size="mini" v-if="!drawRates.showBtn[$index]">
               编辑
               </el-button>
               <el-button @click="handleCopy($index,row)" type="warning" size="mini" v-if="!drawRates.showBtn[$index]">
               复制
               </el-button>
               <el-button @click="handleDelete($index,row)" type="danger" size="mini" v-if="!drawRates.showBtn[$index]">
               删除
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
        <el-button size="small" @click="save" type="primary" >保存</el-button>
        <el-button size="small" @click="addData" type="primary" >新增</el-button>
        <el-button size="small" @click="addAll" type="primary" >批量复制</el-button>
        <el-button size="small" @click="deleteAll" type="primary" >批量删除</el-button>
      </el-row>
    </div>
  </div>
</template>

<style></style>

<script>
export default {
  name: "newCode",
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
      multipleSelection:[],
      queryInfo: {
        dateSelect: "",
        pageNumber: 1,
        pageSize: 10
      },
      total: 0,
      isLoading: false,
      showPagination: false,
      // 储存编辑数据
      drawRates:{
        showEdit:[],
        showBtn:[],
        editIndex:'',
        editData:'',
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
    sortName(){

    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection,"this.multipleSelection")
    },
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
    save(){},
    addData(){
      let obj={
          id:'',
          currencyNumber: "",
          currencyName: "",
          uploadDate:''
      }
      this.tableData.push(obj);
      this.handleEdit(this.tableData.length-1,obj)

    },
    handleEdit(index,row){
     this.handleUpdate(this.drawRates.editIndex,this.drawRates.editData)
     this.drawRates.editIndex = index;
     let obj =JSON.parse(JSON.stringify(row));
     this.drawRates.editData = obj
     this.drawRates.showEdit[index] =true;
     this.drawRates.showBtn[index] =true;
     this.$set(this.drawRates.showEdit,index,true)
     this.$set(this.drawRates.showBtn,index,true)
    },
     handleCancle(index,row){
     this.drawRates.showEdit[index] =false;
     this.drawRates.showBtn[index] =false;
     this.$set(this.drawRates.showEdit,index,false)
     this.$set(this.drawRates.showBtn,index,false)
     this.tableData[index] =this.drawRates.editData
     this.drawRates.editData = ''
     },
     handleUpdate(index,row){
     this.drawRates.showEdit[index] =false;
     this.drawRates.showBtn[index] =false;
     this.$set(this.drawRates.showEdit,index,false)
     this.$set(this.drawRates.showBtn,index,false)
     let obj1 =JSON.parse(JSON.stringify(row));
     this.drawRates.editData = obj1
     },

     handleDelete(index,row){
this.$confirm('此操作将永久删除该数据，是否继续？','提示',{
  confirmButtonText:'确定',
  cancelButtonText:'取消',
  type:'warning'
}).then(()=>{
  this.tableData.splice(index,1);
  this.$message({
    type:'success',
    message:'删除成功'
  })
}).catch(()=>{
    this.$message({
    type:'info',
    message:'已取消'
  })
})
     },

     handleCopy(index,row){
       this.tableData.splice(index,0,JSON.parse(JSON.stringify(row)))
     },

     addAll(){
       if(this.multipleSelection.length==0){
let list={
            id:'',
          currencyNumber: "",
          currencyName: "",
          uploadDate:''
}
this.tableData.push(list)
       }else{
         this.multipleSelection.forEach((val,index) => {
           this.tableData.splice(index,0,JSON.parse(JSON.stringify(val)))
         })
       }
     },

     deleteAll(){
      for(let i=0;i<this.tableData.length;i++){
        const element = this.tableData[i]
        element.id=i
      }
      if(this.multipleSelection.length==0 ) this.$message.error("请至少选择一项！")
      this.multipleSelection.forEach(element =>{
        this.tableData.forEach((e,i) =>{
          if(element.id == e.id)
          this.tableData.splice(i,1)
        })
      })

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
          id:'001',
          currencyNumber: "1",
          currencyName: "机构名称1",
          uploadDate:'2021-05-12'
        },
        {
          id:'002',
          currencyNumber: "2",
          currencyName: "机构名称2",
          uploadDate:'2021-05-12'
        }
      ],
        this.total = 2;
        this.isLoading = false;
        this.showPagination = this.tableData.length < 1 ? false:true;
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
