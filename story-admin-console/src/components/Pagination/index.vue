<template>
  <!-- 外联样式 :class="{}" 这里的 :class="{'hidden':hidden}" 是直接使用自定义hidden对象，其中第一个hidden是自定义类名，需要在外部定义css样式，第二个hidden直接显示props中的hidden的值  -->
  <!-- 自定义hidden在下面的style中 .pagination-container.hidden { display: none;} -->
  <div :class="{'hidden':hidden}" class="pagination-container" style="float:right;">
    <!-- el-pagination分页组件
    background	   为分页按钮添加背景色
    page-size	     接受一个整型数组，数组元素为每页显示条目个数，支持 .sync 修饰符，[100, 200, 300, 400]表示四个选项，每页显示 100 个，200 个，300 个或者 400 个。
    current-page	 当前页数，支持 .sync 修饰符
    page-sizes	   每页显示个数选择器的选项设置
    layout	       设置layout，表示需要显示的内容，用逗号分隔，布局元素会依次显示。prev表示上一页，next为下一页，pager表示页码列表，除此以外还提供了jumper和total，size和特殊的布局符号->，->后的元素会靠右显示，jumper表示跳页元素，total表示总条目数，size用于设置每页显示的页码数量。
    total	         总条目数
    v-bind         声明式渲染
    size-change	   pageSize改变时会触发，回调参数为每页条数
    current-change currentPage改变时会触发，回调参数为当前页
    pager-count    可以设置最大页码按钮数，默认情况下，当总页数超过 7 页时，Pagination 会折叠多余的页码按钮，即默认为7
     -->
    <el-pagination
      :background="background"
      :current-page.sync="currentPage"
      :page-size.sync="pageSize"
      :layout="layout"
      :page-sizes="pageSizes"
      :total="total"
      v-bind="$attrs"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"/>
  </div>
</template>

<script>
import { scrollTo } from '@/utils/scrollTo'

export default {
  name: 'Pagination', // 分页组件
  props: {
    // 必填的数字
    total: {
      type: Number,
      required: true
    },
    // 带有默认值的数字
    page: {
      type: Number,
      default: 1
    },
    limit: {
      type: Number,
      default: 20
    },
    // 带有默认值的数组
    pageSizes: {
      type: Array,
      default() {
        return [10, 20, 30, 50]
      }
    },
    layout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    background: {
      type: Boolean,
      default: true
    },
    autoScroll: {
      type: Boolean,
      default: true
    },
    hidden: { // 是否显示/隐藏，样式
      type: Boolean,
      default: false
    }
  },
  computed: {
    currentPage: {
      get() {
        return this.page
      },
      set(val) {
        // 在有些情况下，我们可能需要对一个 prop 进行“双向绑定”。不幸的是，真正的双向绑定会带来维护上的问题，因为子组件可以变更父组件，且在父组件和子组件都没有明显的变更来源
        // 这也是为什么我们推荐以 update:myPropName 的模式触发事件取而代之。举个例子，在一个包含 title prop 的假设的组件中，我们可以用以下方法表达对其赋新值的意图
        // this.$emit('update:title', newTitle)
        this.$emit('update:page', val)
      }
    },
    pageSize: {
      get() {
        return this.limit
      },
      set(val) {
        this.$emit('update:limit', val)
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      this.$emit('pagination', { page: this.currentPage, limit: val })
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    },
    handleCurrentChange(val) {
      this.$emit('pagination', { page: val, limit: this.pageSize })
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    }
  }
}
</script>

<style scoped>
/* 自定义类名，下面都是自定义的外部css样式 */
.pagination-container {
  background: #fff;
  padding: 32px 16px;
}
.pagination-container.hidden {
  display: none;
}
</style>
