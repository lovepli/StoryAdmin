<template>
  <div class="app-container">
    <data-grid
      ref="dataList"
      url="/sysmgr/role/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
      <template slot="form">
        <el-form-item label="名称">
          <el-input
            v-model="listQuery.name"
            placeholder="名称"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!--extendOperation-->
      <!-- 扩展插槽 新增按钮 -->
      <template slot="extendOperation">
        <el-button
          v-waves
          class="filter-item"
          style="margin-left: 10px;"
          type="primary"
          size="small"
          icon="el-icon-edit"
          @click="modify()"
        >新增</el-button>
      </template>
      <!--body-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px"/>
        <el-table-column align="center" prop="name" label="名称"/>
        <el-table-column align="center" prop="roleDesc" label="描述"/>
        <el-table-column align="center" prop="createdTime" label="创建时间">
             <!-- 作用域插槽 slot-scope 的使用-->
             <!--在 <template> 上使用特殊的 slot-scope attribute，可以接收传递给插槽的 prop,例如： -->
              <!-- 
                  <template slot="default" slot-scope="slotProps">
                {{ slotProps.msg }}
                  </template>
              -->
            <!-- 这里的 slot-scope 声明了被接收的 prop 对象会作为 slotProps 变量存在于 <template> 作用域中。你可以像命名 JavaScript 函数参数一样随意命名 slotProps -->
          <template slot-scope="scope">{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="230" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-waves type="primary" size="mini" @click="modify(scope.row)">编辑</el-button>
            <el-button v-waves size="mini" @click="auth(scope.row)">权限</el-button>
            <el-button v-waves type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>
    <!-- 角色信息详情对话框 -->
    <el-dialog :visible.sync="modifyVisible" title="角色信息">
      <el-form
        ref="roleForm"
        :model="roleForm"
        :rules="rules"
        label-width="70px"
        label-position="right"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="名称" prop="account" size="medium">
          <el-input v-model="roleForm.name" class="filter-item" placeholder="请输入角色名称"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="modifyVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog :visible.sync="authVisible" title="权限">
      <!--权限树组件 -->
      <!-- element-ui的Tree树形控件 -->
      <el-tree
        ref="tree2"
        :data="dataNodes"
        :default-checked-keys="defaultSelected"
        :default-expanded-keys="[0,1]"
        :expand-on-click-node="false"
        accordion
        show-checkbox
        node-key="id"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="authVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAuth">确 定</el-button>
      </span>
    </el-dialog>
    
  </div>
</template>

<script>
import {
  // eslint-disable-next-line no-unused-vars
  getList,
  findById,
  save,
  drop,
  modifyAuth,
  findRoleAuth
} from '@/api/sysmgr/role';
import { getList as findAuthList } from '@/api/sysmgr/authority'; // getList 重命名成 findAuthList
import { parseTime } from '@/utils';
// import Pagination from "@/components/Pagination";
import DataGrid from '@/components/DataGrid';
import waves from '@/directive/waves'; // Waves directive
export default {
  name: 'Role',
  components: { DataGrid },
  directives: { waves },
  // 定义本地过滤器
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      };
      return statusMap[status];
    },
    parseTime
  },
  // 定义本地过滤器
  // eslint-disable-next-line no-dupe-keys
  // filters: {
  //   parseTime
  // },
  data() {
    return {
      // total: 0,
      // list: null,
      // listLoading: true,

      listQuery: { // 查询参数对象
        pageNo: 1,
        limit: 10,
        name: null
      },
      modifyVisible: false, // 修改可见性，是否显示弹框
      roleForm: {}, // form表单对象
      rules: { // form表单验证规则rules
        name: [{ required: true, message: '名称是必填项', trigger: 'blur' }]
      },
      currentAuthRoleId: null, // 临时赋值中间变量, roleId
      authVisible: false, // 是否显示权限对话框
      // authTreeLoading: false, // 权限树加载 暂时没有用，这个属性
      dataNodes: [], // 节点数组对象
      defaultSelected: [] // 存储权限ID集合的数组对象
    };
  },
  watch: {
    // 如果 `authVisible`属性 发生改变，这个函数就会运行
    authVisible(val) {
      if (!val) {
        this.defaultSelected = [];
      }
    }
  },
  methods: {
    onDataRest() {
      this.listQuery = {};
    },
    handleFilter() {
      this.$refs.dataList.fetchData();
    },
    modify(role) {
      this.modifyVisible = true;
      if (role) {
        const params = {
          id: role.id
        };
        findById(params).then(res => {
          if (res.result) {
            this.roleForm = res.data;
          } else {
            // 这个方法的作用？？
            this.tipMsgBox(res.code);
          }
        });
      } else { //  添加角色信息
        this.roleForm.id = null;
        this.roleForm.name = null;
      }
    },
    submitForm() {
      // 保存
      this.$refs['roleForm'].validate(valid => {
        if (valid) {
          const para = this.roleForm;
          save(para).then(res => {
            this.modifyVisible = false;
            this.$refs.dataList.fetchData();
          });
        } else {
          return false;
        }
      });
    },
    dropRow(row) {
      // element-ui的消息弹框，调用$confirm方法即可打开消息提示，它模拟了系统的 confirm
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
    // 权限按钮
    auth(row) {
      this.authVisible = true;
      // this.authTreeLoading = true;
      // 权限id赋值
      this.currentAuthRoleId = row.id;
      // 获取权限节点，接口返回的是权限树结构
      findAuthList().then(res => {
        var node = {
          id: 0,
          label: '所有权限',
          children: res.data
        };
        this.dataNodes = [node];
      });

      // 获取已勾选项
      // 根据角色Id查询角色所有权限，返回的是权限id集
      findRoleAuth({ roleId: row.id }).then(res => {
        this.defaultSelected = res.data;
        // this.authTreeLoading = false;
      });
    },
    submitAuth() {
      var authCodes = this.$refs.tree2.getCheckedKeys(); // 获取选中节点的值， 选中的菜单项
      var authCodesHalf = this.$refs.tree2.getHalfCheckedKeys(); // 获取半选中节点的值
      var authCodesAll = authCodesHalf.concat(authCodes); // 全部节点的值
      console.log(
        'submitAuth authCodes=>',
        authCodes,
        ' authCodesHalf=>',
        authCodesHalf,
        ' authCodesAll=>',
        authCodesAll
      );
      const params = {
        roleId: this.currentAuthRoleId, // 角色id
        authorityIds: authCodesAll // 权限集合ids
      };
      // 修改权限
      modifyAuth(params).then(res => {
        // 关闭权限对话框
        this.authVisible = false;
        // 清空 currentAuthRoleId临时变量的值
        this.currentAuthRoleId = null;
        // 将tree树默认勾选值属性置为空，可以添加下一行数据或者不添加都可以
        // this.defaultSelected = [];
      });
    }
  }
};
</script>
