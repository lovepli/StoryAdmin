<template>
  <div class="app-container">
    <!-- data-grid组件
    @dataRest="onDataRest" 为监听事件，
    子组件通过this.$emit('dataRest')触发dataRest事件，父组件通过@dataRest监听事件，然后执行实际的方法onDataRest -->
    <data-grid
      ref="dataList"
      url="/sysmgr/user/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
      <!-- form插槽 -->
      <!-- 一个组件中只要出现多个插槽，请始终为所有的插槽使用完整的基于 <template> 的语法： -->
      <!-- 所以下面的插槽都是 template slot="" 格式的-->
      <!-- 下面使用自定义表格组件data-grid组件中的自定义插槽 form-->
      <template slot="form">
        <el-form-item label="账号">
          <!-- Vue 还提供了 v-model 指令，它能轻松实现表单输入和应用状态之间的双向绑定 -->
          <!-- 按键修饰符 @keyup.enter  -->
          <el-input
            v-model="listQuery.account"
            placeholder="账号"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
        <el-form-item label="姓名">
          <!-- 按键修饰符 @keyup.enter  -->
          <el-input
            v-model="listQuery.name"
            placeholder="姓名"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!-- 使用自定义表格组件data-grid组件中的自定义插槽 extendOperation-->
      <!--extendOperation 扩展操作,data-grid组件只包含查询和重置按钮，这里扩展插槽，新添加了一个新增按钮并排着-->
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
      <!--body-->
      <!-- 使用自定义表格组件data-grid组件中的自定义插槽 body， sortable排序-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px" sortable />
        <el-table-column align="center" prop="account" label="账号"/>
        <el-table-column align="center" prop="name" label="姓名"/>

        <!-- <el-table-column align="center" prop="age" label="年龄"/> -->
        <el-table-column align="center" prop="birthday" label="生日"/>
        <el-table-column align="center" prop="phone" label="手机号码"/>
        <el-table-column label="性别" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.sex | sexTagFilter">{{ scope.row.sex | sexFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="avatar" label="用户头像">
          <template slot-scope="scope">
            <img v-if="scope.row.avatar" :src="scope.row.avatar" width="40" @click="showImg(scope.row)">
          </template>
        </el-table-column>
        <el-table-column align="center" prop="email" label="邮箱" />
        <el-table-column label="状态" align="center">
          <!-- 作用域插槽/带数据的插槽 -->
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | statusTagFilter">{{ scope.row.status | statusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="ERP账号" align="center">
          <!-- 作用域插槽/带数据的插槽 -->
          <template slot-scope="scope">
            <!-- Tag 标签：用于标记和选择
            由type属性来选择tag的类型，也可以通过color属性来自定义背景色-->
            <el-tag
              :type="scope.row.erpFlag | erpFlagTagFilter"
            >{{ scope.row.erpFlag | erpFlagFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间">
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
            <!-- 按钮权限控制 -->
            <!-- <el-button v-if="hasPerm('sysmgr.user.save')" type="primary" size="mini" @click="modify(scope.row)">编辑</el-button> -->
            <el-button type="primary" size="mini" @click="modify(scope.row)">编辑</el-button>
            <el-button size="mini" @click="modifyUserRole(scope.row)">角色</el-button>
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>

    <!-- 用户信息详情弹框 -->
    <el-dialog :visible.sync="modifyVisible" title="用户信息">
      <el-form
        ref="userForm"
        :model="userForm"
        :rules="rules"
        label-width="70px"
        label-position="right"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="账号" prop="account" size="medium">
          <el-input v-model="userForm.account" class="filter-item" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <!-- 使用clearable属性即可得到一个可清空的输入框 -->
          <el-input v-model="userForm.name" placeholder="请输入姓名" clearable/>
        </el-form-item>
        <!-- <el-form-item label="年龄" prop="age">
          <el-input v-model="userForm.age" placeholder="请输入" clearable/>
        </el-form-item> -->
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            v-model="userForm.birthday"
            type="date"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
            @change="selectDate"
          />
        </el-form-item>

        <el-form-item label="性别" prop="sex">
          <el-select v-model="userForm.sex" class="filter-item" placeholder="请选择...">
            <el-option
              v-for="item in sexOptions"
              :key="item.key"
              :label="item.key"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="userForm.phone" placeholder="请输入" clearable/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" type="password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item label="用户头像" prop="avatar">
          <el-upload
            :headers="importHeaders"
            :action="filePostUrl"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="userForm.avatar" :src="userForm.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item label="状态">
          <!-- 表单输入绑定 中v-model 在内部为不同的输入元素使用不同的 property 并抛出不同的事件-->
          <!-- select 字段将 value 作为 prop 并将 change 作为事件 -->
          <el-select v-model="userForm.status" class="filter-item" placeholder="请选择...">
            <!--列表渲染 v-for 指令可以绑定数组的数据来渲染一个项目列表-->
            <!-- v-for 指令需要使用 item in items 形式的特殊语法，其中 items 是源数据数组，
            而 item 则是被迭代的数组元素的别名 -->
            <el-option
              v-for="item in statusOptions"
              :key="item.key"
              :label="item.key"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="EPR标识">
          <!-- 表单输入绑定 中v-model 在内部为不同的输入元素使用不同的 property 并抛出不同的事件-->
          <!--  1、select 字段将 value 作为 prop 并将 change 作为事件 -->
          <!--  2、checkbox 和 radio 使用 checked property 和 change 事件-->
          <!--  3、text 和 textarea 元素使用 value property 和 input 事件-->
          <el-select v-model="userForm.erpFlag" class="filter-item" placeholder="请选择...">
            <el-option
              v-for="item in erpFlagOptions"
              :key="item.key"
              :label="item.key"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="modifyVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 选择角色弹框
    roleVisible 是否显示
     -->
    <el-dialog :visible.sync="roleVisible" title="角色">
      <!--角色树组件 props 父组件向子组件传值  defaultProps -->
      <!-- element-ui的Tree树形控件
       1、可将 Tree 的某些节点设置为默认展开或默认选中:分别通过default-expanded-keys和default-checked-keys设置默认展开和默认选中的节点。需要注意的是，此时必须设置node-key，其值为节点数据中的一个字段名，该字段在整棵树中是唯一的。 -->
      <el-tree
        ref="tree2"
        :data="dataNodes"
        :props="defaultProps"
        :expand-on-click-node="false"
        :default-expanded-keys="[0,1]"
        :default-checked-keys="defaultSelected"
        accordion
        show-checkbox
        node-key="id"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="roleVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUserRole">确 定</el-button>
      </span>
    </el-dialog>

    <!--      展示附件图片的对话框-->
    <el-dialog
      :title="'上传的文件'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="70%">
      <el-image v-for="(item, index) in imgList" :key="index" :src="item" style="width: 50%;height: 50%"/>
      <el-divider style="margin-top: 100px"/>
      <div v-for="(item, index) in pdfList" :key="index">
        <!--          <embed :src="item" width="100%" height="300px">-->
        <!--          <iframe :src="item" width="100%" height="100%"></iframe>-->
        <object :data="item" type="application/pdf" width="100%" height="800px"/>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleClose">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import {
  findById,
  save,
  drop,
  findUserRole,
  saveUserRole
} from '@/api/sysmgr/user';

import { findAllRoleList } from '@/api/sysmgr/role';
import { findFileInfoDetail } from '@/api/sysmgr/user';
import { getToken } from '@/utils/auth'; // 从Cookies中获取token
// import Pagination from "@/components/Pagination"; // 分页组件
import DataGrid from '@/components/DataGrid'; // 引入表格子组件
import { parseTime } from '@/utils'; // 引入util中的格式化方法
// import { Message, MessageBox } from 'element-ui'; // 单独引入element-ui的Message消息提示, MessageBox弹框

const statusOptions = [
  { key: '正常', value: '1' },
  { key: '禁用', value: '0' }
];
const erpFlagOptions = [
  { key: '否', value: '0' },
  { key: '是', value: '1' }
];

const sexOptions = [
  { key: '男', value: '1' },
  { key: '女', value: '2' }
];

const statusTypeKeyValue = statusOptions.reduce((acc, cur) => {
  acc[cur.value] = cur.key;
  return acc;
}, {});
const erpFlagTypeKeyValue = erpFlagOptions.reduce((acc, cur) => {
  acc[cur.value] = cur.key;
  return acc;
}, {});

const sexTypeKeyValue = sexOptions.reduce((acc, cur) => {
  acc[cur.value] = cur.key;
  return acc;
}, {});

export default {
  name: 'User',
  components: { DataGrid }, // 注册子组件
  // 定义本地过滤器
  filters: {
    // 状态过滤器
    statusFilter(status) {
      return statusTypeKeyValue[status];
    },
    // 状态标签过滤器
    statusTagFilter(status) {
      const statusMap = {
        '1': 'success',
        '0': 'danger'
      };
      return statusMap[status];
    },
    erpFlagFilter(flag) {
      return erpFlagTypeKeyValue[flag];
    },
    erpFlagTagFilter(flag) {
      const statusMap = {
        '1': 'success',
        '0': 'info'
      };
      return statusMap[flag];
    },
    sexFilter(sex) {
      return sexTypeKeyValue[sex];
    },
    // 性别标签过滤器
    sexTagFilter(sex) {
      const sexMap = {
        '1': 'success',
        '0': 'danger'
      };
      return sexMap[sex];
    },
    parseTime
  },
  // data中存放的是el中需要的数据
  data() {
    // 自定义密码校验规则
    var validatePass = (rule, value, callback) => {
      // 输入内容为空并且erp标识为否，则密码是必填的
      if (!value && this.userForm.erpFlag === '0') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      importHeaders: { Authorization: getToken() },
      // 上传图片接口
      filePostUrl: process.env.BASE_API + '/common/uploadImageFile',
      // 查询参数对象
      listQuery: {
        pageNo: 1,
        limit: 10,
        id: null,
        account: null
      },
      modifyVisible: false, // 修改可见性，是否显示弹框
      statusOptions: statusOptions, // 状态选项
      erpFlagOptions: erpFlagOptions, // erp标志选项
      sexOptions: sexOptions, // 性别选项
      // form表单对象
      userForm: {
        account: '', // 账号
        name: '', // 姓名
        // age: '', // 年龄
        birthday: '', // 生日
        phone: '', // 手机号码
        sex: '1', // 性别
        avatar: '',
        password: '', // 密码
        email: '', // 邮箱
        status: '', // 状态
        erpFlag: '0' // erp标识
      },
      // form表单验证规则rules
      rules: {
        account: [ // 账号验证
          { required: true, message: '账户是必填项', trigger: 'blur' },
          { min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur' }
        ],
        password: [{ validator: validatePass, trigger: 'blur' }] // 密码验证
      },
      currentUserId: null, // 临时赋值中间变量，userId
      roleVisible: false, // 是否显示角色对话框
      dataNodes: null, // 节点数组对象，渲染到树形控件的数据
      defaultSelected: [], // 默认选中，存储角色ID集合的数组对象
      // roleTreeLoading: false, // 角色树加载 暂时没有用，这个属性
      // 父组件向子组件传递数据，子组件通过props获取属性值
      defaultProps: {
        children: 'children', // 子节点
        label: 'name' // 节点名称 这里是角色名称，这个name值应该要与返回的json中的name字段保持一致
      },
      // 图片展示
      imgList: [],
      // pdf展示
      pdfList: [],
      dialogVisible: false
    };
  },
  // 侦听属性
  // 虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。
  // 这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。
  // 当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的
  watch: {
    // 如果 `roleVisible`属性 发生改变，这个函数就会运行
    roleVisible(val) {
      if (!val) {
        this.defaultSelected = [];
      }
    }
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  // 此函数中，data和methods都已经初始化好了，如果需要调用methods中的方法或操作data中的值最早就在created函数中操作。
  created() {},
  // methods是Vue内置的对象，用于存放一些自定义的方法函数
  methods: {
    handleClose() {
      this.dialogVisible = false
    },
    selectDate(val) {
      console.log('选择的日期为', +val)
    },
     	/**
       * 查看附件
       */
    async showImg(row) {
      this.dialogVisible = true
      this.imgList = []
      await findFileInfoDetail({
        'userId': row.id
      }).then(res => {
        // eslint-disable-next-line no-array-constructor
        var arr = new Array()
        arr = res.data.split('&&&')
        for (var i = 0; i < arr.length - 1; i++) {
          if (arr[i].indexOf('data:application/pdf;base64,') !== -1) {
            this.pdfList.push(arr[i])
          } else {
            this.imgList.push(arr[i])
          }
        }
      })
    },
    // onDataRest() {}这是ES6的简写方式,之前是 methodName:function(){} 这种写法
    // 重置按钮
    onDataRest() {
      this.listQuery = {};
    },
    // 点击键盘Enter，触发进行条件搜索
    handleFilter() {
      // 刷新列表
      // 通过使用this.$refs.dataList 来访问这个子组件 <data-grid> 实例，调用该实例的fetchData()方法，刷新列表
      this.$refs.dataList.fetchData();
    },
    // 新增/编辑按钮
    modify(user) {
      // 显示编辑弹框
      this.modifyVisible = true;
      if (user) { // 编辑用户信息，所以需要查询用户信息显示到弹框中
        const params = {
          id: user.id
        };
        // 语法：
        // 正常我们调用函数会写：name(function(){})，而ES6也提供了一个方式：methodName(() => {})，
        // 这种用法的好处就解决了this指向问题，因为如果元素定义在了函数内部，那么其中的this就表示当前函数的对象，
        // 如果我们需要使用外部的对象，除了在外部全局定义一个对象，一个简单的方式就是使用ES6提供的=>
        findById(params).then(res => {
          if (res.result) {
            // 请求成功，渲染列表
            this.userForm = res.data;
          } else {
            // 请求失败，返回错误信息
            this.$message.error(res.code);
          }
        });
      } else { // 添加用户信息，弹框表单信息内容置为空，这里可以参考定时任务那里使用watch监听来实现表单内容置为空
        this.userForm.id = null;
        this.userForm.account = null;
        this.userForm.name = null;
        // this.userForm.age = null;
        this.userForm.birthday = null;
        this.userForm.phone = null;
        this.userForm.sex = '1';
        this.userForm.avatar = null;
        this.userForm.password = null;
        this.userForm.email = null;
        this.userForm.status = '1'; // 状态默认值为1
        this.userForm.erpFlag = '0'; // erp标识默认值为0
      }
    },
    // 上传图像 返回图片的URL地址
    uploadAvatar: function(response) {
      // console.log(response)
      this.userForm.avatar = response.data
      // console.log(response.data)
      this.$message.success('图片上传成功')
    },
    // 保存(添加/修改)-提交按钮
    submitForm() {
      // this.$refs 获取userForm表单组件,验证表单组件的rules规则
      this.$refs['userForm'].validate(valid => {
        if (valid) { // 如果验证通过
          // this.userForm.roleIds=this.$refs.multipleTree.getCheckedKeys();
          const para = this.userForm;
          // 调用保存数据的接口，参数para为表单数据，在表单中用v-model绑定需要添加的参数
          save(para).then(res => {
            // 关闭弹框
            this.modifyVisible = false;
            // 请求成功，即添加了一条新的数据，那么需要重新刷新列表（不然新数据不能及时的更新到页面上）
            this.$refs.dataList.fetchData();
          });
        } else { // 验证失败
          return false;
        }
      });
    },
    // 删除行数据
    dropRow(row) {
      // element-ui的消息弹框，参考官方文档：https://element.eleme.cn/#/zh-CN/component/message-box
      // 调用$confirm方法即可打开消息提示，它模拟了系统的 confirm
      // 模拟系统的消息提示框而实现的一套模态对话框组件，用于消息提示、确认消息和提交内容。
      // 如果你完整引入了 Element，它会为 Vue.prototype 添加如下全局方法：$msgbox, $alert, $confirm 和 $prompt。
      // 可以在vm实例中通过this.$message(options)方法来调用出message，也可以通过在文件中单独引入Message,通过Message(options)来调用
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 确定删除的逻辑
          const params = {};
          if (row.id) {
            params.id = row.id;
            // 调用删除行数据接口，传递id作为参数params
            drop(params).then(res => {
              // 刷新列表
              this.$refs.dataList.fetchData();
            });
          }
          // 删除成功页面显示的提示信息,这里是前端写死的接口调用成功的返回信息，我们要做的是接口调用成功返回的结果交由后台传递的数据来展示
          // 使用全局的Message
          // this.$message({
          //   type: 'success',
          //   message: '删除成功!'
          // });
        })
        .catch(() => {
          // 删除失败的提示信息
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    // 角色按钮
    modifyUserRole(row) {
      // 显示角色对话框
      this.roleVisible = true;
      // 角色树加载
      // this.roleTreeLoading = true;
      // 将用户信息记录的行id赋值给currentUserId字段
      this.currentUserId = row.id;
      // 查询全部角色,调用api接口
      // get请求说明：
      // 当我们请求成功后，可以通过.then来获取请求成功响应的数据，而可以通过.data或.body来获取响应data，而我们通常使用res.body来获取具体响应的参数。注意其中的res => {}是ES6中的写法。
      findAllRoleList().then(res => {
      //  console.log(res.body);
      // 当前节点的node对象
        var node = {
          id: 0, // 节点的key node-key
          name: '所有角色', // 节点的名称 name即为tree树形控件的label
          children: res.data // 子节点 后台返回的json数据赋值给children对象
        };
        // 将节点对象数据赋值给数据节点对象，渲染到tree树控件
        this.dataNodes = [node];
      });
      // 根据用户id查询用户角色,获取已勾选项
      findUserRole({ userId: row.id }).then(res => {
        // 默认选中的节点，res.data返回的是roleId的值
        this.defaultSelected = res.data;
        // 角色树加载
        // this.roleTreeLoading = false;
      });
    },
    // 确定修改角色按钮
    submitUserRole() {
      // 父组件通过使用this.$refs.tree2 来访问这个子组件 <el-tree> 实例，调用该实例的getCheckedKeys()方法，获取选中节点的key值即id值
      var roleIds = this.$refs.tree2.getCheckedKeys();// 获取选中节点的key值即id值，选中的节点可以为多个
      // 查询参数
      const params = {
        userId: this.currentUserId, // 这里将之前从row.id的值存入到临时变量currentUserId，现在赋值给userId
        roleIds: roleIds // 角色集合ids
      };
      // post请求说明：
      // post请求常用于类似提交表单的功能，而对于提交表单，存在一个表单提交格式，默认是：application/x-wwww-form-urlencoded ；而通过Vue发起的post请求，默认没有表单格式，所以，有的服务器就处理不了
      // 那么我们可以通过post方法的第三个参数：{ emulateJSON: true }来设置提交内容类型为普通表单数据格式。
      saveUserRole(params).then(res => {
        // 关闭角色对话框
        this.roleVisible = false;
        // 清空 currentUserId临时变量的值
        this.currentUserId = null;
        // 将tree树默认勾选值属性置为空
        this.defaultSelected = [];
      });
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>
