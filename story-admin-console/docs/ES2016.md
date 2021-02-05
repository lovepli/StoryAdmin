# STORY-ADMIN
## Vue-全局API


// VUE基础语法复习：https://docs.tumo.tycoding.cn/#/docs/vue/vue-2
// 后端的MVC和前端的MVVM之间的区别:
// MVC是后端的分层开发概念
// MVVM是前端视图层的概念，主要关注于：视图层分离；也就是说：MVV将前端分为三个部分Model、View、VM（ViewModel）
// 1、Model： 页面需要展示的数据(这里的M保存的是每个页面中单独的数据)
// 2、View: 视图、HTML(就是每个页面中的HML结构)
// 3、VM: 数据（Model）和视图（View）之间的调度者(他是一个调度者，分割了M和V，每当v层想要获取后保存数据的时候，都要由vm做中间的处理)

// 基础语法：
// v-bind:
// 被v-bind:绑定的属性，其元素不再是字符串，而是被识别为Vue的绑定的变量（同样这个变量必须声明了）。另外v-bind:还有一个简易写法：:
// v-bind:value="msg" 可以简写为 :value="msg"

// v-bind指令说明：
//缩写：:
//修饰符：
//.prop - 作为一个 DOM property 绑定而不是作为 attribute 绑定。(差别在哪里？)
//.camel - (2.1.0+) 将 kebab-case attribute 名转换为 camelCase。(从 2.1.0 开始支持)
//.sync (2.3.0+) 语法糖，会扩展成一个更新父组件绑定值的 v-on 侦听器。
//用法：
//1、动态地绑定一个或多个 attribute，或一个组件 prop 到表达式。
//2、在绑定 class 或 style attribute 时，支持其它类型的值，如数组或对象。可以通过下面的教程链接查看详情。
//3、在绑定 prop 时，prop 必须在子组件中声明。可以用修饰符指定不同的绑定类型。
//4、没有参数时，可以绑定到一个包含键值对的对象。注意此时 class 和 style 绑定不支持数组和对象。
//示例：
//<!-- 绑定一个 attribute -->
//<img v-bind:src="imageSrc">
//
//<!-- 动态 attribute 名 (2.6.0+) -->
//<button v-bind:[key]="value"></button>
//
//<!-- 缩写 -->
//<img :src="imageSrc">
//
//<!-- 动态 attribute 名缩写 (2.6.0+) -->
//<button :[key]="value"></button>
//
//<!-- 内联字符串拼接 -->
//<img :src="'/path/to/images/' + fileName">
//
//<!-- class 绑定 -->
//<div :class="{ red: isRed }"></div>
//<div :class="[classA, classB]"></div>
//<div :class="[classA, { classB: isB, classC: isC }]">
//
//<!-- style 绑定 -->
//<div :style="{ fontSize: size + 'px' }"></div>
//<div :style="[styleObjectA, styleObjectB]"></div>
//
//<!-- 绑定一个全是 attribute 的对象 -->
//<div v-bind="{ id: someProp, 'other-attr': otherProp }"></div>
//
//<!-- 通过 prop 修饰符绑定 DOM attribute -->
//<div v-bind:text-content.prop="text"></div>
//
//<!-- prop 绑定。“prop”必须在 my-component 中声明。-->
//<my-component :prop="someThing"></my-component>
//
//<!-- 通过 $props 将父组件的 props 一起传给子组件 -->
//<child-component v-bind="$props"></child-component>
//
//<!-- XLink -->
//<svg><a :xlink:special="foo"></a></svg>
//.camel 修饰符允许在使用 DOM 模板时将 v-bind property 名称驼峰化，例如 SVG 的 viewBox property：
//
//<svg :view-box.camel="viewBox"></svg>
//在使用字符串模板或通过 vue-loader/vueify 编译时，无需使用 .camel。


// v-on:
// Vue提供了事件绑定机制的指令：v-on:；用其我们可以用来绑定一些常见的触发事件：click、mouseover … 另外v-on:还有一个简易写法：@
// v-on:click="show" 可以简写为 @click="show"

