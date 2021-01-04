<template>
    <div class="storyAdminGit" >
  <el-tooltip content="源码地址" effect="dark" placement="bottom">
      <el-button  type="text"  icon="el-icon-document" @click="goto()">
  </el-button>
   </el-tooltip>
  </div>
</template>

<script>
export default {
  name: 'StoryAdminGit',
  data() {
    return {
      url: 'https://gitee.com/y_project/RuoYi-Vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
