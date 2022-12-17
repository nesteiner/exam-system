package com.example.backend.controller

import com.example.backend.model.Message
import com.example.backend.service.MessageService
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class MessageController {
    @Autowired
    lateinit var messageService: MessageService

    @GetMapping("/{page}/{size}")
    fun findAll(@PathVariable("page") page: Int, @PathVariable("size") size: Int): Response<Page<Message>> {
        val pageable = PageRequest.of(page, size)
        val data = messageService.findAll(pageable)
        return Response.Ok("all messages", data)
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable("id") id: Long): Response<Message> {
        val data = messageService.findOne(id)
        if (data == null) {
            return Response.Err("no such message")
        } else {
            return Response.Ok("this message", data)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable("id") id: Long): Response<Status> {
        messageService.deleteOne(id)
        return Response.Ok("delete ok", Status.Ok)
    }

    @PostMapping
    fun insertOne(@RequestBody message: Message): Response<Message> {
        val data = messageService.insertOne(message)
        return Response.Ok("insert ok", data)
    }
}