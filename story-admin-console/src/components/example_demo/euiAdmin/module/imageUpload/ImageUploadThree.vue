<template>
  <div style="width:300px">
    <el-button
      :disabled="fileList.length==0"
      size="small"
      type="success"
      style="float:right"
      @click="upload_image_all">上传全部文件到服务器</el-button>
    <el-upload
      :on-change="image_change"
      :on-preview="choose_file"
      :file-list="fileList"
      :auto-upload="false"
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
    >
      <el-button size="small" type="primary" plain>选择图片</el-button>
    </el-upload>
  </div>
</template>
<script>
export default {
  data() {
    return {
      fileList: [],
      file: ''
    };
  },
  methods: {
    image_change(file, fileList) {
      this.file = file;
      this.fileList = fileList;
      console.log(file)
      console.log(this.fileList)
    },
    choose_file(file) {
      this.file = file;
      console.log(this.file)
      this.$message.success('上传图片成功')
    },
    upload_image_all(fileList) {
      console.log(fileList)
      this.$message.success('上传图片成功')
    }
  }
};
</script>
