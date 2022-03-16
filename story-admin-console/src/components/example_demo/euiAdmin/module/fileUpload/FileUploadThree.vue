<template>
  <div>
    <el-upload
      :auto-upload="false"
      :show-file-list="false"
      :on-change="choose_file"
      class="upload-demo"
      action
      drag
    >
      <i class="el-icon-upload"/>
      <div class="el-upload__text">
        将文件拖到此处自动上传，或
        <em>点击上传</em>
      </div>
    </el-upload>
  </div>
</template>
<script>
export default {
  data() {
    return {
      file: ''
    };
  },
  methods: {
    choose_file(file) {
      this.file = file;
      this.$message.success('上传成功')
      console.log(file)
    }
  }
};
</script>
