<template>
  <div class="add">
    <el-tabs v-model="data.activeName">
      <el-tab-pane name="first">
        <span slot="label"><i class="el-icon-circle-plus"></i>添加试题</span>
        <section class="append">
          <ul>
            <li>
              <span>题目类型:</span>
              <el-select v-model="data.optionValue" placeholder="请选择题型" class="w150">
                <el-option
                    v-for="item in data.options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="data.optionValue === '选择题'">
              <span>所属章节：</span>
              <el-input
                  placeholder="请输入对应章节"
                  v-model="data.postChange.section"
                  class="w150"
                  clearable>
              </el-input>
            </li>
            <li v-if="data.optionValue === '填空题'">
              <span>所属章节：</span>
              <el-input
                  placeholder="请输入对应章节"
                  v-model="data.postFill.section"
                  class="w150"
                  clearable>
              </el-input>
            </li>
            <li v-if="data.optionValue === '判断题'">
              <span>所属章节：</span>
              <el-input
                  placeholder="请输入对应章节"
                  v-model="data.postJudge.section"
                  class="w150"
                  clearable>
              </el-input>
            </li>
            <li v-if="data.optionValue === '选择题'">
              <span>难度等级:</span>
              <el-select v-model="data.postChange.level" placeholder="选择难度等级" class="w150">
                <el-option
                    v-for="item in data.levels"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="data.optionValue === '填空题'">
              <span>难度等级:</span>
              <el-select v-model="data.postFill.level" placeholder="选择难度等级" class="w150">
                <el-option
                    v-for="item in data.levels"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="data.optionValue === '判断题'">
              <span>难度等级:</span>
              <el-select v-model="data.postJudge.level" placeholder="选择难度等级" class="w150">
                <el-option
                    v-for="item in data.levels"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li v-if="data.optionValue === '选择题'">
              <span>正确选项:</span>
              <el-select v-model="data.postChange.rightAnswer" placeholder="选择正确答案" class="w150">
                <el-option
                    v-for="item in data.rights"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
          </ul>
          <!-- 选择题部分 -->
          <div class="change" v-if="data.optionValue === '选择题'">
            <div class="title">
              <el-tag>题目:</el-tag><span>在下面的输入框中输入题目,形如--DNS 服务器和DHCP服务器的作用是（）</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postChange.question"
                  placeholder="请输入题目内容"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="options">
              <ul>
                <li>
                  <el-tag type="success">A</el-tag>
                  <el-input
                      placeholder="请输入选项A的内容"
                      v-model="data.postChange.answerA"
                      clearable>
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">B</el-tag>
                  <el-input
                      placeholder="请输入选项B的内容"
                      v-model="data.postChange.answerB"
                      clearable>
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">C</el-tag>
                  <el-input
                      placeholder="请输入选项C的内容"
                      v-model="data.postChange.answerC"
                      clearable>
                  </el-input>
                </li>
                <li>
                  <el-tag type="success">D</el-tag>
                  <el-input
                      placeholder="请输入选项D的内容"
                      v-model="data.postChange.answerD"
                      clearable>
                  </el-input>
                </li>
              </ul>
            </div>
            <div class="title">
              <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postChange.analysis"
                  placeholder="请输入答案解析"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="changeSubmit">立即添加</el-button>
            </div>
          </div>
          <!-- 填空题部分 -->
          <div class="change fill" v-if="data.optionValue === '填空题'">
            <div class="title">
              <el-tag>题目:</el-tag><span>输入题目,形如--从计算机网络系统组成的角度看，计算机网络可以分为()和()。注意需要考生答题部分一定要用括号（英文半角）括起来。</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postFill.question"
                  placeholder="请输入题目内容"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="fillAnswer">
              <el-tag>正确答案:</el-tag>
              <el-input v-model="data.postFill.answer"></el-input>
            </div>
            <div class="title analysis">
              <el-tag type="success">解析:</el-tag><span>下方输入框中输入答案解析</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postFill.analysis"
                  placeholder="请输入答案解析"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="fillSubmit">立即添加</el-button>
            </div>
          </div>
          <!-- 判断题 -->
          <div class="change judge" v-if="data.optionValue === '判断题'">
            <div class="title">
              <el-tag>题目:</el-tag><span>在下面的输入框中输入题目</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postJudge.question"
                  placeholder="请输入题目内容"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="judgeAnswer">
              <el-radio v-model="data.postJudge.answer" label="T">正确</el-radio>
              <el-radio v-model="data.postJudge.answer" label="F">错误</el-radio>
            </div>
            <div class="title">
              <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
              <el-input
                  type="textarea"
                  rows="4"
                  v-model="data.postJudge.analysis"
                  placeholder="请输入答案解析"
                  resize="none"
                  class="answer">
              </el-input>
            </div>
            <div class="submit">
              <el-button type="primary" @click="judgeSubmit">立即添加</el-button>
            </div>
          </div>
        </section>
      </el-tab-pane>

      <el-tab-pane name="second">
        <span slot="label"><i class="iconfont icon-daoru-tianchong"></i>在线组卷</span>
        <div class="box">
          <ul>
            <li>
              <span>试题难度:</span>
              <el-select v-model="data.difficultyValue" placeholder="试题难度" class="w150">
                <el-option
                    v-for="item in data.difficulties"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </li>
            <li>
              <span>选择题数量：</span> <el-input type="text" v-model="data.changeNumber"></el-input>
            </li>
            <li>
              <span>填空题数量：</span> <el-input type="text" v-model="data.fillNumber"></el-input>
            </li>
            <li>
              <span>判断题数量：</span> <el-input type="text" v-model="data.judgeNumber"></el-input>
            </li>
            <li>
              <el-button type="primary" @click="create">立即组卷</el-button>
            </li>
          </ul>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {useRoute} from "vue-router";
