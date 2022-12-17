import {instance} from "@/api/index";

async function findAllExamsByExamCode(examCode: number) {
    let response = await instance.get(`/exams/examCode=${examCode}`)
    return response.data["data"]
}

export {
    findAllExamsByExamCode
}