<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card>
            <!-- <personal-info /> -->
            <user-card :user="user" />
          </el-card>
        </el-col>

        <el-col :span="16">
          <el-card>
            <el-tabs v-model="activeTab" style="height: auto" @tab-click="handleClick">
              <el-tab-pane label="操作日志" name="operationlog">
                <!-- 这里用登录日志代替 -->
                <operation-log ref="operationLog" />
              </el-tab-pane>
              <el-tab-pane label="账户信息" name="accountinfo">
                <account-info />
              </el-tab-pane>
              <el-tab-pane label="Activity" name="activity">
                <activity />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import UserCard from './components/UserCard';
import OperationLog from './components/OperationLog'; // 操作日志
import AccountInfo from './components/AccountInfo'; // 账户信息
import Activity from './components/Activity';
//  import PersonalInfo from './components/PersonalInfo' // 个人信息
import defaultAvatar from '@/assets/img/avatar.png';
export default {
  name: 'PersonalCenter',
  components: { UserCard, OperationLog, AccountInfo, Activity },
  data() {
    return {
      user: {},
      activeTab: 'activity'
    };
  },
  computed: {
    ...mapGetters(['name', 'avatar', 'roles'])
  },
  created() {
    this.getUser();
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab.name)
      if (tab.name === 'operationlog') {
        this.$refs.operationLog.loadLog()
      // eslint-disable-next-line no-empty
      } else if (tab.name === 'accountinfo') {
      // eslint-disable-next-line no-empty
      } else if (tab.name === 'activity') {

      }
    },
    getUser() {
      this.user = {
        name: this.name,
        roles: this.roles,
        email: '1171205514@qq.com',
        avatar: this.avatar === undefined ? defaultAvatar : this.avatar
      };
    }
  }
};
</script>
<style scoped>
.log-scroll ::-webkit-scrollbar {
  /*隐藏滚轮*/
  display: none;
}
</style>
