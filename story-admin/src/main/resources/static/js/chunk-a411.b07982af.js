(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-a411"],{U3g8:function(e,o,i){"use strict";i.r(o);var t=i("omC7"),s=i.n(t),n={data:function(){return{login_title:"EuiAdmin",login_adress:"EuiAdmin登录模板，极简超强的vue框架",login_form:{user_name:"EuiAdmin",user_password:"12346"},remember_password:!1}},mounted:function(){},methods:{login:function(){var e=this;this.$message.success("登录成功"),setTimeout(function(){e.$router.push("/euihome/index")},1500)},to:function(e){this.$router.push(e)},setting_web:function(){this.color_form={aside_background_color:"#000000",aside_text_color:"#ffffff",aside_icon_color:"#ffffff",aside_active_text_color:"#67C23A",aside_title:"EuiAdmin",aside_small_title:"Eui",aside_width:200,aside_small_width:65,head_icon_color:"#606266",head_background_color:"#fff",head_text_color:"#606266",head_active_text_color:"#303133",head_height:60,main_background_color:"#f2f6fc"},this.$cookies.set("setting",s()(this.color_form))},vist:function(){console.log(!this.$cookies.isKey("vist_label")),this.$cookies.isKey("vist_label")||(this.$cookies.set("vist_label","null",-1),this.$axios({method:"post",url:"/vist/ip",data:this.$qs.stringify({vist_label:this.$cookies.get("vist_label")})}).then(function(e){console.log(e)}))}}},r=(i("yM6/"),i("ZrdR")),l=Object(r.a)(n,function(){var e=this,o=e.$createElement,i=e._self._c||o;return i("div",{attrs:{id:"login"}},[i("div",{attrs:{id:"form_space"}},[i("div",{attrs:{align:"center"}},[i("h1",[e._v(e._s(e.login_title))]),e._v(" "),i("p",[e._v(e._s(e.login_adress))])]),e._v(" "),i("div",{staticStyle:{padding:"20px"}},[i("el-form",{ref:"form",attrs:{model:e.login_form}},[i("el-form-item",[i("el-input",{attrs:{"prefix-icon":"el-icon-user"},model:{value:e.login_form.user_name,callback:function(o){e.$set(e.login_form,"user_name",o)},expression:"login_form.user_name"}})],1),e._v(" "),i("el-form-item",[i("el-input",{attrs:{"prefix-icon":"el-icon-lock","show-password":""},model:{value:e.login_form.user_password,callback:function(o){e.$set(e.login_form,"user_password",o)},expression:"login_form.user_password"}})],1),e._v(" "),i("el-form-item",[i("el-checkbox",{model:{value:e.remember_password,callback:function(o){e.remember_password=o},expression:"remember_password"}},[e._v("记住密码")])],1),e._v(" "),i("el-form-item",{attrs:{align:"center"}},[i("el-button",{attrs:{type:"primary",icon:"el-icon-right"},on:{click:function(o){e.login()}}},[e._v("登录")])],1),e._v(" "),i("el-form-item",[i("el-link",{staticStyle:{float:"left"},attrs:{type:"danger"},on:{click:function(o){e.to("/euihome/register")}}},[e._v("注册")]),e._v(" "),i("el-link",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:function(o){e.to("/euihome/forget/password")}}},[e._v("找回密码？")])],1)],1)],1)])])},[],!1,null,"1b31086c",null);l.options.__file="Login.vue";o.default=l.exports},lCw3:function(e,o,i){},"yM6/":function(e,o,i){"use strict";var t=i("lCw3");i.n(t).a}}]);