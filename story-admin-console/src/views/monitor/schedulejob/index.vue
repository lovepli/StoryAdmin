<template>
  <div class="app-container">
    <!-- 子组件 data-grid列表组件 -->
    <!-- url和data-name的属性值会传递到data-grid组件，这两个属性是data-grid自定义的属性-->
    <!-- data-grid组件
    @dataRest="" 为监听事件，子组件通过this.$emit('dataRest')触发dataRest事件，父组件通过@dataRest监听事件，然后执行实际的方法onDataRest
    -->
    <!-- 访问子组件实例或子元素
      尽管存在 prop 和事件，有的时候你仍可能需要在 JavaScript 里直接访问一个子组件。为了达到这个目的，你可以通过 ref 这个属性为子组件赋予一个ID引用。
      现在在你已经定义了这个ref的组件里，你可以使用this.$refs.dataList 来访问这个 <data-grid> 实例，以便不时之需。
    -->
    <!-- this.$refs可以获取元素和组件（以及组件中的元素）
      1、如果在HTML中定义了 ref="xx" 那么在Vue实例中通过this.$refs.xx就能获取到当前定义ref="xx"的DOM元素
      2、如果在组件引用上（比如<son ref="xx">）上使用了ref，那么在父组件Vue实例中通过this.$refs获取到的是整个子组件的对象，可以通过.的方式调用子组件data和methods中绑定数据
    -->
    <data-grid
      ref="dataList"
      url="/monitor/schedulejob/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
      <!-- 具名插槽form
      按键修饰符 @keyup.enter
      -->
      <template slot="form">
        <el-form-item label="任务编码">
          <el-input
            v-model="listQuery.jobId"
            placeholder="任务编码"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
        <el-form-item label="任务名称">
          <el-input
            v-model="listQuery.jobName"
            placeholder="任务名称"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!--extendOperation-->
      <template slot="extendOperation">
        <el-button
          class="filter-item"
          style="margin-left: 10px;"
          type="primary"
          size="small"
          icon="el-icon-edit"
          @click="modify()"
        >新增</el-button>
      </template>
      <!--body列表体-->
      <template slot="body">
        <el-table-column align="center" prop="jobId" label="任务编码"/>
        <el-table-column align="center" prop="jobName" label="任务名称"/>
        <el-table-column align="center" prop="startJob" label="启动状态">
          <!-- 作用域插槽slot-scope="scope" -->
          <template slot-scope="scope">
            <!-- tag标签过滤statusTagFilter，主要是用来区别标签显示的北京样式，启动状态属性值过滤statusFilter  -->
            <!-- 过滤器可以在v-bind或{{}}里使用，通过管道符(|)来连接,这里刚好是这两种用法的实现！！-->
            <el-tag :type="scope.row.startJob | statusTagFilter">{{ scope.row.startJob | statusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="startTime" label="启动时间">
          <template slot-scope="scope">
            <!-- parseTime 格式化时间的方法，在这里作为过滤器使用  -->
            <span>{{ scope.row.startTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="fireTime" label="触发时间">
          <template slot-scope="scope">
            <span>{{ scope.row.fireTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="lastFireTime" label="上次执行时间">
          <template slot-scope="scope">
            <span>{{ scope.row.lastFireTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="nextFireTime" label="下次执行时间">
          <template slot-scope="scope">
            <span>{{ scope.row.nextFireTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>

        <el-table-column label="更新时间">
          <template slot-scope="scope">
            <span>{{ scope.row.modifiedTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="160"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="modify(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>

    <!-- 详情对话框el-dialog -->
    <el-dialog :visible.sync="modifyVisible" title="任务信息">
      <!-- el-form表单
      :model="scheduleForm" 数据绑定对象
      :rules="rules" 表单验证规则
       -->
      <el-form
        ref="scheduleForm"
        :model="scheduleForm"
        :rules="rules"
        label-width="100px"
        label-position="right"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="任务编号" prop="jobId" size="medium">
          <!-- 下拉框 -->
          <el-select
            v-model="scheduleForm.jobId"
            placeholder="请选择..."
            style="width:300px;"
            @change="onScheduleChanged"
          >
            <!-- 遍历任务编号选项   :label="item.jobName" jobName是任务名称，jobId是任务编号-->
            <el-option
              v-for="item in jobIdOptions"
              :key="item.jobId"
              :label="item.jobId"
              :value="item.jobId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="任务名称" prop="jobName">
          <el-input v-model="scheduleForm.jobName" placeholder="请输入任务名称"/>
        </el-form-item>
        <el-form-item label="Cron表达式" prop="cron">
          <el-input v-model="scheduleForm.cron" type="cron" placeholder="请输入Cron表达式"/>
        </el-form-item>
        <el-form-item label="是否启动" prop="startJob">
          <el-switch v-model="scheduleForm.startJob" active-text="是" inactive-text="否"/>
        </el-form-item>
        <el-form-item label="启动时间" prop="startTime">
          <el-date-picker v-model="scheduleForm.startTime" type="datetime" placeholder="选择启动时间"/>
        </el-form-item>
        <el-form-item label="任务描述" prop="jobDesc">
          <el-input :rows="2" v-model="scheduleForm.jobDesc" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="modifyVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  findById,
  save,
  drop,
  getJobCombo
} from '@/api/monitor/schedulejob';

import DataGrid from '@/components/DataGrid'; // 引入子组件
import { parseTime } from '@/utils'; // 引入parseTime日期格式化的方法，可以当作一个过滤时间的方法，格式化时间

// 状态选项
const statusOptions = [
  { key: '正常', value: true },
  { key: '禁用', value: false }
];

// reduce() 方法对数组中的每个元素执行一个由您提供的reducer函数(升序执行)，将其结果汇总为单个返回值
// reducer 函数接收4个参数:
// Accumulator (acc) (累计器)
// Current Value (cur) (当前值)
// Current Index (idx) (当前索引)
// Source Array (src) (源数组)
const statusTypeKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.value] = cur.key;
  return acc;
}, {});

export default {
  name: 'ScheduleJob',
  components: { DataGrid }, // 注册子组件
  // 自定义局部过滤器
  filters: {
    // 过滤器写法
    // statusFilter:function(status){
    //   return statusTypeKeyValue[status + ''];
    // }

    // ES6的写法
    // 状态过滤器
    statusFilter(status) { // 这个形式参数status所传的的实际参数为startJob,他的默认值为0
      return statusTypeKeyValue[status + ''];
    },
    // 状态标签过滤器
    statusTagFilter(status) { // 这个形式参数status所传的的实际参数为startJob,他的默认值为0
      // 由type属性来选择tag标签的类型，也可以通过color属性来自定义背景色。这里我们给tag的颜色为success和danger
      const statusMap = {
        true: 'success',
        false: 'danger'
      };
      return statusMap[status];
    },
    // 时间过滤器 日期格式化
    parseTime
  },
  data() {
    return {
      // tableKey: 0,
      // total: 0,
      // list: null,
      // listLoading: true,
      listQuery: { // 查询参数对象
        pageNo: 1,
        limit: 10,
        jobId: null,
        jobName: null
      },

      modifyVisible: false, // 是否显示弹框
      statusOptions: statusOptions, // 状态选项
      jobIdOptions: [], // 任务编号选项
      scheduleForm: { // 表单对象
        id: null, // id主键
        jobId: '', // 任务编号
        jobName: '', // 任务名称
        cron: '', // cron表达式
        jobClass: '', // 定时任务类名
        startJob: 0, // 启动状态 默认为0
        startTime: '', // 开始时间
        jobDesc: '' // 任务描述
      },
      rules: { // form表单验证规则rules
        jobId: [
          { required: true, message: '任务编码是必填项', trigger: 'blur' }
        ],
        jobName: [
          { required: true, message: '任务名称是必填项', trigger: 'blur' }
        ],
        cron: [
          { required: true, message: 'Cron表达式是必填项', trigger: 'blur' }
        ]
      }
    };
  },
  // 侦听属性
  // 数据响应式变化：虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。
  // 这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。
  // 当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的
  watch: {// 如果 `modifyVisible`属性值发生改变，这个函数里的内容就会执行
    modifyVisible(val) { // 这里的意思是信息弹框显示，清空信息弹框的内容
      if (!val) {
        this.scheduleForm.id = null;
        this.scheduleForm.jobId = null;
        this.scheduleForm.jobName = null;
        this.scheduleForm.cron = null;
        this.scheduleForm.jobClass = null;
        this.scheduleForm.startJob = 0;
        this.scheduleForm.startTime = null;
        this.scheduleForm.jobDesc = null;
      }
    }
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  // 此函数中，data和methods都已经初始化好了，如果需要调用methods中的方法或操作data中的值最早就在created函数中操作。
  created() {
    // 获取下拉选项接口
    getJobCombo().then(response => {
      // 任务编号选项，数据库查询的返回值包括任务编号jobId，任务名称jobName，任务类名jobClass
      this.jobIdOptions = response.data;
    });
  },
  methods: {
    // 重置搜索框内容按钮
    onDataRest() {
      this.listQuery = {};
    },
    // 点击键盘Enter，触发进行条件搜索
    handleFilter() {
      // 请求成功，需要重新刷新列表（不然新数据不能及时的更新到页面上）
      // 父组件通过使用this.$refs.dataList 来访问这个子组件 <data-grid> 实例，调用该实例的fetchData()方法，刷新列表
      this.$refs.dataList.fetchData();
    },
    // 新增/编辑按钮
    modify(obj) {
      // 显示信息编辑弹框
      this.modifyVisible = true;
      if (obj) { // 如果点击modify按钮，传过来的参数user不为空，说明是编辑操作，根据id查询这出条记录信息，然后渲染到信息弹框页面
        const params = {
          id: obj.id
        };
        findById(params).then(res => {
          if (res.result) {
            this.scheduleForm = res.data;
          } else {
            this.$message.error(res.code);
          }
        });
      }
    },
    // 任务编号下拉框按钮
    onScheduleChanged(jobId) {
      this.jobIdOptions.some(element => {
        // eslint-disable-next-line eqeqeq
        if (element.jobId == jobId) { // 比较表单选择的任务编号是否与数据库查询出来的相同
          this.scheduleForm.jobClass = element.jobClass;// 根据下拉框选择的jobId，匹配到对应的jobClass,然后赋值给表单对象
          this.scheduleForm.jobName = element.jobName; // 根据下拉框选择的jobId，自动匹配填充jobName
          return true;
        }
      });
    },
    // 表单提交(添加/修改保存)
    submitForm() {
      // this.$refs 获取scheduleForm表单组件,验证表单组件的rules规则
      this.$refs['scheduleForm'].validate(valid => {
        if (valid) {
          const para = this.scheduleForm;
          // 调用接口
          save(para).then(res => {
            // 关闭弹框
            this.modifyVisible = false;
            // 刷新列表
            this.$refs.dataList.fetchData();
          });
        } else {
          return false;
        }
      });
    },
    // 删除按钮
    dropRow(row) {
      // element-ui的消息弹框，调用$confirm方法即可打开消息提示，它模拟了系统的 confirm
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 删除逻辑
          const params = {};
          if (row.id) {
            params.id = row.id;
            drop(params).then(res => {
              // 刷新列表
              this.$refs.dataList.fetchData();
            });
          }
        })
        .catch(() => {
          // 删除失败的提示信息
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    }
  }
};
</script>
