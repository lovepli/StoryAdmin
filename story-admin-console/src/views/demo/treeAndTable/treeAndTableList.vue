<template>
  <el-row>
    <el-col :span="6">
      <div class="app-container calendar-list-container">
        <div id="treeBox" class="filter-container">
          <el-table :data="treeList" :load="load" style="width: 100%;" row-key="id" border lazy @row-click="getListByTreeId">
            <el-table-column prop="name" label="名称" sortable />
          </el-table>
        </div>
      </div>
    </el-col>
    <el-col :span="18">
      <div class="app-container">
        <div class="filter-container">
          <div class="filter-item">
            <span>部门名称:</span>
            <el-input v-model="listQuery.name" placeholder="请输入部门名称" style="width: 200px" />
          </div>
          <div class="filter-item">
            <span>类型:</span>
            <el-input v-model="listQuery.type" placeholder="请输入类型" style="width: 200px" />
          </div>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
            查询
          </el-button>
          <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
            添加
          </el-button>
        </div>

        <el-table
          v-loading="listLoading"
          :key="tableKey"
          :data="list"
          border
          fit
          highlight-current-row
          style="width: 100%;">
          <el-table-column label="部门名称" min-width="150px">
            <template slot-scope="{row}">
              <span>{{ row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column label="类型" min-width="150px">
            <template slot-scope="{row}">
              <span>{{ row.type }}</span>
            </template>
          </el-table-column>
          <el-table-column label="标签" min-width="150px">
            <template slot-scope="{row}">
              <span>{{ row.tag }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
            <template slot-scope="{row}">
              <el-button size="small" type="text" icon="el-icon-edit" @click="handleUpdate(row)">
                编辑
              </el-button>
              <el-button v-if="row.status!='deleted'" size="small" type="text" icon="el-icon-delete" class="delete-text-btn" @click="handleDelete(row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" :page-sizes="[10,20,30,50,100,300,500,1000]" @pagination="getList" />

        <tree-and-table-form ref="form" @refreshList="getList" />
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { drop, getList } from '@/api/demo/treeAndTable'
import { getList as getTreeTableList } from '@/api/demo/treeTable' // 调用treeTable的接口
import waves from '@/directive/waves' // waves directive
import treeAndTableForm from './treeAndTableForm'
import Pagination from '@/components/Pagination'

export default {
  name: 'TreeAndTableList',
  components: { treeAndTableForm, Pagination },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      treeList: [],
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        name: undefined,
        type: undefined,
        page: 1,
        limit: 10
      }
    }
  },
  created() {
    this.getTreeList()
    this.getList()
  },
  methods: {
    getTreeList() {
      this.listLoading = true
      getTreeTableList(this.listQuery).then(response => {
        this.treeList = response.data.records
        this.listLoading = false
        this.$refs.form.setList(this.treeList)
      })
    },
    load(tree, treeNode, resolve) {
      resolve(tree.children)
    },
    getListByTreeId(data) {
      this.listQuery.areaId = data.id
      this.getList()
    },
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        // Just to simulate the time of the request
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },

    handleUpdate(row) {
      this.$refs.form.setList(this.list)
      this.$refs.form.handleUpdate(row.id)
    },
    handleDelete(row) {
      this.$confirm('确定删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const params = {};
        params.id = row.id;
        drop(params).then(response => {
          this.getList()
        })
      })
    },
    handleCreate() {
      this.$refs.form.setList(this.list)
      this.$refs.form.handleCreate()
    }
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
