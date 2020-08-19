<template>
  <div class="app-container">
    <!-- handleSelectionChange 为表格的批量删除方法 -->
    <data-grid
      ref="dataList"
      url="/sysmgr/loginlog/list"
      data-name="listQuery"
      @dataRest="onDataRest"
      @handleSelectionChange="handleSelectionRowChange"
    >
      <template slot="form">
        <el-form-item label="账号">
          <el-input
            v-model="listQuery.account"
            placeholder="账号"
            size="small"
            class="filter-item"
            @keyup.enter.native="handleFilter"
          />
        </el-form-item>
      </template>
      <!--extendOperation 扩展功能-->
      <template slot="extendOperation">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="handleDelete"
        >批量删除</el-button>
      </template>

      <!--body-->
      <template slot="body">
        <!-- 批量删除勾选 -->
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column align="center" prop="id" label="ID" width="100px"/>
        <el-table-column align="center" prop="account" label="账号"/>
        <el-table-column label="访问时间">
          <template slot-scope="scope">
            <span>{{ scope.row.loginTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="content" label="内容"/>
        <el-table-column label="创建时间">
          <template slot-scope="scope">
            <span>{{ scope.row.createdTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="120"
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
import { drop, delLoginLogs } from '@/api/sysmgr/loginlog';
import DataGrid from '@/components/DataGrid';
import { parseTime } from '@/utils';
import waves from '@/directive/waves'; // Waves directive

export default {
  name: 'User',
  components: { DataGrid },
  directives: { waves },
  filters: {
    parseTime
  },
  data() {
    return {
      // tableKey: 0,
      // total: 0,
      // list: null,
      // listLoading: true,
      listQuery: {
        pageNo: 1,
        limit: 10,
        id: null,
        account: null
      },
      // 选中数组
      ids: []
    };
  },
  methods: {
    onDataRest() {
      this.listQuery = {};
    },
    handleFilter() {
      this.$refs.dataList.fetchData();
    },

    // 多选框选中数据
    handleSelectionRowChange(selection) {
      // debugger // 这里的map说是没有定义
      this.ids = selection.map(item => item.id);
      // this.ids = selection
      console.log('多选框的数据=>' + this.ids)
      // this.single = selection.length !== 1
      // this.multiple = !selection.length
    },

    /** 删除按钮操作 */
    handleDelete() {
      const ids = this.ids;
      this.$confirm('是否确认删除编号为"' + ids + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delLoginLogs(ids);
      }).then(() => {
        this.$refs.dataList.fetchData();
        this.msgSuccess('删除成功');
      }).catch(function() {});
    },

    // 删除单行数据
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
          // 删除成功的提示信息
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
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
