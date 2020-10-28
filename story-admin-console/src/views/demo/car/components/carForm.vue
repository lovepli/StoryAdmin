<template>
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="汽车品牌" prop="name">
        <el-input v-model="temp.name" />
      </el-form-item>
      <el-form-item label="品牌编码" prop="code">
        <el-input v-model="temp.code" />
      </el-form-item>
      <el-form-item label="品牌备注">
        <el-input v-model="temp.remarks" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="Please input" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取消</el-button>
      <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确认</el-button>
      <el-button v-else type="primary" @click="updateData">确认</el-button>
    </div>
  </el-dialog>

</template>

<script>
import { save, findById } from '@/api/demo/car'

export default {
  name: 'CarForm',
  data() {
    return {
      temp: {
        id: undefined,
        name: '',
        code: '',
        remarks: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑品牌',
        create: '添加品牌'
      },
      rules: {
        name: [{ required: true, message: '品牌名称必填', trigger: 'change' }],
        code: [{ required: true, message: '品牌编码必填', trigger: 'blur' }]
      }

    }
  },
  methods: {
    getList() {
      this.$emit('getList')
    },

    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        code: '',
        remarks: ''
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
    }

  }

}
</script>

<style scoped>

</style>
