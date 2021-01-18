<template>
  <el-table :data="tableData" border v-loading="tabLoading">
    <el-table-column prop="name" label="姓名" width="180px"> </el-table-column>
    <el-table-column prop="age" label="年龄" width="180px"></el-table-column>
    <el-table-column prop="gender" label="性别"></el-table-column>
    <el-table-column prop="email" label="邮箱"></el-table-column>
    <el-table-column prop="mobilePhone" label="手机"></el-table-column>
  </el-table>
</template>

<script>
import Mock from 'mockjs';
 // import api from '@/api';
 const firstData = Mock.mock({
  "list|7": [{
    name: "@cname",
    age: "@natural(30,40)",
    gender: '@pick(["男", "女"])',
    email: "@email",
    mobilePhone: /^1[345789]\d{9}$/,
  }]
})

const secondData = Mock.mock({
  "list|10": [{
    name: "@cname",
    age: "@natural(30,40)",
    gender: '@pick(["男", "女"])',
    email: "@email",
    mobilePhone: /^1[345789]\d{9}$/,
  }]
})

const thirdData = Mock.mock({
  "list|6": [{
    name: "@cname",
    age: "@natural(30,40)",
    gender: '@pick(["男", "女"])',
    email: "@email",
    mobilePhone: /^1[345789]\d{9}$/,
  }]
})

const fourthData = Mock.mock({
  "list|12": [{
    name: "@cname",
    age: "@natural(30,40)",
    gender: '@pick(["男", "女"])',
    email: "@email",
    mobilePhone: /^1[345789]\d{9}$/,
  }]
})

  export default {
    props: {
      name: {
        required: true,
        type: String,
      }
    },
    data() {
      return {
        tableData: [],
        tabLoading: false
      }
    },
    created() {
     this.getList();
    },
methods: {
   getList(){
      this.tabLoading = true
      let data = {};
    switch (this.name ) {
      case 'first':
        data = firstData.list;
        break;
      case 'second':
        data = secondData.list;
        break;
      case 'third':
        data = thirdData.list;
        break;
      case 'fourth':
        data = fourthData.list;
        break;
      default:
        data = {};
    }
        this.tableData = data;
        this.tabLoading = false;
      }

    }
  }
</script>
