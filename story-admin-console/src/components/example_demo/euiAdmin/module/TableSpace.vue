<template>
  <div>
    <div>
      <UserTableSpace />
    </div>
  </div>
</template>
<script>
export default {
  components: {
    UserTableSpace:resolve=>{require(['@/components/example_demo/euiAdmin/module/table/UserTableSpace'],resolve)},
  },
};
</script>