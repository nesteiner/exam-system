import {instance} from "@/api/index";

async function insertMultiQuestion(data: MultiQuestion) {
    let response = await instance.post("/multiQuestions", data)
    return response.data["data"]
}

async function findMultiQuestionId() {
    let response = await instance.get("/multiQuestions/questionId")
    return response.data["data"]
}

async function insertFillQuestion(data: FillQuestion) {
    let response = await instance.post("/fillQuestions", data)
    return response.data["data"]
}

async function findFillQuestionId() {
    let response = await instance.get("/fillQuestions/questionId")
    return response.data["data"]
}

async function insertJudgeQuestion(data: JudgeQuestion) {
    let response = await instance.post("/judgeQuestions", data)
    return response.data["data"]
}

async function findJudgeQuestionId() {
    let response = await instance.get("/judgeQuestions/questionId")
    return response.data["data"]
}

async function insertQuestionItems(data: QuestionItem) {
    let response = await instance.post("/questionItems", data)
    return response.data["data"]
}

async function findAllAnswersByPage(page: number, size: number) {
    let response = await instance.get(`/answers/${page}/${size}`)
    return response.data["data"]
}

export {
    insertMultiQuestion,
    findMultiQuestionId,
    insertFillQuestion,
    findFillQuestionId,
    insertJudgeQuestion,
    findJudgeQuestionId,
    insertQuestionItems,
    findAllAnswersByPage
}