<template>
  <div/>
</template>

<script>
// 引入水印 https://blog.csdn.net/qq_38158631/article/details/88058535
import { removeWatermark, setWaterMark } from '@/utils/watermark'
export default {
  name: 'Blank', // 空白页
  data() {
    return {};
  },
  created() {},
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
