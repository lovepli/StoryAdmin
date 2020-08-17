<template>
  <!-- Breadcrumb面包屑：显示当前页面的路径，快速返回之前的任意页面
    在el-breadcrumb中使用el-breadcrumb-item标签表示从首页开始的每一级。Element 提供了一个separator属性，在el-breadcrumb标签中设置它来决定分隔符，它只能是字符串，默认为斜杠/ -->
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <!-- 官方文档说不推荐同时使用 v-if 和 v-for -->
      <!-- 当它们处于同一节点，v-for 的优先级比 v-if 更高，这意味着 v-if 将分别重复运行于每个 v-for 循环中。当你只想为部分项渲染节点时，这种优先级的机制会十分有用 -->
      <!-- 下面的代码将只渲染item.meta.title部分的item level。 -->
      <el-breadcrumb-item v-for="(item,index) in levelList" v-if="item.meta.title" :key="item.path">
        <span
          v-if="item.redirect==='noredirect'||index==levelList.length-1"
          class="no-redirect"
        >{{ item.meta.title }}</span>
        <!-- 事件修饰符 @click.prevent阻止事件的默认行为，这里是阻止a标签跳转，仅执行函数handleLink()-->
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
import pathToRegexp from 'path-to-regexp';

export default {
  data() {
    return {
      levelList: null
    };
  },
  watch: {
    $route() {
      this.getBreadcrumb();
    }
  },
  // created 钩子可以用来在一个实例被创建之后执行代码
  // 此函数中，data和methods都已经初始化好了，如果需要调用methods中的方法或操作data中的值最早就在created函数中操作。
  created() {
    this.getBreadcrumb();
  },
  methods: {
    getBreadcrumb() {
      // filter() 替换数组的方法，不会变更原始数组，而总是返回一个新数组
      // 显示过滤/排序后的结果
      // 有时，我们想要显示一个数组经过过滤或排序后的版本，而不实际变更或重置原始数据。在这种情况下，可以创建一个计算属性，来返回过滤或排序后的数组
      let matched = this.$route.matched.filter(item => {
        if (item.name) {
          return true;
        }
      });
      const first = matched[0];
      if (first && first.name !== 'dashboard') {
        matched = [{ path: '/dashboard', meta: { title: '首页' }}].concat(
          matched
        );
      }
      this.levelList = matched;
    },
    pathCompile(path) {
      // To solve this problem https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route;
      var toPath = pathToRegexp.compile(path);
      return toPath(params);
    },
    handleLink(item) {
      const { redirect, path } = item;
      if (redirect) {
        this.$router.push(redirect);
        return;
      }
      this.$router.push(this.pathCompile(path));
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 13.5px;
  line-height: 45px;
  margin-left: 10px;
  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
