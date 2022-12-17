package com.example.backend.repository

import com.example.backend.model.IdOnly
import com.example.backend.model.JudgeQuestion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface JudgeQuestionRepository: JpaRepository<JudgeQuestion, Long> {
    @Query(
        value = "select * from JudgeQuestion where id in " +
                "(select questionId from PaperManage where questionType = 3 and paperId = :paperId)",
        nativeQuery = true
    )
    fun findAllByPaperId(paperId: Long): List<JudgeQuestion>
    fun findFirstByOrderByIdDesc(): IdOnly?
    fun findAllBySubject(subject: String, pageable: Pageable): Page<IdOnly>
}