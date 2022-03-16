<template>
  <div>
    <!-- 卡片视图 -->
    <el-card>
      <tree-table
        :data="authList"
        :columns="columns"
        :selection-type="false"
        :expand-type="false"
        :show-index="true"
        index-text="序号"
        border>
        <!-- 等级模板 -->
        <template slot="levelss" slot-scope="scope">
          <el-tag v-if="scope.row.level=='0'">一级</el-tag>
          <el-tag v-if="scope.row.level=='1'" type="success">二级</el-tag>
          <el-tag v-if="scope.row.level=='2'" type="warning">三级</el-tag>
        </template>
      </tree-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 权限列表
      authList: [],
      columns: [
        {
          label: '权限名称',
          prop: 'name'
        },
        {
          label: '路径',
          prop: 'path'
        },
        {
          label: '权限等级',
          type: 'template',
          template: 'levelss'
        }
      ]
    }
  },
  created() {
    // 获取所有权限
    this.getAuth()
  },
  methods: {
    getAuth() {
      this.authList = [
        {
          'id': 2,
          'name': '系统管理',
          'path': '',
          'pid': 0,
          'route_name': null,
          'icon': null,
          'is_menu': 0,
          'level': 0,
          'children': [
            {
              'id': 6,
              'name': '管理员管理',
              'path': '/user',
              'pid': 2,
              'route_name': 'admin.user.index',
              'icon': '',
              'is_menu': 0,
              'level': 1,
              'children': [
                {
                  'id': 15,
                  'name': '管理员编辑',
                  'path': null,
                  'pid': 6,
                  'route_name': 'admin.user.userEdit',
                  'icon': null,
                  'is_menu': 1,
                  'level': 2
                },
                {
                  'id': 14,
                  'name': '管理员状态编辑',
                  'path': null,
                  'pid': 6,
                  'route_name': 'admin.user.edit',
                  'icon': '',
                  'is_menu': 1,
                  'level': 2
                }

              ]
            }

          ]

        }
      ]
    }
  }
}
</script>
<style scoped>

</style>
