
<template>
  <div>
    <div style="padding:30px 200px;">
      <aplayer
        :music="{
          title: '给我一个理由忘记',
          artist: 'A-Lin',
          src: 'http://music.163.com/song/media/outer/url?id=25640799.mp3',
          pic: 'http://p2.music.126.net/0POVOSSjqgVoOUGc5haWBQ==/109951163392311918.jpg'
        }"
        :list="musicList"
        style="margin-top:20px;margin-bottom:30px;"
        autoplay
      />
    </div>
  </div>
</template>

<script>
import aplayer from 'vue-aplayer';
export default {
  name: 'Aplayer',
  components: {
    // 别忘了引入组件
    aplayer: aplayer
  },
  data() {
    return {
      musicList: [{
        'artist': 'Eminem',
        'lrc': '',
        'title': 'Airplanes',
        'src': 'http://music.163.com/song/media/outer/url?id=26714821.mp3',
        'pic': 'http://p4.music.126.net/H9HJibEzTL34aIT6nsqKsQ==/5682276092402519.jpg'
      }, {
        'artist': 'Tinashe',
        'lrc': '',
        'title': 'Story of Us',
        'src': 'http://music.163.com/song/media/outer/url?id=1403428061.mp3',
        'pic': 'http://p3.music.126.net/l2XttTpEa14IEZtUsQX1HA==/109951164486978461.jpg'
      }, {
        'artist': 'Chris Brown',
        'lrc': '',
        'title': 'War For You',
        'src': 'http://music.163.com/song/media/outer/url?id=30431534.mp3',
        'pic': 'http://p3.music.126.net/YWkl1JXVKm7bOBAew72lGg==/109951163958771792.jpg'
      }, {
        'artist': 'Sarah Darling',
        'lrc': '',
        'title': 'Jack of Hearts',
        'src': 'http://music.163.com/song/media/outer/url?id=19132440.mp3',
        'pic': 'http://p4.music.126.net/4q3kpn5VLo3x7hVWttj0QA==/109951164802108652.jpg'
      }, {
        'artist': 'Benjamin Ingrosso',
        'lrc': '',
        'title': 'Costa Rica',
        'src': 'http://music.163.com/song/media/outer/url?id=1372897252.mp3',
        'pic': 'http://p4.music.126.net/mmm97zC81t73rToPFuXXnw==/109951164159882466.jpg'
      }, {
        'artist': 'Yo Trane',
        'lrc': '',
        'title': 'Affection',
        'src': 'http://music.163.com/song/media/outer/url?id=1393553542.mp3',
        'pic': 'http://p4.music.126.net/T_vdbfQPO4HE4zVE_8rgCQ==/109951164389023010.jpg'
      }]
    }
  }
}
</script>

<style scoped>
</style>
