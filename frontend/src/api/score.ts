import {instance} from "@/api/index";

async function findAllScoresByPage(page: number, size: number, studentId: number) {
    let response = await instance.get(`/scores/${page}/${size}/${studentId}`)
    return response.data["data"]
}

async function findAllScoreByStudentId(studentId: number) {
    let response = await instance.get(`/scores?studentId=${studentId}`)
    return response.data["data"]
}

export {
    findAllScoresByPage,
    findAllScoreByStudentId
}