// v-on指令说明：
// 缩写 ：@
//修饰符：
//.stop - 调用 event.stopPropagation()。
//.prevent - 调用 event.preventDefault()。
//.capture - 添加事件侦听器时使用 capture 模式。
//.self - 只当事件是从侦听器绑定的元素本身触发时才触发回调。
//.{keyCode | keyAlias} - 只当事件是从特定键触发时才触发回调。
//.native - 监听组件根元素的原生事件。
//.once - 只触发一次回调。
//.left - (2.2.0) 只当点击鼠标左键时触发。
//.right - (2.2.0) 只当点击鼠标右键时触发。
//.middle - (2.2.0) 只当点击鼠标中键时触发。
//.passive - (2.3.0) 以 { passive: true } 模式添加侦听器

//用法：
//1、绑定事件监听器。事件类型由参数指定。表达式可以是一个方法的名字或一个内联语句，如果没有修饰符也可以省略。
//2、用在普通元素上时，只能监听原生 DOM 事件。用在自定义元素组件上时，也可以监听子组件触发的自定义事件。
//3、在监听原生 DOM 事件时，方法以事件为唯一的参数。如果使用内联语句，语句可以访问一个 $event property：v-on:click="handle('ok', $event)"。
//4、从 2.4.0 开始，v-on 同样支持不带参数绑定一个事件/监听器键值对的对象。注意当使用对象语法时，是不支持任何修饰器的。

//示例：
//<!-- 方法处理器 -->
//<button v-on:click="doThis"></button>
//
//<!-- 动态事件 (2.6.0+) -->
//<button v-on:[event]="doThis"></button>
//
//<!-- 内联语句 -->
//<button v-on:click="doThat('hello', $event)"></button>
//
//<!-- 缩写 -->
//<button @click="doThis"></button>
//
//<!-- 动态事件缩写 (2.6.0+) -->
//<button @[event]="doThis"></button>
//
//<!-- 停止冒泡 -->
//<button @click.stop="doThis"></button>
//
//<!-- 阻止默认行为 -->
//<button @click.prevent="doThis"></button>
//
//<!-- 阻止默认行为，没有表达式 -->
//<form @submit.prevent></form>
//
//<!--  串联修饰符 -->
//<button @click.stop.prevent="doThis"></button>
//
//<!-- 键修饰符，键别名 -->
//<input @keyup.enter="onEnter">
//
//<!-- 键修饰符，键代码 -->
//<input @keyup.13="onEnter">
//
//<!-- 点击回调只会触发一次 -->
//<button v-on:click.once="doThis"></button>
//
//<!-- 对象语法 (2.4.0+) -->
//<button v-on="{ mousedown: doThis, mouseup: doThat }"></button>

//在子组件上监听自定义事件 (当子组件触发“my-event”时将调用事件处理器)：
//<my-component @my-event="handleThis"></my-component>
//
//<!-- 内联语句 -->
//<my-component @my-event="handleThis(123, $event)"></my-component>
//
//<!-- 组件中的原生事件 -->
//<my-component @click.native="onClick"></my-component>

// v-model
// 唯一的双向绑定指令：v-model
// 单向绑定指令：v-bing

// v-model用法:
//1、预期：随表单控件类型不同而不同。
//2、限制：
//<input>
//<select>
//<textarea>
//components
//3、修饰符：
//.lazy - 取代 input 监听 change 事件
//.number - 输入字符串转为有效的数字
//.trim - 输入首尾空格过滤
//4、用法：在表单控件或者组件上创建双向绑定。



// v-for用法： 基于源数据多次渲染元素或模板块。此指令之值，必须使用特定语法 alias in expression，为当前遍历的元素提供别名：
      //<div v-for="item in items">
      //  {{ item.text }}
      //</div>
// 另外也可以为数组索引指定别名 (或者用于对象的键)：在遍历对象时，会按 Object.keys() 的结果遍历，但是不能保证它的结果在不同的 JavaScript 引擎下都一致。
      //<div v-for="(item, index) in items"></div>
      //<div v-for="(val, key) in object"></div>
      //<div v-for="(val, name, index) in object"></div>
// v-for 的默认行为会尝试原地修改元素而不是移动它们。要强制其重新排序元素，你需要用特殊 attribute key 来提供一个排序提示：
// 为了给 Vue 一个提示，以便它能跟踪每个节点的身份，从而重用和重新排序现有元素，你需要为每项提供一个唯一 key attribute：
      //<div v-for="item in items" :key="item.id">
      //  {{ item.text }}
      //</div>
