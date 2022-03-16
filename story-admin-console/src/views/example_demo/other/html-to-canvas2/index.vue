<template>
  <div class="individuation">
    <!-- 把需要生成图片的元素放在一个元素容器里,设置ref -->
    <div ref="imageTofile">
      　　<!-- 这里放需要截图的元素,自定义组件元素也可以 -->
      哈哈哈哈
    </div>
    <!-- 如果需要展示截取的图片,给一个img标签 -->
    <img :src="htmlUrl" />
  </div>
</template>
<script>
import html2canvas from "html2canvas";
export default {
   name: "htmlToCanvas2",
  data() {
    return {
      htmlUrl: "",
    };
  },
  mounted() {
    this.toImage();
  },
  methods: {
    // 页面元素转图片
    toImage() {
      let option = {
        scale: 3,
        useCORS: true,
        backgroundColor: null,
      }; // 第一个参数是需要生成截图的元素,第二个是自己需要配置的参数

      html2canvas(this.$refs.imageTofile, option).then((canvas) => {
        let url = canvas.toDataURL("image/png");
        console.log(url);
        this.htmlUrl = url; // 把生成的base64位图片上传到服务器,生成在线图片地址 //this.sendUrl();
      });
    },
  },
};
</script>