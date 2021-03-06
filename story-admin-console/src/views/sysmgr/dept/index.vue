<template>
  <div class="app-container">
    <el-container>
      <el-header>
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" style="margin-bottom:30px;" />
      </el-header>

      <el-container>
        <el-aside
          v-loading="treeloading"
          width="300px"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.1)"
        >
          <!-- element-ui的Tree树形控件 -->
          <el-tree
            ref="tree2"
            :data="dataNodes"
            :expand-on-click-node="false"
            :default-expanded-keys="[0,1]"
            :filter-node-method="filterNode"
            class="filter-tree"
            node-key="id"
            @node-click="selectNode"
          >
            <span slot-scope="{ node, data }" class="custom-tree-node">
              <span>{{ node.label }}</span>
              <span>
                <el-button
                  v-show="data.id==0"
                  type="text"
                  size="mini"
                  @click="($event) => loadData(data,$event)"
                >
                  <i class="el-icon-refresh" />
                </el-button>
                <el-button type="text" size="mini" @click="($event) => append(data,$event)">
                  <i class="el-icon-plus" />
                </el-button>
                <el-button
                  v-if="data.id!=0"
                  type="text"
                  size="mini"
                  @click="($event) => remove(node, data,$event)"
                >
                  <i class="el-icon-close" />
                </el-button>
              </span>
            </span>
          </el-tree>
        </el-aside>

        <el-main>
          <!-- transition过度动效 如果你想让每个路由组件有各自的过渡效果，可以在各路由组件内使用 <transition> 并设置不同的 name-->
          <transition name="el-fade-in">
            <el-form
              v-show="modifyVisible"
              ref="deptForm"
              :rules="rules"
              :model="formData"
              label-width="100px"
              style="width: 400px;"
            >
              <el-form-item label="部门编码" prop="code"> <!--这里没有定义code编码，用id代替  -->
                <el-input v-model="formData.code" placeholder="请输入..." clearable/>
              </el-form-item>
              <el-form-item label="部门名称" prop="name">
                <el-input v-model="formData.name" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="排序" prop="showOrder">
                <!-- 如果想自动将用户的输入值转为数值类型，可以给 v-model 添加 number 修饰符 -->
                <!-- 这通常很有用，因为即使在 type="number" 时，HTML 输入元素的值也总会返回字符串。如果这个值无法被 parseFloat() 解析，则会返回原始的值 -->
                <el-input v-model.number="formData.showOrder" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="描述" prop="deptDesc">
                <!-- 文本域：用于输入多行文本信息，通过将 type 属性的值指定为 textarea
                文本域高度可通过 rows 属性控制，autosize表示可自适应文本高度的文本域，通过设置 autosize 属性可以使得文本域的高度能够根据文本内容自动进行调整，并且 autosize 还可以设定为一个对象，指定最小行数和最大行数。
                对于类型为 text 或 textarea 的输入框，在使用 maxlength 属性限制最大输入长度的同时，可通过设置 show-word-limit 属性来展示字数统计。
                 -->
                <el-input
                  v-model="formData.deptDesc"
                  :autosize="{ minRows: 2, maxRows: 4}"
                  type="textarea"
                  placeholder="请输入..."
                  maxlength="30"
                  show-word-limit
                />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" size="small" @click="submitForm">保存</el-button>
                <el-button v-show="nodeDeleteVisible" type="danger" size="small" @click="drop">删除</el-button>
              </el-form-item>
            </el-form>
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { getList, save, drop } from '@/api/sysmgr/dept';
// eslint-disable-next-line no-unused-vars
import { Message, MessageBox } from 'element-ui'; // 单独引入element-ui的Message消息提示, MessageBox弹框
export default {
  data() {
    return {
      filterText: '', // 过滤条件
      dataNodes: [], // tree-data
      defaultProps: {
        children: 'children',
        label: 'label'
      },

      treeloading: false,
      formData: {
        // 明细表单
        code: null,
        name: null,
        pid: null,
        showOrder: null,
        deptDesc: null
      },
      modifyVisible: false, // 表单是否显示
      nodeDeleteVisible: false,
      rules: {
        name: [{ required: true, message: '名称是必填项', trigger: 'blur' }]
      },

      currentNodeData: null, // 缓存当前操作的节点
      currentNodeId: null,
      tempNodePrefix: 'temp', // 新增节点临时id前缀
      tempNodeIndex: 1 // 新增节点临时id序号
    };
  },
  // 侦听属性
  watch: {
    filterText(val) {
    // filter() 替换数组的方法，不会变更原始数组，而总是返回一个新数组
    // 显示过滤/排序后的结果
      this.$refs.tree2.filter(val);
    },
    currentNodeId() {
      this.nodeDeleteVisible = !this.isTempNodeId(this.currentNodeId);
    }
  },
  // 只要执行完了mounted，表示整个Vue实例已经初始化完毕了，此时组件已经进入了运行阶段。
  mounted() {
    this.loadData();
  },
  methods: {
    isTempNodeId(nodeId) {
      // 是否临时节点ID
      return (nodeId + '').indexOf(this.tempNodePrefix) >= 0;
    },
    newTempNodId() {
      // 生成临时节点ID
      return this.tempNodePrefix + this.tempNodeIndex++;
    },

    loadData() {
      this.treeloading = true;
      getList().then(res => {
        var node = {
          id: 0,
          label: '所有部门',
          children: res.data
        };
        this.dataNodes = [node];
        this.treeloading = false;
      });
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },

    append(data, event) {
      this.clear();

      const newChild = {
        id: this.newTempNodId(),
        label: '新增部门',
        parentId: data.id,
        parentName: data.label,
        children: []
      };

      if (!data.children) {
        this.$set(data, 'children', []);
      }
      data.children.push(newChild);
      this.modifyVisible = true;

      this.selectNode(newChild);
      event.stopPropagation();
    },
    remove(node, data, event) {
      this.drop(data);
      event.stopPropagation();
    },
    clear() {
      // 清理未保存的node
      if (this.currentNodeData && this.isTempNodeId(this.currentNodeData.id)) {
        this.$refs.tree2.remove(this.currentNodeData);
      }
    },
    selectNode(data) {
      if (data.id) {
        // eslint-disable-next-line eqeqeq
        if (this.currentNodeData && data.id == this.currentNodeData.id) {
          return;
        }

        this.clear();

        this.currentNodeId = data.id;
        this.currentNodeData = data;

        this.modifyVisible = true;
        this.formData.id = data.id;
        this.parentName = data.parentName;
        this.formData.name = data.label;
        this.formData.code = data.id;
        this.formData.pid = data.parentId;
        this.formData.showOrder = data.showOrder;
        this.formData.deptDesc = data.deptDesc;
      } else {
        this.modifyVisible = false;
      }
    },
    modify() {},
    submitForm() {
      this.$refs['deptForm'].validate(valid => {
        if (valid) {
          const param = this.formData;
          // 新增节点，则清除临时ID
          if (this.isTempNodeId(param.id)) {
            param.id = null;
          }
          save(param).then(res => {
            this.modifyVisible = false;
            Message({
              message: '保存成功',
              type: 'success',
              duration: 5 * 1000
            });
            this.loadData();
          });
        } else {
          return false;
        }
      });
    },
    drop(node) {
      // element-ui的消息弹框，调用$confirm方法即可打开消息提示，它模拟了系统的 confirm
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const id = node.id || this.currentNodeData.id;
        console.log(id);
        const params = {};
        if (id) {
          params.id = id;
          drop(params).then(res => {
            // tree中移除
            if (node.id) {
              this.$refs.tree2.remove(node);
            } else {
              this.$refs.tree2.remove(this.currentNodeData);
            }

            // eslint-disable-next-line eqeqeq
            if (this.currentNodeData.id == id) {
              this.currentNodeData = {};
              this.currentNodeId = null;

              this.modifyVisible = false;
            }
          });
        }
        // 删除成功的提示信息
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
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

<style>
/* 自定义样式 */
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 13.5px;
  padding-right: 8px;
}

.custom-tree-node .el-button {
  color: #606266;
  display: none;
}
.el-tree-node__content:hover .el-button {
  display: inline-block;
}
</style>
