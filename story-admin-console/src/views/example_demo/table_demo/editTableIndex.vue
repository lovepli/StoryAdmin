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
              :value="item.value"/>
          </el-select>
        </el-form-item>

        <el-form-item label="日期搜索：">
          <el-date-picker
            v-model="limitdate"
            type="date"
            range-separator="至"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            @change="chageDate"/>
        </el-form-item>

        <el-form-item label="当前时间：">
          <el-date-picker
            v-model="dayTime"
            :disabled="false"
            type="datetime"
            format="yyyy-MM-dd HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期和时间"
          />
        </el-form-item>

        <el-form-item label="时间搜索范围：">
          <el-date-picker
            v-model="dateRange"
            :picker-options="pickerOptions"
            :default-time="['00:00:00','23:59:59']"
            type="datetimerange"
            range-separator="至"
            value-format="yyyy-MM-dd HH:mm:ss"
            start-placeholde="开始日期"
            end-placeholde="结束日期"
            clearable/>
        </el-form-item>

        <el-form-item label="机构号：" placeholder="请选择">
          <el-select v-model="netSiteNumber" filterable placeholder="请输入" clearable @change="selectNetSite">
            <el-option label="全部" value="all" />
            <el-option
              v-for="item in netList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <newCode
      v-if="showTable1"
      :key="num"
      :select-date="selectDate"
      :table-id = "tableId"
    />
    <erp
      v-if="showTable2"
      :key="num"
      :select-date="selectDate"
      :table-id = "tableId"
    />

  </div>
</template>

<style>

</style>

<script>
import newCode from '@/components/demo/editTable/newCode';
import erp from '@/components/demo/editTable/erp';

export default {

  name: 'EditTableIndex',
  components: { newCode, erp },
  // 定义本地过滤器
  filters: {

  },
  // data中存放的是el中需要的数据
  data() {
    return {
      selectDate: '', // 选择日期 yyyy-MM-dd
      limitdate: '', // 日期默认格式
      dayTime: '',// 当天日期和时间
      dateRange: '', // 日期搜索范围
      showTable: false,
      showTable1: false,
      showTable2: false,
      tableId: '', // 表格id
      tableName: '', // 表格名称
      netSiteNumber: '',
      // netList:[],
      netList: [
        {
          value: '1',
          label: '网点1'
        },
        {
          value: '2',
          label: '网点2'
        }
      ],
      tablelList: [
        {
          value: '1',
          label: '新一代表'
        },
        {
          value: '2',
          label: 'erp表'
        }
      ],
      num: 0,
      pickerOptions: {
        disabledDate(time) {
          var todayYear = (new Date()).getFullYear();
          var todayMonth = (new Date()).getMonth();
          var todayDay = (new Date()).getDay();
          var todayTime = (new Date(todayYear, todayMonth, todayDay, '23', '59', '59')).getTime();
          return time.getTime > todayTime;
        }
      }

    };
  },
  // 侦听属性
  watch: {

  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  created() {
    this.getNowTime();
    this.initDate();
  },
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
    // 日期转换
    chageDate(val) {
      this.selectDate = val;
    },
    selectChange(val) {
      this.tablelList.find((item) => {
        if (item.value == val) {
          return (this.tableName = item.label);
        }
      });
    },
    selectNetSite(val) {
      this.netSiteNumber = val === 'all' ? '全部' : val;
    },
    search() {
      this.showTableList();
    },
    showTableList() {
      this.num += 1;
      if (this.tableId === '1') {
        this.showTable1 = true
        this.showTable2 = false
      } else {
        this.showTable1 = false
        this.showTable2 = true
      }
    },
    // 获取系统当前时间
    getNowTime() {
    // 当前设定的日期时间
      const d = new Date()
      let year, month, day, hour, minute, second;
      // eslint-disable-next-line prefer-const
      [year, month, day, hour, minute, second] = [d.getFullYear(), d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()]
      const dateTime = new Date(year, month, day, hour, minute, second)
      this.dayTime = dateTime
    },
    // 时间范围默认设置为当天24小时制
    initDate() {
      var todayYear = (new Date()).getFullYear();
      var todayMonth = (new Date()).getMonth();
      var todayDay = (new Date()).getDay();
      var todayTimeStart = (new Date(todayYear, todayMonth, todayDay, '00', '00', '00')).getTime();
      var todayTimeEnd = (new Date(todayYear, todayMonth, todayDay, '23', '59', '59')).getTime();
      var todayTimeStart2 = this.formatDate(todayTimeStart);
      var todayTimeEnd2 = this.formatDate(todayTimeEnd);
      this.dateRange = [todayTimeStart2, todayTimeEnd2];
    },
    formatDate(time) {
      // eslint-disable-next-line no-redeclare
      var time = new Date(time);
      var y = time.getFullYear();
      var m = time.getMonth() + 1;
      var d = time.getDay();
      var h = time.getHours();
      var mm = time.getMinutes();
      var s = time.getSeconds();
      return y + '-' + this.rep(m) + '-' + this.rep(d) + ' ' + this.rep(h) + ':' + this.rep(mm) + ':' + this.rep(s);
    },
    rep(m) {
      return m < 10 ? '0' + m : m;
    }

  }
};
</script>
