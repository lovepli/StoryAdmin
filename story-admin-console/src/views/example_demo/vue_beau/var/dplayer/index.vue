<template>
  <div class="about">
    <d-player ref="player" id="player" :options="options"></d-player>
  </div>
</template>

<script>
// 单页引入弹幕视频播放器组件
import Vue from "vue";
import VueDPlayer from "vue-dplayer";
import "vue-dplayer/dist/vue-dplayer.css";
Vue.use(VueDPlayer);

export default {
  name: "dplayer",
  data() {
    return {
      options: {
        container: document.getElementById("dplayer"),   //播放器容器
        mutex: false, //  防止同时播放多个用户，在该用户开始播放时暂停其他用户
        theme: "#b7daff", // 风格颜色，例如播放条，音量条的颜色
        loop: false, // 是否自动循环
        lang: "zh-cn", // 语言，'en', 'zh-cn', 'zh-tw'
        // screenshot: true, // 是否允许截图（按钮），点击可以自动将截图下载到本地
        hotkey: true, // 是否支持热键，调节音量，播放，暂停等
        preload: "auto", // 自动预加载
        volume: 0.7, // 初始化音量
        playbackSpeed: [0.5, 0.75, 1, 1.25, 1.5, 2, 3], //可选的播放速度，可自定义
        logo:
          "https://qczh-1252727916.cos.ap-nanjing.myqcloud.com/pic/273658f508d04d488414fd2b84c9f923.png", // 在视频左角上打一个logo
        video: {
          url: "https://media.w3.org/2010/05/sintel/trailer.mp4", // 播放视频的路径
          quality: [
            // 设置多个质量的视频
            {
              name: "高清",
              url: "https://media.w3.org/2010/05/sintel/trailer.mp4",
              type: "auto", // 'auto', 'hls', 'flv', 'dash', 'webtorrent', 'normal' 或 其他自定义类型
            },
            {
              name: "标清",
              url:
                "https://api.dogecloud.com/player/get.mp4?vcode=5ac682e6f8231991&userId=17&ext=.mp4",
              type: "auto",
            },
          ],
          defaultQuality: 0, // 默认是高清
          pic: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606462956126&di=2d87964d4faf656af55d09d938640d97&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201310%2F10%2F150326y7dzdd8d4kpjjdsd.jpg", // 视频封面图片
          thumbnails: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606462956126&di=2d87964d4faf656af55d09d938640d97&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201310%2F10%2F150326y7dzdd8d4kpjjdsd.jpg", // 进度条上的缩略图,需要通过dplayer-thumbnails来生成
        },
        // subtitle: {   //字幕
        //   url: "",   //字幕网址
        //   color:'#fff' 字幕颜色
        //   fontSize: "20px",
        //   bottom: "40px",
        // },
        danmaku: {
          // 弹幕
          id: "9E2E3368B56CDBB4",
          api: "https://api.prprpr.me/dplayer/",
          token: "tokendemo", //后端验证令牌
          maximum: 1000, //弹幕最大数量
          addition: ["https://api.prprpr.me/dplayer/v3/bilibili?aid=4157142"], //其他弹幕
          user: "DIYgod", //弹幕用户名
          bottom: "15%", //值，例如：10px，10％，即弹幕底部和播放器底部之间的距离，以防止遮挡字幕
          unlimited: true, //即使弹幕重叠也显示所有弹幕
        },
        contextmenu: [
          //  自定义上下文菜单
          // 右键菜单
          {
            text: "custom1",
            link: "https://www.bilibili.com",
          },
          {
            text: "custom2",
            click: (player) => {
              console.log(player);
            },
          },
        ],
        highlight: [
          //进度条上的自定义时间标记
          // 进度条时间点高亮
          {
            text: "10M",
            time: 6,
          },
          {
            text: "20M",
            time: 12,
          },
        ],
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.about {
  width: 1000px;
  height: 800px;
  margin: 0 auto;
  text-align: center;
}
</style>