package com.example.backend.controller

import com.example.backend.exception.NoSuchUserException
import com.example.backend.model.Teacher
import com.example.backend.request.UpdateTeacherRequest
import com.example.backend.request.RegisterTeacherRequest
import com.example.backend.service.TeacherService
import com.example.backend.utils.JwtTokenUtil
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("/teachers")
class TeacherController {
    @Autowired
    lateinit var teacherService: TeacherService
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @PostMapping("/register")
    fun insertOne(@RequestBody @Valid request: RegisterTeacherRequest, result: BindingResult): Response<Teacher> {
        return Response.Ok("register ok", teacherService.insertOne(request))
    }

    @PutMapping
    fun updateOne(@RequestBody @Valid teacher: UpdateTeacherRequest): Response<Teacher> {
        val data = teacherService.updateOne(teacher)
        return Response.Ok("update ok", data)
    }

    @GetMapping("/{page}/{size}")
    fun findAll(@PathVariable("page") page: Int, @PathVariable("size") size: Int): Response<Page<Teacher>> {
        val pageable = PageRequest.of(page, size)
        val data = teacherService.findAll(pageable)
        return Response.Ok("all teachers of this page", data)
    }

    @GetMapping("/{id}")
    @Throws(NoSuchUserException::class)
    fun findOne(@PathVariable("id") id: Long): Response<Teacher> {
        val data = teacherService.findOne(id)
        if (data == null) {
            throw NoSuchUserException("no such teacher")
        } else {
            return Response.Ok("this teacher", data)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable("id") id: Long): Response<Status> {
        teacherService.deleteOne(id)
        return Response.Ok("delete ok", Status.Ok)
    }
}