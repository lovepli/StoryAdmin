<template>
  <div>
    <el-card shadow="never" style="min-height:30vh;margin-top:20px;">
      <!-- 具名插槽的使用： -->
      <!-- 在 <template> 上使用特殊的 slot attribute，可以将内容从父级传给具名插槽 -->
      <!-- 使用方法1、带有 slot attribute 的具名插槽  -->
      <!-- 
        <template slot="header">
              <h1>Here might be a page title</h1>
        </template>
      -->
      <!-- 使用方法2、直接把 slot attribute 用在一个普通元素上,例如这里直接在div元素中使用 -->
       <!-- 
         <h1 slot="header">Here might be a page title</h1>
        -->
      <div slot="header" class="clearfix">
        <span>点击选择自动上传</span>
      </div>
      <ImageUploadOne />
    </el-card>
    <el-card shadow="never" style="min-height:40vh;margin-top:20px;">
      <div slot="header" class="clearfix">
        <span>管理上传</span>
      </div>
      <ImageUploadTwo />
    </el-card>
    <el-card shadow="never" style="min-height:30vh;margin-top:20px;">
      <div slot="header" class="clearfix">
        <span>文字列表-单击上传</span>
      </div>
      <ImageUploadThree />
    </el-card>
    <el-card shadow="never" style="min-height:30vh;margin-top:20px;">
      <div slot="header" class="clearfix">
        <span>拖拽上传</span>
      </div>
      <ImageUploadFour />
    </el-card>
  </div>
</template>
<script>
export default {
  components: {
    ImageUploadOne: resolve => { require(['@/components/example_demo/euiAdmin/module/imageUpload/ImageUploadOne'], resolve) },
    ImageUploadTwo: resolve => { require(['@/components/example_demo/euiAdmin/module/imageUpload/ImageUploadTwo'], resolve) },
    ImageUploadThree: resolve => { require(['@/components/example_demo/euiAdmin/module/imageUpload/ImageUploadThree'], resolve) },
    ImageUploadFour: resolve => { require(['@/components/example_demo/euiAdmin/module/imageUpload/ImageUploadFour'], resolve) }
  },
  data() {
    return {};
  }
};
</script>
<style scoped>
#adress {
  width: 100%;
  height: 50px;
  background-color: #f2f6fc;
  margin-top: 20px;
  border-style: solid;
  border-width: 0px;
  border-left-width: 5px;
  border-left-color: #67c23a;
}
</style>
