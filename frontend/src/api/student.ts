import {instance} from "@/api/index";

async function insertStudent(data: RegisterStudentRequest) {
    let response = await instance.post("/students", data)
    return response.data["data"]
}

async function findAllStudents(page: number, size: number) {
    let response = await instance.get(`/students/${page}/${size}`)
    return response.data["data"]
}

async function findStudent(id: number) {
    let response = await instance.get(`/students/${id}`)
    return response.data["data"]
}

async function deleteStudent(id: number) {
    let response = await instance.delete(`/students/${id}`)
    return response.data["data"]
}

async function updateStudent(data: Student) {
    let response = await instance.put("/students", data)
    return response.data["data"]
}

export {
    insertStudent,
    findAllStudents,
    findStudent,
    deleteStudent,
    updateStudent
}