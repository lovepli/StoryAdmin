<template>
  <div class="app-container">
    <!-- <el-container>：外层容器。当子元素中包含 <el-header> 或 <el-footer> 时，全部子元素会垂直上下排列，否则会水平左右排列。 -->
    <el-container>
      <!-- <el-header>：顶栏容器 -->
      <el-header>
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" style="margin-bottom:30px;" />
      </el-header>

      <el-container>
        <!-- <el-aside>：侧边栏容器 ，这里的侧边栏容器是一个树Tree控件-->
        <!-- v-loading="listLoading"  是否显示表格正在加载Loading -->
        <el-aside
          v-loading="treeloading"
          width="300px"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.1)"
        >
          <!-- element-ui的Tree树形控件
          1、在需要对节点进行过滤时，调用 Tree 实例的filter方法，参数为关键字。需要注意的是，此时需要设置filter-node-method，值为过滤函数。
          -->
          <el-tree
            ref="tree2"
            :data="dataNodes"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            class="filter-tree"
            node-key="id"
            default-expand-all
            @node-click="selectNode"
          >
            <!-- slot-scope作用域插槽 custom-tree-node自定义图片大小样式-->
            <!-- Cascader Slots：自定义备选项的节点内容，参数为 { node, data }，分别为当前节点的 Node 对象和数据 -->
            <span slot-scope="{ node, data }" class="custom-tree-node">
              <span>
                <!-- svg-icon为全局注册的组件 -->
                <!-- 图片在label名字前面显示 -->
                <svg-icon :icon-class="data.iconClass" />
                {{ node.label }}
              </span>
              <span>
                <!--  v-show="data.id==0" 当是所有菜单时才显示刷新图标 -->
                <el-button
                  v-show="data.id==0"
                  type="text"
                  size="mini"
                  @click="($event) => loadData(data,$event)"
                >
                  <!-- 刷新按钮(图标) -->
                  <!-- icon图标都使用 是element-ui提供都一套常用的图标集合
                  使用方法：直接通过设置类名为 el-icon-iconName 来使用即可
                   -->
                  <i class="el-icon-refresh" />
                </el-button>
                <el-button
                  type="text"
                  size="mini"
                  @click="($event) => append(data,$event)"
                >
                  <!-- 添加按钮(图标) -->
                  <i class="el-icon-plus" />
                </el-button>
                <!--  v-show="data.id！=0" 除了所有菜单其他都显示关闭图标 -->
                <el-button
                  v-if="data.id!=0"
                  type="text"
                  size="mini"
                  @click="($event) => remove(node, data,$event)"
                >
                  <!-- 关闭按钮(图片) -->
                  <i class="el-icon-close" />
                </el-button>
              </span>
            </span>
          </el-tree>
        </el-aside>

        <!-- <el-main>：主要区域容器。这里的主要区域容器是一个表单控件 -->
        <el-main>
          <!-- transition过度动效 如果你想让每个路由组件有各自的过渡效果，可以在各路由组件内使用 <transition> 并设置不同的 name-->
          <transition name="el-fade-in">
            <!-- form 表单 -->
            <el-form
              v-show="modifyVisible"
              ref="menuForm"
              :rules="rules"
              :model="formData"
              label-width="100px"
              style="width: 400px;"
            >
              <el-form-item label="权限编码" prop="code">
                <!--  Cascader 级联选择器
                  当一个数据集合有清晰的层级结构时，可通过级联选择器逐级查看并选择。
                  v-model 选中绑定值
                  options 可选项数据源，键名可通过 Props 属性配置，类型为array
                  props 配置选项，类型为object
                  placeholder 输入框占位符文本
                  filterable 是否可搜索选项 类型boolean
                  可以仅在输入框中显示选中项最后一级的标签，而不是选中项所在的完整路径,属性show-all-levels定义了是否显示完整的路径，将其赋值为false则仅显示最后一级
                  clearable 是否支持清空选项，类型boolean
                -->
                <el-cascader
                  :options="options"
                  v-model="auth_path"
                  :show-all-levels="false"
                  :props="props_auth"
                  props-expand-trigger="hover"
                  style="width: 100%;"
                  clearable
                  filterable
                  placeholder="试试搜索：系统管理"
                />
              </el-form-item>
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="formData.name" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="图标" prop="iconClass">
                <el-input v-model="formData.iconClass" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="菜单路径" prop="url">
                <el-input v-model="formData.url" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="页面路径" prop="component">
                <el-input v-model="formData.component" placeholder="请输入..."/>
              </el-form-item>
              <el-form-item label="是否隐藏" prop="hiddenFlag">
                <!-- Switch 开关:表示两种相互对立的状态间的切换，多用于触发「开/关」 -->
                <!-- 基本用法：绑定v-model到一个Boolean类型的变量。可以使用active-color属性与inactive-color属性来设置开关的背景色 -->
                <el-switch v-model="formData.hiddenFlag"/>
              </el-form-item>
              <el-form-item label="排序" prop="showOrder">
                <!-- 如果想自动将用户的输入值转为数值类型，可以给 v-model 添加 number 修饰符 -->
                <el-input v-model.number="formData.showOrder" placeholder="请输入..."/>
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
import { getList, save, drop } from '@/api/sysmgr/resource';
import { getList as getAuthList } from '@/api/sysmgr/authority';
// eslint-disable-next-line no-unused-vars
import { Message, MessageBox } from 'element-ui'; // 单独引入element-ui的Message消息提示, MessageBox弹框
export default {
  data() {
    return {
      filterText: '', // 过滤条件
      dataNodes: [], // tree-data
      // 父组件向子组件传递数据，子组件通过props获取属性值
      defaultProps: {
        children: 'children', // 子节点
        label: 'label' // 节点名称
      },

      treeloading: false, // 左侧菜单树默认不加载
      // 明细表单
      formData: {
        name: null, // 菜单名称
        iconClass: null, // 图标
        url: null, // 页面路径
        pid: null, // 父id
        fullId: null, // 编号路径
        component: null, // 菜单路径
        hiddenFlag: null, // 是否隐藏,对应的数据库表中没有这个字段
        showOrder: null // 排序
      },
      modifyVisible: false, // 表单是否显示
      nodeDeleteVisible: false,
      rules: {
        name: [{ required: true, message: '名称是必填项', trigger: 'blur' }]
      },

      options: [], // 级联选择器中的可选项数据源
      // 权限选择项配置(在级联选择器中填写权限编码，级联选择器中也有类似的Tree树结构，注意与树结构区别)
      props_auth: {
        value: 'id', // 节点value值
        label: 'label', // 节点名称
        children: 'children' // 子节点
      },
      auth_path: [],
      auth_find_flag: false,

      currentNodeData: null, // 缓存当前操作的节点
      currentNodeId: null,
      tempNodePrefix: 'temp', // 新增节点临时id前缀
      tempNodeIndex: 1 // 新增节点临时id序号
    };
  },
  watch: {
    // element-ui自己对Tree树形控件的过滤方法，数据绑定，根据输入对内容对变化，通过操控<el-tree></el-tree>组件，.filter过滤数组内容
    filterText(val) {
      // filter() 替换数组的方法，不会变更原始数组，而总是返回一个新数组
      this.$refs.tree2.filter(val);
    },
    currentNodeId() {
      this.nodeDeleteVisible = !this.isTempNodeId(this.currentNodeId);
    }
  },
  // 只要执行完了mounted，表示整个Vue实例已经初始化完毕了，此时组件已经进入了运行阶段。
  mounted() {
    // 加载权限列表，即右侧级联选择器中的内容
    this.loadAuth();
    // 加载左侧的菜单树节点数据
    this.loadData();
  },
  methods: {
    // 是否临时节点ID
    isTempNodeId(nodeId) {
      return (nodeId + '').indexOf(this.tempNodePrefix) >= 0;
    },
    // 生成临时节点ID
    newTempNodId() {
      return this.tempNodePrefix + this.tempNodeIndex++;
    },
    // 加载权限列表
    loadAuth() {
      getAuthList().then(res => {
        // 对后台返回的权限列表数据进行处理
        this.recursiveCheckChildren(res.data);
        // 将后台返回的数据渲染到权限编码的级联选择器中
        this.options = res.data;
      });
    },
    // 递归查询combo列表中的子元素
    recursiveCheckChildren(arr) {
      // 遍历集合
      for (var i = 0; i < arr.length; i++) {
        // 存在子节点则递归，否则将子节点置为空
        if (arr[i].children && arr[i].children.length > 0) {
          this.recursiveCheckChildren(arr[i].children);
        } else {
          arr[i].children = null;
        }
      }
    },
    // 加载左侧菜单树
    loadData() {
      // 加载菜单树
      this.treeloading = true;
      getList().then(res => {
        // 当前树节点的node对象
        var node = {
          id: 0, // 节点的key node-key
          label: '所有菜单', // 节点的名称 label即为tree树形控件的label
          iconClass: 'tree', // 树节点图标
          children: res.data // 子节点对象，后台返回的json数据赋值给children对象
        };
        // 将节点对象数据赋值给数据节点对象，渲染到tree树控件
        this.dataNodes = [node];
        this.treeloading = false;
      });
    },
    // element-ui自己的Tree树形控件的过滤方法,对左侧树结构进行过滤
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 新增菜单按钮
    append(data, event) {
      // 清理未保存都菜单树节点
      this.clear();

      // 定义一个临时子菜单节点对象，并设置属性值
      const newChild = {
        id: this.newTempNodId(), // 临时节点前缀
        label: '新增菜单', // 临时子节点名称，默认为"新增菜单"
        parentId: data.id, // 父id
        // parentName: data.label, // 父级菜单名称，这里好像没有用到这属性？
        children: [] // 子节点为空
      };

      if (!data.children) { // 如果子节点不为空，设置为空
        this.$set(data, 'children', []);
      }
      // 添加临时子节点对象到菜单对象
      data.children.push(newChild);
      // 显示表单
      this.modifyVisible = true;

      // 点击菜单树某一个节点进行查询，临时子节点做为参数
      this.selectNode(newChild);
      event.stopPropagation();
    },
    // 关闭/删除菜单树中某一个节点按钮
    remove(node, data, event) {
      // 删除节点
      this.drop(data);
      event.stopPropagation();
    },
    // 清理未保存的node
    clear() {
      if (this.currentNodeData && this.isTempNodeId(this.currentNodeData.id)) {
        // tree树中移除节点
        this.$refs.tree2.remove(this.currentNodeData);
      }
    },
    selectNode(data) {
      if (data.id) {
        // eslint-disable-next-line eqeqeq
        if (this.currentNodeData && data.id == this.currentNodeData.id) {
          return;
        }

        // 清除未保存的树节点
        this.clear();

        this.currentNodeId = data.id;
        this.currentNodeData = data;

        this.auth_find_flag = false;
        this.auth_path = []; // 权限路径，存储权限ID值
        // 递归查找当前选择的菜单的权限节点id路径，authorityId为当前菜单表中存储的权限id值authorityId，options为级联选择器选中的权限编码值，options数组中存储authorityId权限值
        this.recursiveFindPath(data.authorityId, this.options);
        // 往数组对象里面添加权限id数据
        this.auth_path.push(data.authorityId);

        // 显示表单
        this.modifyVisible = true;
        // form表单赋值
        this.formData.id = data.id; // 表单id
        this.formData.fullId = data.fullId; // 编号路径
        this.formData.name = data.label; // 菜单名称，默认为“新增菜单”，因为前面在data里面设置了label名称
        this.formData.iconClass = data.iconClass; // 图标
        this.formData.showOrder = data.showOrder; // 排序
        this.formData.url = data.url; // 菜单路径
        this.formData.component = data.component; // 页面路径
        this.formData.pid = data.parentId; // 父id
        this.formData.authorityId = data.authorityId; // 权限id
        this.formData.hiddenFlag = data.hiddenFlag; // 是否隐藏
        console.log('authorityId' + data.authorityId)
        console.log('hiddenFlag' + data.hiddenFlag)

        // this.parentName = data.parentName; // 父级菜单名称
      } else {
        this.modifyVisible = false;
      }
    },

    // 递归查找当前选择的菜单的权限节点id路径
    // 根据权限authorityId值，对权限集合进行递归，将数据渲染到级联选择器，auths为级联选择器的options数组对象
    recursiveFindPath(authid, auths) {
      // 遍历循环数组
      for (var i = 0; i < auths.length; i++) {
        if (auths[i].id === authid) {
          this.auth_path.push(auths[i].id); // 这里的id为props_auth对象中的id是吧？
          return true;
        } else if (auths[i].children) { // 存在子节点，这里的children为props_auth对象中的children是吧？
          const isPath = this.recursiveFindPath(authid, auths[i].children);
          if (isPath) {
            // 拼接
            this.auth_path.splice(0, 0, auths[i]);
            return true;
          }
        }
      }
    },
    // 表单提交
    submitForm() {
      this.$refs['menuForm'].validate(valid => {
        if (valid) {
          const param = this.formData;
          // 新增节点，则清除临时ID
          if (this.isTempNodeId(param.id)) {
            param.id = null;
          }
          param.authorityId = this.auth_path[this.auth_path.length - 1]; // 参数为权限id
          save(param).then(res => {
            this.modifyVisible = false;
            Message({
              message: '保存成功',
              type: 'success',
              duration: 5 * 1000
            });
            // 刷新左侧菜单树
            this.loadData();
          });
        } else {
          return false;
        }
      });
    },
    // 删除菜单树中某一个节点
    drop(node) {
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const id = node.id || this.currentNodeData.id;
        const params = {};
        if (id) {
          params.id = id;
          drop(params).then(res => {
            // tree中移除
            if (node.id) {
              this.$refs.tree2.remove(node);
            } else {
              // 删除缓存节点
              this.$refs.tree2.remove(this.currentNodeData);
            }

            // 清空缓存节点数据
            // eslint-disable-next-line eqeqeq
            if (this.currentNodeData.id == id) {
              this.currentNodeData = {};
              this.currentNodeId = null;

              this.modifyVisible = false;
            }
          });
        }
      })
        .catch(() => {
          // 删除失败的提示信息
          // 使用单独引入的Message
          Message({
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
