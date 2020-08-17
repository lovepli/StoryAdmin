<template>
  <div>
    <span>{{ name }}</span>
    <el-divider />
    <div>
      <el-tag v-for="(r,i) in roles" :key="i" size="small" style="margin:10px 8px">{{ r }}</el-tag>
    </div>
  </div>
</template>
<script>
import { findAllRoleList } from '@/api/sysmgr/role'
export default {
  name: 'PersonalInfo',
  data() {
    return {
      roleNameMap: {}
    }
  },
  computed: {
    name() { return this.$store.getters.name },
    roles() {
      return this.$store.getters.roles
        .map(r => this.roleNameMap[r])
        .filter(r => r)
    }
  },
  created() {
    findAllRoleList().then(r => {
      this.roleNameMap = {}
      r.data.forEach(role => { // 遍历角色名称
        this.roleNameMap[role.name] = role.name
      })
    }).catch(e => {})
  }
}
</script>
