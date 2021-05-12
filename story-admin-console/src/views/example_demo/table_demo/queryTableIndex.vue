<template>
  <div class="app-container">
            <!--搜索条件-->
        <el-row>
          <el-form :inline="true" size="small">

              <el-form-item label="数据表名称：" placeholder="请选择">
                <el-select v-model="tableId" filterable placeholder="请输入" clearable @change="selectChange"> 
                  <el-option
                    v-for="item in tablelList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>

        <el-form-item label="时间搜索范围：">
          <el-date-picker
            v-model="limitdate"
            type="date"
            range-separator="至"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            @change="chageDate"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </el-form-item>
          </el-form>
        </el-row>
        
        <newCode
        :key="num"
        :selectDate="selectDate"
        :tableId = "tableId"
        v-if="showTable1"
        >
        </newCode>
        <erp
        :key="num"
        :selectDate="selectDate"
        :tableId = "tableId"
        v-if="showTable1"
        ></erp>




  </div>
</template>

<style>

</style>

<script>
import newCode  from '@/components/demo/queryTable/newCode';
import erp  from '@/components/demo/queryTable/erp';

export default {

  name: 'queryTableIndex',
  components: { newCode, erp },
  // 定义本地过滤器
  filters: {

  },
  // data中存放的是el中需要的数据
  data() {
    return {
      selectDate:'',//选择日期 yyyy-MM-dd
      limitdate:'',//日期默认格式
      showTable:false,
      showTable1:false,
      showTable2:false,
      tableId:'', // 表格id
      tableName:'',// 表格名称
      tablelList:[
        {
          value:'1',
          label:'新一代表'
        },
        {
          value:'2',
          label:'erp表'
        }
      ],
      num:0

    };
  },
  // 侦听属性
  watch: {


  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  created() {},
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
    // 日期转换
    chageDate(val){
     this.selectDate=val;
    },
    selectChange(val){
      
      this.tablelList.find((item) =>{
        if(item.value == val){
          return (this.tableName = item.label);
        }
      });
    },
    search(){
      this.showTableList();
    },
    showTableList(){
      this.num +=1;
      if(this.tableId === '1'){
        this.showTable1 = true
        this.showTable2 = false
      }else{
        this.showTable1 = false
        this.showTable2 = true
      }
    }

  }
};
</script>