// 建议尽可能在使用 v-for 时提供 key attribute，除非遍历输出的 DOM 内容非常简单，或者是刻意依赖默认行为以获取性能上的提升。
// 因为它是 Vue 识别节点的一个通用机制，key 并不仅与 v-for 特别关联。后面我们将在指南中看到，它还具有其它用途。
// 注意：不要使用对象或数组之类的非基本类型值作为 v-for 的 key。请用字符串或数值类型的值。

// Vue提供了遍历集合、数组的指令：v-for；用法: v-for="别名 in 集合名"
// 注意：
// 在vue2.0+版本里，当使用v-for渲染数据，必须制定对应的key值（这里的key是一个属性，不是前面迭代的key值）
// 当和 v-if 一起使用时，v-for 的优先级比 v-if 更高
// 用法:
// <p v-for="item in user" :key="item.id">
// 其中 :key 就说明了key属性必须是通过v-bind绑定的元素，而 :key="" 中指定的值必须是string/number类型的值，比如此处使用的是item.id中ID是number值，并且是唯一的。
// 目的：避免迭代元素时，为循环元素绑定的是列表中的第几个元素（指定位置），而不是指定的某个元素（指定身份）。

// 特殊 attribute说明
// key说明：
// key 的特殊 attribute 主要用在 Vue 的虚拟 DOM 算法，在新旧 nodes 对比时辨识 VNodes。如果不使用 key，Vue 会使用一种最大限度减少动态元素并且尽可能的尝试就地修改/复用相同类型元素的算法。而使用 key 时，它会基于 key 的变化重新排列元素顺序，并且会移除 key 不存在的元素。
// 有相同父元素的子元素必须有独特的 key。重复的 key 会造成渲染错误。最常见的用例是结合 v-for：
   //<ul>
   //  <li v-for="item in items" :key="item.id">...</li>
   //</ul>
// 它也可以用于强制替换元素/组件而不是重复使用它。当你遇到如下场景时它可能会很有用：
   //1、完整地触发组件的生命周期钩子
   //2、触发过渡
   // 例如下面：当 text 发生改变时，<span> 总是会被替换而不是被修改，因此会触发过渡。
   //<transition>
   //<span :key="text">{{ text }}</span>
   //</transition>

// v-show和v-if
// Vue提供了两个指令来实现元素显示状态的切换：v-if v-show
// 区别:
// 1、v-if的特点：每次都会重新删除和创建元素，具有较高的切换性能消耗（因为每次执行都要进行删除和创建元素）。
// 1-1、v-if另外一个很少用到的方法：用 key 管理可复用的元素， Vue 为你提供了一种方式来表达“这两个元素是完全独立的，不要复用它们”。只需在元素添加一个具有唯一值的 key attribute 即可。例如，如果你允许用户在不同的登录方式之间切换
// 2、注意，当和 v-if 一起使用时，v-for 的优先级比 v-if 更高
// 3、v-show的特点：每次不会重建进行DOM的删除和创建操作，只是切换了元素的display:none样式，具有较高的初始渲染消耗（即每次都只是将元素隐藏了，并没有真正的删除掉）
// 4、注意，v-show 不支持 <template> 元素，也不支持 v-else

// v-if vs v-show 官方说明：
// v-if 是“真正”的条件渲染，因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建。
// v-if 也是惰性的：如果在初始渲染时条件为假，则什么也不做——直到条件第一次变为真时，才会开始渲染条件块。
// 相比之下，v-show 就简单得多——不管初始条件是什么，元素总是会被渲染，并且只是简单地基于 CSS 进行切换。
// 一般来说，v-if 有更高的切换开销，而 v-show 有更高的初始渲染开销。因此，如果需要非常频繁地切换，则使用 v-show 较好；如果在运行时条件很少改变，则使用 v-if 较好。

// v-if 和 v-else-if 和 v-else 指令

// 事件修饰符
// .stop 阻止单击事件继续传播
// .prevent 阻止默认事件
// .capture 添加时间侦听器时使用时间捕获模式
// .self 只当事件在该元素本身（比如不是子元素）触发时触发回调
// .once 事件只触发一次
// .passive

