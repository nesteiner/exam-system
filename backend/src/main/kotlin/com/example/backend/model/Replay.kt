package com.example.backend.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity(name = "Replay")
class Replay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    val id: Long?

    @Column(nullable = false)
    val messageId: Long

    @Column(length = 255, nullable = false)
    val replay: String

    @Column(nullable = false)
    val replayTime: Date

    constructor(id: Long, messageId: Long, replay: String, replayTime: String) {
        this.id = id
        this.messageId = messageId
        this.replay = replay
        this.replayTime = Date.valueOf(replayTime)
    }
}