# STORY-ADMIN
## Vue-Loader

### 
1.钩子函数
钩子函数是Windows消息处理机制的一部分，通过设置“钩子”，应用程序可以在系统级对所有消息、事件进行过滤，访问在正常情况下无法访问的消息。钩子的本质是一段用以处理系统消息的程序，通过系统调用，把它挂入系统。（百度百科）

2.相对于前端来讲
对于前端来说，钩子函数就是指在所有函数执行前，我先执行了的函数，即 钩住 我感兴趣的函数，只要它(函数)执行，我(钩子)就先执行。

3.vue中的mounted
在这发起后端请求，拿回数据，配合路由钩子做一些事情
类型：Function
详细：
el被新创建的 vm.el替换，并挂载到实例上去之后调用该钩子。如果root实例挂载了一个文档内元素，当mounted被调用时vm.el替换，并挂载到实例上去之后调用该钩子。如果root实例挂载了一个文档内元素，当mounted被调用时vm.el 也在文档内
该钩子在服务器端渲染期间不被调用。

4.代码实例
new Vue({
 el: '#app',
 data: {
 totalMoney: 0,
 productList: []
 },
 filters: {
 },
 mounted: function() {
 //这个是钩子函数
 //如果cartView函数要执行，必须先执行钩子函数
 //这个钩子函数完成了对cratView函数的调用
 //应该注意的是，使用mounted 并不能保证钩子函数中的 this.$el 在 document 中。为此还应该引入    Vue.nextTick/vm.$nextTick
  this.$nextTick(function () {
   this.cartView() 
  })
 })
 },
 methods: {
 //这个是要执行的函数
  cartView: function() {
  var _this = this;
  this.$http.get("data/cartData.json", {"id": 123}).then(function(res) {
   _this.productList = res.body.result.list;
   _this.totalMoney = res.body.result.totalMoney;
  });
  }
  }
 }
});

# 钩子函数说明
created:html加载完成之前，执行。执行顺序：父组件-子组件

mounted:html加载完成后执行。执行顺序：子组件-父组件

methods：事件方法执行

watch：watch是去监听一个值的变化，然后执行相对应的函数。

computed：computed是计算属性，也就是依赖其它的属性计算所得出最后的值

### 举例：
export default {
     name: "draw",
     data(){      // 定义变量source        
       return {
         source:new ol.source.Vector({wrapX: false}),

       }
     },
    props:{ //接收父组件传递过来的参数
      map:{
        //type:String
      },

    },

mounted(){   //页面初始化方法
    if (map==map){

    }
    var vector = new ol.layer.Vector({
      source: this.source
    });
    this.map.addLayer(vector);

  },
  watch: {   //监听值变化：map值
    map:function () {
      console.log('3333'+this.map);
      //return this.map
      console.log('444444'+this.map);

      var vector = new ol.layer.Vector({
        source: this.source
      });
      this.map.addLayer(vector);
    }
  },
  methods:{   //监听方法  click事件等，执行drawFeatures方法
       drawFeatures:function(drawType){}
}
}

