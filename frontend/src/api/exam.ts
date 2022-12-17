import {instance} from "@/api/index";

async function findExam(examCode: number) {
    let response = await instance.get(`/exams/${examCode}`)
    return response.data["data"]
}

async function findAllExamsByPage(page: number, size: number) {
    let response = await instance.get(`/exams/${page}/${size}`)
    return response.data["data"]
}

async function findAllExams() {
    let response = await instance.get("/exams")
    return response.data["data"]
}

async function findExamsPaperId() {
    let response = await instance.get("/exams/paperId")
    return response.data["data"]
}

async function insertExam(data: ExamManage) {
    let response = await instance.post("/exams", data)
    return response.data["data"]
}

async function updateExam(data: ExamManage) {
    let response = await instance.put("/exams", data)
    return response.data["data"]
}

async function deleteExam(examCode: number) {
    let response = await instance.delete(`/exams/${examCode}`)
    return response.data["data"]
}

export {
    findExam,
    findAllExamsByPage,
    findAllExams,
    findExamsPaperId,
    insertExam,
    updateExam,
    deleteExam
}