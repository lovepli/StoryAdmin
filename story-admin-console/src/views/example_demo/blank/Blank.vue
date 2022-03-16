<template>
  <div>
    <p> 你复制不了这句话，哈哈哈 </p>
  </div>
</template>

<script>
// 引入水印 https://blog.csdn.net/qq_38158631/article/details/88058535
import { removeWatermark, setWaterMark } from '@/utils/watermark'
export default {
  name: 'Blank', // 空白页
  data() {
    return {};
  },
  created() {
    this.$nextTick(() => {
      // 禁用右键
      document.oncontextmenu = new Function('event.returnValue=false');
      // 禁用选择
      document.onselectstart = new Function('event.returnValue=false');
    });
  },
  mounted() {
    setWaterMark('lovepli', '李二狗');
  },
  updated() {},
  destroyed() {
    removeWatermark();
  },
  methods: {}
};
</script>
<style scoped></style>
