<template>
  <div>
    <el-upload
      :on-change="choose_file"
      :file-list="fileList"
      :auto-upload="false"
      action="#"
      list-type="picture-card"
      style="float:left"
    >
      <i slot="default" class="el-icon-plus"/>
      <div slot="file" slot-scope="{file}">
        <img :src="file.url" class="el-upload-list__item-thumbnail" alt >
        <span class="el-upload-list__item-actions">
          <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
            <i class="el-icon-zoom-in"/>
          </span>
          <span v-if="!disabled" class="el-upload-list__item-delete" @click="image_down(file)">
            <i class="el-icon-download"/>
          </span>
          <span v-if="!disabled" class="el-upload-list__item-delete" @click="upload_image(file)">
            <i class="el-icon-top"/>
          </span>
          <span v-if="!disabled" class="el-upload-list__item-delete" @click="image_delete(file)">
            <i class="el-icon-delete"/>
          </span>
        </span>
      </div>
    </el-upload>
    <el-button type="primary" style="float:left;margin-left:20px" @click="upload_image_all">上传所有选择图片</el-button>
    <el-dialog :visible.sync="dialogVisible">
      <img :src="dialogImageUrl" width="100%" alt >
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      file: '',
      fileList: []
    };
  },
  methods: {
    choose_file(file, fileList) {
      this.file = file;
      this.fileList = fileList;
      console.log(file)
      console.log(fileList)
    },
    image_delete(file) {
      for (let index = 0; index < this.fileList.length; index++) {
        if (this.fileList[index] == file) {
          //  console.log(index)
          this.fileList.splice(index, 1);
        }
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    image_down(file) {
      window.open(file.url);
    },
    upload_image(file) {
      this.file = file;
    },
    upload_image_all() {
      for (let index = 0; index < this.fileList.length; index++) {
        this.file = this.fileList[index];
      }
    }
  }
};
</script>
