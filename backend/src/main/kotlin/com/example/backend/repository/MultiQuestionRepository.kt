package com.example.backend.repository

import com.example.backend.model.IdOnly
import com.example.backend.model.MultiQuestion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MultiQuestionRepository: JpaRepository<MultiQuestion, Long> {
    @Query(
        value = "select * from MultiQuestion where id in " +
                "(select questionId from PaperManage where questionType = 1 and paperId = :paperId)",
        nativeQuery = true
    )
    fun findAllByPaperId(paperId: Long): List<MultiQuestion>
    override fun findAll(pageable: Pageable): Page<MultiQuestion>
    fun findFirstByOrderByIdDesc(): IdOnly?
    fun findAllBySubject(subject: String, pageable: Pageable): List<IdOnly>
}