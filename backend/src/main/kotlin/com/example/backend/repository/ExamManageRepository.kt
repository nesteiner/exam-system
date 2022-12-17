package com.example.backend.repository

import com.example.backend.model.ExamManage
import com.example.backend.model.PaperIdOnly
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Repository

@Repository
interface ExamManageRepository: JpaRepository<ExamManage, Int> {
    override fun findAll(pageable: Pageable): Page<ExamManage>
    fun findByExamCode(examCode: Int): ExamManage?

    @Modifying
    @Transactional
    fun deleteByExamCode(examCode: Int): Int
    fun findFirstByOrderByPaperIdDesc(): PaperIdOnly?

}