<template>
  <div class="form-base">
    <el-form ref="form" :model="formData" :rules="formRules" label-width="80px">
      <el-row :gutter="20">
        <el-col :xs="24" :md="12">
          <el-form-item label="名称" prop="name">
            <el-input
              v-model="formData.name"
              placeholder="请输入活动名称"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :md="12">
          <el-form-item label="数量" prop="amount">
            <el-input
              v-model.number="formData.amount"
              placeholder="请输入数量"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :xs="24" :md="12">
          <el-form-item label="区域" prop="region">
            <el-select
              v-model="formData.region"
              placeholder="请选择活动区域"
              clearable
            >
              <el-option
                v-for="item in regionList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :md="12">
          <el-form-item label="开始时间" prop="startTime">
            <el-date-picker
              v-model="formData.startTime"
              type="datetime"
              placeholder="请选择开始时间"
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :xs="24" :md="12">
          <el-form-item label="手机" prop="mobilePhone">
            <el-input
              v-model="formData.mobilePhone"
              placeholder="请输入主办方联系电话"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :md="12">
          <el-form-item label="邮箱" prop="email">
            <el-input
              v-model="formData.email"
              placeholder="请输入主办方联系邮箱"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item
        class="inherit-line-height"
        label="即时配送"
        prop="delivery"
      >
        <el-switch v-model="formData.delivery"/>
      </el-form-item>

      <el-form-item class="inherit-line-height" label="类型" prop="type">
        <el-checkbox-group v-model="formData.type">
          <el-checkbox label="1">美食线上活动</el-checkbox>
          <el-checkbox label="2">地推活动</el-checkbox>
          <el-checkbox label="3">线下主题活动</el-checkbox>
          <el-checkbox label="4">单纯品牌曝光</el-checkbox>
        </el-checkbox-group>
      </el-form-item>

      <el-form-item
        class="inherit-line-height"
        label="特殊资源"
        prop="resource"
      >
        <el-radio-group v-model="formData.resource">
          <el-radio label="1">线上品牌商赞助</el-radio>
          <el-radio label="2">线下场地免费</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="描述" prop="description">
        <el-input
          v-model="formData.description"
          :rows="3"
          :maxlength="100"
          type="textarea"
          show-word-limit
          placeholder="请输入描述"
        />
      </el-form-item>
    </el-form>

    <div style="text-align:center;">
      <el-button
        :loading="submitLoading"
        type="primary"
        @click="handleSubmit"
      >提交</el-button
      >
      <el-button type="info" @click="handleCancel">取消</el-button>
    </div>
  </div>
</template>

<script>
// import tableMng from '@/utils/wangluyao/tableMng';
const region = [
  {
    id: '1',
    name: '华南'
  },
  {
    id: '2',
    name: '华北'
  },
  {
    id: '3',
    name: '华东'
  },
  {
    id: '4',
    name: '西南'
  },
  {
    id: '5',
    name: '东北'
  },
  {
    id: '6',
    name: '西北'
  }
];
export default {
  name: 'BaseForm',
  data() {
    return {
      formData: {
        name: '',
        amount: '',
        region: '',
        startTime: '',
        mobilePhone: '',
        email: '',
        delivery: false,
        type: [],
        resource: '',
        description: ''
      },
      formRules: {
        name: [
          {
            required: true,
            message: '请填写名称',
            trigger: 'blur'
          },
          {
            max: 20,
            message: '名称不能超过20个字',
            trigger: 'blur'
          }
        ],
        amount: [
          {
            type: 'number',
            message: '数量必须为数值'
          }
        ],
        region: [
          {
            required: true,
            message: '请选择区域',
            trigger: 'change'
          }
        ],
        startTime: [
          {
            required: true,
            message: '请选择时间',
            trigger: 'change'
          }
        ],
        mobilePhone: [
          {
            required: true,
            message: '请填写手机号',
            trigger: 'blur'
          },
          {
            pattern: /^1[345789]\d{9}$/,
            message: '手机号码格式不正确',
            trigger: 'blur'
          }
        ],
        email: [
          {
            required: true,
            message: '请填写邮箱',
            trigger: 'blur'
          },
          {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }
        ],
        type: [
          {
            required: true,
            message: '请选择类型',
            trigger: 'change'
          }
        ]
      },
      regionList: [], // 区域
      submitLoading: false
    };
  },
  created() {
    // this.regionList = tableMng.getTable('region');
    this.regionList = region;
    console.log('查询出来的数据为：' + JSON.stringify(this.regionList));
  },
  methods: {
    handleSubmit() {
      this.submitLoading = true;
      this.$refs.form.validate(valid => {
        if (valid) {
          console.log(this.formData);
          this.$message.success('提交成功！');
        } else {
          this.$message.error('请按正确格式填写信息！');
        }
      });
      this.submitLoading = false;
    },
    handleCancel() {
      this.$refs.form.resetFields();
    }
  }
};
</script>

<style lang="scss" scoped>
.form-base {
  width: 70%;
  min-width: 500px;
  padding: 1em;
  margin: 0 auto;
  border-radius: 10px;
  background-color: #fff;
}
</style>

<style lang="scss">
.form-base {
  .inherit-line-height {
    .el-form-item__label,
    .el-form-item__content {
      line-height: inherit;
    }
  }
}
</style>
