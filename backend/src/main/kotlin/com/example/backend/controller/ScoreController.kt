package com.example.backend.controller

import com.example.backend.model.Score
import com.example.backend.service.ScoreService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scores")
class ScoreController {
    @Autowired
    lateinit var scoreService: ScoreService

    @GetMapping
    fun findAll(): Response<List<Score>> {
        val data = scoreService.findAll()
        return Response.Ok("all scores", data)
    }

    @GetMapping("/{page}/{size}/{studentId}")
    fun findAllByStudentId(@PathVariable("page") page: Int,
                           @PathVariable("size") size: Int,
                           @PathVariable("studentId") studentId: Long): Response<Page<Score>> {
        val pageable = PageRequest.of(page, size)
        val data = scoreService.findAllByStudentId(studentId, pageable)
        return Response.Ok("scores from id", data)
    }

    @GetMapping(params = ["studentId"])
    fun findAllByStudentId(@RequestParam("studentId") studentId: Long): Response<List<Score>> {
        val data = scoreService.findAllByStudentId(studentId)
        return Response.Ok("all scores of this student", data)
    }

    @PostMapping
    fun insertOne(@RequestBody score: Score): Response<Score> {
        val data = scoreService.insertOne(score)
        return Response.Ok("insert ok", data)
    }

    @GetMapping(params = ["examCode"])
    fun findAllByExamCode(@RequestParam("examCode") examCode: Int): Response<List<Score>> {
        val data = scoreService.findAllByExamCode(examCode)
        return Response.Ok("all scores of this examcode", data)
    }
}