<template>
  <div class="vab-ad">
    <el-carousel
      v-if="adList"
      height="30px"
      direction="vertical"
      :autoplay="true"
      :interval="3000"
      indicator-position="none"
    >
      <el-carousel-item v-for="(item, index) in adList" :key="index">
        <el-tag type="warning">Ad</el-tag>
        <a target="_blank" :href="item.url">{{ item.title }}</a>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
// import Mock from 'mockjs';
  // import { getList } from '@/api/ad'

  const data = [
  {
    title:
      'vue-admin-beautiful-pro 2.0版本已发布，增加多项贴心功能，点我提前体验',
    url:
      'http://beautiful.panm.cn/vue-admin-beautiful-pro?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=',
  },
  {
    title: 'vue-admin-beautiful（antdv） vue3.0版本已发布，点我提前体验',
    url:
      'http://beautiful.panm.cn/vue-admin-beautiful-antdv?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=',
  },
  {
    title: 'vue-admin-beautiful（element-plus） vue3.0版本已发布，点我提前体验',
    url:
      'http://beautiful.panm.cn/vue-admin-beautiful-element-plus?hmsr=homeAd&hmpl=&hmcu=&hmkw=&hmci=',
  },
]
  export default {
    name: 'VabAd',
    data() {
      return {
        nodeEnv: process.env.NODE_ENV,
        adList: [],
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      // async fetchData() {
      //   const { data } = await getList()
      //   this.adList = data
      // },

        fetchData() {
        // const { data } = data
        this.adList = data
      },
    },
  }
</script>
<style lang="scss" scoped>
  .vab-ad {
    height: 30px;
    padding-right: 20px;
    padding-left: 20px;
    margin-bottom: -20px;
    line-height: 30px;
    cursor: pointer;

    a {
      color: #999;
    }
  }
</style>
