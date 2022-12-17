package com.example.backend.service

import com.example.backend.model.Message
import com.example.backend.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    fun findAll(pageable: Pageable): Page<Message> {
        return messageRepository.findAll(pageable)
    }

    fun findOne(id: Long): Message? {
        return messageRepository.findByIdOrNull(id)
    }

    fun deleteOne(id: Long) {
        messageRepository.deleteById(id)
    }

    fun updateOne(message: Message): Message {
        return messageRepository.save(message)
    }

    fun insertOne(message: Message): Message {
        return messageRepository.save(message)
    }
}