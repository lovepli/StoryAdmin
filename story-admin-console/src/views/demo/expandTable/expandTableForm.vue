<template>
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
      <el-form-item label="商品名称" prop="name">
        <el-input v-model="temp.name" />
      </el-form-item>
      <el-form-item label="所属店铺" prop="shop">
        <el-input v-model="temp.shop" />
      </el-form-item>
      <el-form-item label="商品分类" prop="category">
        <el-input v-model="temp.category" />
      </el-form-item>
      <el-form-item label="店铺地址" prop="address">
        <el-input v-model="temp.address" />
      </el-form-item>
      <el-form-item label="商品描述" prop="description">
        <el-input v-model="temp.description" />
      </el-form-item>
      <el-form-item label="标签" prop="tag">
        <el-input v-model="temp.tag" />
      </el-form-item>
      <el-form-item label="图片" prop="image">
        <el-input v-model="temp.image" />
        <el-upload
          ref="uploader"
          :on-success="handleUploadSuccess"
          :action="uploadUrl"
          :before-upload="beforeUpload"
          :show-file-list="false"
          class="avatar-uploader"
          accept="image/jpeg,image/png,image/jpg">
          <img v-if="temp.image" :src="temp.image" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
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
import { save, findById } from '@/api/demo/expandTable'

export default {
  name: 'ExpandTableForm',
  data() {
    return {
      rules: {
      },
      temp: {
        id: undefined,
        name: undefined,
        shop: undefined,
        category: undefined,
        address: undefined,
        description: undefined,
        tag: undefined,
        image: undefined,
        createDate: undefined,
        updateBy: undefined,
        updateDate: undefined,
        remarks: undefined,
        delFlag: undefined,
        createBy: undefined,
        remark: ''
      },
      uploadUrl: 'http://' + process.env.VUE_APP_BASE_API + '/oss/attachment/upload?dir=test',
      textMap: {
        update: '编辑',
        create: '新建'
      },
      dialogFormVisible: false,
      dialogStatus: ''
    }
  },
  methods: {
    handleUploadSuccess(res, file) {
      console.log(res)
      if (res.code === 20000) {
        this.temp.image = res.data
      } else {
        this.$message.error(res.message)
      }
    },
    beforeUpload(file) {
      this.$refs.uploader.action = this.uploadUrl
      const isLt2M = file.size / 1024 < 300
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 300KB!')
      }
      return isLt2M
    },
    getList() {
      this.$emit('refreshList')
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: undefined,
        shop: undefined,
        category: undefined,
        address: undefined,
        description: undefined,
        tag: undefined,
        image: undefined,
        createDate: undefined,
        updateBy: undefined,
        updateDate: undefined,
        remarks: undefined,
        delFlag: undefined,
        createBy: undefined,
        remark: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      // this.$el 表示Vue 实例使用的根 DOM 元素 、
      // this.$parent表示父实例，如果当前实例有的话、
      // this.$root当前组件树的根 Vue 实例。如果当前实例没有父实例，此实例将会是其自己。
      // 访问父级组件实例:和 $root 类似，$parent property 可以用来从一个子组件访问父组件的实例。它提供了一种机会，可以在后期随时触达父级组件，以替代将数据以 prop 的方式传入子组件的方式
      // this.$children当前实例的直接子组件。需要注意 $children 并不保证顺序，也不是响应式的。如果你发现自己正在尝试使用 $children 来进行数据绑定，考虑使用一个数组配合 v-for 来生成子组件，并且使用 Array 作为真正的来源
      console.log(this.$el.textContent) // => 页面显示的文本内容未更新
      this.$nextTick(() => {
        console.log(this.$el.textContent) // => '页面显示的文本内容已更新
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
      console.log(this.$el.textContent) // => 'create' 页面显示的文本内容未更新
      // Vue.nextTick( [callback, context] )  在下次 DOM 更新循环结束之后执行延迟回调。在修改数据之后立即使用这个方法，获取更新后的 DOM。
      this.$nextTick(() => {
        console.log(this.$el.textContent) // => 'update' 页面显示的文本内容已更新
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
