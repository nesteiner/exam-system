package com.example.backend.service

import com.example.backend.model.FillQuestion
import com.example.backend.model.IdOnly
import com.example.backend.repository.FillQuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FillQuestionService {
    @Autowired
    lateinit var fillQuestionRepository: FillQuestionRepository

    fun findAllByPaperId(paperId: Long): List<FillQuestion> {
        return fillQuestionRepository.findAllByPaperId(paperId)
    }

    fun findAll(pageable: Pageable): Page<FillQuestion> {
        return fillQuestionRepository.findAll(pageable)
    }

    fun findOnlyQuestionId(): IdOnly? {
        return fillQuestionRepository.findFirstByOrderByIdDesc()
    }

    fun insertOne(data: FillQuestion): FillQuestion {
        return fillQuestionRepository.save(data)
    }

    fun findAllBySubject(subject: String, pageable: Pageable): List<IdOnly> {
        return fillQuestionRepository.findAllBySubject(subject, pageable)
    }
}