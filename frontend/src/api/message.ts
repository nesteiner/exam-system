import {instance} from "@/api/index";

async function findMessages(page: number, size: number) {
    let response = await instance.get(`/messages/${page}/${size}`)
    return response.data["data"]
}

async function insertMessage(message: PostMessageRequest) {
    let data = {
        ...message,
        replays: [] as Replay[]
    }
    let response = await instance.post("/message", data)
    return response.data["data"]
}

export {
    findMessages,
    insertMessage
}