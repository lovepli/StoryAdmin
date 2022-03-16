<template>
<!-- vue内置组件transition -->
<!-- 用法：<transition> 元素作为单个元素/组件的过渡效果。<transition> 只会把过渡效果应用到其包裹的内容上，而不会额外渲染 DOM 元素，也不会出现在可被检查的组件层级中。 -->
<!-- 结果：影响的组件的css表现 过渡 & 动画 -->
  <transition name="fade">
    <div
      v-show="visible"
      ref="rocket"
      class="rocket"
      @click.stop="clickRocket"
      @mouseenter="enterRocket"
      @mouseleave="leaveRocket"
    />
  </transition>
</template>

<script>
import { scroll } from '@/utils/wangluyao/core' // 滚动,先慢后快，缓动的效果比easeIn动画明显
import Animate from '@/utils/wangluyao/animate'

export default {
  props: {
    // 滚动容器
    container: {
      type: String,
      default: '.inner-layout__page'
    },
    // 页面向下滚动到多少距离才显示火箭
    visibleHeight: {
      type: Number,
      default: 100
    },
    // 火箭的位置
    position: {
      type: Object,
      default() {
        return {
          bottom: '20px',
          right: '30px'
        }
      }
    },
    // 火箭滚动所用的时间
    duration: {
      type: Number,
      default: 500
    }
  },
  data() {
    return {
      isClickRocket: false,
      visible: false,
      scrollElem: null
    }
  },
  mounted() {
    this.scrollElem = document.querySelector(this.container)
    this.scrollElem.addEventListener('scroll', this.handleRocketShow)
    const rocket = this.$refs.rocket
    rocket.style.bottom = this.position.bottom
    rocket.style.right = this.position.right
  },
  destroyed() {
    this.scrollElem.removeEventListener('scroll', this.handleRocketShow)
  },
  methods: {
    // 当页面向下滚动到移动距离时火箭才显示
    handleRocketShow() {
      if (this.scrollElem.scrollTop > this.visibleHeight) {
        this.visible = true
      } else {
        // 如果没有点击火箭，火箭才不显示，不然当火箭向上移动到visibleHeight的位置时就会消失
        if (this.isClickRocket === false) {
          this.visible = false
        }
      }
    },
    // 火箭和页面的运动都结束之后执行的回调
    callback() {
      this.isClickRocket = false
      this.visible = false
      this.changeRocketImagePosition('-31px -15px')
      // 火箭不能在向上移动到目标位置之后就立刻定位到原来的位置，因为它消失的过程有个渐进的动画，完全隐藏之后再进行定位。
      setTimeout(() => {
        this.$refs.rocket.style.bottom = this.position.bottom
      }, 500)
    },
    // 点击火箭的时候，一方面页面先快后慢滚动到顶部，一方面火箭先慢后快移动直到消失。
    clickRocket(event) {
      this.isClickRocket = true
      this.changeRocketImagePosition('-204px -15px')
      const callback = () => {
        this.isClickRocket = false
        this.visible = false
        this.changeRocketImagePosition('-31px -15px')
        // 火箭不能在向上移动到目标位置之后就立刻定位到原来的位置，因为它消失的过程有个渐进的动画，完全隐藏之后再进行定位。
        setTimeout(() => {
          this.$refs.rocket.style.bottom = this.position.bottom
        }, 500)
      }
      scroll(this.scrollElem, 0, 800)
      const animate = new Animate(this.$refs.rocket)
      // 设置火箭移动花费的时间比页面要慢点，然后在火箭运动结束后执行回调
      animate.start('bottom', window.innerHeight, 850, 'easeIn', callback)
    },
    enterRocket() {
      this.changeRocketImagePosition('-117px -15px')
    },
    leaveRocket() {
      if (this.isClickRocket == false) {
        this.changeRocketImagePosition('-31px -15px')
      }
    },
    // 切换火箭的图片
    changeRocketImagePosition(position) {
      this.$refs.rocket.style.backgroundPosition = position
    }
  }
}
</script>

<style scoped>
.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.rocket {
  position: fixed;
  width: 31px;
  height: 76px;
  background: url(~@/assets/images/common/rocket.png) no-repeat -31px -15px;
  cursor: pointer;
  z-index: 10000;
}

/* vue内置组件transition说明： */
/* 
Props：

name - string，用于自动生成 CSS 过渡类名。例如：name: 'fade' 将自动拓展为 .fade-enter，.fade-enter-active 等。默认类名为 "v"
appear - boolean，是否在初始渲染时使用过渡。默认为 false。
css - boolean，是否使用 CSS 过渡类。默认为 true。如果设置为 false，将只通过组件事件触发注册的 JavaScript 钩子。
type - string，指定过渡事件类型，侦听过渡何时结束。有效值为 "transition" 和 "animation"。默认 Vue.js 将自动检测出持续时间长的为过渡事件类型。
mode - string，控制离开/进入过渡的时间序列。有效的模式有 "out-in" 和 "in-out"；默认同时进行。
duration - number | { enter: number, leave: number } 指定过渡的持续时间。默认情况下，Vue 会等待过渡所在根元素的第一个 transitionend 或 animationend 事件。
enter-class - string
leave-class - string
appear-class - string
enter-to-class - string
leave-to-class - string
appear-to-class - string
enter-active-class - string
leave-active-class - string
appear-active-class - string

事件：
before-enter
before-leave
before-appear
enter
leave
appear
after-enter
after-leave
after-appear
enter-cancelled
leave-cancelled (v-show only)
appear-cancelled 

用法：（过渡：进入，离开和列表）
<transition> 元素作为单个元素/组件的过渡效果。<transition> 只会把过渡效果应用到其包裹的内容上，而不会额外渲染 DOM 元素，也不会出现在可被检查的组件层级中*/

</style>


