<template>
  <div class="storyAdminDoc" >
  <el-tooltip content="文档地址" effect="dark" placement="bottom">
      <el-button  type="text"  icon="el-icon-notebook-2" @click="goto()">
  </el-button>
   </el-tooltip>
  </div>
</template>

<script>
export default {
  name: 'StoryAdminDoc',
  data() {
    return {
      url: 'http://doc.ruoyi.vip/ruoyi-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
