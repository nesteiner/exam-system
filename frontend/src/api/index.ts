import Axios from "axios"
import router from "@/router"
const BASE_URL = "http://localhost/api"
const instance = Axios.create({
    baseURL: BASE_URL
})

const LOCAL_TOKEN_KEY = "TOKEN"

instance.interceptors.request.use(
    config => {
        if(config.url?.startsWith("/authenticate")) {
            return config
        }

        if (config.headers !== undefined) {
            config.headers["Authorization"] = localStorage.getItem(LOCAL_TOKEN_KEY) || "no token"
        }
        return config
    },

    error => {
        return Promise.reject(error.response)
    }
)

instance.interceptors.response.use(
    response => response,
    error => {
        let status = error.response.status
        if(status == 401) {
            router.replace({name: "login"})
        }

        return Promise.reject(error)
    }
)
export {instance, LOCAL_TOKEN_KEY}
export * from "./user"
export * from "./admin"
export * from "./teacher"
export * from "./exam"
export * from "./paper"
export * from "./message"
export * from "./replay"
export * from "./score"
export * from "./question"
export * from "./student"