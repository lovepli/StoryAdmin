<template>
  <div>
    <ul v-infinite-scroll="loadLog" :infinite-scroll-immediate="false" class="log-scroll" style="overflow:auto;height:570px;">
      <el-card v-for="(l,i) in logList" :key="i" style="margin:15px 0px">
        <p>{{ l.content }}</p> <!-- 描述信息  -->
        <!-- <p>{{ ' @ ' + dateFormat(l.loginTime) + ' from '+l.request_source_ip }}</p> -->  <!-- 登录ip地址  -->
        <p>{{ ' @ ' + dateFormat(l.loginTime) }}</p>
        <!-- 请求时间，登录IP地址 -->
      </el-card>
    </ul>
    <br>
    <p v-if="logLoading" style="text-align:center;">加载中...</p>
    <p v-if="!logLoading&&!noMoreLog" style="text-align:center;">下滑查看更多记录</p>
    <p v-if="noMoreLog" style="text-align:center;">没有更多了</p>
  </div>
</template>
<script>
import { queryLog } from '@/api/sysmgr/loginlog' // 这里的操作日志换成登录日志
import { parseTime } from '@/utils'
export default {
  name: 'OperationLog',
  data() {
    return {
      logList: [],
      logTotal: 0,
      logPage: {
        num: 0,
        limit: 4
      },
      logLoading: false,
      noMoreLog: false
    }
  },
  created() {
    this.loadLog()
  },
  methods: {
    loadLog() {
      if (this.noMoreLog || this.logLoading) {
        return
      }
      this.logLoading = true
      this.logPage.num++
      // 根据账户名查询操作日志
      queryLog({ userAccount: 'admin' }, this.logPage)
        .then(r => {
          this.logTotal = r.data.total
          this.logList = this.logList.concat(r.data.records)
          if (this.logTotal === this.logList.length) this.noMoreLog = true
        }).catch(e => {}).finally(() => { this.logLoading = false })
    },
    dateFormat(d) { return parseTime(d) }
  }
}
</script>
