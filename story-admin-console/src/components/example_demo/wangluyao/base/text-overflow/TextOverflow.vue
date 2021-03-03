<template>
  <div class="text-overflow">
    <!-- <el-tooltip
      popper-class="text-overflow__tooltip"
      :content="text"
      effect="light"
      :disabled="tooltipDisabled"> -->

    <div v-if="limit > 0">
      {{ content }}
    </div>
       <!-- 指令绑定  Class 与 Style  -->
       <!-- v-bind:class 绑定 HTML Class -->
       <!-- 我们也可以在这里绑定一个返回对象的计算属性。这是一个常用且强大的模式： -->
       <!-- v-bind:style 绑定内联样式 -->
    <div v-else ref="lineLimit" :class="ellipsisClass" :style="ellipsisStyle">
      {{ text }}
    </div>
    <!-- </el-tooltip> -->
  </div>
</template>

<script>
export default {
  props: {
    // 文本内容
    text: {
      type: String,
      default: ''
    },
    // 文字个数限制
    limit: {
      type: Number,
      default: 0
    },
    // 内容宽度限制
    width: {
      type: Number,
      default: 0
    },
    // 内容行数限制
    lineClamp: {
      type: Number,
      default: 0
    },
    // 是否显示tooltip
    showTooltip: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    content() {
      const { text, limit } = this
      if (text.length <= limit) {
        return text
      } else {
        return text.slice(0, limit) + '...'
      }
    },
    ellipsisClass() {
      return this.lineClamp > 0
        ? 'text-overflow__multiple'
        : 'text-overflow__single'
    },
    ellipsisStyle() {
      return {
        width: this.width > 0 ? this.width + 'px' : '100%',
        '-webkit-line-clamp': this.lineClamp
      }
    },
    tooltipDisabled() {
      const textLengthOver = text.length > limit

      return textLengthOver
    }
  }
}
</script>

<style lang="scss" scoped>
.text-overflow {
  //多行文本文字超过行数限制后显示省略号
  &__multiple {
    word-break: break-all;
    text-overflow: ellipsis;
    overflow: hidden;
    display: -moz-box;
    display: -webkit-box;
    display: -ms-flexbox;
    -webkit-box-orient: vertical;
    -moz-box-orient: vertical;
  }

  // 单行文本超出显示省略号
  &__single {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
}
</style>
<style>
.text-ellipsis__tooltip {
  max-width: 250px;
}
</style>
