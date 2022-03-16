### 【vue】总结几种处理数据的常用方法

**1.forEach ()**
forEach 和 map 的作用类似，都是循环数组去做一些事情，区别在于 map 会返回一个新数组，而 forEach 不会返回

//forEach
var arr = [
    { id: 1, age: 18 },
    { id: 2, age: 24 },
    { id: 3, age: 26 },
    { id: 4, age: 19 },
]

var newArr = arr.forEach( item => {
    item.age++
    return item
})
console.log(arr,newArr);
// [……]， undefined

//map
var arr = [
    { id: 1, age: 18 },
    { id: 2, age: 24 },
    { id: 3, age: 26 },
    { id: 4, age: 19 },
]

var newArr = arr.map( item => {
    item.age++
    return item
})
console.log(arr,newArr);
// [……]， [……]

**2.map ()**
const dataList= [
    {
        value: 1,
        name: '蓝天'
    },{
        value: 2,
        name: '白云'
    },{
        value: 3,
        name: '花'
    },{
        value: 4,
        name: '草'
    }
]

// 只获取dataList里面的value,
const valueList= dataList.map(item=>{
    return item.value
})
console.log(valueList)    
// [1,2,3,4]

// 获取value加上name的合并字符串
const nameList= dataList.map(item => {
    return item.value+ item.name
})
console.log(nameList)    
// ["1蓝天", "2白云", "3花", "4草"]

**3.find()**
find返回数组中第一个满足条件的元素（如果有的话）， 如果没有，则返回undefined。
需要注意的是 find 只会返回第一个符合条件的那一个对象，filter 则是返回所有符合的项组成的新数组

var arr = [
    { id: 1, age: 18 },
    { id: 2, age: 24 },
    { id: 3, age: 20 },
    { id: 4, age: 26 },
    { id: 5, age: 20 },
    { id: 6, age: 19 },
]

var obj = arr.find(item => item.age == 20);
console.log(obj);    
// {id: 3, age: 20}

**4.filter()**
filter过滤, 和map一样，也会返回新数组
需要注意的是 filter 是返回所有符合的项组成的新数组，而find 只会返回第一个符合条件的那一个对象

var arr = [
    { id: 1, age: 18 },
    { id: 2, age: 24 },
    { id: 3, age: 20 },
    { id: 4, age: 26 },
    { id: 5, age: 20 },
    { id: 6, age: 19 },
]

var obj = arr.filter(item => item.age == 19);
console.log(obj);    
// [{id: 6, age: 19}]

**5.Array.from()**
可以用于把有length属性的对象生成一个新数组，所以可以用他来新建数组，也可以结合Set来做数组的去重。

var arr = [1, 2, 3, 3, 4, 1, 5];
var newArr = Array.from(new Set(arr));
console.log(newArr)    
 // [1, 2, 3, 4, 5]

**6.sort 排序 reverse 数组倒序**
var arr = [1, 3, 5, 4, 1, 2, 3, 6, 7]
//从小到大
arr.sort((a,b) => a-b);
console.log(arr)     //[1, 1, 2, 3, 3, 4, 5, 6, 7]
//从大到小
arr.sort((a,b) => b-a);
console.log(arr)     //[7, 6, 5, 4, 3, 3, 2, 1, 1]
//倒序
var arr = [1, 3, 5, 4, 1, 2, 3, 6, 7]
arr.reverse();
console.log(arr)     //[7, 6, 3, 2, 1, 4, 5, 3, 1]

**7.includes**
从已有的菜单列表和后台返回已选中的id列表，筛选选中id对应的数据，组成新数组，勾选回显

var idList= ['1', '3', '5']
var dataList= [{id:'1',name:'qqq'},{id:'2',name:'www'},{id:'3',name:'eee'},{id:'4',name:'rrr'},{id:'5',name:'ttt'}]
var arr = []
dataList.forEach((item, index) => {
     if (idList.includes(item.id)) {   //筛选没选的 (!idList.includes(item.id))
          console.log(item.id);
          arr.push(item);
     }
 });
console.log(arr);
//  [{id: "1", name: "qqq"},{id: "3", name: "eee"},{id: "5", name: "ttt"}]

作者：胖胖爱吃鱼啊
链接：https://www.jianshu.com/p/a06aabc464ff
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。