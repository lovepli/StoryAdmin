> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/iamlujingtao/article/details/105186117)

### vue+ElementUI 表单嵌套表格逐行校验（新增、编辑）的完美解决方案

*   [一、成果](#_2)
*   [二、要点和解决思路](#_17)
*   [三、源码](#_100)

一、成果
====

[**在线地址**](https://lujingtao.github.io/vue-ElementUI-Form-nested-table-for-single-line-verification/dist/index.html)

[gitHub 地址](https://github.com/lujingtao/vue-ElementUI-Form-nested-table-for-single-line-verification)

`如果帮到你，请点个赞^_^`  
![](https://img-blog.csdnimg.cn/20200329212923318.gif#pic_center)

如图，ElementUI 表单里嵌套了表格，表格内每行能进行新增、编辑、删除等操作，`同时能针对该行的字段进行校验（而不是整个表单校验！）`，这种需求应该很常见，但是搜了很多资料，没有完美的解决方案，大部分都只是针对整个表单进行校验，而不是一行里面的字段校验，所以困扰了很久，最近终于研究了完美解决方案，分享给大家。

二、要点和解决思路
=========

1.  **表格数据 datas 必须是某对象（以 form 为例）的一个属性**
    
    ```
    form: {
        datas: [
          { id: 1, name: "张三", age:20 },
          { id: 2, name: "李四", age:32 },
        ],
      },
    ```
    
2.  **每个字段要动态绑定表单的 prop 属性（以 name 字段为例）**
    
    ```
    <el-form :model="form" :rules="rules" ref="form">
    	.....
       <el-form-item :prop="'datas.'+scope.$index + '.name'" :rules='rules.name'>
    </el-form>
    ```
    
    关键代码`:prop="'datas.'+scope.$index + '.name'"`，这是 elementui 规定的格式，渲染后的结果为’datas.1.name’。所以必须结合第 1 点才能实现，否则提示出错！_（这里坑了我很长时间）_
    
3.  **每个字段要动态绑定表单的 rules 属性**  
    <el-form-item :prop="‘datas.’+scope.$index + ‘.name’" `:rules='rules.name'`>
    
4.  **针对某一行的所有字段进行校验**  
    通过控制台查看得知：`表单的字段对象存在this.$refs[‘form’].fields里面，并且字段对象具有prop属性（’datas.1.name’）和validateField方法（验证’datas.1.name’能否通过校验）。`
    
    那么我们创建一个函数 validateField，来判断第 index 行的所有字段能否通过校验：
    
    ```
    //对部分表单字段进行校验
      validateField(form,index){
        let result = true;
        for (let item of this.$refs[form].fields) {
          if(item.prop.split(".")[1] == index){
            this.$refs[form].validateField(item.prop,(error)=>{
              if(error!=""){
                result = false;
              }
            });
          }
          if(!result) break;
        }
        return result;
      },
    ```
    
5.  **针对某一行的所有字段进行重置操作**  
    同理，`表单的字段对象存在resetField方法来重置数据`，那么我们创建一个函数 resetField，来重置第 index 行的所有字段
    
    ```
    //对部分表单字段进行重置
      resetField(form,index){
        this.$refs[form].fields.forEach(item=>{
          if(item.prop.split(".")[1] == index){
            item.resetField();
          }
        })
      },
    ```
    
6.  **每一行的状态可以通过添加属性 action 来处理**  
    数据初始化的时候，每一行对象添加 action 属性（‘view’：查看状态，'edit’编辑状态，'add’新增状态）
    
    ```
    created() {
         //处理数据，为已有数据添加action:'view'
         this.form.datas.map(item => {
           this.$set(item,"action","view")
           return item;
         });
    
         //再插入一条添加操作的数据
         this.form.datas.unshift({
           id:undefined,
           name:undefined,
           age:undefined,
           action: "add"
         });
       },
    ```
    

三、源码
====

```
<template>
  <div id="app">
    <el-form :model="form" :rules="rules" ref="form">
      <el-table :data="form.datas" highlight-current-row style="width: 100%">
        <el-table-column prop="id" label="序号" width="60"></el-table-column>

        <el-table-column prop="name" label="姓名">
          <template slot-scope="scope">
            <template v-if="scope.row.action == 'view'">
              {{scope.row.name}}
            </template>
            <template v-else>
              <el-form-item :prop="'datas.'+scope.$index + '.name'" :rules='rules.name'>
                <el-input size="mini" v-model.trim="scope.row.name" style="width: 120px;"></el-input>
              </el-form-item>
            </template>
          </template>
        </el-table-column>
        
        <el-table-column prop="name" label="年龄">
          <template slot-scope="scope">
            <template v-if="scope.row.action == 'view'">
              {{scope.row.age}}
            </template>
            <template v-else>
              <el-form-item :prop="'datas.'+scope.$index + '.age'" :rules='rules.age'>
                <el-input size="mini" v-model.number="scope.row.age" style="width: 60px;"></el-input>
              </el-form-item>
            </template>
          </template>
        </el-table-column>

        <el-table-column prop="operation" label="操作">
          <template slot-scope="scope">
            <template v-if="scope.row.action == 'view'">
              <el-button size="mini" @click="click_edit(scope.row, scope.$index)">编辑</el-button>
              <el-button size="mini" @click="click_delete(scope.row, scope.$index)">删除</el-button>
            </template>
            <template v-else-if="scope.row.action == 'add'">
              <el-button size="mini" @click="click_add( scope.row, scope.$index)">新增</el-button>
              <el-button size="mini" @click="click_reset(scope.row, scope.$index)">重置</el-button>
            </template>
            <template v-else>
              <el-button size="mini" @click="click_save(scope.row, scope.$index)">保存</el-button>
              <el-button size="mini" @click="click_cancle(scope.row, scope.$index)">取消</el-button>
            </template>
          </template>
        </el-table-column>
      </el-table>
    </el-form>
  </div>
</template>
<script>



</script>
<script>
  export default {
    data() {
      return {
        form: {
          datas: [
            { id: 1, name: "张三", age:20 },
            { id: 2, name: "李四", age:32 },
          ],
        },

        //表单验证规则
        rules: {
          name: [{
            type: 'string',
            required: true,
            trigger: 'blur',
            message: '请输入姓名',
          }],
          age: [{
            type: 'number',
            required: true,
            trigger: 'blur',
            message: '请输入年龄',
          },
          {
            type: 'number',
            trigger: 'blur',
            min: 0,
            max: 120,
            message: '年龄最小0，最大120',
          }],
        }
      }
    },

    created() {
      //处理数据，为已有数据添加action:'view'
      this.form.datas.map(item => {
        this.$set(item,"action","view")
        return item;
      });

      //再插入一条添加操作的数据
      this.form.datas.unshift({
        id:undefined,
        name:undefined,
        age:undefined,
        action: "add"
      });
    },

    methods: {
      //对部分表单字段进行校验
      validateField(form,index){
        let result = true;
        for (let item of this.$refs[form].fields) {
          if(item.prop.split(".")[1] == index){
            this.$refs[form].validateField(item.prop,(error)=>{
              if(error!=""){
                result = false;
              }
            });
          }
          if(!result) break;
        }
        return result;
      },
      
      //对部分表单字段进行重置
      resetField(form,index){
        this.$refs[form].fields.forEach(item=>{
          if(item.prop.split(".")[1] == index){
            item.resetField();
          }
        })
      },
      
      //新增操作
      click_add(item,index) {
        if( !this.validateField('form',index) ) return;
        //模拟新增一条数据
        let itemClone = JSON.parse(JSON.stringify(item));
        itemClone.id = this.form.datas.length;
        itemClone.action = "view";
        this.form.datas.push(itemClone);
        this.resetField('form',index);
      },
      
      //新增-重置操作
      click_reset(item,index) {
        this.resetField('form',index);
      },
      
      //编辑-保存操作
      click_save(item,index) {
        if( !this.validateField('form',index) ) return;
        item.action = "view";
      },
      
      //编辑-取消操作
      click_cancle(item,index) {
        this.resetField('form',index);
        item.action = "view";
      },
      
      //编辑操作
      click_edit(item,index) {
        item.action = "edit";
      },

      //删除操作
      click_delete(item,index) {
        this.$confirm("确定删除该条数据(ID" + item.id + ")吗?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
          .then(() => {
            //模拟删除一条数据
            this.form.datas.splice(index,1);
          })
          .catch(() => {});
      },

    },
  }
</script>


<style>
  .el-table .cell{
    overflow: visible;
  }
  .el-form-item{
    margin-bottom: 0;
  }
  .el-form-item__error{
    padding-top:0;
    margin-top:-3px;
  }
</style>
```