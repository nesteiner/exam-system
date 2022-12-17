package com.example.backend.service

import com.example.backend.model.IdOnly
import com.example.backend.model.JudgeQuestion
import com.example.backend.repository.JudgeQuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class JudgeQuestionService {
    @Autowired
    lateinit var judgeQuestionRepository: JudgeQuestionRepository

    fun findAllByPaperId(paperId: Long): List<JudgeQuestion> {
        return judgeQuestionRepository.findAllByPaperId(paperId)
    }

    fun findAll(pageable: Pageable): Page<JudgeQuestion> {
        return judgeQuestionRepository.findAll(pageable)
    }

    fun findOnlyQuestionId(): IdOnly? {
        return judgeQuestionRepository.findFirstByOrderByIdDesc()
    }

    fun insertOne(data: JudgeQuestion): JudgeQuestion {
        return judgeQuestionRepository.save(data)
    }

    fun findAllBySubject(subject: String, pageable: Pageable): Page<IdOnly> {
        return judgeQuestionRepository.findAllBySubject(subject, pageable)
    }
}