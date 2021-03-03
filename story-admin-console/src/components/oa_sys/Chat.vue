<template>
  <div class="chat">
    <el-dialog
      :visible.sync="chatDialog"
      :close-on-click-modal="false"
      title="聊天室"
      center
    >
      <el-scrollbar ref="elscrollbar" style="height: 300px">
        <ul>
          <li v-for="item in messages" :key="item.id">
            <div v-if="item.username != name" class="message">
              <div class="user">
                <!-- 用户图像 -->
                <el-avatar :src="item.avatar" class="avatar" />
                <div class="name">{{ item.username }}</div>
              </div>
              <!-- 发送的内容 -->
              <div class="text">{{ item.content }}</div>
            </div>

            <div v-else class="message mine">
              <div class="user">
                <el-avatar :src="item.avatar" class="avatar" />
                <div class="name">{{ item.name }}</div>
              </div>
              <div class="text">{{ item.content }}</div>
            </div>
          </li>
        </ul>
      </el-scrollbar>

      <span slot="footer" class="dialog-footer">
        <el-input v-model="content" type="textarea" />
        <div class="send-button">
          <el-button
            size="small"
            type="primary"
            @click="sendMessage"
          >发送</el-button
          >
        </div>
      </span>
    </el-dialog>
    <el-badge :value="unreadCount" :max="99" class="open-button">
      <!-- 点击聊天，弹框弹出来 -->
      <!-- <el-button size="small"  @click="openDialog">聊天</el-button> -->

    <el-tooltip content="聊天" effect="dark" placement="bottom">
      <el-button  type="text"  icon="el-icon-chat-dot-round" @click="openDialog()">
  </el-button>
   </el-tooltip>

    </el-badge>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getToken } from '@/utils/auth' // 验权

export default {
  name: 'Chat',
  data() {
    return {
      // userId: this.$store.getters.id, // 当前用户ID
      // name: this.$store.getters.name, // 当前用户昵称
      // avatar: this.$store.getters.avatar, // 当前用户头像

      content: '',
      messages: [],
      unreadCount: 0,
      webSocket: null,
      chatDialog: false
    };
  },
  computed: {
    ...mapGetters([
      'name', // 当前用户
      'avatar' // 用户图像
    ])
  },
    // 侦听属性
  // 数据响应式变化：虽然计算属性在大多数情况下更合适，但有时也需要一个自定义的侦听器。
  // 这就是为什么 Vue 通过 watch 选项提供了一个更通用的方法，来响应数据的变化。
  // 当需要在数据变化时执行异步或开销较大的操作时，这个方式是最有用的
  watch: {
    // 如果 `messages`属性值发生改变，这个函数里的内容就会执行
    // 这里的message()的message是指data对象里的`messages`属性对象，方法名必须为属性名，与计算属性的getter方法名称不同，计算属性的getter方法名可以随便取一个
    messages() {
      if (this.chatDialog === true) {
        const div = this.$refs['elscrollbar'].$refs['wrap'];
        // 消息长度增长
        this.$nextTick(() => {
          div.scrollTop = div.scrollHeight;
        });
      }
    }
  },
  mounted() {
  // if (this.auth && 'WebSocket' in window) {
    if (getToken() && 'WebSocket' in window) {
    //  this.initWebSocket();
    }
  },
  methods: {
    // 进入页面创建websocket连接
    initWebSocket() {
      this.webSocket = new WebSocket('ws://127.0.0.1:9428/chat');
      this.webSocket.onmessage = this.webSocketMessage;
    },
    webSocketMessage(event) {
      this.messages.push(JSON.parse(event.data));
      if (this.chatDialog === false) {
        this.unreadCount++;
      }
    },
    // 发送聊天信息
    sendMessage() {
      if (this.content !== null && this.content.trim() !== '') {
        this.webSocket.send(this.content);
        this.content = null;
      }
    },
    openDialog() {
      if (this.webSocket == null && 'WebSocket' in window) {
        this.initWebSocket();
      }
      this.chatDialog = true;
      this.unreadCount = 0;
    }
  }
};
</script>

<style scoped>
/* 聊天按钮置于底部 */
/* .open-button {
  position: fixed;
  bottom: 25px;
  right: 40px;
} */

.send-button {
  margin-top: 15px;
  text-align: right;
}

.message {
  position: relative;
  margin-bottom: 10px;
  padding-left: 60px;
}

.user {
  position: absolute;
  left: 0;
}

.name {
  position: absolute;
  left: 60px;
  top: 0;
  color: #888;
  font-size: 12px;
  white-space: nowrap;
}

.text {
  color: #333;
  position: relative;
  line-height: 25px;
  margin-top: 20px;
  padding: 8px 15px;
  border-radius: 3px;
  display: inline-block;
  background-color: #e2e2e2;
  word-break: break-all;
}

.mine {
  text-align: right;
  padding-left: 0;
  padding-right: 70px;
}

.mine .user {
  right: 10px;
}

.mine .name {
  left: auto;
  right: 70px;
  text-align: right;
}

.mine .text {
  color: #fff;
  text-align: left;
  background-color: #5fb878;
}

.text::after {
  content: "";
  position: absolute;
  left: -10px;
  top: 12px;
  overflow: hidden;
  border-width: 10px;
  border-style: solid dashed dashed;
  border-color: #e2e2e2 transparent transparent;
}

.mine .text::after {
  left: auto;
  right: -10px;
  border-top-color: #5fb878;
}

.el-alert {
  width: 40%;
}
</style>
