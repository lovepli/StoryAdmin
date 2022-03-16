<template>
  <div>
    <el-card shadow="never" class="card">
      <div slot="header" class="clearfix">
        <span>{{ title }}（{{ email_data.length }}）</span>
        <div style="float:right">
          <el-input v-model="input" size="small" placeholder="请输入内容" style="width:200px"/>
          <el-button size="small" type="primary">搜索</el-button>
        </div>
      </div>
      <div id="charts_one" style="width:100%;min-height:300px">
        <el-button
          :disabled="selection_button_state"
          type="danger"
          size="small"
          icon="el-icon-finished"
          @click="show_selection"
        >{{ selection_button_title }}</el-button>
        <el-table
          :data="email_data"
          max-height="350"
          style="width: 100%"
          @selection-change="selection"
        >
          <el-table-column type="selection" width="55"/>
          <el-table-column type="index" label="序号" width="50"/>
          <el-table-column property="email_send_user_name" label="收件人"/>
          <el-table-column property="to_email_title" label="邮件标题"/>
          <el-table-column property="send_email_time" label="发送时间"/>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button :title="title=='草稿'?'发送该邮件':'再次向该用户发送邮件'" size="mini" type="primary" icon="el-icon-position" @click="editor_user(scope.$index, scope.row)"/>
              <!-- 报错，引入el-popconfirm组件没有注册？？ -->
              <el-popconfirm
                confirm-button-text="确认删除"
                cancel-button-text="取消"
                confirm-button-type="danger"
                cancel-button-type="success"
                title="这是一段内容确定删除吗？"
                @onConfirm="delete_email(scope.$index, scope.row)"
              >
                <el-button slot="reference" size="mini" type="danger">
                  <i class="el-icon-delete" />
                </el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          :current-page="page_data.current_page"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="page_data.page_size"
          :total="page_data.page_total"
          layout="total, sizes, prev, pager, next, jumper"
          style="margin-top:10px"
          @size-change="page_size_change"
          @current-change="current_change"
        />
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  props: ['email_data', 'title'],
  data() {
    return {
      selection_button_title: '未选择数据',
      selection_button_state: true,
      page_data: {
        page_size: 10,
        current_page: 1,
        page_total: 200
      }
    };
  },
  mounted() {
  },
  methods: {
    delete_email() {
      console.log(this.email_data)
      this.email_data.splice(index, 1)
    }
  }
};
</script>
