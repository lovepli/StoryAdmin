<template>
  <div id="app">
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <!-- 这里的 <router-view> 是最顶层的出口，渲染最高级路由匹配到的组件。 -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
