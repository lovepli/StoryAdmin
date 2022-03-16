<template>
  <div class="personal-net-disk">
    <!-- personal 参数作为区别公共网盘和私人网盘 -->
    <Files personal="1"/>
  </div>
</template>

<script>
import Files from '@/components/oa_sys/Files';

export default {
  name: 'PersonalNetDisk', // 个人网盘
  components: {
    Files
  }
}
</script>

<style scoped>

</style>
