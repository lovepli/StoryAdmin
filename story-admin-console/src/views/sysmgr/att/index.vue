<template>
  <div class="app-container">
    <data-grid
      ref="dataList"
      url="/sysmgr/att/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
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
      <!--extendOperation 扩展功能-->
      <template slot="extendOperation">
        <el-button
          class="filter-item"
          style="margin-left: 10px;"
          type="primary"
          size="small"
          icon="el-icon-upload2"
          @click="showUploadForm()"
        >上传</el-button>
        <el-button
          class="filter-item"
          type="success"
          size="small"
          icon="el-icon-download"
          @click="showDownloadForm()"
        >导出当页数据</el-button>
        <el-button
          class="filter-item"
          type="success"
          size="small"
          icon="el-icon-download"
          @click="DownloadForm()"
        >导出表格</el-button>
      </template>
      <!--body-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px"/>
        <!-- <el-table-column align="center" prop="name" label="名称" ></el-table-column> -->
        <el-table-column :formatter="r=>r.originName" align="center" prop="originName" label="源文件名" />
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
        <el-table-column :show-overflow-tooltip="true" align="center" prop="path" label="路径">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <span @click="showPdf(scope.row)" >{{ scope.row.path }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="description" label="描述" />
        <el-table-column label="创建时间">
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="230"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
            <el-button type="primary" size="mini" @click="downloadRow(scope.row)">下载</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>

    <el-dialog :visible.sync="uploadVisible" title="上传文件">
      <el-row>
        <el-col :span="24">
          <!-- 通过 slot 你可以传入自定义的上传按钮类型和文字提示。
          可通过设置limit和on-exceed来限制上传文件的个数和定义超出限制时的行为。
          可通过设置before-remove来阻止文件移除操作。
          Upload组件:http-request属性覆盖默认的上传行为，可以自定义上传的实现。-->
          <el-upload
            ref="upload"
            :action="filePostUrl"
            :http-request="uploadFile"
            :accept="acceptFileType"
            :limit="1"
            :headers="importHeaders"
            :on-exceed="handleExceed"
            :data="fileUploadParam"
            :before-upload="beforeUpload"
            :before-remove="beforeRemove"
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
      <el-row>
        <a style="color: red;" href="http://localhost:9430/data/contest_templates.xlsx" download="contest_templates.xlsx" title="excel模板下载">
          Excel模板下载
        </a>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button :loading="uploadLoading" type="primary" size="mini" @click="submitUpload">确定上传</el-button>
        <el-button size="mini" @click="uploadVisible=false">取消</el-button>
      </span>
    </el-dialog>
    <!-- 导出文件 -->
    <el-dialog :visible.sync="showDownloadDialog" width="695px" title="请选择打印内容">
      <file-export :data="list" :cols="downloadColomns" table-name="用户附件管理数据导出" file-name="附件管理数据" />
    </el-dialog>

    <!--      展示附件pdf的对话框-->
    <el-dialog
      :title="'上传的文件'"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="70%">
      <el-image v-for="(item, index) in imgList" :key="index" :src="item" style="width: 50%;height: 50%"/>
      <el-divider style="margin-top: 100px"/>
      <div v-for="(item, index) in pdfList" :key="index">
        <!--          <embed :src="item" width="100%" height="300px">-->
        <!--          <iframe :src="item" width="100%" height="100%"></iframe>-->
        <object :data="item" type="application/pdf" width="100%" height="800px"/>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleClose">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { drop, uploadFile, downloadFile, findFileInfoDetail } from '@/api/sysmgr/att';
import { getToken } from '@/utils/auth'; // 从Cookies中获取token
import DataGrid from '@/components/DataGrid';
import { parseTime, formatFileSize } from '@/utils';
import waves from '@/directive/waves'; // Waves directive
import FileExport from '@/components/FileExport' // 导出文件

export default {
  name: 'User',
  components: { DataGrid, FileExport },
  directives: { waves },
  filters: {
    parseTime,
    formatFileSize
  },
  data() {
    return {
      listQuery: {
        pageNo: 1,
        limit: 10,
        id: null,
        originName: null,
        slotId: null
      },

      filePostUrl: process.env.BASE_API,
      addFiles: [],

      importHeaders: { Authorization: getToken() },
      fileList: [],
      // uploadAction: process.env.BASE_API + '/sysmgr/att/upload', // 调用上传文件的接口
      uploadVisible: false, // 上传文件弹框
      uploadLoading: false,
      showDownloadDialog: false,
      list: [],
      downloadColomns: [
        { name: '序号', value: 'id', map: (v, c, i) => i + 1 },
        { name: '源文件名', value: 'originName' },
        { name: '批次', value: 'slotId' },
        { name: '类型', value: 'type' },
        { name: '大小', value: 'fileSize' },
        { name: '路径', value: 'path' },
        { name: '描述', value: 'description' },
        { name: '创建时间', value: 'createdTime', map: this.dateFormat }
      ],
      acceptFileType:
        '.jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF,.ZIP,.RAR',
      downLoadLoading: '',
      fileUploadParam: {
        sourceDir: 'temp'
      },
      url: '',
      jsonParam: {},
      downLoadName: '',
      // 图片展示
      imgList: [],
      // pdf展示
      pdfList: [],
      dialogVisible: false
    };
  },
  methods: {
    handleClose() {
      this.dialogVisible = false
    },
     	/**
       * 查看附件
       */
    async showPdf(row) {
      this.dialogVisible = true
      await findFileInfoDetail({
        'attId': row.id
      }).then(res => {
        // eslint-disable-next-line no-array-constructor
        var arr = new Array()
        arr = res.data.split('&&&')
        for (var i = 0; i < arr.length - 1; i++) {
          if (arr[i].indexOf('data:application/pdf;base64,') !== -1) {
            this.pdfList.push(arr[i])
          } else {
            this.imgList.push(arr[i])
          }
        }
      })
    },
    // 格式化时间
    dateFormat(d) { return parseTime(d, '{y}-{m}-{d} {h}:{i}:{s}') },
    // 数据重置
    onDataRest() {
      this.listQuery = {};
    },
    // 查询
    handleFilter() {
      this.$refs.dataList.fetchData();
    },
    // 上传文件
    uploadFile(content) {
      uploadFile(content.file).then(r => { content.onSuccess(r, content.file) })
        .catch(e => {
          this.addFiles.splice(this.addFiles.indexOf(content.file.name), 1)
          content.onError(e.message)
        })
    },
    // 删除接口
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
        })
        .catch(() => {
          // 删除失败的提示信息
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },
    // 下载接口
    // downloadRow(row) {
    //   downloadFile(row.id).then(r => {
    //     // this.$refs.dataList.fetchData();
    //   }).catch(e => { this.$message.error('下载失败') })
    // },

    // 下载文件
    downloadRow(row) {
      downloadFile(row.id).then(r => {
        if (!r) return
        const url = window.URL.createObjectURL(new Blob([r]))
        const link = document.createElement('a')
        link.style.display = 'none'
        link.href = url
        link.setAttribute('download', row.originName)
        document.body.appendChild(link)
        link.click()
      }).catch(e => { this.$message.error('下载失败') })
    },

    // 上传
    showUploadForm() {
      this.uploadVisible = true;
      // this.fileList = null;
    },
    // 导出当前页数据
    showDownloadForm() {
      // 将表格数据复制到信息弹框中
      this.list = this.$refs.dataList.list;
      this.showDownloadDialog = true;
    },
    // 导出表格
    async DownloadForm() {
      this.url = '/sysmgr/att/export'
      // this.jsonParam = this.queryInfo
      this.downLoadName = '报表数据'
      await this.$http.post(this.url, this.jsonParam,
        {
          responseType: 'blob' // 设置响应类型
        })
        .then(res => {
          if (res.status === 200) {
            const url = window.URL.createObjectURL(new Blob([res.data]))
            const link = document.createElement('a')
            link.style.display = 'none'
            link.href = url
            link.setAttribute('download', this.downLoadName) // 自定义喜爱在文件名
            document.body.appendChild(link)
            link.click()
            document.body.removeChild(link) // 下载完移除元素
            window.URL.revokeObjectURL(url)
          } else {
            this.$message.error('下载失败！')
          }
        })
        .catch(() => {
          this.$message.error('获取失败，请检查网络连接！')
        })
    },
    handleExceed(files, fileList) {
      this.$message.warning('只能选择1个文件!');
      // this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    // 上传之前
    beforeUpload(file) {
      // 文件类型
      // eslint-disable-next-line no-unused-vars
      var fileName = file.name.substring(file.name.lastIndexOf('.') + 1);
      // 判断是不是xls文件
      // eslint-disable-next-line eqeqeq
      // if (fileName != 'xls') {
      //   // eslint-disable-next-line no-undef
      //   this.$message({
      //     type: 'error',
      //     showClose: true,
      //     duration: 3000,
      //     message: '文件类型不是.xls文件!'
      //   });
      //   return false;
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
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    // 上传过程
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
    //  确定上传
    submitUpload() {
      this.uploadLoading = true;
      var that = this;
      setTimeout(function() {
      // this.$children当前实例的直接子组件。需要注意 $children 并不保证顺序，也不是响应式的。如果你发现自己正在尝试使用 $children 来进行数据绑定，考虑使用一个数组配合 v-for 来生成子组件，并且使用 Array 作为真正的来源
        if (that.$refs.upload.$children[0].fileList.length === 1) {
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
      }, 100);
      // 知识点：Vue中使用定时器setInterval和setTimeout https://www.cnblogs.com/jin-zhe/p/10001236.html
      // js中定时器有两种，一个是循环执行setInterval，另一个是定时执行setTimeout
      // 一、循环执行（setInterval）
      // 顾名思义，循环执行就是设置一个时间间隔，每过一段时间都会执行一次这个方法,直到这个定时器被销毁掉
      // 用法是setInterval（“方法名或方法”，“延时”）， 第一个参数为方法名或者方法，注意为方法名的时候不要加括号,第二个参数为时间间隔
      // 实例：有时候我们需要在页面上添加一个类似时钟的东西来实时显示当前时间，这个时候我们可以利用定时器来完成这个功能
      // 二、定时执行 （setTimeout）
      // 定时执行setTimeout是设置一个时间，等待时间到达的时候只执行一次，但是执行完以后定时器还在，只是没有运行
      // 用法是setTimeout(“方法名或方法”, “延时”); 第一个参数为方法名或者方法，注意为方法名的时候不要加括号,第二个参数为时间间隔
    },
    // 上传成功
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
          message: response.message // 后台传过来的响应结果
        });
      }
    },
    // 上传失败
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
