<template>
  <div class="app-container">
    <!-- data-grid组件
    @dataRest="" 为监听事件，子组件通过this.$emit('dataRest')触发dataRest事件，父组件通过@dataRest监听事件，然后执行实际的方法onDataRest
    -->
    <!-- 访问子组件实例或子元素
      尽管存在 prop 和事件，有的时候你仍可能需要在 JavaScript 里直接访问一个子组件。为了达到这个目的，你可以通过 ref 这个 attribute 为子组件赋予一个 ID 引用。
      现在在你已经定义了这个ref的组件里，你可以使用this.$refs.dataList 来访问这个 <data-grid> 实例，以便不时之需。
    -->
    <!-- this.$refs可以获取元素和组件（以及组件中的元素）
      1、如果在HTML中定义了 ref="xx" 那么在Vue实例中通过this.$refs.xx就能获取到当前定义ref="xx"的DOM元素
      2、如果在组件引用上（比如<son ref="xx">）上使用了ref，那么在父组件Vue实例中通过this.$refs获取到的是整个子组件的对象，可以通过.的方式调用子组件data和methods中绑定数据
    -->
    <!--
      ref 被用来给元素或子组件注册引用信息。引用信息将会注册在父组件的 $refs 对象上。如果在普通的 DOM 元素上使用，引用指向的就是 DOM 元素；如果用在子组件上，引用就指向组件实例：
      <p ref="p">hello</p> `vm.$refs.p` will be the DOM node
      <child-component ref="child"></child-component>`vm.$refs.child` will be the child component instance
      当 v-for 用于元素或组件的时候，引用信息将是包含 DOM 节点或组件实例的数组
     -->

    <data-grid
      ref="dataList"
      url="/baseinfo/dict/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
      <!-- 一个组件中只要出现多个插槽，请始终为所有的插槽使用完整的基于 <template> 的语法： -->
      <!-- 所以下面的插槽都是 template slot="" 格式的-->
      <template slot="form">
        <el-form-item label="名称">
          <!-- 按键修饰符 @keyup.enter -->
          <!-- 将原生事件绑定到组件：你可能有很多次想要在一个组件的根元素上直接监听一个原生事件。这时，你可以使用 v-on 的 .native 修饰符 -->
          <!-- 注意！！！如果用了封装组件的话，比如element，这个时候使用按键修饰符需要加上.native -->
          <!-- 在监听键盘事件时，我们经常需要检查详细的按键。Vue 允许为 v-on 在监听键盘事件时添加按键修饰符： -->
          <!-- 只有在 `key` 是 `Enter` 时调用 `vm.handleFilter` -->
          <el-input
            v-model="listQuery.chnName"
            placeholder="名称"
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
      <!--body 引入具名插槽body-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px"/>
        <el-table-column align="center" prop="code" label="编号"/>
        <el-table-column align="center" prop="chnName" label="中文名称"/>
        <el-table-column align="center" prop="showOrder" label="排序"/>
        <el-table-column label="创建时间">
          <!-- 作用域插槽
          这里的 slot-scope 声明了被接收的 prop 对象会作为 slotProps 变量存在于 <template> 作用域中。-->
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="230"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <!-- @click事件处理，带参数 -->
            <el-button type="primary" size="mini" @click="modify(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>

    <!--el-dialog对话框  -->
    <el-dialog :visible.sync="modifyVisible">
      <!-- Tabs 标签页：分隔内容上有关联但属于不同类别的数据集合
      tab-click，tab被选中时触发，回调参数：被选中的标签tab实例 -->
      <el-tabs v-model="tabName" @tab-click="handleClick">
        <!-- label 选项卡标题 -->
        <el-tab-pane label="详情" name="baseInfo">
          <!-- form表单 -->
          <el-form
            ref="dictForm"
            :model="dictForm"
            :rules="rules"
            label-width="70px"
            label-position="right"
            style="width: 400px; margin-left:50px;"
          >
            <el-form-item label="编码" prop="code" size="medium">
              <!-- Vue 还提供了 v-model 指令，它能轻松实现表单输入和应用状态之间的双向绑定 -->
              <el-input v-model="dictForm.code" class="filter-item" placeholder="请输入账号"/>
            </el-form-item>
            <el-form-item label="中文名" prop="chnName">
              <el-input v-model="dictForm.chnName" placeholder="请输入..."/>
            </el-form-item>
            <el-form-item label="英文名" prop="engName">
              <el-input v-model="dictForm.engName" placeholder="请输入..."/>
            </el-form-item>
            <el-form-item label="排序" prop="showOrder">
              <el-input v-model="dictForm.showOrder" placeholder="请输入..."/>
            </el-form-item>
          </el-form>
          <div class="dialog-footer" style="overflow:hidden;text-align:right;margin-top:20px;">
            <el-button @click="modifyVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
          </div>
        </el-tab-pane>

        <!-- 枚举标签页 -->
        <el-tab-pane :disabled="!isShowDictMnt" label="枚举" name="dictMnt">
          <el-form>
            <div style="text-align:right;">
              <!--事件修饰符 @click.native原生点击事件 -->
              <!-- 1、给vue组件绑定事件时候，必须加上native ，不然不会生效（监听根元素的原生事件，使用 .native 修饰符）
                   2、等同于在子组件中：
                   子组件内部处理click事件然后向外发送click事件：$emit("click".fn) -->
              <el-button type="primary" size="small" @click.native="appendEnum">
                <i class="fa fa-plus-circle" aria-hidden="true"/>新增
              </el-button>
            </div>
            <el-table
              v-loading="dictMntList.listLoading"
              ref="multipleTable"
              :data="dictMntList.dictMnts"
              stripe
              tooltip-effect="dark"
              style="width: 100%;"
              size="small"
              @row-click="enumRowClick"
            >
              <el-table-column type="index" width="50"/>
              <el-table-column prop="code" label="编码" sortable="custom" show-overflow-tooltip>
                <template slot-scope="scope">
                  <!-- v-if 条件渲染 v-if 指令用于条件性地渲染一块内容。这块内容只会在指令的表达式返回 truthy 值的时候被渲染 -->
                  <span v-if="scope.row.modifyFlag">
                    <el-input
                      v-model="scope.row.code"
                      type="text"
                      placeholder="请输入..."
                      style="width:100%"
                    />
                  </span>
                  <span v-else>{{ scope.row.code }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="chnName" label="名称" sortable="custom" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span v-if="scope.row.modifyFlag">
                    <el-input
                      v-model="scope.row.chnName"
                      type="text"
                      placeholder="请输入..."
                      style="width:100%"
                    />
                  </span>
                  <span v-else>{{ scope.row.chnName }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="engName" label="英文" sortable="custom" show-overflow-tooltip>
                <template slot-scope="scope">
                  <span v-if="scope.row.modifyFlag">
                    <el-input
                      v-model="scope.row.engName"
                      type="text"
                      placeholder="请输入..."
                      style="width:100%"
                    />
                  </span>
                  <span v-else>{{ scope.row.engName }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="showOrder" label="排序" sortable="custom">
                <template slot-scope="scope">
                  <span v-if="scope.row.modifyFlag">
                    <el-input
                      v-model="scope.row.showOrder"
                      type="text"
                      placeholder="请输入..."
                      style="width:100%"
                    />
                  </span>
                  <span v-else>{{ scope.row.showOrder }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="操作"
                align="center"
                width="100"
                class-name="small-padding fixed-width"
              >
                <!-- 作用域插槽 -->
                <template slot-scope="scope">
                  <el-button
                    type="danger"
                    size="mini"
                    @click="removeEnum(scope.$index, scope.row)"
                  >移除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!-- el-pagination分页组件 -->
            <div style="overflow:hidden;">
              <el-pagination
                :current-page="dictMntList.pageNo"
                :total="dictMntList.total"
                :page-sizes="dictMntList.pageSizes"
                :page-size="dictMntList.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="dictMntHandleSizeChange"
                @current-change="dictMntListToCurPage"
              />
            </div>
            <div class="dialog-footer" style="overflow:hidden;text-align:right;margin-top:20px;">
              <el-button @click="searchEnumList">重 置</el-button>
              <el-button type="primary" @click="saveEnumList">保 存</el-button>
            </div>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import { getList, findById, save, drop, batchSave } from '@/api/baseinfo/dict';

// eslint-disable-next-line vue/no-parsing-error
import DataGrid from '@/components/DataGrid'; // 引入自定义的子组件data-grid
import { parseTime } from '@/utils';
// eslint-disable-next-line no-unused-vars
import { Message, MessageBox } from 'element-ui'; // 单独引入element-ui的Message消息提示, MessageBox弹框
import waves from '@/directive/waves'; // Waves directive

export default {
  name: 'Dict',
  components: { DataGrid }, // 注册子组件到父组件中
  directives: { waves },
  // 定义本地过滤器
  filters: {
    parseTime
  },
  // data中存放的是el中需要的数据
  data() {
    // eslint-disable-next-line no-unused-vars
    var checkShowOrder = (rule, value, callback) => {
      // 校验显示顺序
      if (value) {
        var regShowOrder = /^\+?[1-9][0-9]*$/;
        if (!regShowOrder.test(value)) {
          callback(new Error('请输入正整数'));
        } else {
          callback();
        }
      }
    };
    return {
      // 这几个属性好像没有用？
      // tableKey: 0,
      total: 0,
      // list: null,
      listLoading: true,
      listQuery: {
        pageNo: 1,
        // eslint-disable-next-line no-undef
        limit: PAGE_SIZE,
        id: null,
        code: null,
        parentCode: ''
      },

      modifyVisible: false,
      dictForm: {
        id: '',
        code: '',
        chnName: '',
        showOrder: ''
      },
      rules: {
        code: [{ required: true, message: '编码是必填项', trigger: 'blur' }]
      },

      tabName: '', // Tab选择标签名称
      isShowDictMnt: false, // 枚举是否disabled
      dictMntList: {// 列表查询条件封装对象
        // 定义本地过滤器
        filters: {
          parentCode: ''
        },
        dictMnts: [], // 列表绑定对象
        total: 0, // 总条数
        pageNo: 1, // 当前页
        // eslint-disable-next-line no-undef
        pageSize: PAGE_SIZE, // 分页大小
        sort: 'showOrder', // 排序字段
        order: 'asc', // 排序顺序
        // eslint-disable-next-line no-undef
        pageSizes: PAGE_SIZES, // 分页大小集合
        listLoading: false, // 列表加载标识,
        removedDictMnts: [] // 移除的对象
      },
      selectedEnumRow: {}
    };
  },
  methods: {
    onDataRest() {
      this.listQuery.pageNo = 1;
      // eslint-disable-next-line no-undef
      this.listQuery.limit = PAGE_SIZE;
      this.listQuery.id = null;
      this.listQuery.code = null;
      this.listQuery.parentCode = '';
    },
    handleFilter() {
      // 请求成功，需要重新刷新列表（不然新数据不能及时的更新到页面上）
      this.$refs.dataList.fetchData(); // 点击键盘 enter回车键，获取数据，在获取数据显示之前有一个数据加载过程的显示，这里是调用DdataGrid表格组件中的fetchData()方法
    },
    // 编辑
    modify(dict) {
      // 显示编辑弹框
      this.modifyVisible = true;
      this.tabName = 'baseInfo';
      this.dictMntList.filters.parentCode = '';
      this.dictMntList.dictMnts.splice(0, this.dictMntList.dictMnts.length);

      if (dict) {
        const params = {
          id: dict.id
        };
        findById(params).then(res => {
          if (res.result) {
            this.dictForm = res.data;
            this.isShowDictMnt = true;
          } else {
            this.$message.error(res.code);
          }
        });
      } else {
        this.dictForm.id = null;
        this.dictForm.code = null;
        this.dictForm.chnName = null;
        this.dictForm.engName = null;
        this.dictForm.parentCode = null;
        this.dictForm.showOrder = null;
      }
    },
    submitForm() {
      // 保存
      this.$refs['dictForm'].validate(valid => {
        if (valid) {
          const para = this.dictForm;
          save(para)
            .then(res => {
              this.modifyVisible = false;
              this.$refs.dataList.fetchData();
            })
            // eslint-disable-next-line handle-callback-err
            .catch(error => {});
        } else {
          return false;
        }
      });
    },
    dropRow(row) {
      // element-ui的消息弹框，参考官方文档：https://element.eleme.cn/#/zh-CN/component/message-box
      // 调用$confirm方法即可打开消息提示，它模拟了系统的 confirm
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const params = {};
          if (row.id) {
            params.id = row.id;
            drop(params).then(res => {
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
    },
    handleClick(tab, event) {
      // eslint-disable-next-line eqeqeq
      if (this.tabName == 'dictMnt') {
        this.dictMntList.filters.parentCode = this.dictForm.code;
        this.searchEnumList();
      }
    },
    // 获取枚举列表
    searchEnumList() {
      this.dictMntList.removedDictMnts.splice(
        0,
        this.dictMntList.removedDictMnts.length
      );

      const para = {
        pageNo: this.dictMntList.pageNo,
        pageSize: this.dictMntList.pageSize,
        sort: this.dictMntList.sort,
        order: this.dictMntList.order,
        parentCode: this.dictMntList.filters.parentCode
      };
      this.dictMntList.listLoading = true;
      getList(para).then(res => {
        this.dictMntList.listLoading = false;
        // eslint-disable-next-line
        if (res.code == RESULT_SUCCESS_CODE) {
          res.data.records.forEach(element => {
            element.modifyFlag = false;
          });
          this.dictMntList.total = res.data.total;
          this.dictMntList.dictMnts = res.data.records;
        }
      });
    },
    dictMntListToCurPage(val) {
      // 跳转到指定页
      this.dictMntList.pageNo = val;
      this.searchEnumList();
    },
    dictMntHandleSizeChange(val) {
      // 分页大小改变监控方法
      this.dictMntList.pageSize = val;
      this.searchEnumList();
    },
    enumRowClick(row, event, column) {
      // eslint-disable-next-line eqeqeq
      if (this.selectedEnumRow && row.id != this.selectedEnumRow.id) {
        this.selectedEnumRow.modifyFlag = false;
        this.selectedEnumRow = null;
      }
      row.modifyFlag = true;
      this.selectedEnumRow = row;
    },

    // 新增枚举行
    appendEnum() {
      // 获取临时枚举行数据
      const tempEnumRow = this.createEnum();
      // 放进枚举列表
      this.dictMntList.dictMnts.push(tempEnumRow);

      this.selectedEnumRow.modifyFlag = false;
      this.selectedEnumRow = tempEnumRow;
    },
    createEnum(item) {
      // 行索引增加1
      // eslint-disable-next-line no-unused-vars
      var index = this.dictMntList.dictMnts.length + 1;
      // 行数据对象
      var enumRecord = {
        id: '',
        parentCode: this.dictForm.code,
        chnName: '',
        engName: '',
        showOrder: '',
        modifyFlag: true
      };
      return enumRecord;
    },
    // 移除行数据
    removeEnum(index, row) {
      Array.prototype.push.apply(
        this.dictMntList.removedDictMnts,
        this.dictMntList.dictMnts.splice(index, 1)
      );
    },
    // 保存
    saveEnumList() {
      if (this.selectedEnumRow != null) {
        this.selectedEnumRow.modifyFlag = false;
        this.selectedEnumRow = null;
      }
      var params = [];
      var that = this;
      this.dictMntList.dictMnts.forEach(element => {
        var param = {
          id: element.id,
          parentCode: element.parentCode,
          code: element.code,
          chnName: element.chnName,
          engName: element.engName,
          showOrder: element.showOrder
        };
        params.push(param);
      });
      this.dictMntList.removedDictMnts.forEach(element => {
        if (element.id) {
          var param = {
            id: element.id,
            parentCode: element.parentCode,
            code: element.code,
            chnName: element.chnName,
            engName: element.engName,
            showOrder: element.showOrder,
            ynFlag: '0'
          };
          params.push(param);
        }
      });
      batchSave(params).then(res => {
        that.searchEnumList();
        // element-ui的消息弹框,因为这里是单独引入Message，所以调用方式不是this.$message()打开消息弹框
        // Message({
        //   message: '保存成功',
        //   type: 'success',
        //   duration: 5 * 1000
        // });

        // 使用全局的Message
        this.$message({
          message: '保存成功',
          type: 'success',
          duration: 5 * 1000
        });
      });
    }
  }
};
</script>
