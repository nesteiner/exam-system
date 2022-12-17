package com.example.backend.controller

import com.example.backend.exception.LoginException
import com.example.backend.model.User
import com.example.backend.request.LoginRequest
import com.example.backend.response.UserResponse
import com.example.backend.service.AdminService
import com.example.backend.service.StudentService
import com.example.backend.service.TeacherService
import com.example.backend.utils.ErrorStatus
import com.example.backend.utils.JwtTokenUtil
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
@Validated
class AuthenticationController {
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var teacherService: TeacherService
    @Autowired
    lateinit var adminService: AdminService
    @PostMapping("/authenticate/student")
    @Throws(LoginException::class)
    fun createStudentToken(@RequestBody @Valid request: LoginRequest, result: BindingResult): ResponseEntity<UserResponse> {
        try {
            // val user = studentService.loadUserByUsername(request.username)
            val userdetails: UserDetails = studentService.loadUserByUsername(request.username)
            val user: User = studentService.findOne(request.username)!!

            if(request.passwordHash != userdetails.password) {
                throw LoginException("password error", ErrorStatus.UserNamePasswordError)
            } else {
                val authentication: Authentication = UsernamePasswordAuthenticationToken(userdetails, null, userdetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
                val token = jwtTokenUtil.generateToken(userdetails)
                return ResponseEntity.ok(UserResponse(user.id!!, user.name, token, user.roles.map({it.name}).joinToString(separator = ",")))
            }
        } catch (exception: DisabledException) {
            throw LoginException("user diabled", ErrorStatus.UserDisabled)
        } catch (exception: BadCredentialsException) {
            throw LoginException("in AuthenticationController: no such user or password error", ErrorStatus.UserNamePasswordError)
        }
    }

    @PostMapping("/authenticate/teacher")
    @Throws(LoginException::class)
    fun createTeacherToken(@RequestBody @Valid request: LoginRequest, result: BindingResult): ResponseEntity<UserResponse> {
        try {
            val userdetails = teacherService.loadUserByUsername(request.username)
            val user: User = teacherService.findOne(request.username)!!

            if(request.passwordHash != userdetails.password) {
                throw LoginException("password error", ErrorStatus.UserNamePasswordError)
            } else {
                val authentication: Authentication = UsernamePasswordAuthenticationToken(userdetails, null, userdetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
                val token = jwtTokenUtil.generateToken(userdetails)
                return ResponseEntity.ok(UserResponse(user.id!!, user.name, token, user.roles.map({it.name}).joinToString(separator = ",")))
            }
        } catch (exception: DisabledException) {
            throw LoginException("user diabled", ErrorStatus.UserDisabled)
        } catch (exception: BadCredentialsException) {
            throw LoginException("in AuthenticationController: no such user or password error", ErrorStatus.UserNamePasswordError)
        }

    }

    @PostMapping("/authenticate/admin")
    @Throws(LoginException::class)
    fun createAdminToken(@RequestBody @Valid request: LoginRequest, result: BindingResult): ResponseEntity<UserResponse> {
        try {
            val userdetails = adminService.loadUserByUsername(request.username)
            val user = adminService.findOne(request.username)!!

            if(request.passwordHash != userdetails.password) {
                throw LoginException("password error", ErrorStatus.UserNamePasswordError)
            } else {
                val authentication: Authentication = UsernamePasswordAuthenticationToken(userdetails, null, userdetails.authorities)
                SecurityContextHolder.getContext().authentication = authentication
                val token = jwtTokenUtil.generateToken(userdetails)
                return ResponseEntity.ok(UserResponse(user.id!!, user.name, token, user.roles.map({it.name}).joinToString(separator = ",")))
            }
        } catch (exception: DisabledException) {
            throw LoginException("user diabled", ErrorStatus.UserDisabled)
        } catch (exception: BadCredentialsException) {
            throw LoginException("in AuthenticationController: no such user or password error", ErrorStatus.UserNamePasswordError)
        }

    }

}