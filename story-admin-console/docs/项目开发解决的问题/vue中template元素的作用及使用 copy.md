> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/bfzqvasc5NkBD1-pyJAt_w)

前言

数组和对象作为一个最基础数据结构，在各种编程语言中都充当着至关重要的角色，你很难想象没有数组和对象的编程语言会是什么模样。特别是 JS，弱类型语言，非常灵活。本文带你了解常用数组遍历、对象遍历的使用，对比以及注意事项。

### 数组遍历

随着 JS 的不断发展，截至 ES7 规范已经有十多种遍历方法。下面按照功能类似的方法为一组，来介绍数组的常用遍历方法。

#### for、forEach、for ...of

```
const list = [1, 2, 3, 4, 5, 6, 7, 8,, 10, 11];

for (let i = 0, len = list.length; i < len; i++) {
  if (list[i] === 5) {
    break; // 1 2 3 4
    // continue; // 1 2 3 4 6 7 8 undefined 10 11
  }
  console.log(list[i]);
}

for (const item of list) {
  if (item === 5) {
    break; // 1 2 3 4
    // continue; // 1 2 3 4 6 7 8 undefined 10 11
  }
  console.log(item);
}

list.forEach((item, index, arr) => {
  if (item === 5) return;
  console.log(index); // 0 1 2 3 5 6 7 9 10
  console.log(item); // 1 2 3 4 6 7 8 9 10
});
```

**小结**

*   三者都是基本的由左到右遍历数组。
    
*   forEach 无法跳出循环，for 和 for ...of 可以使用 break 或者 continue 跳过或中断。
    
*   for ...of 直接访问的是实际元素，for 遍历数组索引，forEach 回调函数参数更丰富，元素、索引、原数组都可以获取。
    
*   for ...of 与 for 如果数组中存在空元素，同样会执行。
    

#### some、every

```
const list = [
  { name: '头部导航', backward: false },
  { name: '轮播', backward: true },
  { name: '页脚', backward: false },
];
const someBackward = list.some(item => item.backward);
// someBackward: true
const everyNewest = list.every(item => !item.backward);
// everyNewest: false
```

**小结**

*   二者都是用来做数组条件判断的，都是返回一个布尔值。
    
*   二者都可以被中断。
    
*   some 若某一元素满足条件，返回 true，循环中断。所有元素不满足条件，返回 false。
    
*   every 与 some 相反，若有一元素不满足条件，返回 false，循环中断。所有元素满足条件，返回 true。
    

#### filter、map

```
const list = [
{ name: '头部导航', type: 'nav', id: 1 },,
{ name: '轮播', type: 'content', id: 2 },
{ name: '页脚', type: 'nav', id: 3 },
];
const resultList = list.filter(item => {
  console.log(item);
  return item.type === 'nav';
});
// resultList: [
//   { name: '头部导航', type: 'nav', id: 1 },
//   { name: '页脚', type: 'nav', id: 3 },
// ]

const newList = list.map(item => {
  console.log(item);
  return item.id;
});
// newList: [1, empty, 2, 3]

// list: [
//   { name: '头部导航', type: 'nav', id: 1 },
//   empty,
//   { name: '轮播', type: 'content', id: 2 },
//   { name: '页脚', type: 'nav', id: 3 },
// ]
```

**小结**

*   二者都是生成一个新数组，都不会改变原数组（不包括遍历对象数组时，在回调函数中操作元素对象）。
    
*   二者都会跳过空元素。有兴趣的同学可以自己打印一下。
    
*   map 会将回调函数的返回值组成一个新数组，数组长度与原数组一致。
    
*   filter 会将符合回调函数条件的元素组成一个新数组。
    
*   map 生成的新数组元素可自定义。
    
*   filter 生成的新数组元素不可自定义，与对应原数组元素一致。
    

#### find、findIndex

```
const list = [
    { name: '头部导航', id: 1 },
    { name: '轮播', id: 2 },
    { name: '页脚', id: 3 },
];
const result = list.find(item => item.id === 3);
// result: { name: '页脚', id: 3 }
const index = list.findIndex(item => item.id === 3);
// index: 2
```

**小结**

*   二者都是用来查找数组元素。
    
*   find 方法返回数组中满足 callback 函数的第一个元素的值。如果不存在返回 undefined。
    
