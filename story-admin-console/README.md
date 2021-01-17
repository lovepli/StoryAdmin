# STORY-ADMIN
## 项目简介

    基于Vue+Element的通用后台管理系统。提供了一些典型的中后台业务功能。

## 技术依赖

- 主体：Vue、ElementUI、webpack
- 图表：Antv/G2
- Excel：js-xlsx
- pdf：pdf.js
- 图片生成：html2canvas
- 富文本编辑器：Tinymce
- 数据：axios、Mock
- 地图：高德

## 项目地址

- [github](https://github.com/Wluyao/vue-element-manage)
- [gitee](https://github.com/Wluyao/vue-element-manage)
- [预览](https://wluyao.gitee.io/vue-element-manage)

## 功能

- 登录/退出
- 全屏浏览
- 一键换肤
- 系统风格
- 元素大小
- 个人中心
- 侧边菜单
- 标签导航
- 图表
  - 折线图
  - 面积图
  - 柱状图
  - 条形图
  - 饼图
  - 散点图
- 表单
  - 基础表单
  - 步骤表单
  - 动态表单
- 表格
- Tab 选项卡
- 权限控制
- 用户管理
- 文章管理
  - 创建文章
  - 文章列表
- pdf
- 上传
  - 头像上传
  - 文件上传
- 错误处理
  - 403
  - 404
- 其他功能
  - 导入/导出 excel
  - 滚动条
  - 打印
  - html2canvas
  - 拖拽 Dialog
  - 地图
  - 快捷复制
  - 文本溢出


## 目录结构

```
|-- config              webpack配置文件
|-- dist                webpack构建目录
|-- docs                文档
|-- public              html模板
|-- src                 源码目录
|	|-- api                   接口
|	|-- assets                静态资源文件，会被webpack解析为模块依赖
|		|-- img                     图片
|		|-- fonts                   字体
|	|-- components            全局公共组件
|	|-- directive             全局公共指令
|	|-- filters               过滤器
|	|-- layouts               基础布局
|	|-- mock                  数据模拟
|	|-- pages                 页面级组件
|	|-- router                路由管理
|	|-- store                 状态管理
|	|-- utils                 全局公用方法
|	|-- App.vue               根组件
|	|-- main.js               入口文件，加载各种组件
|-- static              第三方纯静态资源，不会被webpack处理
|-- .babelrc            babel-loader 配置
|-- .editorconfig       IDE配置
|-- .gitignore          git提交时忽略的文件
|--	package.json        项目基本信息
|-- README.md           项目说明
```

## 项目截图

![](https://s2.ax1x.com/2020/01/02/lt7zse.png)

![](https://s2.ax1x.com/2020/01/02/lt7FvF.png)

![](https://s2.ax1x.com/2020/01/02/ltHMon.png)

## 使用

#### 安装依赖
```
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org
```

#### 运行
```
npm run devdev
```

#### 构建
```
npm run build
```
###*********************############

> 以下为此框架所用模板，可供参考使用

**Live demo:** http://panjiachen.github.io/vue-admin-template

[前端模板中文文档](https://github.com/PanJiaChen/vue-admin-template/blob/master/README-zh.md)
vue-element-admin官方文档：https://panjiachen.gitee.io/vue-element-admin-site/zh/

前端文档食用参考：https://tycoding.cn/2019/05/30/cloud/cloud-template-app/
https://docs.tumo.tycoding.cn/#/docs/vue/vue-1

## 出现的问题解决方案
1. vue-cli3 一直运行 http://localhost:8080/sockjs-node/info?t=1462183700002
解决方案 参考：https://www.cnblogs.com/sichaoyun/p/10178080.html

## Build Setup

```bash
# Clone project
git clone https://github.com/sunnj/story-admin-console.git

# Install dependencies
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report
```

# Vue相关开源项目库汇总(史上最全)
https://www.jianshu.com/p/e1c0cbbfb599
# vue开源项目库汇总
https://www.jianshu.com/p/f5a5c70013d4


