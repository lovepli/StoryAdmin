<template>
  <div class="goods-list-container">
    <!-- <vab-query-form>
      <vab-query-form-right-panel :span="24">
        <el-form
          ref="form"
          :model="queryForm"
          :inline="true"
          @submit.native.prevent
        >
          <el-form-item>
            <el-input v-model="queryForm.title" placeholder="商品名称" />
          </el-form-item>
          <el-form-item>
            <el-button
              icon="el-icon-search"
              type="primary"
              native-type="submit"
              @click="handleQuery"
            >
              查询
            </el-button>
          </el-form-item>
        </el-form>
      </vab-query-form-right-panel>
    </vab-query-form>-->
    <el-row :gutter="20">
      <el-col
        v-for="(item, index) in list"
        :key="index"
        :xs="24"
        :sm="8"
        :md="8"
        :lg="8"
        :xl="6"
      >
        <el-card :body-style="{ padding: '0px' }" shadow="hover">
          <div class="goods-list-card-body">
            <div class="goods-list-tag-group">
              <el-tag v-if="item.isRecommend" hit type="success">推荐</el-tag>
              <el-tag v-if="item.status === 0" hit type="danger">缺货</el-tag>
            </div>
            <div class="goods-list-image-group">
              <img :src="item.image" class="goods-list-image" >
            </div>
            <div class="goods-list-title">{{ item.title }}</div>
            <div class="goods-list-description">{{ item.description }}</div>
            <div class="goods-list-price">
              <span>¥ {{ item.price }} 元</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-pagination
      :total="total"
      :page-size="queryForm.pageSize"
      :layout="layout"
      :current-page="queryForm.pageNo"
      background
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
    />
  </div>
</template>

<script>
import Mock from 'mockjs';
// import { getList } from '@/api/goodsList'

const List = [];
const count = 999;
let num = 0;
for (let i = 0; i < count; i++) {
  List.push(
    Mock.mock({
      uuid: '@uuid',
      image: `https://picsum.photos/300/600?random=${num++}`,
      title: '@ctitle',
      description: '@csentence',
      link: 'https://www.baidu.com',
      price: '@integer(100, 500)',
      'status|1': [1, 0],
      'isRecommend|1': [1, 0]
    })
  );
}

export default {
  name: 'Goods',
  components: {},
  data() {
    return {
      queryForm: {
        pageNo: 1,
        pageSize: 20,
        title: ''
      },
      list: null,
      listLoading: true,
      layout: 'total, sizes, prev, pager, next, jumper',
      total: 0,
      elementLoadingText: '正在加载...'
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    handleSizeChange(val) {
      this.queryForm.pageSize = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.queryForm.pageNo = val;
      this.fetchData();
    },
    handleQuery() {
      this.queryForm.pageNo = 1;
      this.fetchData();
    },
    async fetchData() {
      this.listLoading = true;
      this.getList();
      // const { data, totalCount } = await getList(this.queryForm)
      // console.log('--' + JSON.stringify(data))
      // this.list = data
      // this.total = totalCount
    },
    // 分页查询
    getList() {
      const { title = '', pageNo = 1, pageSize = 20 } = this.queryForm
      const mockList = List.filter(item => {
        if (title && item.title.indexOf(title) < 0) return false;
        return true;
      });
      const pageList = mockList.filter(
        (item, index) =>
          index < pageSize * pageNo && index >= pageSize * (pageNo - 1)
      );

      this.list = pageList;
      this.total = count;
    }
  }
};
</script>
<style lang="scss" scoped>
.goods-list-container {
  .goods-list-card-body {
    position: relative;
    text-align: center;
    cursor: pointer;

    .goods-list-tag-group {
      position: absolute;
      top: 10px;
      right: 5px;
      z-index: 9;
    }

    .goods-list-image-group {
      height: 400px;
      overflow: hidden;

      .goods-list-image {
        width: 100%;
        height: 400px;
        transition: all ease-in-out 0.3s;

        &:hover {
          transform: scale(1.1);
        }
      }
    }

    .goods-list-title {
      margin: 8px 0;
      font-size: 16px;
      font-weight: bold;
    }

    .goods-list-description {
      font-size: 14px;
      color: #808695;
    }

    .goods-list-price {
      margin: 8px 0;
      font-size: 14px;
      color: #ff6700;

      s {
        color: #c5c8ce;
      }
    }
  }
}
</style>
