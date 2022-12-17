package com.example.backend.repository

import com.example.backend.model.FillQuestion
import com.example.backend.model.IdOnly
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface FillQuestionRepository: JpaRepository<FillQuestion, Long> {
    @Query(
        value = "select * from FillQuestion " +
                "where id in " +
                "(select questionId from PaperManage where questionType = 2 and paperId = ?1)",
        nativeQuery = true
    )
    fun findAllByPaperId(paperId: Long): List<FillQuestion>
    override fun findAll(pageable: Pageable): Page<FillQuestion>
    fun findFirstByOrderByIdDesc(): IdOnly?
    fun findAllBySubject(subject: String, pageable: Pageable): List<IdOnly>
}