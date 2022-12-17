import {instance} from "@/api/index";
import {Md5} from "ts-md5";

async function insertTeacher(data: RegisterTeacherRequest) {
    let response = await instance.post("/teachers/register", data)
    return response.data["data"]
}

async function findTeachers(page: number, size: number) {
    let response = await instance.get(`/teachers/${page}/${size}`)
    return response.data["data"]
}

async function findTeacher(id: number) {
    let response = await instance.get(`/teachers/${id}`)
    return response.data["data"]
}

async function updateTeacher(data: UpdateTeacherRequest) {
    let passwordHash = Md5.hashStr(data.password)
    let response = await instance.post("/teachers", {
        ...data,
        passwordHash
    })
    return response.data["data"]
}

async function deleteTeacher(id: number) {
    let response = await instance.delete(`/teachers/${id}`)
    return response.data["data"]
}

export {
    insertTeacher,
    findTeachers,
    findTeacher,
    updateTeacher,
    deleteTeacher
}