<template>
  <div>
    <span>{{ name }}</span>
    <el-divider /> <!-- 横线  -->
    <div>
      <!-- 遍历角色对象 -->
      <el-tag v-for="(r,i) in roles" :key="i" size="small" style="margin:10px 8px">{{ r }}</el-tag>
    </div>
  </div>
</template>
<script>
import { findRoleListByAccount } from '@/api/sysmgr/user'
export default {
  name: 'PersonalInfo',
  data() {
    return {
      roleNameMap: {},
      roles: []
    }
  },
  // 动态计算属性,计算属性的结果会被缓存，除非依赖的响应式 property 变化才会重新计算。注意，如果某个依赖 (比如非响应式 property) 在该实例范畴之外，则计算属性是不会被更新的。
  computed: {
    name() { return this.$store.getters.name }
    // roles() {
    //   return this.$store.getters.roles
    //     .map(r => this.roleNameMap[r])
    //     .filter(r => r)
    // }
  },
  // 一个实例被创建之后执行代码
  created() {
    this.roles = this.$store.getters.roles
  // this.getRoleListByAccount();
  },
  methods: {

    getRoleListByAccount() {
    // 根据登录的账户名查询多有角色
      findRoleListByAccount({ userName: this.name }).then(r => {
        this.roles = r.data;
        console.log('角色数组=>', r.data);
      }).catch(e => {})
    },
    getRoleListByAccount2() {
      findRoleListByAccount({ userName: this.$store.getters.name }).then(r => {
        this.roleNameMap = {}
        // console.log('角色名称集合=>', r.data);
        r.data.forEach(role => { // 遍历角色
          this.roleNameMap[role.name] = role.name
        })
      }).catch(e => {})
    }

  }
}
</script>
