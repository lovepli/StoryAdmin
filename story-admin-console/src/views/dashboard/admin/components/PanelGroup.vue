<template>
  <div>

    <el-row :gutter="40" class="panel-group">

      <el-col :span="17">
        <inform-list class="dashboard-component" style="padding-bottom:45px" />
      </el-col>
      <el-col :span="7">
        <todo-list class="dashboard-component" />
      </el-col>

    </el-row>

    <!-- Weather -->
    <el-row :gutter="20" class="panel-group">
      <div class="card-title">{{ weather.city }}天气</div>
      <!-- 列表渲染 v-for 还支持一个可选的第二个参数，即当前项的索引index。-->
      <!-- 为了给 Vue 一个提示，以便它能跟踪每个节点的身份，从而重用和重新排序现有元素，你需要为每项提供一个唯一 key attribute
      :k的使用说明：不要使用对象或数组之类的非基本类型值作为 v-for 的 key。请用字符串或数值类型的值。 -->
      <!-- 你也可以提供第二个的参数为 property 名称 (也就是键名) -->
      <!-- 还可以用第三个参数作为索引 -->
      <!-- 遍历一周的天气状况 -->
      <el-col
        v-for="(item,index) in weather.dateList"
        :xs="12"
        :sm="12"
        :lg="(index<3?4:3)"
        :key="index"
        :label="item"
        class="card-panel-col"
      >
        <!-- 这里只显示后三天的天气状况  -->
        <div class="wea-panel">
          <div :class="'wea-panel-icon-wrapper'+(index<3?' float-left':'')">
            <img :src="item.weaImg" :alt="item.wea" >
          </div>
          <div v-if="index<3" class="wea-panel-description">
            <div class="wea-panel-date">{{ item.date | parseTime('{m}-{d}') }} <span>{{ item.week }}</span></div>
            <div class="wea-panel-text">{{ item.wea }}</div>
            <div class="wea-panel-text">{{ item.tem2 }} - {{ item.tem1 }}</div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getWeather } from '@/api/dashboard';
import { parseTime } from '@/utils';
import InformList from './InformList'
import TodoList from './TodoList'
export default {
  components: { InformList, TodoList }, // 引入子组件
  filters: {
    parseTime
  },
  data() {
    return {
      weather: {}
    };
  },
  // 只要执行完了mounted，表示整个Vue实例已经初始化完毕了，此时组件已经进入了运行阶段。
  mounted() {
    this.showWeather();
  },
  methods: {
    showWeather() {
      const _this = this;
      // this.treeloading = true;
      getWeather().then(res => {
        res.data.dateList.forEach(element => {
          // 这个特殊的 `require` 语法将会告诉 webpack
          // 自动将你的构建代码切割成多个包，这些包
          // 会通过 Ajax 请求加载
          element.weaImg = require('@/assets/weather/' +
            element.weaImg +
            '.png');
        });
        // console.log(res.data)
        _this.weather = res.data;
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
/* 自定义类名，下面都是自定义的外部css样式, 这里的样式都是引入stylesheet/scss的样式 */
.panel-group {
  margin-top: 18px;
  .card-title {
    margin: 0px 10px 6px 10px;
    padding-left: 10px;
    text-align: left;
    font-size: 16px;
    font-weight: 600;
    color: rgba(0, 0, 0, 0.75);
  }
  .card-panel-col {
    margin-bottom: 22px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(0, 0, 0, 0.05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
        background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shopping {
        background: #34bfa3;
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shopping {
      color: #34bfa3;
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }

  .float-left {
    float: left;
  }

  .wea-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(241, 221, 221, 0.05);
    .wea-panel-icon-wrapper {
      padding: 3px;
      margin: 10px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
      background-color: #ddd;
    }
    .wea-panel-description {
      float: left;
      font-weight: bold;
      margin: 16px 0px 0px 0px;
      .wea-panel-date {
        line-height: 28px;
        color: rgba(0, 0, 0, 0.75);
        font-size: 14px;
        // margin-bottom: 12px;
      }
      .wea-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 12px;
        // margin-bottom: 12px;
      }
      .wea-panel-num {
        font-size: 20px;
      }
    }
  }
}

.dashboard-component {
  background: #fff;
  padding: 16px 16px 30px;
}
</style>
