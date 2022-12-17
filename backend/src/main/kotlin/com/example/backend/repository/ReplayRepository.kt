package com.example.backend.repository

import com.example.backend.model.Replay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReplayRepository: JpaRepository<Replay, Long> {
    fun findAllByMessageId(messageId: Long): List<Replay>
}