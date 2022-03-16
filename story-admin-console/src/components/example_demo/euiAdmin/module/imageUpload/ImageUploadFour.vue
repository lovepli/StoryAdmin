<template>
  <el-upload
    :on-change="image_change"
    :auto-upload="false"
    :show-file-list="false"
    class="upload-demo"
    drag
    action="">
    <i v-show="file==''" class="el-icon-upload"/>
    <div v-show="file==''" class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    <el-image v-show="file!=''" :src="file"/>
  </el-upload>
</template>
<script>
export default {
  data() {
    return {
      file: ''
    };
  },
  methods: {
    image_change(file) {
      this.file = URL.createObjectURL(file.raw);
      console.log(this.file)
      this.$message.success('上传图片成功')
    }
  }
};
</script>
