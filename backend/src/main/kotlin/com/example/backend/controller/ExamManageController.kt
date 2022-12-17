package com.example.backend.controller

import com.example.backend.model.ExamManage
import com.example.backend.service.ExamManageService
import com.example.backend.utils.ErrorStatus
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exams")
class ExamManageController {
    @Autowired
    lateinit var examManageService: ExamManageService

    @GetMapping
    fun findAll(): Response<List<ExamManage>> {
        val data = examManageService.findAll()
        return Response.Ok("all exams", data)
    }

    @GetMapping("/{page}/{size}")
    fun findAll(@PathVariable("page") page: Int, @PathVariable("size") size: Int): Response<Page<ExamManage>> {
        val pageable = PageRequest.of(page, size)
        val data = examManageService.findAll(pageable)
        return Response.Ok("all exams", data)
    }

    @GetMapping("/{examCode}")
    fun findByExamCode(@PathVariable("examCode") examCode: Int): Response<ExamManage> {
        val data = examManageService.findOne(examCode)
        if(data == null) {
            return Response.Err("考试编号不存在")
        } else {
            return Response.Ok("this exam", data)
        }
    }

    @DeleteMapping("/{examCode}")
    fun deleteByExamCode(@PathVariable("examCode") examCode: Int): Response<Status> {
        examManageService.deleteByExamCode(examCode)
        return Response.Ok("delete ok", Status.Ok)
    }

    @PutMapping
    fun updateOne(@RequestBody examManage: ExamManage): Response<ExamManage> {
        val data = examManageService.updateOne(examManage)
        return Response.Ok("update ok", data)
    }

    @PostMapping
    fun insertOne(@RequestBody examManage: ExamManage): Response<ExamManage> {
        val data = examManageService.insertOne(examManage)
        return Response.Ok("insert ok", data)
    }

    @GetMapping("/paperId")
    fun findOnlyPaperId(): Response<Long> {
        val data = examManageService.findOnlyPaperId()?.getPaperId()
        if(data != null) {
            return Response.Ok("this paper id", data)
        } else {
            return Response.Err("no such paper id")
        }
    }
}