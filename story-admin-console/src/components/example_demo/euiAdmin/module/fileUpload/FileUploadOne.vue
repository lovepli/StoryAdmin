<template>
  <div>
    <el-upload
      :auto-upload="false"
      :show-file-list="false"
      :on-change="choose_file"
      class="upload-demo"
      action
    >
      <el-button size="small" type="primary">点击上传</el-button>
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
