<template>
  <div class="app-container">
    <el-form ref="tableForm" :model="tableForm">
      <el-table
        v-loading="isLoading"
        :data="tableForm.tableData"
        :row-key="getRowKey"
        :header-cell-style="{
          background: '#eef1f6',
          color: '#606266',
          fontweight: 600
        }"
        border
        stripe
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="40px"/>
        <!--     :reserve-selection="true" -->

        <el-table-column label="序号" fixed width="70px">
          <template slot-scope="scope">
            <span>{{
              (queryInfo.pageNumber - 1) * queryInfo.pageSize + scope.$index + 1
            }}</span>
          </template>
        </el-table-column>

        <el-table-column
          label="机构号"
          prop="orgNumber"
          fixed
          width="100"
        />

        <el-table-column
          label="机构名称"
          prop="orgName"
          show-overflow-tooltip
          width="200"
        />

        <!-- 使用template块，可以在字段下面放一个输入框，也可以是下拉框，日期选择框 -->
        <el-table-column label="归口管理部门" width="100">
          <template slot-scope="{ row, $index }">
            <span v-if="drawRates.showEdit[$index] || row.isSet">
              <el-tooltip
                :disabled="drawRates.showToolTip[$index]"
                :content="row.centManageDep"
                effect="dark"
                placement="top"
              >
                <el-input
                  v-model="row.centManageDep"
                  size="mini"
                  placeholder="请输入内容"
                  style="width:160px"
                  @input="showToolTip($index, row.centManageDep)"
                />
              </el-tooltip>
            </span>
            <span v-else>{{ row.centManageDep }}</span>
          </template>
        </el-table-column>

        <el-table-column label="计提日" width="200">
          <template slot-scope="{ row, $index }">
            <el-form-item
              :ref="`accDate.${row.id}`"
              :prop="`tableData.${$index}.accDate`"
              :rules="[
                {
                  required: true,
                  validator: validateAccDate,
                  trigger: ['change', 'blur'],
                  id: row.id
                }
              ]"
            >
              <el-date-picker
                v-model="row.accDate"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
                style="width:220px"
                @change="calclossDayChange($index, row)"
              />
            </el-form-item>
          </template>
        </el-table-column>

        <el-table-column label="挂账日期" width="200">
          <template slot-scope="{ row, $index }">
            <el-form-item
              :ref="`accountDate.${row.id}`"
              :prop="`tableData.${$index}.accountDate`"
              :rules="[
                {
                  required: true,
                  validator: validateAccountDate,
                  trigger: ['change', 'blur'],
                  id: row.id
                }
              ]"
            >
              <el-date-picker
                v-model="row.accountDate"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
                style="width:220px"
                @change="calclossDayChange($index, row)"
              />
            </el-form-item>
          </template>
        </el-table-column>

        <el-table-column label="挂账天数" width="100">
          <template slot-scope="scope">
            <el-input
              v-if="scope.row.isSet"
              v-model="scope.row.accountDays"
              type="text"
            />
            <span v-else>{{ scope.row.accountDays }}</span>
          </template>
        </el-table-column>

        <el-table-column label="备注" width="100">
          <template slot-scope="scope">
            <el-input
              v-if="scope.row.isSet"
              v-model="scope.row.remarks"
              :autosize="{ minRows: 2, maxRows: 4 }"
              type="textarea"
            />
            <span v-else>{{ scope.row.remarks }}</span>
          </template>
        </el-table-column>

        <!-- 流程状态、结束备注,多个状态使用前端枚举来区别，也可以使用filter过滤器，也可以使用 v-if来写，这些都不好维护，而且代码量多 -->
        <el-table-column label="流程状态" prop="flowStatus" width=150 sortable>
          <template slot-scope="scope">
            <el-tag type="success">{{enumSet.wechatFlowStatusEnum.getLabelByValue(scope.row.flowStatus)}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          fixed="right"
          align="center"
          min-width="10%"
        >
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="mini"
              style="margin-left:0px;"
              @click="
                handleDelete(scope.$index, tableForm.tableData, scope.row)
              "
            >
              删除
            </el-button>
            <el-button
              type="info"
              size="mini"
              style="margin-left:0px;"
              @click="handleDetail(scope.$index, tableForm.tableData)"
            >
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-form>

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
        <el-button size="small" @click="goBack">返回</el-button>
        <el-button size="small" @click="editTable">编辑</el-button>
        <el-button size="small" @click="bulkUpdate">批量更新</el-button>
        <el-button
          type="primary"
          size="small"
          @click="save('tableForm')"
        >提交</el-button
        >
      </el-row>
    </div>

    <el-dialog :visible.sync="isShowDailog" title="批量更新">
      <el-form :model="submitForm">
        <el-form-item label="资产减值比率" label-width="120">
          <el-input
            v-model="submitForm.lossRatio"
            auto-complete="off"
            style="width:220px"
          />
        </el-form-item>
        <div slot="footer" class="dialog-footer">
          <el-button @click="isShowDailog = false">取消</el-button>
          <el-button type="primary" @click="changeLossRatio">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<style></style>

<script>
export default {
  name: 'Erp',
  // 定义本地过滤器
  filters: {},
  components: {},
  // 接受父组件传递的参数
  props: {
    // eslint-disable-next-line vue/require-default-prop
    selectDate: String,
    // eslint-disable-next-line vue/require-default-prop
    tableId: String
  },
  // data中存放的是el中需要的数据
  data() {
    return {
      // 相关枚举定义
      enumSet:{
        wechatFlowStatusEnum:this.$enums.wechatFlowStatusEnum
      },
      multipleSelection: [],
      tableForm: {
        tableData: []
      },
      canUpload: false, // 是否可以上传附件
      fileList: [[]],
      curRowIndex: null,
      queryInfo: {
        dateSelect: '',
        pageNumber: 1,
        pageSize: 10
      },
      total: 0,
      isLoading: false,
      showPagination: false,
      isShowDailog: false, // 是否显示批量更新弹框
      submitForm: {
        lossRatio: ''
      },
      // 储存编辑数据
      drawRates: {
        showEdit: [],
        showBtn: [],
        showTooltip: [],
        editIndex: '',
        editData: ''
      },
      lossRatioList: []
    };
  },
  // 侦听属性
  watch: {},
  mounted() {
    this.init();
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  created() {
    // this.tableForm.tableData = [];
  },
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
    init() {
      this.getTableList(); // 页面未显示之前进行数据渲染
      setTimeout(() => {
        // 异步读取函数setTimeout()
        this.editTable();
      }, 100);
    },
    // 删除
    handleDelete(index, rows, row) {
      console.log('删除数据！');
      console.log(row.id);
      console.log(row);
      console.log(rows, index);
    },
    handleDetail(index, rows) {},

    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection, 'this.multipleSelection');
    },
    goBack() {
      // this.$router.push({path:""})
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
      //     this.tableForm.tableData = res.extend.pageData.records
      //     this.total = res.extend.pageData.total
      //     this.showPagination = this.tableForm.tableData.length < 1 ? false:true;
      //    this.editTable(); // 查询出结果就是编辑页面
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
      this.tableForm.tableData = [
        {
          id: '001',
          orgNumber: '1',
          orgName: '机构名称1',
          accountDate: '2021-04-12',
          accDate: '2021-05-12',
          centManageDep: '归口管理部门1',
          remarks: '备注1'
        },
        {
          id: '002',
          orgNumber: '2',
          orgName: '机构名称2',
          accountDate: '2021-04-12',
          accDate: '2021-05-12',
          centManageDep: '归口管理部门2',
          remarks: '备注2'
        }
      ],
      this.total = 2;
      this.isLoading = false;
      this.showPagination = !(this.tableForm.tableData.length < 1);
      // this.editTable();
    },
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getTableList();
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNumber = newPage;
      this.getTableList();
    },
    // 校验规则
    validateAccDate(rule, value, callback) {
      if (!value) {
        callback(new Error('请输入计提日！'));
      } else {
        callback();
      }
    },
    validateAccountDate(rule, value, callback) {
      if (!value) {
        callback(new Error('请输入挂账日期！'));
      } else {
        callback();
      }
    },
    save(formName) {
      console.log(this.multipleSelection, 'this.multipleSelection');
      // 校验数组非空
      // if(!this.multipleSelection){
      //    this.$message.error("请先勾选需要保存的数据！")
      //    return;
      // } else{

      if (this.multipleSelection) {
        this.multipleSelection.map(item => {
          return item.id;
        });
      }

      const params = this.multipleSelection
        ? this.multipleSelection
        : this.tableForm.tableData;
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 给后端传递json数据,调用后台接口
          // await dateNewCodeSave(params)
          // .then(res => {
          //   console.log(res);
          //   this.isLoading = false;
          //   if(res.errCode===0){
          //      this.$message({
          //         message:"批量保存成功！",
          //         type:"success"
          //        }
          //      );
          // this.getTableList();
          //   }else{
          //      this.$message({
          //         message:"批量保存失败",
          //         type:"error"
          //        }
          //      );
          //   }
          //   })
          //   .catch(()=>{
          //     this.isLoading = false;
          //     console.log(res);
          //   })
        }
      });

      // }
    },
    // 编辑全部
    editTable() {
      this.tableForm.tableData.map((i, index) => {
        i.isSet = true;
        // i.disabledTooltip = true;
        this.$set(this.tableForm.tableData, index, i);
        this.$set(this.drawRates.showTooltip, index, true); // 归口管理部门
      });
    },
    // 显示提示框
    showTooltip(index, val) {
      if (val && val.length > 12) {
        this.drawRates.showTooltip[index] = false;
        this.$set(this.drawRates.showTooltip, index, false);
      } else {
        this.drawRates.showTooltip[index] = true;
        this.$set(this.drawRates.showTooltip, index, true);
      }
    },
    getRowKey(key) {
      return key.id;
    },
    bulkUpdate() {
      this.isShowDailog = true;
    },

    // 挂账天数计算，计提日减去挂账日期,结果调用接口查询返回的减值计提分类和计提标准
    async calclossDayChange(index, val) {
      if (val.accountDate && val.accDate) {
        val.accountDays = this.getDaysBetween(val);
        const params = {
          subjectCode: val.subjectCode,
          currency: val.currency
        };
        // 给后端传递json数据,调用后台接口
        // await findNewGenAccTypeAndStandard(params)
        // .then(res => {
        //   console.log(res);
        //   if(res.errCode===0){
        //     val.accType = res.extend.data.accTye
        //     val.accStandard= res.extend.data.accStandard
        //   }else{
        //      this.$message({
        //         message:"失败",
        //         type:"error"
        //        }
        //      );
        //   }
        //   })
        //   .catch(()=>{
        //     console.log(res);
        //   })

        this.drawRates.editIndex = index;
        const obj = JSON.parse(JSON.stringify(val));
        this.drawRates.editData = obj;
        this.handleUpdate(this.drawRates.editIndex, this.drawRates.editData);
      }
    },
    getDaysBetween(val) {
      var accDate = Date.parse(val.accDate);
      var accountDate = Date.parse(val.accountDate);
      var days = (accDate - accountDate) / (1 * 24 * 60 * 60 * 1000);
      return days;
    },

    handleUpdate(index, row) {
      // this.drawRates.showEdit[index] = false
      // this.drawRates.showBtn[index] = false
      // this.$set(this.drawRates.showEdit,index,false);
      // this.$set(this.drawRates.showBtn,index,false);
      const obj1 = JSON.parse(JSON.stringify(row));
      this.drawRates.editData = obj1;
    },

    changeLossRatio() {}
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
