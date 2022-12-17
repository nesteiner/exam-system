import {instance} from "@/api/index";

async function insertReplay(replay: PostReplayRequest) {
    let response = await instance.post("/replays", replay)
    return response.data["data"]
}

export {
    insertReplay
}