<template>
  <div id="exam-message">
    <div class="title">
      <span>试卷列表</span>
      <span>/   {{data.examData.source}}</span>
    </div>

    <div class="wrapper">
      <ul class="top">
        <li class="example">{{data.examData.source}}</li>
        <li><i class="iconfont icon-pen-"/></li>
        <li><i class="iconfont icon-share"/></li>
        <li class="right">
          <div class="right">
            <div>
              <span class="count">总分</span>
              <span class="score">{{data.score[0] + data.score[1] + data.score[2]}}</span>
            </div>
          </div>
        </li>
      </ul>

      <ul class="bottom">
        <li>更新于 {{data.examData.examDate}}</li>
        <li>来自 {{data.examData.institute}}</li>
        <li class="btn">{{data.examData.type}}</li>
        <li class="right">
          <el-button @click="toAnswer(data.examData.examCode)">开始答题</el-button>
        </li>
      </ul>

      <ul class="info">
        <li @click="data.dialogVisible = true">
          <a href="javascript:">
            <i class="iconfont icon-info">
              考生须知
            </i>
          </a>
        </li>
      </ul>
    </div>

    <div class="content">
      <el-collapse v-model="data.activeName">
        <el-collapse-item class="header" name="0">
          <template class="title">
            <div class="title">
              <span>{{data.examData.source}}</span>
              <i class="header-icon el-icon-info"/>
              <span class="time">{{data.examData.totalScore}}分 / {{data.examData.totalTime}}分钟</span>
              <el-button type="primary" size="small">点击查看试卷详情</el-button>
            </div>
          </template>

          <el-collapse class="inner">
            <el-collapse-item>
              <template>
                <div class="title">选择题 (共{{data.topicCount[0]}}题 共计{{data.score[0]}}分)</div>
              </template>

              <div class="content">
                <ul class="question" v-for="(list, index) in data.topic[1]" :key="index">
                  <li>{{index+1}}. {{list.question}} {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>

            <el-collapse-item>
              <template>
                <div class="title">填空题 (共{{data.topicCount[1]}}题  共计{{data.score[1]}}分)</div>
              </template>
              <div class="content">
                <ul class="question" v-for="(list, index) in data.topic[2]" :key="index">
                  <li>{{data.topicCount[0]+index+1}}.{{list.question}}  {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>

            <el-collapse-item>
              <template>
                <div class="title">判断题 (共{{data.topicCount[2]}}题 共计{{data.score[2]}}分)</div>
              </template>
              <div class="content">
                <ul class="question" v-for="(list, index) in data.topic[3]" :key="index">
                  <li>{{data.topicCount[0] + data.topicCount[1] + index+1}}. {{list.question}} {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-collapse-item>
      </el-collapse>
    </div>

    <el-dialog title="考生须知"
               v-model="data.dialogVisible"
               width="30%">
      <span>{{data.examData.tips}}</span>
      <span class="dialog-footer">
        <el-button @click="data.dialogVisible = false">知道了</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {useRoute, useRouter} from "vue-router";
import {findExam, findPaper} from "@/api";

const route = useRoute()
const router = useRouter()
const data = reactive({
  dialogVisible: false,
  activeName: "0",
  topicCount: [] as number[],
  score: [] as number[],

  // 考试信息
  examData: {

  } as ExamManage,

  // 试卷信息
  topic: {

  } as PaperManage
})

function toAnswer(id: number) {
  router.replace({path: "/answer", query: {examCode: id}})
}

onMounted(async () => {
  let examCode = parseInt(route.query["examCode"] as string)
  data.examData = await findExam(examCode)

  let paperId = data.examData.paperId
  data.topic = await findPaper(paperId)
  let keys = Object.keys(data.topic)
  keys.forEach(e => {
    let _data = (data.topic as any)[e]
    data.topicCount.push(_data.length)
    let currentScore = _data.map((x: {score: number}) => x.score).reduce((a: number, b: number) => a + b, 0)
    data.score.push(currentScore)
  })
})
</script>

<style lang="scss" scoped>
.bottom {
  .right {
    .el-button {
      color: #409EFF;
      border-color: #c6e2ff;
      background: #ecf5ff;
    }
  }
}

.right {
  margin-left: auto;
}

.inner .content .question {
  margin-left: 40px;
  color: #9a9a9a;
  font-size: 14px;
}
.content .inner .title {
  margin-left: 20px;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
}
.content .title .time {
  font-size: 16px;
  margin-left: 420px;
  color: #999;
}
.content .title {
  background-color: #0195ff;
}
.content .title span {
  margin-right: 10px;
}
#exam-message .content .title {
  font-size: 20px;
  margin: 0;
  display: flex;
  align-items: center;
}
.content {
  margin-top: 20px;
  background-color: #fff;
}
.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0 0 20px;
  border-top: 1px solid #eee;
  padding: 20px 0 10px 0;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
}
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#exam-message {
  background-color: #eee;
  width: 980px;
  margin: 0 auto;
}
#exam-message .title {
  margin: 20px;
}
#exam-message .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #88949b;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #88949b;
}
.wrapper .right .score {
  position: absolute;
  left: 53px;
  top: -5px;
  padding: 1px 12px;
  font-size: 20px;
  color: #88949b;
  border: 1px dashed #88949b;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
</style>