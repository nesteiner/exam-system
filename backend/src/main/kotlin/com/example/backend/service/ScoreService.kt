package com.example.backend.service

import com.example.backend.model.Score
import com.example.backend.repository.ScoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ScoreService {
    @Autowired
    lateinit var scoreRepository: ScoreRepository

    fun insertOne(data: Score): Score {
        return scoreRepository.save(data)
    }

    fun findAll(): List<Score> {
        return scoreRepository.findAll()
    }

    fun findAllByStudentId(studentId: Long, pageable: Pageable): Page<Score> {
        return scoreRepository.findAllByStudentId(studentId, pageable)
    }

    fun findAllByStudentId(studentId: Long): List<Score> {
        return scoreRepository.findAllByStudentId(studentId)
    }

    fun findAllByExamCode(examCode: Int): List<Score> {
        return scoreRepository.findAllByExamCode(examCode)
    }
}