// 按键修饰符
// .enter
// .tab
// .delete (捕获“删除”和“退格”键)
// .esc
// .space
// .up
// .down
// .left
// .right
// 在监听键盘事件时，我们经常需要检查详细的按键。Vue 允许为 v-on 在监听键盘事件时添加按键修饰符
// 只有在 `key` 是 `Enter` 时调用 `vm.submit()`
// <input v-on:keyup.enter="submit"></input>

// 外联样式
// 1、数组 <h2 :class="['italic','color']">涂陌</h2> 其中的italic、color是自定义的类名，需在外部定义CSS样式
// 2、数组中嵌套对象 <h2 :class="['italic',{'color': flag}]">涂陌</h2> 其中的flag是Vue绑定的变量，在data进行声明
// 3、直接使用对象 <h2 :class="{italic:true, color:flag}">涂陌</h2>

// 内联样式
// 将样式对象定义到data中，并在:style中引用
// <h2 :style="styleObj">涂陌</h2>
// data: { styleObj: { ‘color’: ‘red’, ‘font-weight’: ‘200px’} }

// 过滤器
// Vue.js允许你自定义过滤器，可被用作一些常见元素的格式化。过滤器可以用在两个地方：mustache插值{{ val }}和v-bind表达式,通过管道符(|)来连接。
// 写法:
// {{ 过滤器名称 | function }}
// 定义:
// Vue提供了两种方式创建过滤器：
// 1、全局过滤器 https://cn.vuejs.org/v2/guide/filters.html
// Vue.filter('过滤器名称', function(){}) ,Vue提供的全局过滤器，直接使用Vue调用，而不是定义在Vue实例中
// Vue.js 允许你自定义过滤器，可被用于一些常见的文本格式化。过滤器可以用在两个地方：双花括号插值和 v-bind 表达式 (后者从 2.1.0+ 开始支持)。过滤器应该被添加在 JavaScript 表达式的尾部，由“管道”符号指示：
// 2、局部过滤器, 私有过滤器和全局过滤器用法基本相同，仅仅是作用于不同而已。
// new Vue()({
//   el: '',
//   data: {},
//   methods: {},
//   filters: {
//       过滤器名称: function(){}
//   }
// })

// 自定义指令
// 按键修饰符:
// 在我们搜索商品时，在一些网站中我们直接回车后立即进行搜索，而不是点击搜索按钮才会搜索，那么这个功能怎么实现呢？
// 那么我们就需要了解Vue中提供的按键修饰符 用法： @keyup.按键别名 = "要调用的方法名"
// 按键别名有 .enter .tab .esc .delete …
// 自定义按键修饰符:
// 如果Vue提供的按键修饰符不能满足你的需求，你也可以使用Vue提供的自定义按键修饰符来实现，因为每个键盘的按键都对应了一个键盘码值，比如F2对应的键盘码值是：113

// 获取文本焦点
// 获取文本焦点使用了focus属性，那么我们需要定义一个v-focus指令
// Vue.directive('focus', {
//   bind: function(el) {},
//   inserted: function(el) {},
//   updated: function(el) {}
// });
// 如上，使用Vue.directive()实现定义全局指令，需要注意以下几点：
// 1、在directive()方法中包含两个参数：
//  - 参数1：指令的名称，注意，在定义的时候指令名称不需要加v-前缀，但是在使用的时候需要加v-前缀。
//  - 参数2：是一个对象，这个对象包含一些指令相关的函数，这些函数可以在特定的阶段，执行相关的操作。
// 2、在directive()函数的第二个参数中（对象）中又包含了三个实例方法：
// bind: 当指令绑定到元素上的时候，会立即执行这个bind函数，只执行一次；但是需要知道元素绑定了这个指令，若涉及对DOM操作的，并不会立即执行，因为元素不会立即插入到DOM中。所以涉及对元素进行DOM相关操作的，不要定义到这个方法中。
// inserted: 当元素插入到DOM的时候，会立即执行，并只触发一次。
// updated: 当VNode更新的时候，会指定updated，可能触发多次。
// 钩子函数：
// 指令定义函数提供了几个钩子函数（可选）：
// bind inserted update componentUpdated: 所在组件的VNode及其孩子的VNode全部更新的时候调用 unbind: 只调用一次，指令与元素解除绑定时调用
// 钩子函数参数 在上面使用directive()函数的时候我们已经介绍了一些常用的钩子函数，那么既然是函数，就可能需要进行传参，那么为了实现钩子函数传参，Vue提供了几个参数属性来实现对钩子函数参数的一些操作：
// - el: 指令所绑定的元素，可以用来直接操作DOM。
// - binding: 一个对象，包含以下属性：
// name: 指令名，不包含v-前缀
// value: 指令的绑定值，如v-focus="1 + 1"，那么value=2。
// expression: 绑定值的字符串形式，如v-focus="1+1"，那么experssion的值是1+1。
// 定义私有指令：
// 使用私有指令和全局指令的用法基本相同，我们参考上面讲过的私有过滤器和全局过滤器就能猜想到私有指令的用法：

