<template>
  <div class="dataGrid">
    <!-- 搜索框组件 -->
    <!-- 相比较于v-if,另一个用于根据条件展示元素的选项是 v-show 指令，不同的是带有 v-show 的元素始终会被渲染并保留在 DOM 中。v-show 只是简单地切换元素的 CSS property display -->
    <!-- v-if 是“真正”的条件渲染，因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建。
         v-if 也是惰性的：如果在初始渲染时条件为假，则什么也不做——直到条件第一次变为真时，才会开始渲染条件块。
         相比之下，v-show 就简单得多——不管初始条件是什么，元素总是会被渲染，并且只是简单地基于 CSS 进行切换。
        一般来说，v-if 有更高的切换开销，而 v-show 有更高的初始渲染开销。因此，如果需要非常频繁地切换，则使用 v-show 较好；如果在运行时条件很少改变，则使用 v-if 较好。 -->
    <div v-show="searchHandlerVisible" class="search-handler">
      <!-- 列表组件  -->
      <el-form :inline="true" class="demo-form-inline">
        <!-- Slot 通俗的理解就是“占坑”，在组件模板中占好了位置，当使用该组件标签时候，组件标签里面的内容就会自动填坑（替换组件模板中slot位置）
       并且可以作为承载分发内容的出口 -->
        <!-- 自定义slot具名插槽，我们希望把form表格组件放在这里 -->
        <slot name="form"/>
        <!-- @click事件处理，v-on还可以接收一个需要调用的方法名称
         这里的@click="onSubmit"的全称是v-on:click="onSubmit" onSubmit是下面methods中定义的方法名-->
        <el-form-item>
          <!-- v-waves 自定义点击波浪特效指令 -->
          <el-button
            v-waves
            type="primary"
            class="dataGrid-form-submit"
            size="small"
            icon="el-icon-search"
            @click="onSubmit"
          >查询</el-button>
          <el-button
            v-waves
            type="default"
            class="dataGrid-form-default reset"
            size="small"
            icon="el-icon-refresh"
            @click="onReset"
          >重置</el-button>
          <!-- 自定义slot具名插槽，我们希望把extendOperation组件放在这里，extendOperation组件作为扩展功能组件 -->
          <!-- Slot 通俗的理解就是“占坑”，在组件模板中占好了位置，当使用该组件标签时候，组件标签里面的内容就会自动填坑（替换组件模板中slot位置）
       并且可以作为承载分发内容的出口 -->
          <slot name="extendOperation"/>
        </el-form-item>
      </el-form>
    </div>
    <!-- 表格组件
    v-loading="listLoading"  是否显示表格正在加载Loading
     -->
    <el-table
      v-loading="listLoading"
      :data="list"
      :stripe="true"
      element-loading-text="拼命加载中..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      border
      fit
      highlight-current-row
      @selection-change="handleSelectionChange"
    >
      <!-- 自定义slot具名插槽，我们希望把body表格体组件放在这里 -->
      <slot name="body"/>
    </el-table>
    <!-- 分页组件 -->
    <!--:page.sync 是v-on:update:page的简写 是对应自定义组件pagination中触发事件this.$emit('update:page')事件对监听  -->
    <!--:limit.sync 是v-on:update:limit的简写 是对应自定义组件pagination中触发事件this.$emit('update:limit')事件对监听  -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryedData.pageNo"
      :limit.sync="queryedData.limit"
      @pagination="fetchData"
    />
  </div>
</template>

