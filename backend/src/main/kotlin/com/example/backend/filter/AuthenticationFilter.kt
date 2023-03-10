package com.example.backend.filter

import com.example.backend.service.AdminService
import com.example.backend.service.StudentService
import com.example.backend.service.TeacherService
import com.example.backend.utils.ErrorStatus
import com.example.backend.utils.JwtTokenUtil
import com.example.backend.utils.Response
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationFilter: OncePerRequestFilter() {
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil
    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var adminService: AdminService
    @Autowired
    lateinit var teacherService: TeacherService

    @Throws(UsernameNotFoundException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val username = request.getAttribute("username") as String?
        val jwttoken = request.getAttribute("jwtToken") as String?

        if(username != null && jwttoken != null && SecurityContextHolder.getContext().authentication == null) {
            try {
                // val userdetails = userService.loadUserByUsername(username)
                var userdetails: UserDetails? = null
                if(studentService.findOne(username) != null) {
                    userdetails = studentService.loadUserByUsername(username)
                } else if(teacherService.findOne(username) != null) {
                    userdetails = teacherService.loadUserByUsername(username)
                } else if(adminService.findOne(username) != null) {
                    userdetails = adminService.loadUserByUsername(username)
                } else {
                    throw UsernameNotFoundException("no such user")
                }

                if(jwtTokenUtil.validateToken(jwttoken, userdetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        userdetails, null, userdetails.authorities
                    )

                    usernamePasswordAuthenticationToken.setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            } catch (exception: UsernameNotFoundException) {
                val objectMapper = ObjectMapper()
                val result = Response.Err("username not found", ErrorStatus.UserNameNotFound.code)
                response.status = 400
                response.writer.write(objectMapper.writeValueAsString(result))
                return
            }

        }

        filterChain.doFilter(request, response)
    }
}