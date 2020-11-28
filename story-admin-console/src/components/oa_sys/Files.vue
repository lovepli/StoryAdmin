<template>
  <div class="files">
    <el-form :inline="true">
      <el-form-item>
        <el-button-group>
          <el-button
            type="primary"
            icon="el-icon-top"
            plain
            @click="toParent"
          >返回上一级</el-button
          >
          <el-button
            type="primary"
            icon="el-icon-folder-add"
            plain
            @click="addFolder"
          >新建文件夹</el-button
          >
          <el-button
            type="primary"
            icon="el-icon-upload"
            plain
            @click="uploadFile"
          >上传文件</el-button
          >
          <el-button
            type="primary"
            icon="el-icon-delete"
            plain
            @click="deleteFile"
          >删除</el-button
          >
        </el-button-group>
      </el-form-item>
      <el-form-item class="search">
        <el-input v-model="search" placeholder="输入关键字搜索"/>
      </el-form-item>
      <el-form-item class="path">
        <el-input v-model="currentPath" :disabled="true" />
      </el-form-item>
    </el-form>
    <el-table
      ref="multipleTable"
      :data=" files.list.filter((data) =>!search || data.name.toLowerCase().includes(search.toLowerCase()))"
      border
      style="width: 100%"
    >
      <el-table-column type="selection" width="50" />
      <el-table-column type="index" label="序号" align="center" width="50" />
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.type === '文件夹'">
            <span @click="toFolder(scope.row)">
              {{ scope.row.name }}
            </span>
          </div>
          <div v-else>
            {{ scope.row.name }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="类型" align="center" width="100" />
      <el-table-column prop="size" label="大小" align="center" width="100" />
      <el-table-column prop="user.username" label="作者" align="center" width="100"/>
      <el-table-column prop="createdTime" label="创建时间" align="center" width="200"/>
      <el-table-column label="操作" align="center" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" @click="renameFile(scope.row)">重命名</el-button>
          <el-button v-if="scope.row.type !== '文件夹'" size="mini" @click="downloadFile(scope.row)">下载</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        :pager-count="5"
        :total="files.total"
        :hide-on-single-page="true"
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
      />
    </div>
    <!-- 上传对话框 -->
    <el-dialog :visible.sync="uploadFileDialog" width="26%">
      <el-upload
        :headers="importHeaders"
        :data="{ personal, parentId }"
        :on-success="uploadSuccess"
        :action="filePostUrl"
        drag
        multiple
      >
        <i class="el-icon-upload"/>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
import { addFolder, getFiles, renameFile, deleteFile } from '@/api/oa_sys/oa';
import axios from 'axios';
import { getToken } from '@/utils/auth'; // 从Cookies中获取token

export default {
  name: 'Files',
  // eslint-disable-next-line vue/require-prop-types
  props: ['personal'],
  data() {
    return {
      files: { list: [] },
      search: '',
      history: [],
      current: [1],
      selection: [],
      path: [' / 根目录'],
      uploadFileDialog: false,
      // 文件上传
      filePostUrl: process.env.BASE_API + '/common/uploadFile',
      importHeaders: { Authorization: getToken() }
    };
  },
  // computed 计算属性
  // 定义：当其依赖的属性的值发生变化的时，计算属性会重新计算。反之则使用缓存中的属性值。
  computed: {
    currentPath() {
      return this.path.join(' / ');
    },
    parentId() {
      return this.current[this.current.length - 1];
    }
  },
  created() {
    this.getFiles(1);
  },
  methods: {
    getFiles(parentId, pageNumber) {
      var personal = this.personal;
      getFiles({ parentId, personal, pageNumber }).then((response) => {
        if (response && response.code === 20000) {
          this.files = response.data;
        }
      });
    },
    // 跳到文件夹
    toFolder(file) {
      this.getFiles(file.id);
      this.path.push(file.name);
      this.current.push(file.id);
      this.history.push(file.parentId);
    },
    // 返回上一级
    toParent() {
      if (this.history.length === 0) {
        return;
      } else {
        this.getFiles(this.history.pop());
        this.current.pop();
        this.path.pop();
      }
    },
    // 新建文件夹
    addFolder() {
      this.$prompt('请输入文件夹名', '新建文件夹', {
        inputPattern: /^.{1,20}$/,
        inputErrorMessage: '文件夹名应为1-20个字符'
      })
        .then(({ value }) => {
          var personal = this.personal;
          var folderName = value;
          var parentId = this.current[this.current.length - 1];
          addFolder({ folderName, personal, parentId }).then((response) => {
            if (response && response.code === 20000) {
              this.$message.success(response.message);
              this.getFiles(parentId);
            }
          });
        })
        .catch(() => {});
    },
    // 上传文件
    uploadFile() {
      this.uploadFileDialog = true;
    },
    // 上传成功
    uploadSuccess(response) {
      this.$message.success(response.message);
      this.getFiles(this.current[this.current.length - 1]);
      this.uploadFileDialog = false;
    },
    // 页面跳转
    handleCurrentChange(pageNumber) {
      var parentId = this.current[this.current.length - 1];
      this.getFiles(parentId, pageNumber);
    },
    // 下载文件
    downloadFile(row) {
      this.$message.error('下载失败')
      // axios.get(row.path, { responseType: 'blob' }).then((r) => {
      //   const link = document.createElement('a');
      //   link.href = window.URL.createObjectURL(new Blob([r]));
      //   link.setAttribute('download', row.name + '.' + row.type);
      //   link.click();
      // });
    },

    // 重命名文件
    renameFile(row) {
      this.$prompt('请输入新文件名', '重命名', {
        inputValue: row.name,
        inputPattern: /^.{1,20}$/,
        inputErrorMessage: '文件名应为1-20个字符'
      })
        .then(({ value }) => {
          var newName = value;
          var id = row.id;
          row.name = value;
          renameFile({ id, newName }).then((response) => {
            if (response && response.code === 20000) {
              this.$message.success(response.message);
            }
          });
        })
        .catch(() => {});
    },
    // 删除文件
    deleteFile() {
      if (this.$refs.multipleTable.selection < 1) {
        this.$message.error('至少选择一个文件或文件夹！');
        return;
      }
      this.$confirm('永久删除这些文件, 是否继续?')
        .then(() => {
          var ids = [];
          this.$refs.multipleTable.selection.forEach((item) => {
            ids.push(item.id);
          });
          deleteFile(ids).then((response) => {
            if (response && response.code === 20000) {
              this.$message.success(response.message);
              this.getFiles(this.current[this.current.length - 1]);
            }
          });
        })
        .catch(() => {
          this.$message.info('已取消删除');
        });
    }
  }
};
</script>

<style scoped>
.path {
  float: right;
}

.search {
  float: right;
}
</style>
