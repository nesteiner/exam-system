package com.example.backend.configure

import com.example.backend.encoder.MD5PasswordEncoder
import com.example.backend.filter.AuthenticationFilter
import com.example.backend.filter.LoginFilter
import com.example.backend.service.AdminService
import com.example.backend.service.StudentService
import com.example.backend.service.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfigure {
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(authenticationManagerBuilder: AuthenticationManagerBuilder, adminService: AdminService, studentService: StudentService, teacherService: TeacherService, mD5PasswordEncoder: MD5PasswordEncoder) {
        authenticationManagerBuilder
            .userDetailsService(adminService)
            .passwordEncoder(mD5PasswordEncoder)

        authenticationManagerBuilder
            .userDetailsService(studentService)
            .passwordEncoder(mD5PasswordEncoder)

        authenticationManagerBuilder
            .userDetailsService(teacherService)
            .passwordEncoder(mD5PasswordEncoder)
    }
    @Value("\${authenticate-url}")
    lateinit var authenticateUrl: String

    @Value("\${student.register.url}")
    lateinit var studentRegisterUrl: String

    @Value("\${teacher.register.url}")
    lateinit var teacherRegisterUrl: String

    @Value("\${admin.register.url}")
    lateinit var adminRegisterUrl: String

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity, loginFilter: LoginFilter, authenticateFilter: AuthenticationFilter): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(authenticateUrl).permitAll()
            .requestMatchers(studentRegisterUrl, teacherRegisterUrl, adminRegisterUrl).permitAll()
            .requestMatchers("/admin/**").hasAuthority("admin")
            .and()
            .authorizeHttpRequests().requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterAt(authenticateFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}