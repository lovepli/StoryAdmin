(window.webpackJsonp=window.webpackJsonp||[]).push([["lCXt"],{lCXt:function(e,s,t){"use strict";t.r(s);var a={data:function(){return{article_id:this.$route.query.article_id,article_title:this.$route.query.article_title,dialog_visible_state:!1,dialog_title:"",reply_message:"",user_list_data:[{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"小明",user_message_content:"这是第一条留言内容",user_message_time:"2020/9/21 13:45:01",user_message_state:"no"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"李四",user_message_content:"如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。",user_message_time:"2020/9/21 13:15:01",user_message_state:"yes"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"小明",user_message_content:"这是第一条留言内容",user_message_time:"2020/9/21 13:45:01",user_message_state:"no"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"李四",user_message_content:"如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。",user_message_time:"2020/9/21 13:15:01",user_message_state:"yes"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"小明",user_message_content:"这是第一条留言内容",user_message_time:"2020/9/21 13:45:01",user_message_state:"no"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"李四",user_message_content:"如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。",user_message_time:"2020/9/21 13:15:01",user_message_state:"yes"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"小明",user_message_content:"这是第一条留言内容",user_message_time:"2020/9/21 13:45:01",user_message_state:"no"},{user_avatar_src:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",user_pet_name:"李四",user_message_content:"如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。如果输入的文字没有空格，是连续性的，那么这个时候默认不会换行的，这个时候需要拖拉到右边查看内容。",user_message_time:"2020/9/21 13:15:01",user_message_state:"yes"}]}},methods:{reply:function(e){this.dialog_visible_state=!0,this.dialog_title=this.user_list_data[e].user_pet_name},send:function(){var e=this;this.$message.success("回复成功"),setTimeout(function(){e.dialog_visible_state=!1},1500)},delete_message:function(e){var s=this;this.$message.success("删除成功"),setTimeout(function(){s.user_list_data.splice(e,1)},1500)}}},_=t("ZrdR"),r=Object(_.a)(a,function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("div",[t("el-card",{attrs:{shadow:"never"}},[t("div",{attrs:{slot:"header"},slot:"header"},[t("span",[e._v("正在查看id:"+e._s(e.article_id)+"《"+e._s(e.article_title)+"》的留言")])]),e._v(" "),t("div",[t("el-collapse",{attrs:{accordion:""}},e._l(e.user_list_data,function(s,a){return t("el-collapse-item",{key:a},[t("template",{slot:"title"},[t("el-avatar",{attrs:{src:s.user_avatar_src}}),e._v("\n            "+e._s(s.user_pet_name)+"\n            "),t("el-tag",{staticStyle:{"margin-left":"20px"},attrs:{type:"no"==s.user_message_state?"danger":"success",size:"small"}},[e._v(e._s("no"==s.user_message_state?"未回复":"已回复"))])],1),e._v(" "),t("div",[t("p",{staticStyle:{"word-break":"break-all"}},[e._v(e._s(s.user_message_content))]),e._v(" "),t("p",{staticStyle:{color:"#909399"}},[e._v("留言时间："+e._s(s.user_message_time))])]),e._v(" "),t("div",{staticStyle:{"margin-top":"10px"}},[t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary",size:"mini",plain:""},on:{click:function(s){e.reply(a)}}},[t("i",{staticClass:"el-icon-edit"})]),e._v(" "),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"danger",size:"mini",plain:""},on:{click:function(s){e.delete_message(a)}}},[t("i",{staticClass:"el-icon-delete"})])],1)],2)}))],1)]),e._v(" "),t("el-dialog",{attrs:{title:"正在回复《"+e.dialog_title+"》的留言",visible:e.dialog_visible_state,align:"center"},on:{"update:visible":function(s){e.dialog_visible_state=s}}},[t("el-input",{attrs:{rows:5,type:"textarea",placeholder:"请输入回复内容"},model:{value:e.reply_message,callback:function(s){e.reply_message=s},expression:"reply_message"}}),e._v(" "),t("el-button",{staticStyle:{"margin-top":"20px"},attrs:{type:"primary"},on:{click:e.send}},[e._v("确认回复")])],1)],1)},[],!1,null,null,null);r.options.__file="ArticleReplySpace.vue";s.default=r.exports}}]);