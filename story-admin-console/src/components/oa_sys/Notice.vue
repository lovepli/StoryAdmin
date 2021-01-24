<template>
  <div class="notice">
    <el-popover placement="bottom-start" width="600">
      <el-table
        ref="multipleTable"
        :data="notices"
        height="380"
        border
        style="width: 100%"
      >
        <el-table-column type="selection" width="40" />
        <el-table-column prop="createdTime" label="时间" width="160" />
        <el-table-column prop="content" label="内容" />
      </el-table>

      <!-- <el-badge :value="unreadCount" :max="99" >
     <el-tooltip content="通知" effect="dark" placement="bottom">
      <el-button slot="reference"  type="text"  icon="el-icon-bell">
     </el-button>
    </el-tooltip>
   </el-badge> -->

      <el-button slot="reference" type="text" >
        通知
        <el-badge :value="unreadCount" :max="99" />
      </el-button>

      <div class="mark">
        <el-button size="small" @click="markRead">标记已读</el-button>
      </div>
    </el-popover>
  </div>
</template>

<script>

import { getNotices, markRead } from '@/api/oa_sys/oa_notice';

export default {
  name: 'Notice',
  data() {
    return {
      notices: []
    };
  },
  computed: {
    unreadCount() {
      return this.notices.length;
    }
  },
  watch: {
    $route() {
      this.getNotices();
    }
  },
  created() {
    this.getNotices();
  },
  methods: {
    getNotices() {
      getNotices().then((response) => {
        if (response && response.code === 20000) {
          this.notices = response.data.reverse();
        }
      });
    },
    markRead() {
      if (this.$refs.multipleTable.selection < 1) {
        this.$message.error('至少选择一个！');
        return;
      }
      var ids = [];
      this.$refs.multipleTable.selection.forEach((item) => {
        ids.push(item.id);
      });
      markRead(ids).then((response) => {
        if (response && response.code === 20000) {
        //  this.$message.success(response.message);
          this.getNotices();
        }
      });
    }
  }
};
</script>

<style scoped>
.mark {
  text-align: right;
  margin-top: 10px;
}
</style>
