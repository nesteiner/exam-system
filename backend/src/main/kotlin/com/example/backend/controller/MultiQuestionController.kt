package com.example.backend.controller

import com.example.backend.model.MultiQuestion
import com.example.backend.service.MultiQuestionService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/multiQuestions")
class MultiQuestionController {
    @Autowired
    lateinit var multiQuestionService: MultiQuestionService

    @GetMapping("/questionId")
    fun findOnlyQuestionId(): Response<Long> {
        val data = multiQuestionService.findOnlyQuestionId()?.getId()
        if (data == null) {
            return Response.Err("no such question id")
        } else {
            return Response.Ok("this question id", data)
        }
    }

    @PostMapping
    fun insertOne(@RequestBody multiQuestion: MultiQuestion): Response<MultiQuestion> {
        val data = multiQuestionService.insertOne(multiQuestion)
        return Response.Ok("insert ok", data)
    }
}