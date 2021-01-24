<template>
  <div>
    <div>
      <TopSpace/>
    </div>
    <div style="margin-top:20px">
      <WebDataSpace/>
    </div>
  </div>
</template>
<script>
export default {
  components: {
    TopSpace: resolve => { require(['@/components/example_demo/euiAdmin/home/module/TopSpace'], resolve) },
    WebDataSpace: resolve => { require(['@/components/example_demo/euiAdmin/home/module/WebDataSpace'], resolve) }
  }
};
</script>