import {
  findFillQuestionId,
  findJudgeQuestionId,
  findMultiQuestionId, insertFillQuestion,
  insertJudgeQuestion,
  insertMultiQuestion,
  insertPaper,
  insertQuestionItems
} from "@/api";

const route = useRoute()
const data = reactive({
  changeNumber: 0,
  fillNumber: 0,
  judgeNumber: 0,
  activeName: "first",
  options: [
    {
      value: "选择题",
      label: "选择题"
    },
    {
      value: "填空题",
      label: "填空题"
    },
    {
      value: "判断题",
      label: "判断题"
    },
  ],

  difficulties: [
    {
      value: "简单",
      label: "简单"
    },
    {
      value: "一般",
      label: "一般"
    },
    {
      value: "困难",
      label: "困难"
    }
  ],

  difficultyValue: "简单",
  levels: [
    {
      value: "1",
      label: "1"
    },
    {
      value: "2",
      label: "2"
    },
    {
      value: "3",
      label: "3"
    },
    {
      value: "4",
      label: "4"
    },
    {
      value: "5",
      label: "5"
    },
  ],

  rights: [
    {
      value: "A",
      label: "A"
    },
    {
      value: "B",
      label: "B"
    },
    {
      value: "C",
      label: "C"
    },
    {
      value: "D",
      label: "D"
    },
  ],

  paperId: 0,
  optionValue: "选择题",
  subject: "",
  postChange: {
    subject: '', //试卷名称
    level: '', //难度等级选中值
    rightAnswer: '', //正确答案选中值
    section: '', //对应章节
    question: '', //题目
    analysis: '', //解析
    answerA: '',
    answerB: '',
    answerC: '',
    answerD: '',
  },

  postFill: { //填空题提交内容
    subject: '', //试卷名称
    level: '', //难度等级选中值
    answer: '', //正确答案
    section: '', //对应章节
    question: '', //题目
    analysis: '', //解析
  },

  postJudge: { //判断题提交内容
    subject: '', //试卷名称
    level: '', //难度等级选中值
    answer: '', //正确答案
    section: '', //对应章节
    question: '', //题目
    analysis: '', //解析
  },

  postPaper: { //考试管理表对应字段
    paperId: 0,
    questionType: 1, // 试卷类型 1--选择题  2--填空题   3--判断题
    questionId: 0,
  }
})

async function create() {
  await insertQuestionItems({
    changeNumber: data.changeNumber,
    fillNumber: data.fillNumber,
    judgeNumber: data.judgeNumber,
    paperId: data.paperId,
    subject: "计算机网络"
  })
}

function scoreOf(difficultyValue: string) {
  if(difficultyValue == "简单") {
    return 1
  } else if (difficultyValue == "中等") {
    return 2
  } else if (difficultyValue == "困难") {
    return 3
  } else {
    throw new Error(`unknown difficultyValue: ${difficultyValue}`)
  }
}

async function changeSubmit() {
  data.postChange.subject = data.subject
  await insertMultiQuestion({...data.postChange, score: scoreOf(data.difficultyValue)})
  data.postPaper.questionId = await findMultiQuestionId()
  data.postPaper.questionType = 1
  await insertPaper(data.postPaper)
}

async function fillSubmit() {
  data.postFill.subject = data.subject
  await insertFillQuestion({...data.postFill, score: scoreOf(data.difficultyValue)})
  data.postPaper.questionId = await findFillQuestionId()
  data.postPaper.questionType = 2
  await insertPaper(data.postPaper)
}

async function judgeSubmit() {
  data.postJudge.subject = data.subject
  await insertJudgeQuestion({...data.postJudge, score: scoreOf(data.difficultyValue)})
  data.postPaper.questionId = await findJudgeQuestionId()
  data.postPaper.questionType = 3
  await insertPaper(data.postPaper)
}

onMounted(async () => {
  data.paperId = parseInt(route.query["subject"] as string)
  data.subject = route.query["subject"] as string
  data.postPaper.paperId = data.paperId
})
</script>

<style lang="scss" scoped>
.add {
  margin: 0 40px;
  .box {
    padding: 0 20px;
    ul li {
      margin: 10px 0;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 7%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
  }
  .append {
    margin: 0 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>