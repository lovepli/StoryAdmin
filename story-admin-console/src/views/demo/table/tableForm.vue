<template>
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" destroy-on-close>
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
      <el-form-item label="类型" prop="type">
        <el-select v-model="temp.type" class="filter-item" placeholder="Please select">
          <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间" prop="publishDate">
        <el-date-picker v-model="temp.publishDate" type="datetime" value-format="yyyy-MM-dd HH:mm" format="yyyy-MM-dd HH:mm" />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input v-model="temp.title" />
      </el-form-item>
      <!--组件 -->
      <el-form-item label="作者" prop="author">
        <system-user v-model="temp.author" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="temp.status" class="filter-item" placeholder="Please select">
          <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="重要程度">
        <el-rate v-model="temp.level" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max="3" style="margin-top:8px;" />
      </el-form-item>
      <el-form-item label="内容">
        <Tinymce ref="editor" v-model="temp.content" type="textarea" placeholder="Please input" />

      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">
        取消
      </el-button>
      <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { save, findById } from '@/api/demo/table'
// import { isIntegerGEZRule } from '@/utils/validate'
import Tinymce from '@/components/Tinymce'
import SystemUser from '@/components/system/systemUser'

const calendarTypeOptions = [
  { key: 'CN', display_name: 'China' },
  { key: 'US', display_name: 'USA' },
  { key: 'JP', display_name: 'Japan' },
  { key: 'EU', display_name: 'Eurozone' }
]

export default {
  name: 'TableForm',
  components: { Tinymce, SystemUser },
  data() {
    return {
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }]
        // title: [{ required: true, message: 'title is required', trigger: 'blur' }, { validator: isIntegerGEZRule, tigger: 'blur' }]
      },
      statusOptions: ['published', 'draft', 'deleted'],
      temp: {
        id: undefined,
        level: 1,
        remark: '',
        publishDate: undefined,
        content: undefined,
        title: '',
        type: undefined,
        status: 'published',
        author: ''
      },
      calendarTypeOptions,
      textMap: {
        update: '编辑',
        create: '新建'
      },
      dialogFormVisible: false,
      dialogStatus: ''
    }
  },
  methods: {
    getList() {
      this.$emit('refreshList')
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        level: 1,
        remark: '',
        publishDate: undefined,
        content: undefined,
        title: '',
        type: 'china',
        status: 'published'
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          save(this.temp).then(response => {
            this.getList()
            this.dialogFormVisible = false
          })
        }
      })
    },
    handleUpdate(id) {
      this.resetTemp()
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
      const params = {};
      params.id = id;
      findById(params).then(response => {
        if (response.code === 20000) {
          this.temp = response.data
        } else {
          this.dialogFormVisible = false
          this.$message.error(response.message)
        }
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          save(tempData).then(() => {
            this.getList()
            this.dialogFormVisible = false
          })
        }
      })
    }
  }
}
</script>

