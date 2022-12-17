package com.example.backend.controller

import com.example.backend.model.PaperManage
import com.example.backend.model.Question
import com.example.backend.service.FillQuestionService
import com.example.backend.service.JudgeQuestionService
import com.example.backend.service.MultiQuestionService
import com.example.backend.service.PaperService
import com.example.backend.utils.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/papers")
class PaperController {
    @Autowired
    lateinit var paperService: PaperService
    @Autowired
    lateinit var judgeQuestionService: JudgeQuestionService
    @Autowired
    lateinit var multiQuestionService: MultiQuestionService
    @Autowired
    lateinit var fillQuestionService: FillQuestionService

    @GetMapping
    fun findAll(): Response<List<PaperManage>> {
        val data = paperService.findAll()
        return Response.Ok("all papers", data)
    }

    @GetMapping("/{paperId}")
    fun findByPaperId(@PathVariable("paperId") paperId: Long): Response<Map<Int, List<Question>>> {
        val multiQuestions = multiQuestionService.findByPaperId(paperId)
        val fillQuestions = fillQuestionService.findAllByPaperId(paperId)
        val judgeQuestions = judgeQuestionService.findAllByPaperId(paperId)

        val map = HashMap<Int, List<Question>>()
        map[1] = multiQuestions
        map[2] = fillQuestions
        map[3] = judgeQuestions

        return Response.Ok("questions", map)
    }

    @PostMapping
    fun insertOne(@RequestBody paperManage: PaperManage): Response<PaperManage> {
        val data = paperService.insertOne(paperManage)
        return Response.Ok("insert ok", data)
    }

}