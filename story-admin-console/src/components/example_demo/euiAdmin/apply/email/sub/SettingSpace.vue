<template>
  <div>
    <p style="margin-top: 10px" @click="dialog_state = true">
      <i class="el-icon-setting" />&emsp;设置
    </p>

    <el-dialog :visible.sync="dialog_state" title="邮箱基础设置">
      <div>
        <el-form ref="form" :model="email_setting" label-width="120px">
          <el-form-item label="发件邮箱">
            <el-input
              v-model="email_setting.email_num"
              size="small"
              placeholder="请输入发件邮箱"
            />
          </el-form-item>
          <el-form-item label="发件邮箱授权码">
            <el-input
              v-model="email_setting.eamil_empower_code"
              size="small"
              show-password
              placeholder="请输入发件邮箱授权码"
            />
          </el-form-item>
          <el-form-item label="SMTP地址">
            <el-input
              v-model="email_setting.email_host"
              size="small"
              placeholder="请输入SMTP地址例如@qq.com"
            />
          </el-form-item>
          <el-form-item label="邮箱协议方式">
            <el-input
              v-model="email_setting.email_smtp_label"
              size="small"
              placeholder="请输入邮箱协议方式一般为ssl"
            />
          </el-form-item>
          <el-form-item label="邮箱发件端口">
            <el-input
              v-model="email_setting.email_port_code"
              size="small"
              placeholder="请输入邮箱发件端口，一般为465"
            />
          </el-form-item>
          <el-form-item label="发件人名称">
            <el-input
              v-model="email_setting.to_email_user_name"
              size="small"
              placeholder="请输入邮箱发件端口，一般为465"
            />
          </el-form-item>
          <el-form-item label="回复收件邮箱">
            <el-input
              v-model="email_setting.reply_email"
              size="small"
              placeholder="请输入回复收件邮箱"
            />
          </el-form-item>
        </el-form>
        <div align="center">
          <el-button type="warning" @click="dialog_state=false">取消</el-button>
          <el-button type="success" @click="save_email_setting()">保存</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import Cookies from 'js-cookie'

export default {
  data() {
    return {
      dialog_state: false,
      email_setting: {
        email_num: '',
        eamil_empower_code: '',
        email_host: '',
        email_smtp_label: '',
        email_port_code: '',
        to_email_user_name: '',
        reply_email: ''
      }
    };
  },
  mounted() {
    // if (this.$cookies.isKey('email_setting')) {
    //         this.email_setting=this.$cookies.get('email_setting')
    //     }

    this.email_setting = Cookies.get('email_setting')
  },
  methods: {
    save_email_setting() {
      // 这里使用的是vue-cookies依赖，项目中使用的是js-cookies依赖
      // this.$cookies.set('email_setting',this.email_setting)
      // if (this.$cookies.isKey('email_setting')) {
      //     this.$message.success('保存邮箱设置成功')
      //     setTimeout(()=>{
      //         this.dialog_state=false
      //     },1500)
      // } else {
      //     this.$message.success('保存邮箱设置失败')
      // }

      // 使用的是js-cookies 进行页面存取
      Cookies.set('email_setting', this.email_setting)
      // if (Cookies.isKey('email_setting')) {
      this.$message.success('保存邮箱设置成功')
      setTimeout(() => {
        this.dialog_state = false
      }, 1500)
      // } else {
      //     this.$message.success('保存邮箱设置失败')
      // }
    }
  }
};
</script>
