<template>
  <div class="app-container">
    <el-row :gutter="6">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>字典分组</span>
          </div>
          <div class="item">
            <dict-group @refreshDicts="onRefreshDicts" />
          </div>
        </el-card>

      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>[{{ dictGroupName }}]字典值</span>
          </div>
          <div class="item">
            <dict ref="dict" />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import dictGroup from './components/dictGroup'
import dict from './components/dict'
import { fetchDicts } from '@/api/dict'

export default {
  name: 'wdict',
  components: { dictGroup, dict },
  data() {
    return {
      dictGroupName: '未选择'
    }
  },
  created() {
    // 查询所有用户名
    // debugger
    fetchDicts().then(res => {
      console.log(res.data.records);
    })
  },
  methods: {
    // 字典刷新
    onRefreshDicts: function(row) {
      this.dictGroupName = row.name
      this.$refs.dict.refreshGroupDict(row)
    }
  }
}
</script>
