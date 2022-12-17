package com.example.backend.repository

import com.example.backend.model.Answer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class AnswerRepository {
    @Autowired
    lateinit var multiQuestionRepository: MultiQuestionRepository
    @Autowired
    lateinit var judgeQuestionRepository: JudgeQuestionRepository
    @Autowired
    lateinit var fillQuestionRepository: FillQuestionRepository

    fun findAll(pageable: Pageable): Page<Answer> {
        // val newpageable = PageRequest.of(pageable.pageNumber, pageable.pageSize / 3)
        val part1 = multiQuestionRepository.findAll(pageable).map {
            Answer(it.question, it.subject, it.score.toString(), it.section, it.level, "多选题")
        }

        val part2 = judgeQuestionRepository.findAll(pageable).map {
            Answer(it.question, it.subject, it.score.toString(), it.section, it.level, "判断题")
        }

        val part3 = fillQuestionRepository.findAll(pageable).map {
            Answer(it.question, it.subject, it.score.toString(), it.section, it.level, "填空题")
        }

        return PageImpl(listOf(part1, part2, part3).flatten().subList(0, pageable.pageSize))
    }
}