// Vue实例的生命周期
// 什么是声明周期：从Vue实例创建、运行、到销毁期间，伴随着发生的事件的过程成为生命周期。
// 生命周期钩子：就是声明周期事件的别名。
// 主要的声明周期函数分类

// 创建期间的声明周期函数：
// * beforeCreate: 实例刚在内存中被创建，此时，还没有初始化好data和methods属性。
// * created: 实例已经在内存中创建好，此时data和methods已经创建好，但还没有编译template模板。
// * beforeMount: 此时已经完成了模板的编译，但是还没有挂载到页面上。
// * mounted: 此时，已经将编译好的模板，挂载到了页面指定的容器中。
     // 注意 mounted 不会保证所有的子组件也都一起被挂载。如果你希望等到整个视图都渲染完毕，可以在 mounted 内部使用 vm.$nextTick：

// 运行期间的声明周期函数:
// * beforeUpdate: 状态更新之前执行此函数，此时的data数据是最新的，但是此时还没有开始渲染DOM节点 (由此可见，数据的更新操作总是要在页面渲染之前)
// * updated: 实例更新完毕之后调用此函数，此时data中的状态值和界面上显示的数据都是最新的，界面已经被重新渲染好了。
     // 注意 updated 不会保证所有的子组件也都一起被重绘。如果你希望等到整个视图都重绘完毕，可以在 updated 里使用 vm.$nextTick：

// 销毁期间的生命周期函数
// * beforeDestory: 实例销毁之前调用，在这一步，实例仍然可以使用。
// * destroyed: Vue实例销毁后调用，调用后，Vue实例指示的所有东西都会解除绑定，所有的事件监听器都会被移除，所所有的子实例也会被销毁。

// 插槽 参考：https://juejin.im/post/5a69ece0f265da3e5a5777ed
// 具名插槽：有时候我们一个组件里需要多个插槽那么怎么办呢？ 对于这样的情况，<slot>元素有一个特殊的特性：name ，这个特性可以用来定义额外的插槽。
// 如果一个<slot>不带name属性的话，那么它的name默认为default,在向具名插槽提供内容的时候，我们可以在<template>元素上使用v-slot或者slot指令，并以参数的形式提供其名称。
// 现在 <template> 元素中的所有内容都将会被传入相应的插槽。任何没有被包裹在带有 v-slot 的 <template> 中的内容都会被视为默认插槽的内容。

// 概念：
// 插槽，也就是slot，是组件的一块HTML模板，这块模板显示不显示、以及怎样显示由父组件来决定。 实际上，一个slot最核心的两个问题在这里就点出来了，是显示不显示和怎样显示。
// 由于插槽是一块模板，所以，对于任何一个组件，从模板种类的角度来分，其实都可以分为非插槽模板和插槽模板两大类。
// 1、非插槽模板指的是html模板，比如‘div、span、ul、table’这些，非插槽模板的显示与隐藏以及怎样显示由组件自身控制；
// 2、插槽模板是slot，它是一个空壳子，因为它的显示与隐藏以及最后用什么样的html模板显示由父组件控制。但是插槽显示的位置却由子
// 组件自身决定，slot写在组件template的什么位置，父组件传过来的模板将来就显示在什么位置。
// 插槽的分类：单个插槽/默认插槽/匿名插槽，具名插槽，作用域插槽/带数据的插槽


