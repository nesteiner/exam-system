package com.example.backend.repository

import com.example.backend.model.Score
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ScoreRepository: JpaRepository<Score, Long> {
    fun findAllByOrderByScoreDesc(): List<Score>
    fun findAllByStudentId(studentId: Long, pageable: Pageable): Page<Score>
    fun findAllByStudentId(studentId: Long): List<Score>

    @Query(
        value = "select max(usualScore) as usualScore from Score " +
                "where examCode = :examCode group  by StudentId",
        nativeQuery = true
    )
    fun findAllByExamCode(examCode: Int): List<Score>
}