<template>
  <div id="exam">
    <div class="title">我的练习</div>
    <div class="wrapper">
      <ul class="top">
        <li class="order">
          <el-badge :value="12" class="item" type="primary">
            <span>全部</span>
          </el-badge>
        </li>

        <li class="order">
          <el-badge :value="1" class="item" type="primary">
            <span>未开始</span>
          </el-badge>
        </li>

        <li class="order">
          <el-badge :value="2" class="item" type="primary">
            <span>已开始</span>
          </el-badge>
        </li>

        <li class="order">
          <el-badge :value="1" class="item">
            <span>已过期</span>
          </el-badge>
        </li>

        <li class="search-li">
          <div class="icon">
            <input type="text" placeholder="试卷名称" class="search" v-model="data.key">
            <i class="el-icon-search"/>
          </div>
        </li>
      </ul>

      <ul class="paper" v-loading="data.loading">
        <template v-for="(item, index) in data.exams" :key="index">
          <li class="item">
            <h4 @click="toExamMessage(item.examCode)">{{ item.source }}</h4>
            <p class="name">{{ item.source }}-{{ item.description }}</p>
            <div class="info">
              <i class="el-icon-loading"></i><span>{{ item.examDate.substr(0, 10) }}</span>
              <i class="iconfont icon-icon-time"></i><span
                v-if="item.totalTime != null">限时{{ item.totalTime }}分钟</span>
              <i class="iconfont icon-fenshu"></i><span>满分{{ item.totalScore }}分</span>
            </div>
          </li>
        </template>
      </ul>

      <div class="pagination">
        <el-pagination @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="data.pagination.current"
                       :page-sizes="[6,10,20,40]"
                       :page-size="data.pagination.size"
                       layout="total,sizes,prev,pager,next,jumper"
                       :page-count="data.pagination.total"/>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {findAllExams, findAllExamsByPage} from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()

const data = reactive({
  loading: false,
  key: "",
  exams: [] as ExamManage[],
  pagination: {
    current: 1,
    total: -1,
    size: 6
  }
})

async function getExamInfo() {
  let response = await findAllExamsByPage(data.pagination.current - 1, data.pagination.size)
  data.pagination.total = response["totalPages"]
  data.exams = response["content"]
}

async function handleSizeChange(value: number) {
  data.pagination.size = value
  await getExamInfo()
}

async function handleCurrentChange(value: number) {
  data.pagination.current = value
  await getExamInfo()
}

async function search() {
  let exams = await findAllExams()
  data.exams = exams.filter((item: ExamManage) => {
    return item.source.includes(data.key)
  })
}

function toExamMessage(examCode: number) {
  router.replace({path: "/examMessage", query: {examCode}})
}

onMounted(async () => {
  await getExamInfo()
  data.loading = false
})
</script>

<style lang="scss" scoped>
#exam {
  width: 980px;
  margin: 0 auto;

  .title {
    margin: 20px;
  }

  .paper {
    list-style: none;
    * {
      margin: 20px 0;
    }

    h4 {
      cursor: pointer;
    }

    .item a {
      color: #000;
    }
  }

  .wrapper {
    background: #fff;

    .top {
      display: flex;
      border-bottom: 1px solid #eee;
      margin-bottom: 20px;

      li {
        display: flex;
        align-items: center;
        margin: 20px;
      }

      .search-li {
        margin-left: auto;
      }

      .order {
        cursor: pointer;

        &:hover {
          color: #0195ff;
          border-bottom: 2px solid #0195ff;
        }

        &:visited {
          color: #0195ff;
          border-bottom: 2px solid #0195ff;
        }
      }

      .el-icon-search {
        position: absolute;
        right: 10px;
        top: 10px;
      }

      .icon {
        position: relative;
      }

      .search {
        margin-left: auto;
        padding: 10px;
        border-radius: 4px;
        border: 1px solid #eee;
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;

        &:hover {
          color: #0195ff;
          border-color: #0195ff;
        }
      }
    }

    .paper {
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap;

      .item {
        border-radius: 4px;
        padding: 20px 30px;
        border: 1px solid #eee;
        box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
        transition: all 0.6s ease;

        .info {
          font-size: 14px;
          color: #88949b;
        }

        .name {
          font-size: 14px;
          color: #88949b;
        }

        &:hover {
          box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
        }
      }
    }
  }


  .item {
    width: 380px;
    border-radius: 4px;
    padding: 20px 30px;
    border: 1px solid #eee;
    box-shadow: 0 0 4px 2px rgba(217, 222, 234, 0.3);
    transition: all 0.6s ease;

    &:hover {
      box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
      transform: scale(1.03);
    }

    .info {
      font-size: 14px;
      color: #88949b;

      .name {
        font-size: 14px;
        color: #88949b;
      }

      i {
        margin-right: 5px;
        color: #0195ff;
      }

      span {
        margin-right: 14px;
      }
    }
  }

  .pagination {
    padding: 20px 0 30px 0;

    .el-pagination {
      display: flex;
      justify-content: center;
    }
  }
}
</style>