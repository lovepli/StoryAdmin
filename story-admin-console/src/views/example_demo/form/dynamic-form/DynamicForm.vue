<template>
  <div class="form-dynamic">
    <el-form ref="form" :model="form" label-position="top">
<!-- vue内置组件transition-group -->
<!-- Props: -->
      <!-- 1、tag - string，默认为 span -->
      <!-- 2、move-class - 覆盖移动过渡期间应用的 CSS 类 -->
      <!-- 3、除了 mode，其他 attribute 和 <transition> 相同 -->
<!-- 事件：事件和 <transition> 相同 -->
<!-- 用法：-->
<!-- 1、<transition-group> 元素作为多个元素/组件的过渡效果。<transition-group> 渲染一个真实的 DOM 元素。默认渲染 <span>，可以通过 tag attribute 配置哪个元素应该被渲染。 -->
<!-- 2、注意，每个 <transition-group> 的子节点必须有独立的 key，动画才能正常工作 -->
<!-- 3、<transition-group> 支持通过 CSS transform 过渡移动。当一个子节点被更新，从屏幕上的位置发生变化，它会被应用一个移动中的 CSS 类 (通过 name attribute 或配置 move-class attribute 自动生成)。如果 CSS transform property 是“可过渡”property，当应用移动类时，将会使用 FLIP 技术使元素流畅地到达动画终点。 -->
<!-- 结果：影响的组件的css表现 过渡 & 动画 -->
      <transition-group name="block" tag="div">
        <div v-for="(item,index) in form.workItem" :key="item.id" class="block-item">
          <div class="block-item__index">{{ index + 1 }}</div>

          <el-row>
            <el-col :span="12">
              <el-form-item :label="item.date.label">
                <el-date-picker
                  v-model="item.date.value"
                  type="daterange"
                  format="yyyy年MM月dd日"
                  value-format="yyyy-MM-dd"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  unlink-panels/>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item :label="item.department.label">
                <el-input v-model="item.department.value"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item :label="item.title.label">
            <el-input v-model="item.title.value"/>
          </el-form-item>

          <el-form-item :label="item.task.label">
            <el-input v-model="item.task.value"/>
          </el-form-item>

          <i class="el-icon-remove block-item--remove" @click="handleRemove(index)"/>
        </div>
      </transition-group>
    </el-form>

    <div class="block-item--add" @click="handleAdd">点击新增履历</div>
    <div style="text-align:center;">
      <el-button :loading="submitLoading" type="primary" round @click="handldeSubmit">提交</el-button>
    </div>
  </div>
</template>

<script>
import { guid } from '@/utils/wangluyao/core';

const workTemplate = {
  date: {
    label: '起止时间',
    value: []
  },
  department: {
    label: '所在单位',
    value: ''
  },
  title: {
    label: '职务职称',
    value: ''
  },
  task: {
    label: '主要工作',
    value: ''
  }
}

export default {
  name: 'DynamicForm',
  data() {
    return {
      form: {
        workItem: []
      },
      submitLoading: false
    }
  },
  created() {
    this.initWorkItem(3);
  },
  methods: {
    initWorkItem(amount) {
      for (let i = amount; i--;) {
        this.handleAdd();
      }
    },
    handleAdd() {
      const template = Object.assign({}, workTemplate);
      template.id = guid();
      this.form.workItem.push(template);
    },
    handleRemove(index) {
      this.form.workItem.splice(index, 1)
    },
    handldeSubmit() {
      this.$message.success('提交成功！');
    }
  }
}
</script>

<style lang="scss" scoped>
  .form-dynamic {
    box-sizing: border-box;
    width: 1000px;
    padding: 20px 100px;
    margin: 0 auto;
    border-radius: 10px;
    background-color: #fff;

    .block-item {
      position: relative;
      box-sizing: border-box;
      width: 800px;
      border-left: 1px solid #e6e6e6;
      border-right: 1px solid #e6e6e6;
      margin: 20px 0px;
      padding: 0px 10px;
      transition: all 1s;
    }

    .block-enter,
    .block-leave-to {
      opacity: 0;
      transform: translateY(30px);
    }

    .block-leave-active {
      position: absolute;
    }

    .block-item--remove {
      position: absolute;
      top: 50%;
      right: -61px;
      transform: translate(0, -11px);
      font-size: 28px;
      color: #d95d5d;
      cursor: pointer;
    }

    .block-item--add {
      margin: 0px -100px 20px -100px;
      height: 200px;
      line-height: 200px;
      text-align: center;
      background: url(~@/assets/wangluyao/images/form/add.jpg) no-repeat;
      background-size: cover;
      color: #fff;
      font-size: 16px;
      cursor: pointer;
    }

    .block-item__index {
      position: absolute;
      left: -100px;
      top: 50%;
      transform: translate(0, -30px);
      width: 100px;
      height: 60px;
      font-size: 50px;
      font-weight: 500;
      background: linear-gradient(to bottom right, red, blue);
      color: transparent;
      -webkit-background-clip: text;
      text-align: center
    }
  }
</style>

<style>
  .form-dynamicm .el-form-item__label {
    line-height: 20px;
  }
</style>
