package com.example.backend.controller

import com.example.backend.exception.NoSuchUserException
import com.example.backend.model.Admin
import com.example.backend.request.RegisterAdminRequest
import com.example.backend.request.UpdatePasswordRequest
import com.example.backend.request.UpdateUserNameRequest
import com.example.backend.service.AdminService
import com.example.backend.service.StudentService
import com.example.backend.service.TeacherService
import com.example.backend.utils.JwtTokenUtil
import com.example.backend.utils.Response
import com.example.backend.utils.Status
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admins")
@Validated
class AdminController {
    @Autowired
    lateinit var adminService: AdminService
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var teacherService: TeacherService

    @PostMapping("/register")
    fun insertOne(@RequestBody @Valid request: RegisterAdminRequest, result: BindingResult): Response<Admin> {
        return Response.Ok("register ok", adminService.insertOne(request))
    }

    @DeleteMapping
    @Throws(NoSuchUserException::class)
    fun deleteOne(servletRequest: HttpServletRequest): Response<Status> {
        val username = jwtTokenUtil.getUsernameFromRequest(servletRequest)
        val user = adminService.findOne(username)
        if(user == null) {
            throw NoSuchUserException("no such user ${username}")
        } else {
            adminService.deleteOne(user.id!!)
            return Response.Ok("delete ok", Status.Ok)
        }
    }

    @DeleteMapping("/student/{id}")
    fun deleteOneStudent(@PathVariable id: Long): Response<Status> {
        studentService.deleteOne(id)
        return Response.Ok("delete ok", Status.Ok)
    }

    @DeleteMapping("/teacher/{id}")
    fun deleteOneTeacher(@PathVariable id: Long): Response<Status> {
        teacherService.deleteOne(id)
        return Response.Ok("delete ok", Status.Ok)
    }

    @GetMapping
    @Throws(NoSuchUserException::class)
    fun findOne(servletRequest: HttpServletRequest): Response<Admin> {
        val username = jwtTokenUtil.getUsernameFromRequest(servletRequest)
        val user = adminService.findOne(username)
        if(user == null) {
            throw NoSuchUserException("no such user ${username}")
        } else {
            return Response.Ok("this user", user)
        }
    }

    @GetMapping("/all")
    fun findAll(): Response<List<Admin>> {
        return Response.Ok("all admins", adminService.findAll())
    }

    @PutMapping("/name")
    @Throws(NoSuchUserException::class)
    fun updateName(servletRequest: HttpServletRequest, @RequestBody @Valid request: UpdateUserNameRequest, result: BindingResult): Response<Admin> {
        val username = jwtTokenUtil.getUsernameFromRequest(servletRequest)
        val user = adminService.findOne(username)
        if(user == null) {
            throw NoSuchUserException("no such user ${username}")
        } else {
            user.name = request.name
            val newuser = adminService.updateOne(user)
            return Response.Ok("update ok", newuser)
        }
    }

    @PutMapping("/password")
    @Throws(NoSuchUserException::class)
    fun updatePassword(servletRequest: HttpServletRequest, @RequestBody @Valid request: UpdatePasswordRequest, result: BindingResult): Response<Admin> {
        val username = jwtTokenUtil.getUsernameFromRequest(servletRequest)
        val user = adminService.findOne(username)
        if(user == null) {
            throw NoSuchUserException("no such user ${username}")
        } else {
            user.passwordHash = request.passwordHash
            val newuser = adminService.updateOne(user)
            return Response.Ok("update ok", newuser)
        }
    }
}