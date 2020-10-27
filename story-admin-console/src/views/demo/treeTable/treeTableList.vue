<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
    </div>

    <el-table :data="list" :load="load" style="width: 100%;" row-key="id" border lazy>
      <el-table-column label="机构名称" min-width="150px">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地理编码" min-width="150px">
        <template slot-scope="{row}">
          <span>{{ row.geocoding }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮政编码" min-width="150px">
        <template slot-scope="{row}">
          <span>{{ row.postalCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="small" type="text" icon="el-icon-edit" @click="handleUpdate(row)">编辑</el-button>
          <el-button v-if="row.status!='deleted'" size="small" type="text" icon="el-icon-delete" class="delete-text-btn" @click="handleDelete(row)"> 删除 </el-button>
        </template>
      </el-table-column>
    </el-table>

    <tree-table-form ref="form" @refreshList="getList" />

  </div>
</template>

<script>
import { drop, getList } from '@/api/demo/treeTable'
import waves from '@/directive/waves' // waves directive
import treeTableForm from './treeTableForm'

export default {
  name: 'TreeTable',
  components: { treeTableForm },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: [],
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.$refs.form.setList(this.list)
      })
    },

    load(tree, treeNode, resolve) {
      resolve(tree.children)
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
