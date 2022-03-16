// bus用来在飞父子组件间进行通信

import Vue from 'vue';

const bus = new Vue();

export default bus;

// 知识点：vue使用bus总线，实现非父子组件间的通信 https://www.cnblogs.com/jin-zhe/p/13100415.html
// vue组件通信方式有好多，可以使用props传值，但是props只能父子组件使用。也可以使用vuex，但是vuex比较重，而且非全局的通信最好不要使用vuex
// 在简单的场景下，可以使用一个空的Vue实例作为中央事件总线。
// 这里有两种方式可以使用
// 第一种是新建一个bus.js文件，初始化一个空的Vue实例，作为中央总线，然后再组件引用时调用这个bus.js文件

// import Vue from 'vue';
// const EventBus = new Vue();
// export default EventBus;

// 第二种是全局定义，将bus挂载到vue.prototype上
// import Vue from 'vue';
// Vue.prototype.bus = new Vue();

// 例子：
// 例子就是 left组件传值给bus，然后right组件监听bus的isLest事件，当left组件触发事件的时候，right组件就会触发方法，替换页面的值

// 总结
// 1.可以实现vue跨级组件之间的通信，在实际的开发项目中，如果数据和业务逻辑不是特别复杂，没有必要使用Vuex，那么我们就可以通过这种方式，实现我们再实际业务逻辑中的组件间数据传递，而且代码比较简洁直观。
// 2.注册的总线事件要在组件销毁时卸载，否则会多次挂载，造成触发一次但多个响应的情况

// 补充知识点：vue的$on,$emit的使用
// vue中使用 $emit(eventName) 触发事件，使用 $on(eventName) 监听事件
// $emit(eventName)  触发当前实例上的事件，附加参数都会传给监听器回调。
// $on(eventName) 监听当前实例上的自定义事件。事件可以由 vm.$emit 触发。回调函数会接收所有传入事件触发函数的额外参数。

// 总结
// 1.使用$emit传递事件
// 2.使用$on监听事件
// 3.可以本页面使用，也可以父子组件使用，也可以非关联组件使用

