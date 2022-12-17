import {Md5} from "ts-md5"
import {instance, LOCAL_TOKEN_KEY} from "@/api/index";

async function login(request: LoginRequest, path: string) {
    let passwordHash = Md5.hashStr(request.passwordHash)

    let response = await instance.post(`/authenticate/${path}`, {
        username: request.username,
        passwordHash
    }).catch(error => {
        throw error.response.data["message"]
    })


    let token = response.data["jwttoken"]
    localStorage.setItem(LOCAL_TOKEN_KEY, `Bearer ${token}`)
    localStorage.setItem("username", request.username)
    localStorage.setItem("userid", response.data["id"])
    localStorage.setItem("userroles", response.data["roles"])
    return response.data["jwttoken"]
}


function logout() {
    localStorage.removeItem(LOCAL_TOKEN_KEY)
    localStorage.removeItem("username")
    localStorage.removeItem("userid")
}


export {
    login,
    logout
}