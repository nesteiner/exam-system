package com.example.backend.repository

import com.example.backend.model.PaperManage
import com.example.backend.model.PaperManageId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaperRepository: JpaRepository<PaperManage, PaperManageId> {
    fun findAllByPaperId(paperId: Long): List<PaperManage>
}