*   findIndex 它返回数组中找到的元素的索引，而不是其值，如果不存在返回 -1。
    

#### **reduce、reduceRight**

reduce 方法接收两个参数，第一个参数是回调函数（callback） ，第二个参数是初始值（initialValue）。

reduceRight 方法除了与 reduce 执行方向相反外 (从右往左)，其他完全与其一致。

回调函数接收四个参数：

*   accumulator：MDN 上解释为累计器，但我觉得不恰当，按我的理解它应该是截至当前元素，之前所有的数组元素被回调函数处理累计的结果。
    
*   current：当前被执行的数组元素。
    
*   currentIndex: 当前被执行的数组元素索引。
    
*   sourceArray：原数组，也就是调用 reduce 方法的数组。
    

>  
> 
> 如果不传入初始值，reduce 方法会从索引 1 开始执行回调函数，如果传入初始值，将从索引 0 开始、并从初始值的基础上累计执行回调。

##### 计算对象数组某一属性的总和

```
const list  = [
  { name: 'left', width: 20 },
  { name: 'center', width: 70 },
  { name: 'right', width: 10 },
];
const total = list.reduce((currentTotal, item) => {
  return currentTotal + item.width;
}, 0);
// total: 100
```

##### 对象数组的去重，并统计每一项重复次数

```
const list  = [
  { name: 'left', width: 20 },
  { name: 'right', width: 10 },
  { name: 'center', width: 70 },
  { name: 'right', width: 10 },
  { name: 'left', width: 20 },
  { name: 'right', width: 10 },
];
const repeatTime = {};
const result = list.reduce((array, item) => {
  if (repeatTime[item.name]) {
    repeatTime[item.name]++;
    return array;
  }
  repeatTime[item.name] = 1;
  return [...array, item];
}, []);
// repeatTime: { left: 2, right: 3, center: 1 }
// result: [
//   { name: 'left', width: 20 },
//   { name: 'right', width: 10 },
//   { name: 'center', width: 70 },
// ]
```

##### 对象数组最大 / 最小值获取

```
const list  = [
  { name: 'left', width: 20 },
  { name: 'right', width: 30 },
  { name: 'center', width: 70 },
  { name: 'top', width: 40 },
  { name: 'bottom', width: 20 },
];
const max = list.reduce((curItem, item) => {
  return curItem.width >= item.width ? curItem : item;
});
const min = list.reduce((curItem, item) => {
  return curItem.width <= item.width ? curItem : item;
});
// max: { name: "center", width: 70 }
// min: { name: "left", width: 20 }
```

