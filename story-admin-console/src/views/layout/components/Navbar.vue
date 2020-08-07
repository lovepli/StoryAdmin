<template>
  <!-- NavMenu element-ui导航菜单 -->
  <el-menu class="navbar" mode="horizontal">
    <hamburger :toggle-click="toggleSideBar" :is-active="sidebar.opened" class="hamburger-container"/>
    <breadcrumb />
    <div class="right-menu">
      <div class="user_name right-menu-item">{{ name }}</div>
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <!-- 使用 router-link 组件来导航. -->
          <!-- 通过传入 `to` 属性指定链接. -->
          <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
          <!-- 当你点击 <router-link> 时，这个方法会在内部调用，所以说，点击 <router-link :to="..."> 等同于调用 router.push(...) -->
          <router-link class="inlineBlock" to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>
          <router-link v-if="this.isErp" class="inlineBlock" to="/pwd" >
            <el-dropdown-item >
              修改密码
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout">注销</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </el-menu>
</template>

<script>
import { mapGetters } from 'vuex' // mapGetters 辅助函数仅仅是将 store 中的 getter 映射到局部计算属性：
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  // 官方说明文档：计算属性是基于它们的响应式依赖进行缓存的。只在相关响应式依赖发生改变时它们才会重新求值。这就意味着只要 erp 还没有发生改变，多次访问 isErp 计算属性会立即返回之前的计算结果，而不必再次执行函数。
  computed: { // 动态计算属性，相当于this.$store.getters.name, this.$store.getters.sidebar,
  // 使用对象展开运算符 ...mapGetters 将 getter 混入 computed 对象中
    ...mapGetters([
      'name',
      'sidebar',
      'avatar',
      'erp'
    ]),
    // 这里的计算属性是调用了一个方法，当这个erp值改变的时候，方法才调用
    isErp() {
      return this.erp == '0'
    }
  },
  methods: {
    toggleSideBar() {
      // 分发Action 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        this.$store.dispatch('FedLogOut').then(() => {
          if (this.erp == '1') {
            window.location = process.env.ERP_LOGOUT_HREF
          } else {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          }
        })
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 45px;
  line-height: 36px;
  border-radius: 0px !important;
  .hamburger-container {
    line-height: 50px;
    height: 45px;
    float: left;
    padding: 0 10px;
  }
  .right-menu {
    float: right;
    height: 100%;
    &:focus{
     outline: none;
    }
    .right-menu-item {
      display: inline-block;
      margin: 0 8px;
    }
    .user_name{
      cursor: pointer;
      vertical-align: 13px;
      font-size:13.5px;
    }
    .screenfull {
      position: absolute;
      right: 90px;
      top: 16px;
    }
    .avatar-container {
      height: 45px;
      margin-right: 30px;
      .avatar-wrapper {
        margin-top: 4px;
        position: relative;
        .user-avatar {
          cursor: pointer;
          width: 36px;
          height: 36px;
          border-radius: 10px;
        }
        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>

