package com.example.backend.service

import com.example.backend.model.PaperManage
import com.example.backend.repository.PaperRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaperService {
    @Autowired
    lateinit var paperRepository: PaperRepository

    fun findAll(): List<PaperManage> {
        return paperRepository.findAll()
    }

    fun findAllByPaperId(paperId: Long): List<PaperManage> {
        return paperRepository.findAllByPaperId(paperId)
    }

    fun insertOne(data: PaperManage): PaperManage {
        return paperRepository.save(data)
    }
}