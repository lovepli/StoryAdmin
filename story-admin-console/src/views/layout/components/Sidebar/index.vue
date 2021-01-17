<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <!-- element 滚动条 -->
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :show-timeout="200"
        :default-active="$route.path"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in permission_routers" :key="route.path" :item="route" :base-path="route.path"/>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex' // mapGetters 辅助函数仅仅是将 store 中的 getter 映射到局部计算属性：
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { SidebarItem, Logo }, // 局部注册组件SidebarItem和组件Logo
  // 动态计算属性，相当于this.$store.getters.sidebar, this.$store.getters.permission_routers
  computed: {
    // sidebar和 'permission_routers'
    // 使用对象展开运算符 ...mapGetters 将 getter 混入 computed 对象中
    ...mapGetters([
      'sidebar',
      'permission_routers'
    ]),
    // routes() {
    //   return this.$router.options.routes
    // },
    // 只有sidebarLogo属性改变的时候才调用这个方法
    showLogo() {
      // 通过 store.state 来获取状态对象
      // 由于 store 中的状态是响应式的，在组件中调用 store 中的状态简单到仅需要在计算属性中返回即可。触发变化也仅仅是在组件的 methods 中提交 mutation。
      // 由于 Vuex 的状态存储是响应式的，从 store 实例中读取状态最简单的方法就是在计算属性中返回某个状态
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
