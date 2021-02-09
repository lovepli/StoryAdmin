<template>
  <div class="sign">
    <div class="time">{{ currentTime | formatTime }}</div>
    <div class="date">{{ currentDate | formatDate }}</div>
    <div class="begin">上班时间：{{ attendanceTime[0] }}</div>
    <div class="end">下班时间：{{ attendanceTime[1] }}</div>
    <div class="button">
      <el-button-group>
        <el-button
          :disabled="isSignIn"
          type="primary"
          icon="el-icon-arrow-left"
          @click="signIn"
        >
          签到
        </el-button>
        <el-button :disabled="isSignOut" type="primary" @click="signOut">
          签退<i class="el-icon-arrow-right el-icon--right"/></el-button>
      </el-button-group>
    </div>
    <el-steps :active="active" align-center>
      <el-step>
        <!-- 具名插槽 slot -->
        <div slot="title">
          <span v-if="isSignIn">已签到</span>
          <span v-else>未签到</span>
        </div>
        <div slot="description">
          <span v-if="isSignIn">签到时间：{{ attendance.signInTime }}</span>
        </div>
      </el-step>
      <el-step>
        <div slot="title">
          <span v-if="isSignOut">已签退</span>
          <span v-else>未签退</span>
        </div>
        <div slot="description">
          <span v-if="isSignOut">签退时间：{{ attendance.signOutTime }}</span>
        </div>
      </el-step>
    </el-steps>
  </div>
</template>

<script>
import {
  getAttendance,
  getAttendanceTime,
  signIn,
  signOut
} from '@/api/oa_sys/oa_sign';

export default {
  name: 'Sign',
  filters: {
    formatTime(value) {
      var hours = value.getHours();
      var minutes = value.getMinutes();
      if (hours < 10) hours = '0' + hours;
      if (minutes < 10) minutes = '0' + minutes;
      return hours + ':' + minutes;
    },
    formatDate(value) {
      var year = value.getFullYear();
      var month = value.getMonth() + 1;
      var date = value.getDate();
      if (year < 10) year = '0' + year;
      if (month < 10) month = '0' + month;
      if (date < 10) date = '0' + date;
      return year + '年' + month + '月' + date + '日';
    }
  },
  data() {
    return {
      attendance: {},
      attendanceTime: [],
      currentDate: new Date(),
      currentTime: new Date()
    };
  },
  // computed 计算属性的结果会被缓存，除非依赖的响应式 property 变化才会重新计算。注意，如果某个依赖 (比如非响应式 property) 在该实例范畴之外，则计算属性是不会被更新的。
  // 计算属性和方法的不同:
  // 我们也可以将同一函数定义为一个方法而不是一个计算属性。两种方式的最终结果确实是完全相同的。然而，不同的是计算属性是基于它们的响应式依赖进行缓存的。只在相关响应式依赖发生改变时它们才会重新求值。这就意味着只要 message 还没有发生改变，多次访问 reversedMessage 计算属性会立即返回之前的计算结果，而不必再次执行函数。
  // 相比之下，每当触发重新渲染时，调用方法将总会再次执行函数。
  // 我们为什么需要缓存？
  // 我们为什么需要缓存？假设我们有一个性能开销比较大的计算属性 A，它需要遍历一个巨大的数组并做大量的计算。然后我们可能有其他的计算属性依赖于 A。如果没有缓存，我们将不可避免的多次执行 A 的 getter！如果你不希望有缓存，请用方法来替代
  computed: {
    // 计算属性的 getter的概念理解：
    // 这里我们声明了一个计算属性 isSignIn。我们提供的函数将用作 property this.isSignIn 的 getter 函数：
    // this.isSignIn 的值始终取决于 this.attendance 的值
    // this.isSignIn 依赖于 this.attendance，因此当 this.attendance 发生改变时，所有依赖 this.isSignIn 的绑定也会更新。
    isSignIn() {
      // attendance值改变了，响应式的动态进行改变，相当于监听到了值改变了，然后立即调用isSignIn方法进行值改变，isSignIn这个方法名字可以随便取的
      return this.attendance != null;
    },
    isSignOut() {
      // 一旦this.attendance.hasSignOut值改变了，响应式的动态进行改变hasSignOut的值
      if (this.attendance == null) {
        return false;
      }
      return this.attendance.hasSignOut;
    },
    active() {
      if (this.attendance == null) {
        return 0;
      } else {
        if (this.attendance.hasSignOut) {
          return 2;
        } else {
          return 1;
        }
      }
    }
  },
  created() {
    this.getAttendance();
  },
  methods: {
    getAttendance() {
      getAttendance().then((response) => {
        if (response && response.code === 20000) {
          this.attendance = response.data;
          this.getAttendanceTime();
        }
      });
    },
    getAttendanceTime() {
      getAttendanceTime().then((response) => {
        if (response && response.code === 20000) {
          this.attendanceTime = response.data;
        }
      });
    },
    signIn() {
      signIn().then((response) => {
        if (response && response.code === 20000) {
          // this.$message.success(response.message);
          this.getAttendance();
        }
      });
    },
    signOut() {
      signOut().then((response) => {
        if (response && response.code === 20000) {
          // this.$message.success(response.message);
          this.getAttendance();
        }
      });
    }
  }
};
</script>

<style scoped>
.sign {
  text-align: center;
}

.time {
  margin-top: 40px;
  font-size: 50px;
}

.date {
  margin: 20px 0;
}

.begin {
  margin-top: 40px;
}

.end {
  margin-top: 20px;
}

.button {
  margin: 80px auto;
}

.el-steps {
  max-width: 40%;
  margin: 20px auto;
}
</style>
