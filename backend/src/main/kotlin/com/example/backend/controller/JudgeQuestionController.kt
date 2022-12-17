package com.example.backend.controller

import com.example.backend.model.JudgeQuestion
import com.example.backend.service.JudgeQuestionService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/judgeQuestions")
class JudgeQuestionController {
    @Autowired
    lateinit var judgeQuestionService: JudgeQuestionService

    @PostMapping
    fun insertOne(@RequestBody judgeQuestion: JudgeQuestion): Response<JudgeQuestion> {
        val data = judgeQuestionService.insertOne(judgeQuestion)
        return Response.Ok("insert ok", data)
    }

    @GetMapping("/questionId")
    fun findOnlyQuestionId(): Response<Long> {
        val data = judgeQuestionService.findOnlyQuestionId()?.getId()
        if (data == null) {
            return Response.Err("no such question id")
        } else {
            return Response.Ok("this question id", data)
        }
    }
}