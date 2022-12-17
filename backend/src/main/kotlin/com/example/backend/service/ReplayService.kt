package com.example.backend.service

import com.example.backend.model.Replay
import com.example.backend.repository.ReplayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplayService {
    @Autowired
    lateinit var replayRepository: ReplayRepository

    fun findAll(): List<Replay> {
        return replayRepository.findAll()
    }

    fun findAllByMessageId(messageId: Long): List<Replay> {
        return replayRepository.findAllByMessageId(messageId)
    }

    fun findById(id: Long): Replay? {
        return replayRepository.findByIdOrNull(id)
    }

    fun deleteOne(id: Long) {
        replayRepository.deleteById(id)
    }

    fun updateOne(data: Replay): Replay {
        return replayRepository.save(data)
    }

    fun insertOne(data: Replay): Replay {
        return replayRepository.save(data)
    }
}