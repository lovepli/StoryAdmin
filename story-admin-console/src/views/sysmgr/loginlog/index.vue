<template>
  <div class="app-container">
    <data-grid
      ref="dataList"
      url="/sysmgr/loginlog/list"
      data-name="listQuery"
      @dataRest="onDataRest"
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
      <!--extendOperation-->
      <template slot="extendOperation"/>
      <!--body-->
      <template slot="body">
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
// eslint-disable-next-line no-unused-vars
import { getList, drop } from '@/api/sysmgr/loginlog';

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
