<template>
  <div>
    <!-- 搜索框 -->
    <div class="filter-container">
      <el-input v-model="listQuery.title" class="filter-item" placeholder="标题" />
      <el-select v-model="listQuery.creatorId" class="filter-item" placeholder="发布人" clearable @change="query">
        <el-option v-for="u in allUsers" :key="u.id" :value="u.id" :label="u.name" />
      </el-select>
      <el-select v-model="listQuery.status" class="filter-item" placeholder="状态" clearable @change="query">
        <el-option v-for="(s,i) in statusLabel" :key="i" :value="i" :label="s" />
      </el-select>
      <date-between :value="listQuery.createDate" class="filter-item" name="发布日期" @keypress.native.enter="query" @change="val=>{listQuery.createDate = val;query()}" />
      <el-button :loading="listLoading" class="filter-item" type="primary" icon="el-icon-search" @click="query">
        搜索
      </el-button>
    </div>
    <!-- 表格 -->
    <el-table v-loading="listLoading" :data="dataList" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" label="序号" align="center" width="60" />
      <el-table-column :formatter="r=>r.title" label="标题" align="center" min-width="360" />
      <el-table-column :formatter="r=>parseDate(r.createDate)" label="发布时间" align="center" min-width="120" />
      <el-table-column :formatter="r=>getUserName(r.creatorId)" label="发布人" align="center" min-width="120" />
      <el-table-column label="状态" align="center">
        <template slot-scope="{row}">
          <el-tag :type="statusType[row.status]" size="mini">
            {{ statusLabel[row.status] }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="置顶" align="center" width="100">
        <template slot-scope="{row}">
          <el-tag v-if="row.top" size="mini" type="danger">TOP</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" width="360" label="操作">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="showInfo(row.id)">查看详情</el-button>
          <el-button v-if="row.status===1&&!row.top" size="mini" type="danger" icon="el-icon-top" @click="top(row.id)">置顶</el-button>
          <el-button v-if="row.status===1&&row.top" size="mini" type="" icon="el-icon-bottom" @click="untop(row.id)">取消置顶</el-button>
          <el-button v-if="row.status===1" size="mini" type="info" @click="cancel(row.id,row.title)">撤销</el-button>
          <el-button v-if="row.status===1" size="mini" type="warning" @click="outdate(row.id,row.title)">过期</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页标签 -->
    <nav style="text-align: center; padding-top: 30px">
      <el-pagination
        :page-sizes="[10,20,30,50,100,300,500,1000]"
        :total="total"
        :current-page.sync="page.num"
        :page-size.sync="page.limit"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="query"
        @current-change="query"
      />
    </nav>

  </div>
</template>
<script>
import DateBetween from '@/components/DateBetween' // 引入自定义日期组件
import { queryInform, topInform, untopInform, cancelInform, outdateInform } from '@/api/sysmgr/inform'
import { parseTime } from '@/utils'
import { findAllUserList } from '@/api/sysmgr/user'

export default {
  name: 'InformTable',
  components: { DateBetween },
  data() {
    return {
      listQuery: {
        title: '',
        createDate: [],
        status: undefined,
        createrId: undefined
      },

      // 分页
      page: {
        num: 1,
        size: 10
      },
      total: 0,
      dataList: null,
      listLoading: false, // 表格加载
      allUsers: [],
      statusLabel: ['已撤销', '有效中', '已过期'],
      statusType: ['info', 'success', 'warning'],
      redirect: undefined // 重定向
    }
  },
  // 侦听属性
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    // 查询所有用户名
    // debugger
    findAllUserList()
      .then(res => {
        console.log(res.data.records);
        this.allUsers = res.data
      })
    // 查询列表
    this.query()
  },
  methods: {
    query() {
      this.listLoading = true
      queryInform(this.listQuery, this.page).then(r => {
        this.dataList = r.data.records;
        this.total = r.data.total
      }).catch(e => {}).finally(() => { this.listLoading = false })
    },
    // 日期格式化
    parseDate(t) { return parseTime(t) },
    // 置顶
    top(id) {
      topInform(id).then(r => {
        this.$message.success('操作成功')
        this.query()
      }).catch(e => {})
    },
    // 取消置顶
    untop(id) {
      untopInform(id).then(r => {
        this.$message.success('操作成功')
        this.query()
      }).catch(e => {})
    },
    // 撤销
    cancel(id, title) {
      this.$confirm(`公告撤销后不可恢复，确定撤销《${title}》？`, '操作提示').then(r => {
        cancelInform(id).then(r => {
          this.$message.success('操作成功')
          this.query()
        }).catch(e => {})
      }).catch(e => {})
    },
    // 过期
    outdate(id, title) {
      this.$confirm(`公告过期后不可恢复，确定要让《${title}》过期？`, '操作提示').then(r => {
        outdateInform(id).then(r => {
          this.$message.success('操作成功')
          this.query()
        }).catch(e => {})
      }).catch(e => {})
    },
    // 根据ID查询用户名
    getUserName(id) {
      const user = this.$getById(this.allUsers, id) || {}
      return user.name || '未知'
    },
    // 进入公告详情页面
    showInfo(id) {
      this.redirect = `/inform/${id}`
      //  this.$router.push({ path: `/inform/${id}` })
      this.$router.push({ path: this.redirect || '/inform' }) // 登录成功之后重定向到首页
    }
  }
}
</script>
