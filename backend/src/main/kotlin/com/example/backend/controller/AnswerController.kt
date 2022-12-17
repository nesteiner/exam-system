package com.example.backend.controller

import com.example.backend.model.Answer
import com.example.backend.service.AnswerService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/answers")
class AnswerController {
    @Autowired
    lateinit var answerService: AnswerService

    @GetMapping("/{page}/{size}")
    fun findAll(@PathVariable("page") page: Int, @PathVariable("size") size: Int): Response<Page<Answer>> {
        val pageable = PageRequest.of(page, size)
        val answers = answerService.findAll(pageable)
        return Response.Ok("all answers", answers)
    }
}