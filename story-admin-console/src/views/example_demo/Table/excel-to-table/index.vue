<template>
  <div>
    <input ref="upload" type="file" accept=".xls,.xlsx" class="outputlist_upload">
    <input v-model=" optputString">
  </div>
</template>

<script>
import XLSX from 'xlsx'
// Vue 读取excel数据并生成数组
export default {
  data() {
    return {
      outputs: [], // 设置数据,
      optputString: '' // 展示的表格数组数据
    }
  },
  // 给input标签绑定监听事件：
  mounted() {
    this.$refs.upload.addEventListener('change', e => { // 绑定监听表格导入事件
      this.readExcel(e);
    })
  },
  methods: {
    // 读取excel文件信息并输出内容：
    readExcel(e) { // 表格导入
      var that = this;
      const files = e.target.files;
      console.log(files);
      if (files.length <= 0) { // 如果没有文件名
        return false;
      } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
        this.$Message.error('上传格式不正确，请上传xls或者xlsx格式');
        return false;
      }

      const fileReader = new FileReader();
      fileReader.onload = (ev) => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: 'binary'
          });
          const wsname = workbook.SheetNames[0];// 取第一张表
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]);// 生成json表格内容
          console.log(ws);
          that.optputString = JSON.stringify(ws) // 赋值
          that.outputs = [];// 清空接收数据
          for (var i = 0;i < ws.length;i++) {
            var sheetData = {
              address: ws[i].addr,
              value: ws[i].value
            }
            that.outputs.push(sheetData);
          }
          this.$refs.upload.value = '';
        } catch (e) {
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);
    }
  }
}
</script>
<style scoped>
</style>
