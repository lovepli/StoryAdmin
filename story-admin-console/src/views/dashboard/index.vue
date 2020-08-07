<template>
  <!-- 首页显示登录用户名 -->
  <div class="dashboard-container">
    <div class="dashboard-text">{{ name }}，你好！</div>
    <div class="dashboard-text">欢迎登录 STORY-ADMIN </div>
    <!-- 动态组件 -->
    <!-- v-bind:is="currentRole"
    我们之前曾经在一个多标签的界面中使用 is attribute 来切换不同的组件-->
    <component :is="currentRole"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex' // mapGetters 辅助函数仅仅是将 store 中的 getter 映射到局部计算属性：
import adminDashboard from './admin' // 引入子组件

export default {
  name: 'Dashboard',
  components: { adminDashboard }, // 注册子组件
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  // 这里都计算属性是调用了一个方法
  // 官方说明文档：计算属性是基于它们的响应式依赖进行缓存的。只在相关响应式依赖发生改变时它们才会重新求值。这就意味着只要 message 还没有发生改变，多次访问 getBreadcrumb 计算属性会立即返回之前的计算结果，而不必再次执行函数。
  computed: { // 动态计算属性，相当于this.$store.getters.name, this.$store.getters.roles
  // 使用对象展开运算符 ...mapGetters 将 getter 混入 computed 对象中
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
    text-align: center;
  }
  &-text {
    font-size: 30px;
    line-height: 86px;
  }
}
</style>
