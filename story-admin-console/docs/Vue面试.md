>  原文地址 [www.cnblogs.com](https://www.cnblogs.com/jin-zhe/p/9794713.html)

### 一、什么是 MVVM？

MVVM 是 Model-View-ViewModel 的缩写。MVVM 是一种设计思想。Model 层代表数据模型，也可以在 Model 中定义数据修改和操作的业务逻辑；View 代表 UI 组件，它负责将数据模型转化成 UI 展现出来，ViewModel 是一个同步 View 和 Model 的对象。

在 MVVM 架构下，View 和 Model 之间并没有直接的联系，而是通过 ViewModel 进行交互，Model 和 ViewModel 之间的交互是双向的， 因此 View 数据的变化会同步到 Model 中，而 Model 数据的变化也会立即反应到 View 上。

ViewModel 通过双向数据绑定把 View 层和 Model 层连接了起来，而 View 和 Model 之间的同步工作完全是自动的，无需人为干涉，因此开发者只需关注业务逻辑，不需要手动操作 DOM, 不需要关注数据状态的同步问题，复杂的数据状态维护完全由 MVVM 来统一管理。

### 二、mvvm 和 mvc 区别？它和其它框架（jquery）的区别是什么？哪些场景适合？

mvc 和 mvvm 其实区别并不大。都是一种设计思想。主要就是 mvc 中 Controller 演变成 mvvm 中的 viewModel。mvvm 主要解决了 mvc 中大量的 DOM 操作使页面渲染性能降低，加载速度变慢，影响用户体验。

区别：vue 数据驱动，通过数据来显示视图层而不是节点操作。  
场景：数据操作比较多的场景，更加便捷

### 三、vue 的优点是什么？

> *   低耦合。视图（View）可以独立于 Model 变化和修改，一个 ViewModel 可以绑定到不同的 "View" 上，当 View 变化的时候 Model 可以不变，当 Model 变化的时候 View 也可以不变。
> *   可重用性。你可以把一些视图逻辑放在一个 ViewModel 里面，让很多 view 重用这段视图逻辑。
> *   独立开发。开发人员可以专注于业务逻辑和数据的开发（ViewModel），设计人员可以专注于页面设计。
> *   可测试。界面素来是比较难于测试的，而现在测试可以针对 ViewModel 来写。

### 四、 组件之间的传值？

> *   父组件与子组件传值  
>     父组件通过标签上面定义传值  
>     子组件通过 props 方法接受数据
> *   子组件向父组件传递数据  
>     子组件通过 $emit 方法传递参数

### 五、路由之间跳转

声明式（标签跳转） 编程式（ js 跳转）

### 六、vue.cli 中怎样使用自定义的组件？有遇到过哪些问题吗？

> *   第一步：在 components 目录新建你的组件文件（indexPage.vue），script 一定要 export default {}
> *   第二步：在需要用的页面（组件）中导入：import indexPage from '@/components/indexPage.vue'
> *   第三步：注入到 vue 的子组件的 components 属性上面, components:{indexPage}
> *   第四步：在 template 视图 view 中使用，  
>     例如有 indexPage 命名，使用的时候则 index-page

### 七、vue 如何实现按需加载配合 webpack 设置

webpack 中提供了 require.ensure() 来实现按需加载。以前引入路由是通过 import 这样的方式引入，改为 const 定义的方式进行引入。  
不进行页面按需加载引入方式：import home from '../../common/home.vue'  
进行页面按需加载的引入方式：const home = r => require.ensure([], () => r (require('../../common/home.vue')))

### 八、vuex 面试相关

#### （1）vuex 是什么？怎么使用？哪种功能场景使用它？

vue 框架中状态管理。在 main.js 引入 store，注入。新建一个目录 store，….. export 。场景有：单页应用中，组件之间的状态。音乐播放、登录状态、加入购物车

#### （2）vuex 有哪几种属性？

有五种，分别是 State、 Getter、Mutation 、Action、 Module

*   vuex 的 State 特性  
    A、Vuex 就是一个仓库，仓库里面放了很多对象。其中 state 就是数据源存放地，对应于一般 Vue 对象里面的 data  
    B、state 里面存放的数据是响应式的，Vue 组件从 store 中读取数据，若是 store 中的数据发生改变，依赖这个数据的组件也会发生更新  
    C、它通过 mapState 把全局的 state 和 getters 映射到当前组件的 computed 计算属性中
*   vuex 的 Getter 特性  
    A、getters 可以对 State 进行计算操作，它就是 Store 的计算属性  
    B、 虽然在组件内也可以做计算属性，但是 getters 可以在多组件之间复用  
    C、 如果一个状态只在一个组件内使用，是可以不用 getters
*   vuex 的 Mutation 特性  
    Action 类似于 mutation，不同在于：Action 提交的是 mutation，而不是直接变更状态；Action 可以包含任意异步操作。

#### （3）不用 Vuex 会带来什么问题？

*   可维护性会下降，想修改数据要维护三个地方；
*   可读性会下降，因为一个组件里的数据，根本就看不出来是从哪来的；
*   增加耦合，大量的上传派发，会让耦合性大大增加，本来 Vue 用 Component 就是为了减少耦合，现在这么用，和组件化的初衷相背。

#### （4）你是怎么认识vuex的？

答：vuex可以理解为一种开发模式或框架。比如PHP有thinkphp，java有spring等。
通过状态（数据源）集中管理驱动组件的变化（好比spring的IOC容器对bean进行集中管理）。

应用级的状态集中放在store中； 改变状态的方式是提交mutations，这是个同步的事物； 异步逻辑应该封装在action中。

### 九、 v-show 和 v-if 指令的共同点和不同点

*   v-show 指令是通过修改元素的 display 的 CSS 属性让其显示或者隐藏
*   v-if 指令是直接销毁和重建 DOM 达到让元素显示和隐藏的效果

### 十、 如何让 CSS 只在当前组件中起作用

将当前组件的 <style> 修改为 < style scoped>

### 十一、<keep-alive></keep-alive > 的作用是什么?

<keep-alive></keep-alive> 包裹动态组件时，会缓存不活动的组件实例，主要用于保留组件状态或避免重新渲染。

### 十二、Vue 中引入组件的步骤?

1）采用 ES6 的 import ... from ... 语法或 CommonJS 的 require() 方法引入组件  
2）对组件进行注册, 代码如下

```
// 注册
Vue.component('my-component', {
  template: '<div>A custom component!</div>'
})
```

3）使用组件`<my-component></my-component>`

### 十三、指令 v-el 的作用是什么?

提供一个在页面上已存在的 DOM 元素作为 Vue 实例的挂载目标. 可以是 CSS 选择器，也可以是一个 HTMLElement 实例

### 十四、在 Vue 中使用插件的步骤

*   采用 ES6 的 import ... from ... 语法或 CommonJSd 的 require() 方法引入插件
*   使用全局方法 Vue.use(plugin) 使用插件, 可以传入一个选项对象 Vue.use(MyPlugin, { someOption: true })

### 十五、请列举出 3 个 Vue 中常用的生命周期钩子函数

*   created: 实例已经创建完成之后调用, 在这一步, 实例已经完成数据观测, 属性和方法的运算, watch/event 事件回调. 然而, 挂载阶段还没有开始, $el 属性目前还不可见
*   mounted: el 被新创建的 vm.$el 替换，并挂载到实例上去之后调用该钩子。如果 root 实例挂载了一个文档内元素，当 mounted 被调用时 vm.$el 也在文档内。
*   activated: keep-alive 组件激活时调用

### 十六、active-class 是哪个组件的属性？

vue-router 模块的 router-link 组件。

### 十七、怎么定义 vue-router 的动态路由以及如何获取传过来的动态参数？

在 router 目录下的 index.js 文件中，对 path 属性加上 /:id。  
使用 router 对象的 params.id。

### 十八、vue-router 有哪几种导航钩子？

三种，一种是全局导航钩子：router.beforeEach(to,from,next)，作用：跳转前进行判断拦截。  
第二种：组件内的钩子；  
第三种：单独路由独享组件

### 十九、生命周期相关面试题

总共分为 8 个阶段创建前 / 后，载入前 / 后，更新前 / 后，销毁前 / 后。

> *   创建前 / 后： 在 beforeCreate 阶段，vue 实例的挂载元素 el 和数据对象 data 都为 undefined，还未初始化。在 created 阶段，vue 实例的数据对象 data 有了，el 还没有。
> *   载入前 / 后：在 beforeMount 阶段，vue 实例的 $el 和 data 都初始化了，但还是挂载之前为虚拟的 dom 节点，data.message 还未替换。在 mounted 阶段，vue 实例挂载完成，data.message 成功渲染。
> *   更新前 / 后：当 data 变化时，会触发 beforeUpdate 和 updated 方法。
> *   销毁前 / 后：在执行 destroy 方法后，对 data 的改变不会再触发周期函数，说明此时 vue 实例已经解除了事件监听以及和 dom 的绑定，但是 dom 结构依然存在

#### （1）、什么是 vue 生命周期

答： Vue 实例从创建到销毁的过程，就是生命周期。也就是从开始创建、初始化数据、编译模板、挂载 Dom→渲染、更新→渲染、卸载等一系列过程，我们称这是 Vue 的生命周期。

#### （2）、vue 生命周期的作用是什么

答：它的生命周期中有多个事件钩子，让我们在控制整个 Vue 实例的过程时更容易形成好的逻辑。

#### （3）、vue 生命周期总共有几个阶段

答：可以总共分为 8 个阶段：创建前 / 后, 载入前 / 后, 更新前 / 后, 销毁前 / 销毁后

#### （4）、第一次页面加载会触发哪几个钩子

答：第一次页面加载时会触发 beforeCreate, created, beforeMount, mounted 这几个钩子

#### （5）、DOM 渲染在 哪个周期中就已经完成

答：DOM 渲染在 mounted 中就已经完成了。

#### （6）、简单描述每个周期具体适合哪些场景

答：生命周期钩子的一些使用方法：

*   beforecreate : 可以在这加个 loading 事件，在加载实例时触发
*   created : 初始化完成时的事件写在这里，如在这结束 loading 事件，异步请求也适宜在这里调用
*   mounted : 挂载元素，获取到 DOM 节点
*   updated : 如果对数据统一处理，在这里写上相应函数
*   beforeDestroy : 可以做一个确认停止事件的确认框
*   nextTick : 更新数据后立即操作 dom    参考理解：https://www.cnblogs.com/jin-zhe/p/9985436.html

#### （7）、请详细说下你对vue生命周期的理解？

答：总共分为8个阶段创建前/后，载入前/后，更新前/后，销毁前/后。

创建前/后： 在beforeCreated阶段，vue实例的挂载元素$el和数据对象data都为undefined，还未初始化。在created阶段，vue实例的数据对象data有了，$el还没有。

载入前/后：在beforeMount阶段，vue实例的$el和data都初始化了，但还是挂载之前为虚拟的dom节点，data.message还未替换。在mounted阶段，vue实例挂载完成，data.message成功渲染。

更新前/后：当data变化时，会触发beforeUpdate和updated方法。

销毁前/后：在执行destroy方法后，对data的改变不会再触发周期函数，说明此时vue实例已经解除了事件监听以及和dom的绑定，但是dom结构依然存在

### 二十、说出至少 4 种 vue 当中的指令和它的用法？

v-if：判断是否隐藏；v-for：数据循环；v-bind:class：绑定一个属性；v-model：实现双向绑定

### 二十一、vue-loader 是什么？使用它的用途有哪些？

答：解析.vue文件的一个加载器，跟template/js/style转换成js模块。
用途：js可以写es6、style样式可以scss或less、template可以加jade等

### 二十二、scss 是什么？在 vue.cli 中的安装使用步骤是？有哪几大特性？

答：css 的预编译。  
使用步骤：  
第一步：先装 css-loader、node-loader、sass-loader 等加载器模块  
第二步：在 build 目录找到 webpack.base.config.js，在那个 extends 属性中加一个拓展. scss  
第三步：在同一个文件，配置一个 module 属性  
第四步：然后在组件的 style 标签加上 lang 属性 ，例如：lang=”scss”

特性:

*   可以用变量，例如（$ 变量名称 = 值）；
*   可以用混合器，例如（）
*   可以嵌套

### 二十三、为什么使用 key？

当有相同标签名的元素切换时，需要通过 key 特性设置唯一的值来标记以让 Vue 区分它们，否则 Vue 为了效率只会替换相同标签内部的内容。

### 二十四、为什么避免 v-if 和 v-for 用在一起

当 Vue 处理指令时，v-for 比 v-if 具有更高的优先级，通过 v-if 移动到容器元素，不会再重复遍历列表中的每个值。取而代之的是，我们只检查它一次，且不会在 v-if 为否的时候运算 v-for。

### 二十五、VNode 是什么？虚拟 DOM 是什么？

Vue 在 页面上渲染的节点，及其子节点称为 “虚拟节点 (Virtual Node)”，简写为“VNode”。“虚拟 DOM” 是由 Vue 组件树建立起来的整个 VNode 树的称呼。

### 二十六、Vue的双向数据绑定原理是什么？

答：vue.js 是采用数据劫持结合发布者-订阅者模式的方式，通过Object.defineProperty()来劫持各个属性的setter，getter，在数据变动时发布消息给订阅者，触发相应的监听回调。

具体步骤：

第一步：需要observe的数据对象进行递归遍历，包括子属性对象的属性，都加上 setter和getter
这样的话，给这个对象的某个值赋值，就会触发setter，那么就能监听到了数据变化

第二步：compile解析模板指令，将模板中的变量替换成数据，然后初始化渲染页面视图，并将每个指令对应的节点绑定更新函数，添加监听数据的订阅者，一旦数据有变动，收到通知，更新视图

第三步：Watcher订阅者是Observer和Compile之间通信的桥梁，主要做的事情是:
1、在自身实例化时往属性订阅器(dep)里面添加自己
2、自身必须有一个update()方法
3、待属性变动dep.notice()通知时，能调用自身的update()方法，并触发Compile中绑定的回调，则功成身退。

第四步：MVVM作为数据绑定的入口，整合Observer、Compile和Watcher三者，通过Observer来监听自己的model数据变化，通过Compile来解析编译模板指令，最终利用Watcher搭起Observer和Compile之间的通信桥梁，达到数据变化 -> 视图更新；视图交互变化(input) -> 数据model变更的双向绑定效果。

### 二十七、聊聊你对Vue.js的template编译的理解？

答：简而言之，就是先转化成AST树，再得到的render函数返回VNode（Vue的虚拟DOM节点）

详情步骤：

首先，通过compile编译器把template编译成AST语法树（abstract syntax tree 即 源代码的抽象语法结构的树状表现形式），compile是createCompiler的返回值，createCompiler是用以创建编译器的。另外compile还负责合并option。

然后，AST会经过generate（将AST语法树转化成render funtion字符串的过程）得到render函数，render的返回值是VNode，VNode是Vue的虚拟DOM节点，里面有（标签名、子节点、文本等等）

### 二十八、自定义指令（v-check、v-focus）的方法有哪些？它有哪些钩子函数？还有哪些钩子函数参数？

答：全局定义指令：在vue对象的directive方法里面有两个参数，一个是指令名称，另外一个是函数。组件内定义指令：directives

钩子函数：bind（绑定事件触发）、inserted(节点插入的时候触发)、update（组件内相关更新）

钩子函数参数：el、binding

### 二十九、导航钩子有哪些？它们有哪些参数？

答：导航钩子有：a/全局钩子和组件内独享的钩子。b/beforeRouteEnter、afterEnter、beforeRouterUpdate、beforeRouteLeave

参数：有to（去的那个路由）、from（离开的路由）、next（一定要用这个函数才能去到下一个路由，如果不用就拦截）最常用就这几种

### 三十、请说下封装 vue 组件的过程？

答：首先，组件可以提升整个项目的开发效率。能够把页面抽象成多个相对独立的模块，解决了我们传统项目开发：效率低、难维护、复用性等问题。

然后，使用Vue.extend方法创建一个组件，然后使用Vue.component方法注册组件。子组件需要数据，可以在props中接受定义。而子组件修改好数据后，想把数据传递给父组件。可以采用emit方法。

### 三十一、请说出vue.cli项目中src目录每个文件夹和文件的用法？

答：assets文件夹是放静态资源；components是放组件；router是定义路由相关的配置;view视图；app.vue是一个应用主组件；main.js是入口文件

ps：16题答案同样适合”vue data是怎么实现的？”此面试题。

  
嗯，就酱~~     参考  https://www.jianshu.com/p/b1dd80f4d542