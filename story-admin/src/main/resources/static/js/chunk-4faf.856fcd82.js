(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-4faf"],{"/Tje":function(t,e,n){},DX0T:function(t,e,n){var a=n("fCtq"),r=n("S3Oy")(!0);a(a.S,"Object",{entries:function(t){return r(t)}})},HvUZ:function(t,e,n){"use strict";var a=n("/Tje");n.n(a).a},S3Oy:function(t,e,n){var a=n("Xp5O"),r=n("S5+y"),l=n("iorM"),i=n("DMUv").f;t.exports=function(t){return function(e){for(var n,o=l(e),u=r(o),c=u.length,s=0,d=[];c>s;)n=u[s++],a&&!i.call(o,n)||d.push(t?[n,o[n]]:o[n]);return d}}},Vkta:function(t,e,n){t.exports={default:n("chwg"),__esModule:!0}},c8w1:function(t,e,n){},chwg:function(t,e,n){n("DX0T"),t.exports=n("zpmP").Object.entries},d45l:function(t,e,n){"use strict";n.d(e,"b",function(){return d}),n.d(e,"a",function(){return f});var a=n("Q2cO"),r=n.n(a),l=n("Vkta"),i=n.n(l),o=n("rerW"),u=n.n(o),c=n("+XIM"),s=n.n(c);function d(){var t=function(){return(65536*(1+Math.random())|0).toString(16).substring(1)};return t()+t()+"-"+t()+"-"+t()+"-"+t()+"-"+t()+t()+t()}!function(t,e){var n=e-t+1;Math.floor(t+Math.random()*n)}(2,10);function f(t){if("object"!==(void 0===t?"undefined":r()(t))||null===t)return t;var e=Array.isArray(t)?[]:{},n=!0,a=!1,l=void 0;try{for(var o,c=u()(i()(t));!(n=(o=c.next()).done);n=!0){var d=o.value,m=s()(d,2),v=m[0],p=m[1];e[v]=f(p)}}catch(t){a=!0,l=t}finally{try{!n&&c.return&&c.return()}finally{if(a)throw l}}return e}},pYrt:function(t,e,n){"use strict";var a=n("c8w1");n.n(a).a},rDaH:function(t,e,n){"use strict";n.r(e);var a=n("6ZY3"),r=n.n(a),l=n("d45l"),i={date:{label:"起止时间",value:[]},department:{label:"所在单位",value:""},title:{label:"职务职称",value:""},task:{label:"主要工作",value:""}},o={name:"DynamicForm",data:function(){return{form:{workItem:[]},submitLoading:!1}},created:function(){this.initWorkItem(3)},methods:{initWorkItem:function(t){for(var e=t;e--;)this.handleAdd()},handleAdd:function(){var t=r()({},i);t.id=Object(l.b)(),this.form.workItem.push(t)},handleRemove:function(t){this.form.workItem.splice(t,1)},handldeSubmit:function(){this.$message.success("提交成功！")}}},u=(n("HvUZ"),n("pYrt"),n("ZrdR")),c=Object(u.a)(o,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"form-dynamic"},[n("el-form",{ref:"form",attrs:{model:t.form,"label-position":"top"}},[n("transition-group",{attrs:{name:"block",tag:"div"}},t._l(t.form.workItem,function(e,a){return n("div",{key:e.id,staticClass:"block-item"},[n("div",{staticClass:"block-item__index"},[t._v(t._s(a+1))]),t._v(" "),n("el-row",[n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:e.date.label}},[n("el-date-picker",{attrs:{type:"daterange",format:"yyyy年MM月dd日","value-format":"yyyy-MM-dd","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","unlink-panels":""},model:{value:e.date.value,callback:function(n){t.$set(e.date,"value",n)},expression:"item.date.value"}})],1)],1),t._v(" "),n("el-col",{attrs:{span:12}},[n("el-form-item",{attrs:{label:e.department.label}},[n("el-input",{model:{value:e.department.value,callback:function(n){t.$set(e.department,"value",n)},expression:"item.department.value"}})],1)],1)],1),t._v(" "),n("el-form-item",{attrs:{label:e.title.label}},[n("el-input",{model:{value:e.title.value,callback:function(n){t.$set(e.title,"value",n)},expression:"item.title.value"}})],1),t._v(" "),n("el-form-item",{attrs:{label:e.task.label}},[n("el-input",{model:{value:e.task.value,callback:function(n){t.$set(e.task,"value",n)},expression:"item.task.value"}})],1),t._v(" "),n("i",{staticClass:"el-icon-remove block-item--remove",on:{click:function(e){t.handleRemove(a)}}})],1)}))],1),t._v(" "),n("div",{staticClass:"block-item--add",on:{click:t.handleAdd}},[t._v("点击新增履历")]),t._v(" "),n("div",{staticStyle:{"text-align":"center"}},[n("el-button",{attrs:{loading:t.submitLoading,type:"primary",round:""},on:{click:t.handldeSubmit}},[t._v("提交")])],1)],1)},[],!1,null,"b1b8b772",null);c.options.__file="DynamicForm.vue";e.default=c.exports}}]);