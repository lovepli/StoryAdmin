<template>
  <el-date-picker
    v-model="myValue"
    :start-placeholder="`${start}`"
    :end-placeholder="`${end}`"
    :picker-options="pickerOptions"
    :class="dateClass"
    type="daterange"
    align="right"
    unlink-panels
    range-separator="至"
    style="width:290px"
  />
</template>
<script>
export default {
  name: 'DateBetween',
  // props 可以是数组或对象，用于接收来自父组件的数据。props 可以是简单的数组，或者使用对象作为替代，对象允许配置高级选项，如类型检测、自定义验证和设置默认值。
  props: {
    start: {
      required: true, // required定义该 prop 是否是必填项。在非生产环境中，如果这个值为 truthy 且该 prop 没有被传入的，则一个控制台警告将会被抛出。
      type: String // type:检测类型
    },
    end: {
      required: true,
      type: String
    },
    // 检测类型 + 其他验证
    value: {
      required: false,
      type: Array,
      default: () => { return [] } // default:为该 prop 指定一个默认值。如果该 prop 没有被传入，则换做用这个值。对象或数组的默认值必须从一个工厂函数返回。
    },
    dateClass: {
      type: String,
      required: false,
      default: '' // default: 为该 prop 指定一个默认值。如果该 prop 没有被传入，则换做用这个值。对象或数组的默认值必须从一个工厂函数返回。
    },
    /**
     * 失效的日期
     */
    disabledDate: {
      type: Function,
      default: d => { return false }
    }
  },
  data() {
    return {
      myValue: this.value,
      pickerOptions: {
        shortcuts: [
          {
            text: '本月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * (start.getDate() - 1))
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '上月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * start.getDate())
              end.setTime(start.getTime())
              start.setTime(start.getTime() - 3600 * 1000 * 24 * (start.getDate() - 1))
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近7天',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近15天',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 15)
              picker.$emit('pick', [start, end])
            }
          },
          {
            text: '最近30天',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          }
        ],
        disabledDate: this.disabledDate
      }
    }
  },
  watch: {
    myValue(val) { this.$emit('change', val) },
    value(val) { this.myValue = val },
    disabledDate(val) { this.pickerOptions.disabledDate = val }
  },
  created() { }
}
</script>
