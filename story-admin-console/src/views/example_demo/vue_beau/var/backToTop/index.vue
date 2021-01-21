<template>
  <div class="container ">
    <div v-for="(item, index) in 100" :key="index" style="padding: 20px">
      测试滚轮显示返回顶部-{{ index }}
    </div>
    <!-- 回到顶部组件 target：回到指定类的顶部 visibility-height：滚动长度 -->
       <el-backtop target=".container">
                <div
                    style="{
                        height: 100%;
                        width: 100%;
                        background-color: #f2f5f6;
                        box-shadow: 0 0 6px rgba(0,0,0, .12);
                        text-align: center;
                        line-height: 40px;
                        color: #1989fa;
                    }"
                    >
                    UP
                </div>
            </el-backtop>
  </div>
</template>

<script>
  export default {
    name: 'BackToTop',
    data() {
      return {}
    },
  }
</script>
<style lang="scss" scoped>
  .placeholder-container div {
    margin: 10px;
  }
      /** 容器大小 下滑块 */
    .container {
        height: 100vh;
        overflow-x: hidden;
    }
</style>
