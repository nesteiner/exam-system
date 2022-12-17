package com.example.backend.controller

import com.example.backend.model.FillQuestion
import com.example.backend.service.FillQuestionService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fillQuestions")
class FillQuestionController {
    @Autowired
    lateinit var fillQuestionService: FillQuestionService

    @PostMapping
    fun insertOne(@RequestBody fillQuestion: FillQuestion): Response<FillQuestion> {
        val data = fillQuestionService.insertOne(fillQuestion)
        return Response.Ok("insert ok", data)
    }

    @GetMapping("/questionId")
    fun findOnlyQuestionId(): Response<Long> {
        val data = fillQuestionService.findOnlyQuestionId()?.getId()
        if (data == null) {
            return Response.Err("no such question id")
        } else {
            return Response.Ok("this question id", data)
        }
    }
}