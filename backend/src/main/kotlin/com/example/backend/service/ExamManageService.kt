package com.example.backend.service

import com.example.backend.model.ExamManage
import com.example.backend.model.PaperIdOnly
import com.example.backend.repository.ExamManageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ExamManageService {
    @Autowired
    lateinit var examManageRepository: ExamManageRepository

    fun findAll(): List<ExamManage> {
        return examManageRepository.findAll()
    }

    fun findAll(pageable: Pageable): Page<ExamManage> {
        return examManageRepository.findAll(pageable)
    }

    fun findOne(examCode: Int): ExamManage? {
        return examManageRepository.findByExamCode(examCode)
    }

    fun deleteByExamCode(examCode: Int) {
        examManageRepository.deleteByExamCode(examCode)
    }

    fun updateOne(examManage: ExamManage): ExamManage {
        return examManageRepository.save(examManage)
    }

    fun insertOne(examManage: ExamManage): ExamManage {
        return examManageRepository.save(examManage)
    }

    fun findOnlyPaperId(): PaperIdOnly? {
        return examManageRepository.findFirstByOrderByPaperIdDesc()
    }
}