reduce 很强大，更多奇技淫巧推荐查看这篇《25 个你不得不知道的数组 reduce 高级用法》(https://juejin.im/post/6844904063729926152)

#### 性能对比

说了这么多，那这些遍历方法， 在性能上有什么差异呢？我们在 Chrome 浏览器中尝试。我采用每个循环执行 10 次，去除最大、最小值 取平均数，降低误差。

```
var list = Array(100000).fill(1)

console.time('for');
for (let index = 0, len = list.length; index < len; index++) {
}
console.timeEnd('for');
// for: 2.427642822265625 ms

console.time('every');
list.every(() => { return true })
console.timeEnd('every')
// some: 2.751708984375 ms

console.time('some');
list.some(() => { return false })
console.timeEnd('some')
// some: 2.786590576171875 ms

console.time('foreach');
list.forEach(() => {})
console.timeEnd('foreach');
// foreach: 3.126708984375 ms

console.time('map');
list.map(() => {})
console.timeEnd('map');
// map: 3.743743896484375 ms

console.time('forof');
for (let index of list) {
}
console.timeEnd('forof')
// forof: 6.33380126953125 ms
```

从打印结果可以看出，for 循环的速度最快，for ...of 循环最慢。

#### **常用遍历的终止、性能表格对比**

<table data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)"><thead data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)"><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204);"><th data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)" data-style="box-sizing: border-box; text-align: center; border-top-width: 1px; border-color: rgb(204, 204, 204); background-color: rgb(240, 240, 240); min-width: 85px;"><br data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)"></th><th data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)" data-style="box-sizing: border-box; text-align: left; border-top-width: 1px; border-color: rgb(204, 204, 204); background-color: rgb(240, 240, 240); min-width: 85px;">是否可终止</th><th data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)" data-style="box-sizing: border-box; text-align: center; border-top-width: 1px; border-color: rgb(204, 204, 204); background-color: rgb(240, 240, 240); min-width: 85px;"><br data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)"></th><th data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)" data-style="box-sizing: border-box; text-align: left; border-top-width: 1px; border-color: rgb(204, 204, 204); background-color: rgb(240, 240, 240); min-width: 85px;"><br data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)"></th><th data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)" data-style="box-sizing: border-box; text-align: center; border-top-width: 1px; border-color: rgb(204, 204, 204); background-color: rgb(240, 240, 240); min-width: 85px;"><br data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(240, 240, 240)"></th></tr></thead><tbody data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)"><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">****</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;"><strong data-darkmode-color-16227299513607="rgb(71, 193, 168)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)|rgb(71, 193, 168)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)">break</strong></td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;"><strong data-darkmode-color-16227299513607="rgb(71, 193, 168)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)|rgb(71, 193, 168)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)">continue</strong></td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;"><strong data-darkmode-color-16227299513607="rgb(71, 193, 168)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)|rgb(71, 193, 168)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)">return</strong></td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;"><strong data-darkmode-color-16227299513607="rgb(71, 193, 168)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)|rgb(71, 193, 168)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)">性能（ms）</strong></td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">for</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">终止 ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">跳出本次循环 ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">2.42</td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">forEach</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">3.12</td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">map</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">3.74</td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">for of</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">终止 ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">跳出本次循环 ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">6.33</td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">some</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">return true ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)|rgb(248, 248, 248)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">2.78</td></tr><tr data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204);"><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">every</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">❌</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); min-width: 85px;">return false ✅</td><td data-darkmode-color-16227299513607="rgb(163, 163, 163)" data-darkmode-original-color-16227299513607="#fff|rgb(0,0,0)|rgb(0, 0, 0)" data-darkmode-bgcolor-16227299513607="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16227299513607="#fff|rgb(255, 255, 255)" data-style="box-sizing: border-box; border-color: rgb(204, 204, 204); text-align: center; min-width: 85px;">2.75</td></tr></tbody></table>

最后，不同浏览器内核 也会有些差异, 有兴趣的同学也可以尝试一下。

### **对象遍历**

在对象遍历中，经常需要遍历对象的键、值，ES5 提供了 for...in 用来遍历对象，然而其涉及对象属性的 “可枚举属性”、原型链属性等，下面将从 Object 对象本质探寻各种遍历对象的方法，并区分常用方法的一些特点。

#### **for in**

```
Object.prototype.fun = () => {};
const obj = { 2: 'a', 1: 'b' };
for (const i in obj) {
  console.log(i, ':', obj[i]);
}
// 1: b
// 2: a
// fun : () => {} Object 原型链上扩展的方法也被遍历出来
for (const i in obj) {
  if (Object.prototype.hasOwnProperty.call(obj, i)) {
      console.log(i, ':', obj[i]);
    }
}
// name : a 不属于自身的属性将被 hasOwnProperty 过滤
```

**小结**

使用 for in 循环时，返回的是所有能够通过对象访问的、可枚举的属性，既包括存在于实例中的属性，也包括存在于原型中的实例。如果只需要获取对象的实例属性，可以使用 hasOwnProperty 进行过滤。

使用时，要使用 `(const x in a)` 而不是 `(x in a)` 后者将会创建一个全局变量。

for in 的循环顺序，参考【 JavaScript 权威指南】（第七版）6.6.1。

*   先列出名字为非负整数的字符串属性，按照数值顺序从最小到最大。这条规则意味着数组和类数组对象的属性会按照顺序被枚举。
    
*   在列出类数组索引的所有属性之后，在列出所有剩下的字符串名字（包括看起来像整负数或浮点数的名字）的属性。这些属性按照它们添加到对象的先后顺序列出。对于在对象字面量中定义的属性，按照他们在字面量中出现的顺序列出。
    
*   最后，名字为符号对象的属性按照它们添加到对象的先后顺序列出。
    

#### **Object.keys**

