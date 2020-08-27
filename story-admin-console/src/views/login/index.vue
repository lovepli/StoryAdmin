
<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
      <!-- <h3 class="title">STORY-ADMIN</h3> -->
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input v-model="loginForm.username" name="username" type="text" auto-complete="on" placeholder="默认账号admin" />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="大写开启" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <!-- svg-icon为全局注册的组件 -->
            <svg-icon icon-class="password" />
          </span>
          <!-- 按键修饰符 @keyup.enter -->
          <el-input
            :type="pwdType"
            v-model="loginForm.password"
            name="password"
            auto-complete="on"
            placeholder="默认密码，6个1"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin" />
          <!-- showPwd 显示密码，也可以用el-input组件中的show-password属性即可以得到一个可切换显示隐藏的密码框 -->
          <span class="show-pwd" @click="showPwd">
            <svg-icon icon-class="eye" />
          </span>
        </el-form-item>
      </el-tooltip>

      <!-- 增加验证码功能 -->
      <el-form-item prop="code">
        <!-- <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" /> -->
        <span class="svg-container">
          <svg-icon icon-class="validCode"/>
        </span>
        <el-input
          v-model="loginForm.code"
          auto-complete="off"
          placeholder="请输入验证码"
          style="width: 70%"
          @keyup.enter.native="handleLogin"
        />
        <div class="login-code">
          <img :src="codeUrl" class="login-code-img" @click="getCode">
        </div>
      </el-form-item>
      <!-- 增加记住我功能 -->
      <el-row>
        <el-checkbox v-model="loginForm.rememberMe" style="float:right;margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      </el-row>

      <el-row :gutter="20" style="margin-bottom:30px;">
        <el-col :span="8">
          <!-- 事件修饰符 @click.native.prevent -->
          <el-button :loading="loading" type="primary" style="width:100%;" @click.native.prevent="handleLogin">登陆</el-button>
        </el-col>
        <el-col :span="8">
          <el-tooltip class="item" effect="dark" content="不能自主注册，请联系管理员分配账号。" placement="bottom-start">
            <el-button type="info" style="width:100%;">注册</el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="8">
          <el-tooltip class="item" effect="dark" content="请尝试使用初始密码111111。如果还是登录失败，请联系管理员进行密码重置。" placement="bottom-start">
            <el-button icon="el-icon-question" type="warning" style="width:100%;">忘记密码</el-button>
          </el-tooltip>
        </el-col>
      </el-row>

      <div style="position:relative">
        <div class="tips">
          <span />
        </div>
      </div>

    </el-form>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate' // 引入表达验证
import settings from '@/settings' // 引入配置文件

import { getCodeImg } from '@/api/login'; // 获取验证码

export default {
  name: 'Login',
  data() {
    // 表单验证
    const validateUsername = (rule, value, callback) => {
      // isvalidUsername 是 /utils/validate.js中的验证方法
      if (!isvalidUsername(value) || value.length === 0) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      // loginForm2: {
      //   username: 'admin',
      //   password: '111111'
      // },
      // 验证码和记住我功能
      loginForm: {
        username: 'admin',
        password: '111111',
        rememberMe: false,
        code: '',
        uuid: ''
      },
      codeUrl: '',
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }],
        code: [{ required: true, trigger: 'change', message: '验证码不能为空' }]
      },
      loading: false,
      pwdType: 'password',
      capsTooltip: false,
      redirect: undefined, // 重定向
      title: settings.title // 动态更新标题
    }
  },
  // 侦听属性 路由重定向
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
      // console.info('验证码=>', res.data)
        this.codeUrl = 'data:image/gif;base64,' + res.data.img;
        this.loginForm.uuid = res.data.uuid;
      });
    },
    checkCapslock({ shiftKey, key } = {}) {
      if (key && key.length === 1) {
        if (shiftKey && (key >= 'a' && key <= 'z') || !shiftKey && (key >= 'A' && key <= 'Z')) {
          this.capsTooltip = true
        } else {
          this.capsTooltip = false
        }
      }
      if (key === 'CapsLock' && this.capsTooltip === true) {
        this.capsTooltip = false
      }
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    // click事件触发登录操作:
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 调用登录接口
          // 分发Action 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: this.redirect || '/' }) // 登录成功之后重定向到首页
          }).catch(() => {
            this.loading = false
            this.getCode();
            console.log('catch error submit!!') // 登录失败提示错误
          })
        } else {
          console.log('错误提交，请重新提交！') // 验证失败的提示信息
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$light_gray:#eee;

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      :-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}

</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: $bg;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

    .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

// 验证码
  .login-code {
  // width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.login-code-img {
  height: 38px;
}
}
</style>
