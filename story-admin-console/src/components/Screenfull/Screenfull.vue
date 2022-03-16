<template>
  <div class="screenfull">
    <el-tooltip effect="dark" :content="fullscreen?`取消全屏`:`全屏显示`" placement="bottom">
                <i class="el-icon-full-screen"  @click="handleFullScreen()"></i>
    </el-tooltip>
  </div>
  
</template>

<script>
// import screenfull from 'screenfull'

export default {
  name: 'Screenfull',
  data() {
    return {
    fullscreen: false
    }
  },
  mounted() {

  },
  beforeDestroy() {

  },
  methods: {
      // 全屏事件 直接原生写法
            handleFullScreen() {
                let element = document.documentElement;
                if (this.fullscreen) {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                } else {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.msRequestFullscreen) {
                        // IE11
                        element.msRequestFullscreen();
                    }
                }
                this.fullscreen = !this.fullscreen;
            }
    // 第三方插件实现
    // fullScreen(){
    //   if(!screenfull.isEnabled){
    //      // 如果不允许进入全屏，发出不允许提示
    //     this.$message.warning('当前该浏览器不支持全屏展示')
    //     return false
    //   }
    //   screenfull.toggle()
    // }
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
