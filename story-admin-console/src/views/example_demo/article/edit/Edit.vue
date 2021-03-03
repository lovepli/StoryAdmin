<template>
  <div class="article-edit">
    <div class="article-edit__header">
      <div class="title">
        <section-title :name="articleId ? '编辑文章' : '新增文章'" />
      </div>
      <div class="operation">
        <el-button
          :loading="submitLoading"
          type="primary"
          icon="el-icon-s-promotion"
          @click="handleSubmit"
        >发布</el-button
        >
        <el-button
          type="info"
          icon="el-icon-circle-close"
          @click="handleClose"
        >取消</el-button
        >
      </div>
    </div>

    <el-row>
      <el-col :lg="16">
        <!-- 一个组件上的v-model默认会利用名为value的prop和名为input的事件。 -->
        <tinymce v-model="articleDetail.content" :height="660" />
      </el-col>

      <el-col :lg="8">
        <el-form
          ref="form"
          :model="articleDetail"
          :rules="formRules"
          class="article-edit__form"
          label-width="90px"
        >
          <el-form-item label="文章标题:" prop="name">
            <el-input
              v-model="articleDetail.name"
              placeholder="请输入文章标题"
              clearable
            />
          </el-form-item>

          <el-form-item label="文章类型:" prop="type">
            <el-select
              v-model="articleDetail.type"
              placeholder="请选择文章类型"
              clearable
            >
              <el-option
                v-for="item in articleType"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="文章简介:">
            <el-input
              v-model="articleDetail.brief"
              :rows="3"
              :maxlength="100"
              type="textarea"
              placeholder="请输入文章简介"
              clearable
            />
          </el-form-item>

          <el-form-item label="创建时间:" prop="createDate">
            <el-input v-model="articleDetail.createDate" disabled/>
          </el-form-item>

          <el-form-item label="标题图片:" prop="imageURL">
            <avatar-upload
              v-model="articleDetail.imageURL"
              :round="false"
              action="https://sm.ms/api/v2/upload"
              name="smfile"
              width="80px"
            />
          </el-form-item>

          <el-form-item label="附件上传:">
            <drag-upload
              v-model="articleDetail.accessory"
              action="https://jsonplaceholder.typicode.com/posts"
            />
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Mock from 'mockjs';
import util from '@/utils/example_demo/wangluyao/util';
// import api from '@/api'
import bus from '@/utils/example_demo/wangluyao/bus'; // bus飞父子组件之间通信
// import tableMng from '@/utils/tableMng'
import AvatarUpload from '@/components/example_demo/wangluyao/business/upload/avatar-upload';
import DragUpload from '@/components/example_demo/wangluyao/business/upload/drag-upload';
import SectionTitle from '@/components/example_demo/wangluyao/business/section-title';
import Tinymce from '@/components/example_demo/wangluyao/business/tinymce'; // 富文本
// import dayjs from 'dayjs'

const defaultDetail = {
  id: '',
  name: '',
  type: '',
  content: '',
  // createDate: dayjs().format('YYYY-MM-DD HH:mm:ss'),
  imageURL: '',
  brief: '',
  accessory: []
};

const articleType = [
  {
    id: '1',
    name: '新闻'
  },
  {
    id: '2',
    name: '财经'
  },
  {
    id: '3',
    name: '体育'
  },
  {
    id: '4',
    name: '娱乐'
  },
  {
    id: '5',
    name: '游戏'
  }
];

// 这个列表数据是list页面传递过来的
const articleList = Mock.mock({
  'list|213': [
    {
      id: '@lower(@guid)',
      name: '@ctitle',
      author: '@cname',
      createDate: '@datetime("yyyy-MM-dd HH:mm:ss")',
      type: '@pick(["1", "2", "3", "4", "5"])',
      browseNum: '@natural(1000,9999)',
      imageURL: 'https://source.unsplash.com/random/200x200',
      brief: '@cparagraph(1,3)',
      content: '@cparagraph',
      accessory: [
        {
          id: '1',
          name: '图片图片.jpg',
          url: 'https://s2.ax1x.com/2019/08/02/edRc1P.jpg'
        },
        {
          id: '2',
          name: '营业执照副本.pdf',
          url:
            'http://www.xdocin.com/xdoc?_key=fedii4dtyfhmvgryqyntfjavte&_func=down&_dir=document.pdf'
        },
        {
          id: '3',
          name: '数据采集表',
          url:
            'http://www.xdocin.com/xdoc?_key=fedii4dtyfhmvgryqyntfjavte&_func=down&_dir=data.xlsx'
        }
      ]
    }
  ]
});

