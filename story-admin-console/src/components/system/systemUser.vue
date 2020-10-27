<template>
  <div>
    <el-input v-model="name" :style="{width: width}" readonly @focus="show">
      <el-button slot="append" icon="el-icon-search" @click="show" />
    </el-input>
    <el-dialog :visible.sync="dialogFormVisible" title="选择用户" destroy-on-close append-to-body>
      <div class="app-container calendar-list-container">
        <div class="filter-container">
          <el-input v-model="listQuery.account" style="width: 200px;" class="filter-item" placeholder="请输入账号" @keyup.enter.native="handleFilter" />
          <el-input v-model="listQuery.name" style="width: 200px;" class="filter-item" placeholder="请输入用户名" @keyup.enter.native="handleFilter" />
          <el-input v-model="listQuery.email" style="width: 200px;" class="filter-item" placeholder="请输入邮箱" @keyup.enter.native="handleFilter" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
        </div>

        <el-table
          v-loading="listLoading"
          :key="tableKey"
          :data="list"
          element-loading-text="给我一点时间"
          border
          fit
          highlight-current-row
          style="width: 100%"
        >
          <el-table-column
            width="50"
            align="center"
            label="选择">
            <template slot-scope="scope">
              <el-radio v-model="selectCurentUserId" :label="scope.row.id" @change="selectUser(scope.row)"><i /></el-radio>
            </template>
          </el-table-column>
          <el-table-column min-width="120" align="center" label="账号">
            <template slot-scope="scope">
              <span>{{ scope.row.account }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="120" align="center" label="用户名">
            <template slot-scope="scope">
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column min-width="120" align="center" label="邮箱">
            <template slot-scope="scope">
              <span>{{ scope.row.email }}</span>
            </template>
          </el-table-column>
          <!-- <el-table-column min-width="120" align="center" label="部门">
            <template slot-scope="scope">
              <span>{{ scope.row.organization.name }}</span>
            </template>
          </el-table-column> -->
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" :page-sizes="[10,20,30,40,50]" @pagination="getList" />
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="select">
          确认
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getList, findById } from '@/api/sysmgr/user'
import { objectMerge } from '@/utils/index'
import waves from '@/directive/waves' // 水波纹指令
import Pagination from '@/components/Pagination'

export default {
  name: 'SystemUser',
  components: { Pagination },
  directives: {
    waves
  },
  props: {
    value: {
      type: String,
      default: undefined
    },
    width: {
      type: String,
      default: '100%'
    },
    query: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: null,
      listLoading: true,
      name: undefined,
      listQuery: {
        page: 1,
        limit: 10
      },
      dialogFormVisible: false,
      selectCurentUserId: undefined
    }
  },
  watch: {
    value: {
      immediate: true,
      handler(val) {
        this.getUserInfo()
      }
    }
  },
  created() {
    this.name = undefined
    this.listQuery = objectMerge(this.listQuery, this.query)
    console.log('listQuery', this.listQuery)
    this.getList()
  },
  methods: {
    show() {
      this.dialogFormVisible = true
    },
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    selectUser(row) {
      this.selectCurentUserId = row.id
      this.name = row.account + '(' + row.name + ')'
    },
    select() {
      if (this.selectCurentUserId === undefined) {
        this.$message.warning('请选择一个用户')
        return
      }
      this.$emit('input', this.selectCurentUserId)
      this.dialogFormVisible = false
    },
    getUserInfo() {
      this.name = undefined
      if (this.value === undefined) {
        return
      }
      findById(this.value).then(response => {
        this.name = response.data.account + '(' + response.data.name + ')'
      })
    }
  }

}
</script>

