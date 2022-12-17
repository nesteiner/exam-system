package com.example.backend.repository

import com.example.backend.model.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: JpaRepository<Message, Long> {
    override fun findAll(pageable: Pageable): Page<Message>
}