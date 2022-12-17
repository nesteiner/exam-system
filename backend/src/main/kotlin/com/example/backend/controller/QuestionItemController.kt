package com.example.backend.controller

import com.example.backend.model.PaperManage
import com.example.backend.model.QuestionItem
import com.example.backend.service.FillQuestionService
import com.example.backend.service.JudgeQuestionService
import com.example.backend.service.MultiQuestionService
import com.example.backend.service.PaperService
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/questionItems")
class QuestionItemController {
    @Autowired
    lateinit var multiQuestionService: MultiQuestionService
    @Autowired
    lateinit var fillQuestionService: FillQuestionService
    @Autowired
    lateinit var judgeQuestionService: JudgeQuestionService
    @Autowired
    lateinit var paperService: PaperService

    @PostMapping
    fun insertOne(@RequestBody questionItem: QuestionItem): Response<Status> {
        val changeNumber = questionItem.changeNumber
        val fillNumber = questionItem.fillNumber
        val judgeNumber = questionItem.judgeNumber
        val paperId = questionItem.paperId

        val changeNumbers = multiQuestionService.findAllBySubject(questionItem.subject, PageRequest.of(0, changeNumber))
        changeNumbers.forEach {
            val paperManage = PaperManage(paperId, 1, it.getId())
            paperService.insertOne(paperManage)
        }

        val fills = fillQuestionService.findAllBySubject(questionItem.subject, PageRequest.of(0, fillNumber))
        fills.forEach {
            val paperManage = PaperManage(paperId, 2, it.getId())
            paperService.insertOne(paperManage)
        }

        val judges = judgeQuestionService.findAllBySubject(questionItem.subject, PageRequest.of(0, judgeNumber))
        judges.forEach {
            val paperManage = PaperManage(paperId, 3, it.getId())
            paperService.insertOne(paperManage)
        }

        return Response.Ok("试卷组卷成功", Status.Ok)
    }
}