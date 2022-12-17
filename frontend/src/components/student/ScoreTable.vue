<template>
  <div class="table">
    <p class="title">我的分数</p>
    <section class="content-el">
      <el-table ref="filterTable" :data="data.scores" v-loading="data.loading">
        <el-table-column prop="answerDate"
                         label="考试日期"
                         sortable
                         width="300"
                         column-key="answerDate"
                         :filters="data.filter"
                         :filter-method="filterHandler"/>

        <el-table-column prop="subject"
                         label="课程名称"
                         width="300"
                         filter-placement="bottom-end">
          <template #default="scope">
            <el-tag>{{scope.row.subject}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column prop="usualScore"
                         label="考试分数"
                         width="200"/>

        <el-table-column label="是否及格"
                         width="100">
          <template #default="scope">
            <el-tag :type="scope.row.usualScore >= 60 ? 'success' : danger">
              {{scope.row.usualScore >= 60 ? "及格" : "不及格"}}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>

      <el-row type="flex" justify="center" align="middle" class="pagination">
        <el-pagination @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="data.pagination.current"
                       :page-sizes="[4,6,8,10]"
                       :page-size="data.pagination.size"
                       layout="total,sizes,prev,pager,next,jumper"
                       :page-count="data.pagination.total"/>
      </el-row>
    </section>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {findAllScoresByPage} from "@/api";

const data = reactive({
  pagination: {
    current: 1,
    total: -1,
    size: 10
  },

  loading: false,
  scores: [] as Score[],
  filter: [] as any[]
})


async function getScores() {
  let studentId = parseInt(localStorage.getItem("userid") || "-1")
  let response = await findAllScoresByPage(data.pagination.current - 1, data.pagination.size, studentId)
  data.pagination.total = response["totalPages"]
  data.scores = response["content"]

  let mapvalue = data.scores.map((element, _) => {
    return {
      text: element.answerDate,
      value: element.answerDate
    }
  })

  let hash = {} as {
    [key: string]: string | boolean
  }

  const newarray = mapvalue.reduce((item, next) => {
    hash[next.text] ? '' : hash[next.text] = item.push(next)
  }, [] as any)

  data.filter = newarray

  data.loading = true
}

async function handleSizeChange(value: number) {
  data.pagination.size = value
  await getScores()
}

async function handleCurrentChange(value: number) {
  data.pagination.current = value
  await getScores()
}

function formatter(row: {address: any}, column: any) {
  return row.address
}

function filterTag(value: string, row: {tag: string}) {
  return row.tag === value
}

function filterHandler(value: any, row: any, column: any) {
  const property = column["property"]
  return row[property] === value
}

onMounted(async () => {
  await getScores()
  data.loading = true
})
</script>

<style lang="scss" scoped>
.pagination {
  padding-top: 20px;
}

.table {
  width: 980px;
  margin: 0 auto;

  .title {
    margin: 20px;
  }

  .content-el {
    background: #fff;
    padding: 20px;
  }
}
</style>