const table = articleList.list;

export default {
  name: 'ArticleEdit',
  components: {
    AvatarUpload,
    DragUpload,
    SectionTitle,
    Tinymce
  },
  // eslint-disable-next-line vue/require-prop-types
  props: ['articleId'],
  data() {
    return {
      articleType: [], // 文章类型
      articleDetail: { ...defaultDetail },
      formRules: {
        name: [
          {
            required: true,
            message: '请填写文章标题',
            trigger: 'blur'
          },
          {
            max: 20,
            message: '标题不能超过20个字',
            trigger: 'blur'
          }
        ],
        type: [
          {
            required: true,
            message: '请选择文章类型',
            trigger: 'change'
          }
        ],
        imageURL: [
          {
            required: true,
            message: '请上传标题图片'
          }
        ]
      },
      submitLoading: false
    };
  },
  created() {
    this.getDetail();
    this.articleType = articleType; // 赋值文章类型下拉列表
  },
  methods: {
        // 获取系统当前时间
   async getNowTime(){
    //当前设定的日期时间
      let d = new Date
      let year,month,day,hour,minute,second;
      [year,month,day,hour,minute,second] = [d.getFullYear(),d.getMonth(),d.getDate(),d.getHours(),d.getMinutes(),d.getSeconds()]
      let dateTime = new Date(year, month, day,hour,minute,second)
       console.log("获取系统当前时间："+dateTime)
    },
    /** 获取详细信息 */
    async getDetail() {
      if (this.articleId) {
        const { id } = this.articleId;

        const data = util.find(table, id) || table[0];
        this.articleDetail = {
          id: data.id,
          name: data.name,
          // createDate: this.$dayjs(data.createDate).format(
          //   'YYYY-MM-DD HH:mm:ss'
          // ),
          imageURL: data.imageURL,
          type: data.type,
          brief: data.brief,
          content: data.content,
          accessory: data.accessory
        };
      } else {
        this.articleDetail = { ...defaultDetail };
      }
    },
    // async getDetail() {
    //   if (this.articleId) {
    //     const data = await api.article.getDetail({ id: this.articleId })
    //     this.articleDetail = {
    //       id: data.id,
    //       name: data.name,
    //       // createDate: this.$dayjs(data.createDate).format(
    //       //   'YYYY-MM-DD HH:mm:ss'
    //       // ),
    //       imageURL: data.imageURL,
    //       type: data.type,
    //       brief: data.brief,
    //       content: data.content,
    //       accessory: data.accessory
    //     }
    //   } else {
    //     this.articleDetail = { ...defaultDetail }
    //   }
    // },
    handleSubmit() {
      this.$refs.form.validate(async valid => {
        if (valid) {
          this.submitLoading = true;
          //  await api.article.update({ detail: this.articleDetail })

          this.submitLoading = false;
          this.$message.success('发布成功');
          this.handleClose();
        } else {
          this.$message.error('请按照正确格式填写');
        }
      });
    },
    handleClose() {
      bus.$emit('closeTag', this.$route.path); // 字符串，对应当前路由的路径，总是解析为绝对路径，如 "/foo/bar"。
      this.$router.push('/article/list');
    }
  }
};
</script>

<style lang="scss" scoped>
.article-edit {
  background-color: #fff;
  padding: 1em;

  .article-edit__header {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;

    .title,
    .operation {
      margin-bottom: 1em;
    }
  }

  .article-edit__form {
    box-sizing: border-box;
    height: 660px;
    overflow-y: auto;
    padding: 1em;
    border: 1px solid #c5c5c5;
  }
}
</style>
