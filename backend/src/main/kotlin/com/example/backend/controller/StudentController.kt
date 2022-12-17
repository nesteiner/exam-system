package com.example.backend.controller

import com.example.backend.exception.NoSuchUserException
import com.example.backend.model.Student
import com.example.backend.request.RegisterStudentRequest
import com.example.backend.request.UpdatePasswordRequest
import com.example.backend.request.UpdateStudentRequest
import com.example.backend.request.UpdateUserNameRequest
import com.example.backend.service.StudentService
import com.example.backend.utils.JwtTokenUtil
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("/students")
class StudentController {
    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @PostMapping("/register")
    fun insertOne(@RequestBody @Valid request: RegisterStudentRequest, result: BindingResult): Response<Student> {
        return Response.Ok("register ok", studentService.insertOne(request))
    }

    @PutMapping
    fun updateOne(@RequestBody student: UpdateStudentRequest): Response<Student> {
        val data = studentService.updateOne(student)
        return Response.Ok("update ok", data)
    }

    @GetMapping("/{page}/{size}")
    fun findAll(@PathVariable("page") page: Int, @PathVariable("size") size: Int): Response<Page<Student>> {
        val pageable = PageRequest.of(page, size)
        val data = studentService.findAll(pageable)
        return Response.Ok("all students of this page", data)
    }

    @GetMapping("/{id}")
    @Throws(NoSuchUserException::class)
    fun findOne(@PathVariable("id") id: Long): Response<Student> {
        val data = studentService.findOne(id)
        if (data == null) {
            throw NoSuchUserException("no such student")
        } else {
            return Response.Ok("this student", data)
        }
    }
}