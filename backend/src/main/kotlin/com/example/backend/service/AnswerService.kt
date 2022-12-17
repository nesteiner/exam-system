package com.example.backend.service

import com.example.backend.model.Answer
import com.example.backend.repository.AnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnswerService {
    @Autowired
    lateinit var answerRepository: AnswerRepository

    fun findAll(pageable: Pageable): Page<Answer> {
        return answerRepository.findAll(pageable)
    }
}