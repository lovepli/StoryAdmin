<template>
  <div id="dictComponent">
    <div>
      <div class="filter-container">
        <el-input v-model="listQuery.keyword" style="width: 200px;" class="filter-item" placeholder="请输入字典标签或值" @keyup.enter.native="handleFilter" />
        <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
        <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
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
          prop="label"
          label="字典标签"
          width="160"
        />
        <el-table-column
          prop="value"
          label="字典值"
          width="160"
        />
        <el-table-column :label="响应" align="center" width="180" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="small" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button size="small" type="text" icon="el-icon-delete" class="delete-text-btn" @click="handleDelete(scope.row)">删除 </el-button>
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

      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
        <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
          <el-form-item label="字典标签" prop="label">
            <el-input v-model="temp.label" />
          </el-form-item>
          <el-form-item label="字典值" prop="value">
            <el-input v-model="temp.value" />
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="temp.sort" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="temp.remarks" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="Please input" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确认</el-button>
          <el-button v-else type="primary" @click="updateData">确认</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { getList, save, drop } from '@/api/dict'
import waves from '@/directive/waves' // 水波纹指令

export default {
  name: 'SysDictComponent',
  directives: {
    waves
  },
  data() {
    return {
      dictGroup: {},
      tableKey: 0,
      list: null,
      total: null,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined,
        title: undefined,
        type: undefined
      },
      showReviewer: false,
      // 临时对象
      temp: {
        id: undefined,
        label: '',
        value: '',
        sort: 1,
        remarks: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑字典',
        create: '添加字典'
      },
      rules: {
        label: [{ required: true, message: '字典标签不能为空', trigger: 'change' }],
        value: [{ required: true, message: '字典值不能为空', trigger: 'change' }],
        sort: [{ required: true, message: '排序不能为空', trigger: 'change' }]
      },
      downloadLoading: false
    }
  },
  created() {
    // 查询列表
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(res => {
        this.list = res.data.records
        this.total = res.data.total
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
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        label: '',
        value: '',
        sort: 1,
        remarks: '',
        gid: this.dictGroup.id
      }
    },
    // 刷新字典
    refreshGroupDict(row) {
      this.listQuery.gid = row.id
      this.listQuery.page = 1
      this.listQuery.keyword = ''
      this.dictGroup = row
      this.getList()
    },
    handleCreate() {
      if (this.dictGroup.id === undefined || this.dictGroup.id === '') {
        this.$message.error('请选择字典分组')
      } else {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      }
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          // 添加 保存
          save(this.temp).then(res => {
            this.dialogFormVisible = false;
            this.getList();
          });
        } else {
          return false;
        }
      });
    },

    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          // copy obj
          const tempData = Object.assign({}, this.temp)
          // 保存
          save(tempData).then(res => {
            this.dialogFormVisible = false;
            this.getList();
          });
        } else {
          return false;
        }
      });
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },

    handleDelete(row) {
      this.$confirm('确定删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        drop(row.id).then(res => {
          this.dialogFormVisible = false;
          this.getList();
        })
      })
    }
  }
}
</script>
