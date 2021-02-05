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
    // 响应路由参数的变化
    //复用组件时，想对路由参数的变化作出响应的话，你可以简单地 watch (监测变化) $route 对象
    $route() {
        // 对路由变化作出响应...
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
      // 路由对象属性 $route.matched:一个数组，包含当前路由的所有嵌套路径片段的路由记录 。路由记录就是 routes 配置数组中的对象副本 (还有在 children 数组)。
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
      const { params } = this.$route; // 通过this.$route 访问当前路由！！
      var toPath = pathToRegexp.compile(path);
      return toPath(params);
    },
    handleLink(item) {
      const { redirect, path } = item;
      if (redirect) {                // 编程式的导航:在 Vue 实例内部，你可以通过 $router 访问路由实例。因此你可以调用 this.$router.push
        this.$router.push(redirect); // 通过this.$router 访问路由器！！
        return;
      }
      this.$router.push(this.pathCompile(path));
      // 编程式导航说明：
      // 想要导航到不同的 URL，则使用 router.push 方法。这个方法会向 history 栈添加一个新的记录，所以，当用户点击浏览器后退按钮时，则回到之前的 URL。
      // 当你点击 <router-link> 时，这个方法会在内部调用，所以说，点击 <router-link :to="..."> 等同于调用 router.push(...)。
      // 声明式导航 <router-link :to="..."> 
      // 编程式导航 router.push(...)
      // router.replace(location, onComplete?, onAbort?)编程导航的方法的参数可以是一个字符串路径，或者一个描述地址的对象：同样的规则也适用于 router-link 组件的 to 属性。
           // const userId = '123'
           // 1、字符串
           //router.push('home')
           // 2、对象
           //router.push({ path: 'home' })
           // 3、命名的路由 /user/123
           //router.push({ name: 'user', params: { userId }})
           //             或者这样写也是  /user/123
           //router.push({ path: `/user/${userId}` }) 
           // 4、带查询参数，变成 /register?plan=private
           //router.push({ path: 'register', query: { plan: 'private' }})

           // 注意： 如果目的地和当前路由相同，只有参数发生了改变 (比如从一个用户资料到另一个 /users/1 -> /users/2)，你需要使用 beforeRouteUpdate 来响应这个变化 (比如抓取用户信息)。

           //router.replace(location, onComplete?, onAbort?)
           //跟 router.push 很像，唯一的不同就是，它不会向 history 添加新记录，而是跟它的方法名一样 —— 替换掉当前的 history 记录。
           // 声明式 <router-link :to="..." replace>
           // 编程式 router.replace(...)

           // router.go(n)
           // 这个方法的参数是一个整数，意思是在 history 记录中向前或者后退多少步，类似 window.history.go(n)。
           // 在浏览器记录中前进一步，等同于 history.forward()
               //router.go(1)
               //后退一步记录，等同于 history.back()
               //router.go(-1)
               //前进 3 步记录
               //router.go(3)
               //
               //如果 history 记录不够用，那就默默地失败呗
               //router.go(-100)
               //router.go(100)
           //  补充一个知识点：
           //  强制刷新当前页面 使用this.$router.go(0);或者location.reload();
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
