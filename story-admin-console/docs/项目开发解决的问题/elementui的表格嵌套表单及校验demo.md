> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.cnblogs.com](https://www.cnblogs.com/helena000/p/11418445.html)

你在使用 vue+elementUI 技术栈的时候，有没有碰到表格嵌套表单需求以及需要前台的一个校验？

这里为大家写了一个 demo:

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
1 <template>
  2   <div>
  3     <el-form :model="forms" ref="forms" :rules="rules">
  4       <el-table :data="forms.voList">
  5         <el-table-column
  6         label="商品名称">
  7           <template slot-scope="scope">
  8             <el-form-item :prop="'voList.'+scope.$index+'.goodsName'">
  9               <el-input v-model="scope.row.goodsName"></el-input>
 10             </el-form-item>
 11           </template>
 12         </el-table-column>
 13         <el-table-column
 14           label="商品编码">
 15           <template slot-scope="scope">
 16             <el-form-item :prop="'voList.'+scope.$index+'.goodsCode'">
 17               <el-input v-model="scope.row.goodsCode"></el-input>
 18             </el-form-item>
 19           </template>
 20         </el-table-column>
 21         <el-table-column
 22           label="价格">
 23           <template slot-scope="scope">
 24             <el-form-item :prop="'voList.'+scope.$index+'.unitPrice'" :rules="rules.unitPrice">
 25               <el-input v-model="scope.row.unitPrice"></el-input>
 26             </el-form-item>
 27           </template>
 28         </el-table-column>
 29         <el-table-column
 30           label="数量">
 31           <template slot-scope="scope">
 32             <el-form-item :prop="'voList.'+scope.$index+'.num'" :rules="rules.unitPrice">
 33               <el-input v-model="scope.row.num"></el-input>
 34             </el-form-item>
 35           </template>
 36         </el-table-column>
 37       </el-table>
 38     </el-form>
 39     <el-button type="primary" @click="save">批量开票</el-button>
 40   </div>
 41 </template>
 43 <script>
 44 export default {
 45   name: "table",
 46   data(){
 47     return {
 48       forms:{
 49         id:1,
 50         documentNo:null,
 51         buyerName:"服务技术",
 52         buyerDp:"42118XXXXXXXXXX72X",
 53         buyerBankAccount:"招商银行4890284",
 54         buyerAddressPhone:"深圳市宝安区110112",
 55         buyerEmail:null,
 56         buyerPhone:null,
 57         buyerType:"01",
 58         remarks:"这是备注",
 59         total:350.9,
 60         voList:[
 61           {
 62             goodsName:"黄金",
 63             goodsCode:"44021940",
 64             specification:null,
 65             unit:"克",
 66             num:1,
 67             unitPrice:291.37,
 68             taxRate:0.17,
 69             taxAmount:49.53,
 70             favouredPolicy:"0",
 71             zeroTaxRate:"",
 72             hsbz:"1"
 73           },
 74           {
 75             goodsName:"花生",
 76             goodsCode:"4574511",
 77             specification:null,
 78             unit:"斤",
 79             num:1,
 80             unitPrice:8.55,
 81             taxRate:0.17,
 82             taxAmount:1.45,
 83             favouredPolicy:"0",
 84             zeroTaxRate:"",
 85             hsbz:"1"
 86           }
 87         ]
 88       },
 89       rules:{
 90         num:[{required:true,message:'数量不能为空',trigger:'blur'}],
 91         unitPrice:[{required:true,message:'单价不能为空',trigger:'blur'}]
 92       }
 93     }
 94   },
 95   methods:{
 96     save(){
 97       this.$refs['forms'].validate((valid)=>{
 98         if(valid){
 99           console.log(1)
100         }
101       })
102     }
103   }
104 }
105 </script>
106 <style scoped lang="scss">
108 </style>
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

你可以在你的 vue 项目中尝试一下。

希望这个 demo 对你有所帮助！