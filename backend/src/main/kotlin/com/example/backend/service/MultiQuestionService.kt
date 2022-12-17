package com.example.backend.service

import com.example.backend.model.IdOnly
import com.example.backend.model.MultiQuestion
import com.example.backend.repository.MultiQuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MultiQuestionService {
    @Autowired
    lateinit var multiQuestionRepository: MultiQuestionRepository

    fun findByPaperId(paperId: Long): List<MultiQuestion> {
        return multiQuestionRepository.findAllByPaperId(paperId)
    }

    fun findAll(pageable: Pageable): Page<MultiQuestion> {
        return multiQuestionRepository.findAll(pageable)
    }

    fun findOnlyQuestionId(): IdOnly? {
        return multiQuestionRepository.findFirstByOrderByIdDesc()
    }

    fun insertOne(data: MultiQuestion): MultiQuestion {
        return multiQuestionRepository.save(data)
    }

    fun findAllBySubject(subject: String, pageable: Pageable): List<IdOnly> {
        return multiQuestionRepository.findAllBySubject(subject, pageable)
    }
}