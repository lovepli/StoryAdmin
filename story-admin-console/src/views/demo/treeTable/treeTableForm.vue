<template>
  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
    <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 80%; margin-left:50px;">
      <el-form-item label="上级" prop="parentIds">
        <!-- 这里下拉框数组数据是 parentIds-->
        <el-cascader
          v-model="temp.parentIds"
          :options="list"
          :props="treeProps"
          :show-all-levels="false"
          label="name"
          change-on-select
          clearable
        />
      </el-form-item>
      <el-form-item label="机构名称" prop="name">
        <el-input v-model="temp.name" />
      </el-form-item>
      <el-form-item label="地理编码" prop="geocoding">
        <el-input v-model="temp.geocoding" />
      </el-form-item>
      <el-form-item label="邮政编码" prop="postalCode">
        <el-input v-model="temp.postalCode" />
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
import { add, update, findById } from '@/api/demo/treeTable'

export default {
  name: 'TreeTableForm',
  data() {
    return {
      rules: {
      },
      temp: {
        id: undefined,        // id编号
        name: undefined,      // 名称
        geocoding: undefined, // 地理编码
        postalCode: undefined, //邮政编码
        parentId: undefined,  // 父编号
        parentIds: undefined, // 父编号列表
        // createBy: undefined,
        // createDate: undefined,
        // updateBy: undefined,
        // updateDate: undefined,
        delFlag: undefined, // 删除标记
        remarks: undefined, // 备注
       // remark: ''          // 
      },
      temp2: {
        id: undefined,
        name: undefined,
        geocoding: undefined,
        parentId: undefined,
        postalCode: undefined,
        // parentIds: undefined,
        remark: ''
      },
      list: [], // 级别下拉框选择数组
      treeProps: {   // el-cascader组件的配置参数
        value: 'id',
        label: 'name',
        expandTrigger: 'hover'
      },
      textMap: {
        update: '编辑',
        create: '新建'
      },
      dialogFormVisible: false,
      dialogStatus: ''
    }
  },
  methods: {
    // 级别下拉框数组
    setList(list) {
      this.list = list
    },
    getList() {
      // 查询获取列表数据
      this.$emit('refreshList')
    },
    /**
     * 表单重置
     */
    resetTemp() {
      const userInfo = {
        menuId:'', // id
        menuName:'', // 菜单名称name
        menuGrade:'', // 菜单级别
        parentId:'', // 父级id
        status:'', // 状态，是否启用

        parentIds:'' // 增加一个字段返回
      }
      this.temp = {
        id: undefined,
        name: undefined,
        geocoding: undefined,
        postalCode: undefined,
        parentId: undefined,
        parentIds: undefined,
        // createBy: undefined,
        // createDate: undefined,
        // updateBy: undefined,
        // updateDate: undefined,
        delFlag: undefined,
        remarks: undefined,
       // remark: ''
      }
    },
    /**
     * 添加
     */
    handleCreate() {
      // 重置数据
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    /**
     * 新增保存
     */
    createData() {
      // 预处理提交的数据
      const parentIds = this.temp.parentIds
      if (parentIds !== undefined && parentIds !== '') {
        // 级别下拉框数组
        if (parentIds instanceof Array && parentIds.length > 0) {
          // 下拉框选择的最后一级的值赋值给parentId
          const parentId = parentIds[parentIds.length - 1]
          this.temp.parentId = parentId
        }
      }

      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          // // 预处理提交的数据
          // const parentIds = this.temp.parentIds
          // if (parentIds !== undefined && parentIds !== '') {
          //   if (parentIds instanceof Array && parentIds.length > 0) {
          //     const parentId = parentIds[parentIds.length - 1]
          //     this.temp.parentId = parentId
          //   }
          // }

          if (this.temp.parentIds !== undefined && this.temp.parentIds !== '') {
            this.temp.parentIds.length = 0
          }
          this.temp2.id = this.temp.id;
          this.temp2.name = this.temp.name;
          this.temp2.parentId = this.temp.parentId;
          this.temp2.geocoding = this.temp.geocoding;
          this.temp2.remark = this.temp.remark;
          this.temp2.postalCode = this.temp.postalCode;
         // const tempData2 = Object.assign({}, this.temp)
         // console.log('提交新增的数据：' + tempData2)
          console.log("添加"+JSON.stringify(this.temp2))
          add(this.temp2).then(() => {
            this.getList()
            this.dialogFormVisible = false
          })
        }
      })
    },
    /**
     * 编辑
     */
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
           console.log("根据id查询返回的数据："+JSON.stringify(this.temp))
          if (this.temp.children !== undefined) {
            this.temp.children.length = 0
          }
          if (this.temp.parentIds !== undefined) {
            let parentIds = this.temp.parentIds.trim() 
            // trim()方法返回移除头尾空格的字符串。
            if (parentIds.length > 0) {
              // 将后台传过来的parentIds进行分解
              parentIds = parentIds.substr(0, parentIds.length - 1) 
              // stringObject.substr(start,length) 方法的参数指定的是子串的开始位置和长度
             // 返回值：一个新的字符串，包含从 stringObject 的 start（包括 start 所指的字符） 处开始的 length 个字符。如果没有指定 length，那么返回的字符串包含从 start 到 stringObject 的结尾的字符。
            }
            this.temp.parentIds = parentIds.split('\/') // 返回值为数组
            // StringObject.split( char  [,howmany]) : 把一个字符串分割成字符串数组
            // char:必需。howmany:  可选,字符串或正则表达式。该参数可指定返回的数组的最大长度。如果设置了该参数，返回的子串不会多于这个参数指定的数组。如果没有设置该参数，整个字符串都会被分割，不考虑它的长度。
            // 举例：
            // var Arr = "2:3:4:5".split(":");      //将返回["2", "3", "4", "5"]
            // var Arr = "|a|b|c".split("|");       //将返回["", "a", "b", "c"]
            // var Arr = "hello".split("") ;  //可返回 ["h", "e", "l", "l", "o"] // 单词拆分
          }
        } else {
          this.dialogFormVisible = false
          this.$message.error(response.message)
        }
      })
    },
    /**
     * 编辑保存
     */
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const parentIds = this.temp.parentIds
          if (parentIds !== undefined && parentIds !== '') {
            if (parentIds instanceof Array && parentIds.length > 0) {
              const parentId = parentIds[parentIds.length - 1]
              this.temp.parentId = parentId
            }
          }
          if (this.temp.parentIds !== undefined && this.temp.parentIds !== '') {
            this.temp.parentIds = undefined
          }
          if (this.temp.parent !== undefined) {
            this.temp.parent = undefined
          }
          const tempData = Object.assign({}, this.temp)
          console.log("提交修改的数据："+JSON.stringify(tempData))
          update(tempData).then(() => {
            this.getList()
            this.dialogFormVisible = false
          })
        } else {
          return false;
        }
      })
    }
  }
}
</script>