<script>
import { getRequest } from '@/api/axiosCommom';
import Pagination from '@/components/Pagination'; // 引入分页子组件
import waves from '@/directive/waves';
export default {
  name: 'DataGrid', // 自定义封装的数据表格组件，作为其他组件的子组件
  components: { Pagination }, // 局部注册分页组件，在这之前你必须import导入该局部组件
  directives: { waves }, // 引入自定义的全局指令v-waves(Vue 点击波浪特效指令)
  // Prop 是你可以在组件上注册的一些自定义 attribute。当一个值传递给一个 prop attribute 的时候，它就变成了那个组件实例的一个 property。
  // 单向数据流：所有的 prop 都使得其父子 prop 之间形成了一个单向下行绑定：父级 prop 的更新会向下流动到子组件中，但是反过来则不行。
  // 这样会防止从子组件意外变更父级组件的状态，从而导致你的应用的数据流向难以理解。
  // 额外的，每次父级组件发生变更时，子组件中所有的 prop 都将会刷新为最新的值。这意味着你不应该在一个子组件内部改变 prop。如果你这样做了，Vue 会在浏览器的控制台中发出警告
  props: { // 获取到<data-grid></data-grid> 组件内url和dataName和searchHandlerVisibleSet的值，即得到父组件中传过来的值
    url: {}, // 后端访问列表的api的url地址
    dataName: {}, // 查询参数对象 在<data-grid></data-grid>组件中对应data-name下划线命名方式
    searchHandlerVisibleSet: {}, // 搜索框参数，是否显示列表搜索框，<data-grid></data-grid> 组件没有这个参数，即不传递这个值，则默认有搜索框
    // eslint-disable-next-line vue/require-prop-types
    isInitLoad: {// 是否初始化加载
      default: true
    },
    // 系统日志有用到
    filterStatus: { // 查询条件验证通过状态，在<data-grid></data-grid>组件中对应filter-status下划线命名方式
      default: true,
      type: Boolean
    }
  },
  data() {
    return {
      total: 0, // 总数目
      list: null, // 表格数据
      listLoading: false, // 在数据获取期间展示一个表格loading加载状态，还可以在不同视图间展示不同的loading状态
      // 分页查询参数
      queryedData: {
        pageNo: 1, // 第一页
        limit: 10 // 每页显示的数量
      },
      searchHandlerVisible: true // 是否显示搜索框
    };
  },
  // 侦听属性
  watch: {},
  // created 钩子可以用来在一个实例被创建之后执行代码
  // 此函数中，data和methods都已经初始化好了，如果需要调用methods中的方法或操作data中的值最早就在created函数中操作。
  created: function() {
  // 因为进入列表页面就需要在列表中显示出数据，那么就需要实现加载页面时自动加载页面方法（包括根据条件是否显示搜索框和初始化查询列表数据的方法）
  // 而之前我们已经知道了，Vue的声明周期中，最早可以操作methods和data中的数据的阶段是：created生命周期函数阶段。
  // 那么在这里调用onSubmit方法即可
  // console.log("created=>",this.isInitLoad); // `this` 指向 vm 实例
    var that = this;
    if (that.searchHandlerVisibleSet === 'false') {
      that.searchHandlerVisible = false;
    }
    // 是否进行初始化加载
    if (that.isInitLoad) {
      this.onSubmit();
    }
  },
  methods: {
    onReset: function() { // 点击重置搜索内容
      var that = this;
      // 触发一个dataRest名字的事件，触发的事件名dataRest需要完全匹配监听这个事件所用的名称，监听这个名字的事件是@dataRest
      // 触发事件that.$emit('dataRest'); 对应的监听事件@dataRest="onDataRest"
      // 现在，你已经知道了 $emit 的用法，它可以被 v-on 侦听
      that.$emit('dataRest', undefined);
      return false;
    },
    // 初始化查询列表数据
    onSubmit() {
      const that = this;// this指的是当前当前vue实例，即vue组件对象
      this.queryedData.pageNo = 1;
      this.$emit('onDataValid', undefined);
      setTimeout(function() { // 异步操作，设置超时时间，查询超时时间
        if (that.filterStatus) {
          // $parent访问父级组件实例，$root访问根实例
          // 和 $root 类似，$parent property 可以用来从一个子组件访问父组件的实例。
          // 它提供了一种机会，可以在后期随时触达父级组件，以替代将数据以 prop 的方式传入子组件的方式。
          that.$parent[that.dataName].pageNo = 1;
          // 获取数据，此时data已经被 observed注意到，观察到 了
          that.fetchData();
        }
      }, 100);
    },
    // 多选框选中数据
    handleSelectionChange(rows) {
      this.$emit('handleSelection', rows);
    },
    // 请求成功，需要重新刷新列表（不然新数据不能及时的更新到页面上）
    fetchData() {
      var that = this;
      that.listLoading = true; // 显示loading加载
      //        console.log('parent=>',that.$parent[that.dataName].pageNo,that.$parent[that.dataName].limit)
      //        console.log('query=>', this.queryedData.pageNo,this.queryedData.limit)
      setTimeout(() => { // 异步操作，设置超时时间，查询超时时间
        that.$parent[that.dataName].pageNo = parseInt(this.queryedData.pageNo);
        //          that.$parent[that.dataName].limit = that.$parent[that.dataName].limit ? parseInt(that.$parent[that.dataName].limit) : parseInt(this.queryedData.limit);
        that.$parent[that.dataName].limit = parseInt(this.queryedData.limit);
        getRequest(that.url, that.$parent[that.dataName]).then(response => {
          this.total = response.data.total;
          this.list = response.data.records;
          //            console.log("fetchData list length=>",this.list.length);
          //            console.log('parent=>',that.$parent[that.dataName].pageNo,that.$parent[that.dataName].limit)
          //            console.log('query=>', this.queryedData.pageNo,this.queryedData.limit)
          this.listLoading = false; // 不显示加载
        });
      }, 100);
      /* console.log('parent=>',that.$parent[that.dataName].pageNo,that.$parent[that.dataName].limit)
        console.log('query=>', this.queryedData.pageNo,this.queryedData.limit)
        that.$parent[that.dataName].pageNo = parseInt(this.queryedData.pageNo);
        that.$parent[that.dataName].limit = parseInt(this.queryedData.limit)/!* ? parseInt(that.$parent[that.dataName].limit) : this.queryedData.limit*!/;
        getList(that.$parent[that.dataName]).then(response => {
          this.total = response.data.total
          this.list = response.data.records
          console.log("fetchData list length=>",this.list.length);
          console.log('parent=>',that.$parent[that.dataName].pageNo,that.$parent[that.dataName].limit)
          console.log('query=>', this.queryedData.pageNo,this.queryedData.limit)
          this.listLoading = false
        })*/
    }
  }
};
</script>

