<template>
  <div class="app-container">
    <el-form
      ref="userForm"
      :model="userForm"
      :rules="rules"
      label-width="80px"
      label-position="left"
      style="width: 400px;margin:0px auto;"
    >
      <el-form-item label="原密码" prop="password">
        <el-input v-model="userForm.password" type="password" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="userForm.newPassword" type="password" placeholder="请输入密码"/>
      </el-form-item>

      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="userForm.confirmPassword" type="password" placeholder="请输入密码"/>
      </el-form-item>
    </el-form>
    <div slot="footer" style="text-align:center;">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="$router.push({path:'/'})">取 消</el-button><!-- 跳转到首页  -->
    </div>
  </div>
</template>
<script>
import { editpassword } from '@/api/sysmgr/user';
import { Message } from 'element-ui';
export default {
  name: 'User',
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value !== this.userForm.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userForm: {
        password: '',
        newPassword: '',
        confirmPassword: ''
      },
      rules: {
        password: [
          { required: true, message: '原密码是必填项', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '新密码是必填项', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '确认密码是必填项', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm() {
      // 保存
      // this.$refs可以获取元素和组件（以及组件中的元素）
      // 1、如果在HTML中定义了 ref="xx" 那么在Vue实例中通过this.$refs.xx就能获取到当前定义ref="xx"的DOM元素
      // 2、如果在组件引用上（比如<son ref="xx">）上使用了ref，那么在父组件Vue实例中通过this.$refs获取到的是整个子组件的对象，可以通过.的方式调用子组件data和methods中绑定数据
      this.$refs['userForm'].validate(valid => {
        if (valid) {
          const para = this.userForm;
          editpassword(para).then(res => {
            this.userForm.password = '';
            this.userForm.newPassword = '';
            this.userForm.confirmPassword = '';

            Message({
              message: '修改成功',
              type: 'success',
              duration: 5 * 1000
            });
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>
