<template>

  <div class="invoice-list">
    <!-- 表头的值,自己单独写的 -->
    <ul class="invoice-header">
      <li class="invoice-item">发票号</li>
      <li class="invoice-item">订单号</li>
      <li class="invoice-item">发票金额</li>
      <li class="invoice-item">开票日期</li>
      <li class="invoice-item">收款方式</li>
      <li class="invoice-item">状态</li>
      <li class="invoice-item">发票收款日期</li>
      <li class="invoice-item">操作</li>
    </ul>
    <el-tree
      ref="treeData"
      :props="props"
      :data="tableData"
      show-checkbox
      default-expand-all
      @check-change="handleCheckChange">
      <!-- 使用自定义,需要加slot-scope,返回两个值,node是当前节点指定的对象data是当前节点的数据 -->
     <!-- 作用域插槽slot-scope="scope"的使用-->
     <!-- v-slot 的值实际上可以是任何能够作为函数定义中的参数的 JavaScript 表达式。所以在支持的环境下 (单文件组件或现代浏览器)，你也可以使用 ES2015 解构来传入具体的插槽 prop，如下： -->
     <!-- <current-user v-slot="{ user }">
            {{ user.firstName }}
       </current-user> -->
   <!--这样可以使模板更简洁，尤其是在该插槽提供了多个 prop 的时候。它同样开启了 prop 重命名等其它可能，例如将 user 重命名为 person：  -->
   <!-- <current-user v-slot="{ user: person }">
          {{ person.firstName }}
   </current-user> -->
      <div slot-scope="{ node, data }" class="custom-tree-node">
        <div v-if="data.span" class="total_info_box clearfix">
          <span><i>对账单号:</i> {{ data.accountNo | isEmptyVal }}</span>
          <span><i>对账金额:</i> {{ data.totalReconciledAmount | formatUSD }}</span>
          <span><i>对账日期:</i> {{ data.confirmAccountDate | formatYMD }}</span>
        </div>
        <span v-else class="table_info_node">
          <span class="table_info_item">{{ data.invoiceNo }}</span>
          <span class="table_info_item">{{ data.orderNo }}</span>
          <span class="table_info_item">{{ data.totalAmountTax }}</span>
          <span class="table_info_item">{{ data.billingDate | formatYMD }}</span>
          <span class="table_info_item">{{ data.forCollection }}</span>
          <span class="table_info_item">{{ data.requestStatus }}</span>
          <span class="table_info_item">{{ data.receiptDate }}</span>
          <span class="table_info_item"><el-button @click="toInvoiceDetail(data)">详情</el-button></span>
        </span>
      </div>
    </el-tree>

  </div>
</template>

<script>
import filter from '@/utils/example_demo/filter'

export default {
  filters: { filter },
  data() {
    return {
      props: {
        label: 'accountNo', // 需要指定的节点渲染对象属性
        children: 'orderInvoiceAssemblyList' // 指定的子级
      },
      tableData: [] // tree组件渲染的数据
    }
  },

  created() {
    this.getSupplierPayInvoice()
  },
  // 方法集合
  methods: {
    // tree组件渲染的数据列表
    getSupplierPayInvoice() {
      this.tableData = [{
        accountId: 13,
        accountNo: `66`,
        orderNo: '444',
        totalReconciledAmount: 1000,
        confirmAccountDate: 1548482834000,
        span: true,
        orderInvoiceAssemblyList: [{
          invoiceNo: '67448',
          orderNo: '444',
          totalAmountTax: 1999,
          billingDate: 1548482834000,
          forCollection: 999,
          requestStatus: '未付款',
          receiptDate: '2019-1-30',
          accountInvoiceId: 11
        }, {
          orderNo: '55',
          totalAmountTax: 2999,
          billingDate: 1548482834000,
          forCollection: 5555,
          requestStatus: 1,
          accountInvoiceId: 12
        }]
      }, {
        accountId: 14,
        accountNo: '789',
        orderNo: '444',
        totalReconciledAmount: 2000,
        confirmAccountDate: 1548482834000,
        span: true,
        orderInvoiceAssemblyList: [{
          orderNo: '888',
          totalAmountTax: 3999,
          billingDate: 1548482834000,
          forCollection: 999,
          requestStatus: 2,
          accountInvoiceId: 13
        }, {
          orderNo: '999',
          totalAmountTax: 4888,
          billingDate: 1548482834000,
          forCollection: 5555,
          requestStatus: 1,
          accountInvoiceId: 14
        }, {
          orderNo: '889',
          totalAmountTax: 4888,
          billingDate: 1548482834000,
          forCollection: 5555,
          requestStatus: 1,
          accountInvoiceId: 15
        }]
      }]
    },

    // tree组件选择改变事件
    handleCheckChange() {
      // console.log(val)
      // 使用getCheckedNodes可以获取当前被选中的节点数据
      const selected = this.$refs.treeData.getCheckedNodes()
      console.log(33, selected)
    }
  }
}
</script>
<style lang="less">
.invoice-list {
  border: 1px solid #ebeef5;
  margin-top: 10px;
  .invoice-header {
    background-color: #f8f8f9;
    display: flex;
    padding-left: 63px;
    border-bottom: 1px solid #ebeef5;
    .invoice-item {
      padding: 10px;
      padding-right: 0;
      flex: 1;
      border-left: 1px solid #ebeef5;
      padding-left: 10px;
    }
  }
  .el-tree-node__content {
    background: #f2f2f2;
    height: 40px;
  }
  .el-tree-node__children {
    .el-tree-node__content {
      background: #fff;
      border-bottom: 1px solid #ebeef5;
    }
  }
  .custom-tree-node {
    width: 100%;
    height: 100%;
    .total_info_box {
      background: #f2f2f2;
      line-height: 40px;
      span{
        float: left;
        font-size: 12px;
        margin: 0 15px;
        i{
          display: inline-block;
          margin-right: 3px;
        }
      }
    }
    .table_info_node {
      display: flex;
      height: 100%;
      .table_info_item {
        flex: 1;
        height: 100%;
        border-left: 1px solid #ebeef5;
        padding-left: 10px;
        line-height: 40px;
      }
    }
  }
}
</style>
