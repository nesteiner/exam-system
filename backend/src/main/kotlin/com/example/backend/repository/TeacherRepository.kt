package com.example.backend.repository

import com.example.backend.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository: JpaRepository<Teacher, Long> {
    fun findByName(name: String): Teacher?
}