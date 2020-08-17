<template>
  <section class="app-main">
    <!-- transition过度动效 下面的是给所有路由设置一样的过渡效果-->
    <!-- <router-view> 是基本的动态组件，所以我们可以用 <transition> 组件给它添加一些过渡效果 -->
    <transition name="fade-transform" mode="out-in">
      <!-- 动态组件keep-alive -->
      <!-- 重新创建动态组件的行为通常是非常有用的，但是在这个案例中，我们更希望那些标签的组件实例能够被在它们第一次被创建的时候缓存下来。为了解决这个问题，我们可以用一个 <keep-alive> 元素将其动态组件包裹起来。 -->
      <!-- 失活的组件将会被缓存！-->
      <keep-alive :include="cachedViews">
        <!-- 路由出口 -->
        <!-- 路由匹配到的组件将渲染在这里 -->
        <router-view :key="key" @custom-tag="updateViewTag"/>
      </keep-alive>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  // 动态计算属性，缓存的数据发改变的时候才执行方法
  computed: {
    cachedViews() {
      // 通过 store.state 来获取状态对象
      // 由于 store 中的状态是响应式的，在组件中调用 store 中的状态简单到仅需要在计算属性中返回即可。触发变化也仅仅是在组件的 methods 中提交 mutation。
      // 由于 Vuex 的状态存储是响应式的，从 store 实例中读取状态最简单的方法就是在计算属性中返回某个状态
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  },
  methods: {
    /**
     * 自定义tag栏的标题
     */
    updateViewTag(customTag) {
      const visitedViews = this.$store.state.tagsView.visitedViews
      for (let i = 0; i < visitedViews.length; i++) {
        const view = visitedViews[i]
        if (view.path === this.$route.path) {
          view.title = customTag
          break
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header+.app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 84px);
  }

  .fixed-header+.app-main {
    padding-top: 84px;
  }
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
