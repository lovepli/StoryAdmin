<template>
  <el-row>
    <el-col :span="6">
      <div class="app-container calendar-list-container">
        <div id="treeBox" class="filter-container">
          <el-table :data="treeList" style="width: 100%;" row-key="id" border @row-click="submitForm">
            <el-table-column prop="label" label="名称" sortable />
          </el-table>
        </div>
      </div>
    </el-col>
    <el-col :span="18">
      <div class="app-container calendar-list-container">
        <div class="filter-container">
          <el-input v-model="listQuery.userName" style="width: 200px;" class="filter-item" placeholder="请输入用户名" @keyup.enter.native="handleFilter" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
          <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
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
          <el-table-column width="150" align="center" label="姓名">
            <template slot-scope="scope">
              <span>{{ scope.row.userName }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" label="用户名">
            <template slot-scope="scope">
              <span>{{ scope.row.userName }}</span>
            </template>
          </el-table-column>
          <el-table-column width="120" align="center" label="联系电话">
            <template slot-scope="scope">
              <span>{{ scope.row.phonenumber }}</span>
            </template>
          </el-table-column>
          <el-table-column :label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="small" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
              <el-button size="small" type="text" icon="el-icon-delete" class="delete-text-btn" @click="handleDelete(scope.row)">删除</el-button>
              <el-button size="small" type="text" icon="el-icon-user" @click="toAssignRoles(scope.row)">设置角色</el-button>
              <el-button size="small" type="text" icon="el-icon-refresh" @click="handleModifyPassword(scope.row)">重置密码</el-button>
            </template>
          </el-table-column>
        </el-table>

        <div class="pagination-container">
          <el-pagination
            :current-page.sync="listQuery.page"
            :page-sizes="[10,20,30,50,100,300,500,1000]"
            :page-size="listQuery.limit"
            :total="total"
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </el-col>
  </el-row>

</template>

<script>
import { listUser, exportUser } from '@/api/ruoyi/user';
import { treeselect } from '@/api/ruoyi/dept';
import waves from '@/directive/waves' // 水波纹指令

export default {
  name: 'UserList',
  directives: {
    waves
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        0: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      treeList: [],
      tableKey: 0,
      list: null,
      total: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      downloadLoading: false
    }
  },
  created() {
    this.getTreeList()
    this.getList()
  },
  methods: {
    getTreeList() {
      this.listLoading = true
      treeselect().then((response) => {
        this.treeList = response.data
        this.listLoading = false
      })
    },
    submitForm(data) {
      console.log(data)
      console.log('部门ID:' + data.id)
      this.listQuery.deptId = data.id
      this.getList()
    },
    getList() {
      this.listLoading = false
      listUser(this.listQuery).then(response => {
        this.list = response.rows
        this.total = response.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    handleDownload() {
      this.downloadLoading = false
      exportUser(this.listQuery).then(response => {
        this.downloadLoading = true
        if (response.code === 20000) {
          import('@/vendor/Export2Excel').then(excel => {
            excel.export_byte_to_excel(response.data.bytes, response.data.title)
            this.downloadLoading = false
          })
        } else {
          this.$message.error(response.msg)
        }
      })
    },
  }
}
</script>
<style lang="scss">
#treeBox {
  margin-top: 1px;
  .el-table thead {
    display: none;
  }
}
</style>
