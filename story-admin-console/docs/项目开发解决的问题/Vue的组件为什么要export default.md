> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.cnblogs.com](https://www.cnblogs.com/win-and-coffee/p/10186649.html)

[Vue 的组件为什么要 export default](https://www.cnblogs.com/blog-cxj2017522/p/8562536.html)
====================================================================================

 

Vue 的模块机制

　　Vue 是通过 webpack 实现的模块化，因此可以使用 import 来引入模块，例如：

![](https://images2018.cnblogs.com/blog/1170308/201803/1170308-20180313210235200-1714149358.png)

　　此外，你还可以在 bulid/webpack.base.conf.js 文件中修改相关配置：

![](https://images2018.cnblogs.com/blog/1170308/201803/1170308-20180313210924147-1028432506.png)

　　意思是，你的模块可以省略 ".js"，".vue"，“.json” 后缀，weebpack 会在之后自动添加上；可以用 "@" 符号代替 "src" 字符串等。

　　export 用来导出模块，Vue 的单文件组件通常需要导出一个对象，这个对象是 Vue 实例的选项对象，以便于在其它地方可以使用 import 引入。而 new Vue() 相当于一个构造函数，在入口文件 main.js 构造根组件的同时，如果根组件还包含其它子组件，那么 Vue 会通过引入的选项对象构造其对应的 Vue 实例，最终形成一棵组件树。

　　export 和 export default 的区别在于：export 可以导出多个命名模块，例如：

```
//demo1.js
export const str = 'hello world'

export function f(a){
    return a+1
}
```

　　对应的引入方式：

```
//demo2.js
import { str, f } from 'demo1'
```

　　export default 只能导出一个默认模块，这个模块可以匿名，例如：

```
//demo1.js
export default {
    a: 'hello',
    b: 'world'      
}
```

　　对应的引入方式：

```
//demo2.js
import obj from 'demo1'
```

　　引入的时候可以给这个模块取任意名字，例如 "obj"，且不需要用大括号括起来。

Vue 的模块机制

　　Vue 是通过 webpack 实现的模块化，因此可以使用 import 来引入模块，例如：

![](https://images2018.cnblogs.com/blog/1170308/201803/1170308-20180313210235200-1714149358.png)

　　此外，你还可以在 bulid/webpack.base.conf.js 文件中修改相关配置：

![](https://images2018.cnblogs.com/blog/1170308/201803/1170308-20180313210924147-1028432506.png)

　　意思是，你的模块可以省略 ".js"，".vue"，“.json” 后缀，weebpack 会在之后自动添加上；可以用 "@" 符号代替 "src" 字符串等。

　　export 用来导出模块，Vue 的单文件组件通常需要导出一个对象，这个对象是 Vue 实例的选项对象，以便于在其它地方可以使用 import 引入。而 new Vue() 相当于一个构造函数，在入口文件 main.js 构造根组件的同时，如果根组件还包含其它子组件，那么 Vue 会通过引入的选项对象构造其对应的 Vue 实例，最终形成一棵组件树。

　　export 和 export default 的区别在于：export 可以导出多个命名模块，例如：

```
//demo1.js
export const str = 'hello world'

export function f(a){
    return a+1
}
```

　　对应的引入方式：

```
//demo2.js
import { str, f } from 'demo1'
```

　　export default 只能导出一个默认模块，这个模块可以匿名，例如：

```
//demo1.js
export default {
    a: 'hello',
    b: 'world'      
}
```

　　对应的引入方式：

```
//demo2.js
import obj from 'demo1'
```

　　引入的时候可以给这个模块取任意名字，例如 "obj"，且不需要用大括号括起来。
