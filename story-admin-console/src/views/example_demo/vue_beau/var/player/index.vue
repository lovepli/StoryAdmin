<template>
  <div class="player-container">
    <el-divider content-position="left">
      视频地址采用cdn加速服务，开发时需部署到到本地，使用方法可查看群文档
    </el-divider>
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
        <el-card shadow="hover">
          <div slot="header">播放传统MP4</div>
          <vab-player-mp4 :config="config1" @player="Player1 = $event" />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
        <el-card shadow="hover">
          <div slot="header">播放m3u8，且不暴露视频地址</div>
          <vab-player-hls
            :config="config2"
            @player="Player2 = $event"
          />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
        <el-card shadow="hover">
          <div slot="header">播放flv，且不暴露视频地址</div>
          <vab-player-flv
            :config="config3"
            @player="Player3 = $event"
          />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import { VabPlayerMp4, VabPlayerHls } from '@/plugins/vabPlayer.js'
import { VabPlayerMp4, VabPlayerHls, VabPlayerFlv } from 'zx-player'

export default {
  name: 'Player',
  components: {
    VabPlayerMp4,
    VabPlayerHls,
    VabPlayerFlv
  },
  data() {
    return {
      config1: {
        id: 'mse1',
        // url: 'https://cdn.jsdelivr.net/gh/chuzhixin/videos@master/video.mp4',
        url: 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4',
        // http://vjs.zencdn.net/v/oceans.mp4
        // https://media.w3.org/2010/05/sintel/trailer.mp4
        // http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4
        // https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4
        volume: 1,
        autoplay: false
      },
      Player1: null,
      config2: {
        id: 'mse2',
        // url: 'https://cdn.jsdelivr.net/gh/chuzhixin/videos@master/video.m3u8',
        url: 'https://zkcdn.wb699.com/2018/10/24/2PY1YaEdbzuReOkd/playlist.m3u8',
        volume: 1,
        autoplay: false
      },
      Player2: null,
      config3: {
        id: 'mse3',
        // url: 'https://cdn.jsdelivr.net/gh/chuzhixin/videos@master/video.flv',
        url: 'http://www.chplayer.com/examples/player.html?videourl=http://img.ksbbs.com/asset/Mon_1704/15868902d399b87.flv',
        volume: 1,
        autoplay: false
      },
      Player3: null
    }
  },
  created() {},
  mounted() {},
  methods: {}
}
</script>
