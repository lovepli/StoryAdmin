<template>
  <div class="screenfull">
  <el-tooltip content="全屏显示" effect="dark" placement="bottom">
      <el-button  type="text"  icon="el-icon-full-screen" @click="fullScreen()">
  </el-button>
   </el-tooltip>
  </div>
  
</template>

<script>
import screenfull from 'screenfull'

export default {
  name: 'Screenfull',
  data() {
    return {
   
    }
  },
  mounted() {

  },
  beforeDestroy() {

  },
  methods: {
    fullScreen(){
      if(!screenfull.isEnabled){
         // 如果不允许进入全屏，发出不允许提示
        this.$message.warning('当前该浏览器不支持全屏展示')
        return false
      }
      screenfull.toggle()
    }
  }
}
</script>

<style scoped>
.screenfull-svg {
  display: inline-block;
  cursor: pointer;
  fill: #5a5e66;;
  width: 20px;
  height: 20px;
  vertical-align: 10px;
}
</style>
