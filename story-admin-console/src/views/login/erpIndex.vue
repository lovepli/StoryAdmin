<template>
  <div class="login-container"/>
</template>
<script>
export default {
  name: 'Login',
  data() {
    return {};
  },
  // 只要执行完了mounted，表示整个Vue实例已经初始化完毕了，此时组件已经进入了运行阶段。
  mounted() {
    //路由对象属性 $route.query:一个 key/value 对象，表示 URL 查询参数。例如，对于路径 /foo?user=1，则有 $route.query.user == 1，如果没有查询参数，则是个空对象
    var ticket = this.$route.query.sso_service_ticket;
    // 分发Action 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
    this.$store
      .dispatch('LoginERP', ticket)
      .then(() => {
        // 通过注入路由器，我们可以在任何组件内通过 this.$router 访问路由器，也可以通过 this.$route 访问当前路由
        // 留意一下 this.$router 和 router 使用起来完全一样。我们使用 this.$router 的原因是我们并不想在每个独立需要封装路由的组件中都导入路由。
        // 给出一个路由
        this.$router.push({ path: '/' }); // 参数为对象
      })
      .catch(err => { // catch 类似于springboot中的 try catch 抛出异常
        console.log(err);
        // 给出一个路由
        // 在 Vue 实例内部，你可以通过 $router 访问路由实例。因此你可以调用 this.$router.push
        // 想要导航到不同的 URL，则使用 router.push 方法。这个方法会向 history 栈添加一个新的记录，所以，当用户点击浏览器后退按钮时，则回到之前的 URL
        this.$router.push({ path: '/login' });
      });
  }
};
</script>
