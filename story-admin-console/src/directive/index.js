import Vue from 'vue';

import copy from './copy';
import dragDialog from './dragDialog';

// // 注册 (指令函数)
Vue.directive('copy', copy);
Vue.directive('dragDialog', dragDialog);

