import {instance} from "@/api/index";

async function findPaper(id: number) {
    let response = await instance.get(`/papers/${id}`)
    return response.data["data"]
}

async function insertPaper(data: PaperManage) {
    let response = await instance.post("/papers", data)
    return response.data["data"]
}

export {
    findPaper,
    insertPaper
}