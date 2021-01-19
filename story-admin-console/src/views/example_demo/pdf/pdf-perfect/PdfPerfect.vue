<template>
  <div class="pdf-prefect">
    <!-- <iframe frameborder="0" width="100%" height="100%" src="./static/pdf/base64/web/viewer.html"> </iframe> -->
    <iframe
      frameborder="0"
      width="100%"
      height="100%"
      :src="
        `./static/pdf/url/web/viewer.html?file=${encodeURIComponent(
          'http://www.xdocin.com/xdoc?_key=t7t7z6j54vezfdnwptijcmo63i&_func=down&_dir=math.pdf'
        )}`
      "
    >
    </iframe>
  </div>
</template>

<script>
export default {
  name: 'PdfPerfect'
}
</script>

<style>
.pdf-prefect {
  height: 100%;
}
</style>
