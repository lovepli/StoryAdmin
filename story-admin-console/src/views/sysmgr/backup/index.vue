<template>
  <div class="app-container">
    <data-grid
      ref="dataList"
      url="/sysmgr/backup/list"
      data-name="listQuery"
      @dataRest="onDataRest"
    >
      <template slot="form">
        <el-form-item label="数据库名称">
          <!-- 按键修饰符 @keyup.enter  -->
          <el-input
            v-model="listQuery.dbName"
            placeholder="数据库名称"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!--extendOperation-->
      <template slot="extendOperation" />
      <!--body-->
      <template slot="body">
        <el-table-column align="center" prop="id" label="ID" width="100px" />
        <el-table-column align="center" prop="backupDate" label="备份日期">
          <template slot-scope="scope">
            <span>{{ scope.row.backupDate | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="backupName" label="备份名称" />
        <el-table-column prop="dbName" label="数据库名称" />
        <el-table-column prop="backupPath" label="备份路径" />
        <el-table-column align="right" prop="fileSize" label="文件大小">
          <template slot-scope="scope">
            <span>{{ scope.row.fileSize | formatFileSize }}</span>
          </template>
        </el-table-column>
        <el-table-column align="right" prop="runtime" label="运行时间" />
        <el-table-column align="center" prop="status" label="状态">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status | statusTagFilter">{{ scope.row.status | statusFilter }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间">
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="100"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="dropRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </template>
    </data-grid>
  </div>
</template>

<script>
import { drop } from '@/api/sysmgr/backup';
import DataGrid from '@/components/DataGrid';
import { parseTime, formatFileSize } from '@/utils';
import waves from '@/directive/waves'; // Waves directive

export default {
  name: 'User',
  components: { DataGrid },
  directives: { waves },
  // 定义本地过滤器
  filters: {
    parseTime, // 日期格式化
    formatFileSize, // 格式化文件大小
    statusFilter(status) {
      if (status) {
        return '成功';
      } else {
        return '失败';
      }
    },
    statusTagFilter(status) {
      if (status) {
        return 'success';
      } else {
        return 'danger';
      }
    }
  },
  data() {
    return {
      listQuery: {
        pageNo: 1,
        limit: 10,
        id: null,
        dbName: null
      }
    };
  },
  methods: {
    onDataRest() {
      this.listQuery = {};
    },
    handleFilter() {
      this.$refs.dataList.fetchData();
    },

    dropRow(row) {
      this.$confirm('您确定要删除该数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          const params = {};
          if (row.id) {
            params.id = row.id;
            drop(params).then(res => {
              this.$refs.dataList.fetchData();
            });
          }
        })
        .catch(() => {
          // 删除失败的提示信息
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    }
  }
};
</script>
