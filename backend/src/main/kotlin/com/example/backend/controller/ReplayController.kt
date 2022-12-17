package com.example.backend.controller

import com.example.backend.model.Replay
import com.example.backend.service.ReplayService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/replays")
class ReplayController {
    @Autowired
    lateinit var replayService: ReplayService

    @PostMapping
    fun insertOne(@RequestBody replay: Replay): Response<Replay> {
        val data = replayService.insertOne(replay)
        return Response.Ok("insert ok", data)
    }

    @GetMapping("/{messageId}")
    fun findAllByMessageId(@PathVariable("messageId") messageId: Long): Response<List<Replay>> {
        val data = replayService.findAllByMessageId(messageId)
        return Response.Ok("all replays", data)
    }
}