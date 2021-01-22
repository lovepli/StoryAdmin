
<template>
  <div>
    <div style="padding:10px 0;">
      <a-player :music="songList" :showlrc="3" :narrow="false" theme="#b7daff" mode="circulation" v-if="flag" listmaxheight='96px' ref="player"></a-player>
    </div>
    
  </div>
</template>
 
<script>
import axios from 'axios'
import VueAplayer from 'vue-aplayer'
 
export default {
  components: {
    //别忘了引入组件
    'a-player': VueAplayer
  },
   name: "aplayer",
  data () {
  	return {
      flag:false,
      musicList:'',
      songList:[]
  	}
  },
  async mounted () {
    console.log("hello world!")
    //异步加载，先加载出player再使用
   // await this.init();
   // let aplayer = this.$refs.player.control;
   // aplayer.play();
  },
  methods:{
    async init () {
      //这边是引入了axios然后使用的get请求的一个音乐列表接口
      const getMusicList = url => axios.get(url);
      //这边url随大家更改了
      let url = '';
      let data = await getMusicList(url);
      //以下就是这边对请求的一个处理，看接口了
      if(data && data.data.showapi_res_code==0){
        this.musicList = data.data.showapi_res_body.pagebean.songlist;
        
        for(let i=0;i<=this.musicList.length;i++){
          if(i<=9){
            let obj={};
            //url=>歌曲地址 title=>头部 author=>歌手 pic=>写真图片 lrc=>歌词
            //其中url必须有，其他的都是非必须
            obj.title = this.musicList[i].songname;
            obj.author = this.musicList[i].singername;
            obj.url = this.musicList[i].url;
            obj.pic = this.musicList[i].albumpic_small;
            obj.lrc = this.musicList[i].irl;
            //把数据一个个push到songList数组中，在a-player标签中使用 :music="songList" 就OK了
            this.songList.push(obj);
          }
        }
        //因为是异步请求，所以一开始播放器中是没有歌曲的，所有给了个v-if不然会插件默认会先生成播放器，导致报错(这个很重要)
        this.flag = true;
      };
    }
  }
}
</script>
 
<style scoped>
</style>