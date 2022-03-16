<template>
  <div>
    <vue-json-editor
      v-model="resultInfo"
      :show-btns="false"
      :mode="'code'"
      lang="zh"
      @json-change="onJsonChange"
      @json-save="onJsonSave"
    />
  </div>
</template>
<script>
import vueJsonEditor from 'vue-json-editor'

export default {

  components: {
    vueJsonEditor
  },
  data() {
    return {
      resultInfo: {
        'userId': '1111111129ac7325-30da-4e6a-8a00-9699820fc04a',
        'realName': '小雪18',
        'gradeCode': '166',
        'provinceCode': '110000',
        'cityCode': {
          'test1': 'test1',
          'test2': 'test2'
        },
        'schoolId': 21,
        'schoolLevel': 1,
        'schoolName': '北京第二实验小学朝阳学校'
      }
    }
  },

  methods: {
    onJsonChange(value) {
      console.log('value:', value);
    },
    onJsonSave(value) {
      console.log('value:', value);
    }

  }
}

</script>

<style>

   /* jsoneditor右上角默认有一个链接,加css去掉了 */
  .jsoneditor-poweredBy{
     display: none;
  }
</style>
