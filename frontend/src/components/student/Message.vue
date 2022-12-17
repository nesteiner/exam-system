<template>
  <div id="message">
    <div class="title">给我留言</div>
    <div class="wrapper">
      <div class="title-inner">
        <el-input placeholder="留言标题" v-model="data.title" clearable/>
      </div>

      <div class="content">
        <el-input type="textarea" :rows="3" placeholder="留言内容" v-model="data.content" clearable/>
      </div>

      <div class="button">
        <el-button type="primary" @click="submit">提交留言</el-button>
      </div>

      <div class="all">
        <ul class="message-list">
          <template v-for="(data, index) in data.messages" :key="index">
            <li class="list"
                @mouseenter="enter(index)"
                @mouseleave="leave(index)">
              <p class="title">
                <i class="iconfont icon-untitled33">
                  {{data.title}}
                </i>
              </p>

              <p class="content">{{data.content}}</p>
              <p class="date"><i class="iconfont icon-date">{{data.time}}</i></p>

              <template v-for="(replayData, index2) in data.replays" :key="index2">
                <div>
                  <p class="comment"><i class="iconfont icon-replay">{{replayData.replay}}</i></p>
                </div>
              </template>

              <span class="replay" @click="replay(data.id)" v-if="data.flag && index === data.current">Comment</span>
            </li>
          </template>
        </ul>
      </div>

      <div class="pagination">
        <el-pagination @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="data.pagination.current"
                       :page-sizes="[4,6,8,10]"
                       :page-size="data.pagination.size"
                       layout="total,sizes,prev,pager,next,jumper"
                       :page-count="data.pagination.total"/>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {findMessages, insertMessage, insertReplay} from "@/api";

const data = reactive({
  flag: false,
  current: 0,
  title: "",
  content: "",
  pagination: {
    current: 1,
    total: -1,
    size: 4
  },

  messages: [] as Message[]
})

async function getMessages() {
  let response = await findMessages(data.pagination.current - 1, data.pagination.size)
  data.messages = response["content"]
  data.pagination.total = response["totalPages"]
}

async function handleSizeChange(value: number) {
  data.pagination.size = value
  await getMessages()
}

async function handleCurrentChange(value: number) {
  data.pagination.current = value
  await getMessages()
}

async function submit() {
  let date = new Date()
  if (data.title.length == 0 || data.content.length == 0) {
    alert("留言标题或内容不能为空")
  } else {
    await insertMessage({
      title: data.title,
      content: data.content,
      time: date.toString()
    })

    await getMessages()
  }

  data.title = ""
  data.content = ""
  await getMessages()
}

async function replay(messageId: number) {
  let date = new Date()
  let value = prompt("回复留言", "没有值") || "untitled"
  await insertReplay({
    replay: value,
    replayTime: date.toString(),
    messageId
  })

  await getMessages()
}

function enter(index: number) {
  data.flag = true
  data.current = index
}

function leave(index: number) {
  data.flag = false
  data.current = index
}

onMounted(async () => {
  await getMessages()
})
</script>

<style lang="scss" scoped>
#message {
  .title {
    margin: 20px;
  }

  .content {
    padding: 20px 0;
  }

  .button {
    padding-bottom: 20px;
  }

  .wrapper {
    background: #fff;
    padding: 20px;
  }


  .all {
    .date {
      color: rgb(80, 157, 202);
      line-height: 45px;
      font-size: 13px;
    }

    .list {
      background: #eee;
      padding: 10px;
      border-radius: 4px;
      margin: 10px 0;
      position: relative;
      transition: all .3s ease;

      .title {
        color: #5f5f5f;
        margin: 0;
        font-size: 13px;
        line-height: 30px;
      }

      .content {
        padding: 0;
      }

      .icon-untitled33 {
        font-size: 13px;
        margin-right: 4px;
      }

      .icon-date {
        font-size: 13px;
        margin-right: 4px;
        color: rgb(80, 157, 202);
      }

      .replay {
        position: absolute;
        right: 30px;
        bottom: 15px;
        color: tomato;
        cursor: pointer;
        transition: all .3s ease;
      }

      .comment {
        margin: -7px 0px;
        padding-bottom: 12px;
        font-size: 13px;
        color: #28b2b4;

        i {
          margin-right: 4px;
        }
      }
    }
  }

  .pagination {
    display: flex;
    justify-content: center;
  }
}
</style>