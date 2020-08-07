<template>
  <div class="app-container">
    <data-grid ref="dataList" url="/sysmgr/att/list" data-name="listQuery" @dataRest="onDataRest">
      <template slot="form">
        <el-form-item label="名称">
          <!-- 按键修饰符 @keyup.enter  -->
          <el-input
            v-model="listQuery.originName"
            placeholder="文件名"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
        <el-form-item label="批次">
          <!-- 按键修饰符 @keyup.enter  -->
          <el-input
            v-model="listQuery.slotId"
            placeholder="批次"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!--extendOperation-->
      <template slot="extendOperation">
        <el-button
          class="filter-item"
          style="margin-left: 10px;"
          type="primary"
          size="small"
          icon="el-icon-upload2"
          @click="showUploadForm()"
        >上传</el-button>
      </template>
      <!--body-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px"/>
        <!-- <el-table-column align="center" prop="name" label="名称" ></el-table-column> -->
        <el-table-column align="center" prop="originName" label="源文件名" />
        <el-table-column :show-overflow-tooltip="true" align="center" prop="slotId" label="批次" />
        <!-- <el-table-column align="center" prop="fileCate" label="分类" ></el-table-column> -->
        <el-table-column align="center" prop="type" label="类型" />
        <el-table-column align="center" prop="fileSize" label="大小">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <span>{{ scope.row.fileSize | formatFileSize }}</span>
          </template>
        </el-table-column>
        <!-- element ui 表格数据溢出隐藏，鼠标hover显示tip设置 参考：https://blog.csdn.net/qq_44879525/article/details/102521486-->
        <!-- 给需要处理的列加show-overflow-tooltip属性 -->
        <el-table-column :show-overflow-tooltip="true" align="center" prop="path" label="路径" />
        <el-table-column align="center" prop="description" label="描述" />
        <el-table-column label="创建时间">
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="120"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>

    <el-dialog :visible.sync="uploadVisible" title="上传文件">
      <el-row>
        <el-col :span="24">
          <el-upload
            ref="upload"
            :action="uploadAction"
            :accept="acceptFileType"
            :limit="1"
            :headers="importHeaders"
            :on-exceed="handleExceed"
            :data="fileUploadParam"
            :before-upload="beforeUpload"
            :on-preview="handlePreview"
            :on-progress="handleProgress"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="fileList"
            :auto-upload="false"
            class="upload-demo"
            drag
          >
            <i class="el-icon-upload"/>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button :loading="uploadLoading" type="primary" size="mini" @click="submitUpload">确定上传</el-button>
        <el-button size="mini" @click="uploadVisible=false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import { getList, drop } from '@/api/sysmgr/att';
import { getToken } from '@/utils/auth';
import DataGrid from '@/components/DataGrid';
import { parseTime, formatFileSize } from '@/utils';
import waves from '@/directive/waves'; // Waves directive

export default {
  name: 'User',
  components: { DataGrid },
  directives: { waves },
  filters: {
    parseTime,
    formatFileSize
  },
  data() {
    return {
      // tableKey: 0,
      // total: 0,
      // list: null,
      // listLoading: true,
      listQuery: {
        pageNo: 1,
        limit: 10,
        id: null,
        originName: null,
        slotId: null
      },

      importHeaders: { Authorization: getToken() },
      fileList: [],
      uploadAction: process.env.BASE_API + '/sysmgr/att/upload',
      uploadVisible: false,
      uploadLoading: false,
      acceptFileType:
        '.jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.ZIP,.RAR',
      downLoadLoading: '',
      fileUploadParam: {
        sourceDir: 'temp'
      }
    };
  },
  methods: {
    onDataRest() {
      this.listQuery = {};
    },
    handleFilter() {
      this.$refs.dataList.fetchData();
    },
    dropRow(row) {
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const params = {};
          if (row.id) {
            params.id = row.id;
            drop(params).then(res => {
              this.$refs.dataList.fetchData();
            });
          }
          // 删除成功的提示信息
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
        .catch(() => {
          // 删除失败的提示信息
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    showUploadForm() {
      this.uploadVisible = true;
    },
    handleExceed(files, fileList) {
      this.$message.warning('只能选择1个文件!');
    },
    handleRemove(file, fileList) {
      // console.log(file,fileList);
    },
    handlePreview(file) {
      // console.log(file);
    },
    beforeUpload(file) {
      // 文件类型
      // eslint-disable-next-line no-unused-vars
      var fileName = file.name.substring(file.name.lastIndexOf('.') + 1);
      // if(fileName!='xls'){
      //     that.$message({
      //         type:'error',
      //         showClose:true,
      //         duration:3000,
      //         message:'文件类型不是.xls文件!'
      //     });
      //     return false;
      // }
      // 读取文件大小
      var fileSize = file.size;
      if (fileSize > 1048576) {
        this.$message({
          type: 'error',
          showClose: true,
          duration: 3000,
          message: '文件大于1M!'
        });
        return false;
      }
      return true;
    },
    handleProgress(event, file, fileList) {
      this.downloadLoading = this.$loading({
        lock: true,
        text: '数据导入中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0,0,0,0.7)'
      });
    },
    handleChange(file, fileList) {
      if (this.downloadLoading) {
        this.downloadLoading.close();
        this.uploadLoading = false;
      }
    },
    submitUpload() {
      // this.uploadLoading=true;
      var that = this;
      // setTimeout(function () {
      if (that.$refs.upload.$children[0].fileList.length == 1) {
        that.$refs.upload.submit();
      } else {
        that.uploadLoading = false;
        that.$message({
          type: 'error',
          showClose: true,
          duration: 3000,
          message: '请选择文件!'
        });
      }
      // },100);
    },

    handleSuccess(response, file, fileList) {
      if (response.result) {
        this.$message.success('上传成功');
        this.uploadVisible = false;
        this.$refs.dataList.fetchData();
      } else {
        this.$message({
          type: 'error',
          showClose: true,
          duration: 60000,
          message: response.message
        });
      }
    },
    handleError(err, file, fileList) {
      this.$message({
        type: 'error',
        showClose: true,
        duration: 60000,
        message: '请求失败! error:' + err
      });
    }
  }
};
</script>