```
Object.prototype.fun = () => {};
const str = 'ab';
console.log(Object.keys(str));
// ['0', '1']
const arr = ['a', 'b'];
console.log(Object.keys(arr));
// ['0', '1']
const obj = { 1: 'b', 0: 'a' };
console.log(Object.keys(obj));
// ['0', '1']
```

**小结**

用于获取对象自身所有的可枚举的属性值，但不包括原型中的属性，然后返回一个由属性名组成的数组。

#### **Object.values**

```
Object.prototype.fun = () => {};
const str = 'ab';
console.log(Object.values(str));
// ['a', 'b']
const arr = ['a', 'b'];
console.log(Object.values(arr));
// ['a', 'b']
const obj = { 1: 'b', 0: 'a' };
console.log(Object.values(obj));
// ['a', 'b']
```

**小结**

用于获取对象自身所有的可枚举的属性值，但不包括原型中的属性，然后返回一个由属性值组成的数组。

#### **Object.entries**

```
const str = 'ab';
for (const [key, value] of Object.entries(str)) {
    console.log(`${key}: ${value}`);
}
// 0: a
// 1: b
const arr = ['a', 'b'];
for (const [key, value] of Object.entries(arr)) {
    console.log(`${key}: ${value}`);
}
// 0: a
// 1: b
const obj = { 1: 'b', 0: 'a' };
for (const [key, value] of Object.entries(obj)) {
    console.log(`${key}: ${value}`);
}
// 0: a
// 1: b
```

**小结**

用于获取对象自身所有的可枚举的属性值，但不包括原型中的属性，然后返回二维数组。每一个子数组由对象的属性名、属性值组成。可以同时拿到属性名与属性值的方法。

#### **Object.getOwnPropertyNames**

```
Object.prototype.fun = () => {};
Array.prototype.fun = () => {};
const str = 'ab';
console.log(Object.getOwnPropertyNames(str));
// ['0', '1', 'length']
const arr = ['a', 'b'];
console.log(Object.getOwnPropertyNames(arr));
// ['0', '1', 'length']
const obj = { 1: 'b', 0: 'a' };
console.log(Object.getOwnPropertyNames(obj));
// ['0', '1']
```

**小结**

用于获取对象自身所有的可枚举的属性值，但不包括原型中的属性，然后返回一个由属性名组成的数组。

### **总结**

我们对比了多种常用遍历的方法的差异，在了解了这些之后，我们在使用的时候需要好好思考一下，就能知道那个方法是最合适的。欢迎大家纠正补充。

公众号

公众号：前端食堂

知乎：童欧巴

掘金：童欧巴

这是一个终身学习的男人，他在坚持自己热爱的事情，欢迎你加入前端食堂，和这个男人一起开心的变胖～

> “
> 
> 如果你觉得读了本文有收获的话可以点个在看让我看到。阅读过程中有任何问题、想法或者感触也欢迎你在下方留言，也可以在后台回复加群进入食堂的交流群。
> 
> 沟通创造价值，分享带来快乐。也欢迎你分享给身边有需要的同学，利他就是最好的利己。
> 
> ”

推荐阅读：  

[精读《前端职业规划 - 2021 年》](http://mp.weixin.qq.com/s?__biz=MzkzNTIwNTAwOA==&mid=2247491409&idx=2&sn=256fd61af9cb4583645957cfa4bd431c&chksm=c2b0deaef5c757b8f3c0469acfc8aea8900695d428dddd144ea944a4cc95fc2a7385f0015dda&scene=21#wechat_redirect)  

[VueConf China 2021 《Vue3 生态进展 - 尤雨溪》 Reaction](http://mp.weixin.qq.com/s?__biz=MzkzNTIwNTAwOA==&mid=2247491403&idx=1&sn=09c8fe667f05d7b52727e1b0fbf20bfc&chksm=c2b0deb4f5c757a2d54d8447e23e5d648ca001b5e909e953d30bdf3831307dd09fb685b2a21d&scene=21#wechat_redirect)  

多一个点在看

![](https://mmbiz.qpic.cn/mmbiz_gif/zYdFdnRZ0h95ZAL5c8h6iaMiaqbgljvZ80YraNgwWAtyyZRGT8INEgx8qWKgf9wXribCDNibDvDa2R1EQB4grqAKDg/640?wx_fmt=gif)

多一条小鱼干