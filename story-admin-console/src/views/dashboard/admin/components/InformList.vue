<template>
  <div>
    <h3 class="title">系统公告</h3>
    <el-divider /> <!-- 横线 -->
    <div v-for="i in dataList" :key="i.id" style="width:100%;padding:10px 10px">
      <el-tag v-if="i.top" size="mini" type="danger" style="float:left;margin-right:10px">置顶</el-tag>
      <router-link :to="`inform/${i.id}`" class="titleComment">{{ i.title }}</router-link>
      <a style="float:right;cursor:default;">{{ formatDate(i.createDate) }}</a>
    </div>
    <!-- 分页组件 -->
    <div>
      <el-pagination
        :total.sync="total"
        :current-page.sync="page.num"
        :page-size="page.size"
        style="float:right;margin:10px 0"
        layout="prev, pager, next"
        small
        @size-change="query"
        @current-change="query"
      />
    </div>
  </div>
</template>
<script>
import { parseTime } from '@/utils'
import { queryInform } from '@/api/sysmgr/inform'
export default {
  data() {
    return {
      dataList: [],
      page: {
        num: 1,
        size: 5
      },
      total: 0,
      listQuery: {
        status: 1,
        topFirst: true
      }
    }
  },
  created() {
    this.query()
  },
  methods: {
    query() {
      queryInform(this.listQuery, this.page).then(r => {
        this.dataList = r.data.records
        this.total = r.data.total
      }).catch(e => {})
    },
    formatDate(d) { return parseTime(d, '{y}/{m}/{d}') }
  }
}
</script>

<style scoped>
.title{
  font: 18px 'Helvetica Neue', Helvetica, Arial, sans-serif;
  line-height: 1.4em;
  color: #4d4d4d;
}
.titleComment{
  /* 怎么把系统日志标题置左边 */
/* float: left; */
}
</style>
