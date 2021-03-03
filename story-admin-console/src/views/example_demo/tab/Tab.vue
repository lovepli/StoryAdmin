<template>
  <div>
    <p>el-tab组件会一次性将所有pane中的数据获取完。使用v-if来按需加载对应的pane,使用keep-alive做缓存。</p>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick">
      <el-tab-pane v-for="tab in tabOptions" :key="tab.name" :label="tab.label" :name="tab.name">
        <!-- vue内置组件keep-alive用法说明：  -->
        <!-- Props： 
             1、include - 字符串或正则表达式。只有名称匹配的组件会被缓存。
             2、exclude - 字符串或正则表达式。任何名称匹配的组件都不会被缓存。
             3、max - 数字。最多可以缓存多少组件实例。 -->
        <!-- 用法：
             1、<keep-alive> 包裹动态组件时，会缓存不活动的组件实例，而不是销毁它们。和 <transition> 相似，<keep-alive> 是一个抽象组件：它自身不会渲染一个 DOM 元素，也不会出现在组件的父组件链中。
             2、当组件在 <keep-alive> 内被切换，它的 activated 和 deactivated 这两个生命周期钩子函数将会被对应执行。
             3、在 2.2.0 及其更高版本中，activated 和 deactivated 将会在 <keep-alive> 树内的所有嵌套组件中触发 -->
        <!-- 解决实际用处： -->
        <!-- 1、当在这些组件之间切换的时候，你有时会想保持这些组件的状态，以避免反复重渲染导致的性能问题。例如我们来展开说一说这个多标签界面： -->
        <!-- 2、你会注意到，如果你选择了一篇文章，切换到 first 标签，然后再切换回 second，是不会继续展示你之前选择的文章的。这是因为你每次切换新标签的时候，Vue 都创建了一个新的 currentTabComponent 实例。
             3、重新创建动态组件的行为通常是非常有用的，但是在这个案例中，我们更希望那些标签的组件实例能够被在它们第一次被创建的时候缓存下来。为了解决这个问题，我们可以用一个 <keep-alive> 元素将其动态组件包裹起来。
             4、现在这个 second 标签保持了它的状态 (被选中的文章) 甚至当它未被渲染时也是如此 -->
        <!-- 注意这个 <keep-alive> 要求被切换到的组件都有自己的名字，不论是通过组件的 name 选项还是局部/全局注册。 -->
        <keep-alive>
          <tab-pane v-if="activeName === tab.name" :name="tab.name" />
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import TabPane from './components/TabPane'

export default {
  name: 'Tab',
  components: {
    TabPane
  },
  data() {
    return {
      activeName: 'first',
      tabOptions: [
        {
          label: '标签页一',
          name: 'first'
        },
        {
          label: '标签页二',
          name: 'second'
        },
        {
          label: '标签页三',
          name: 'third'
        },
        {
          label: '标签页四',
          name: 'fourth'
        }
      ]
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>
