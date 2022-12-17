package com.example.backend.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Transient
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity(name = "Message")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    val id: Long?

    @Transient
    val tempId: Long

    @Column(length = 20, nullable = false)
    val title: String

    @Column(length = 255, nullable = false)
    val content: String

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    val time: Date

    @OneToMany
    @JoinColumn(name = "messageId")
    val replays: List<Replay>

    constructor(id: Long, title: String, content: String, time: String, tempId: Long = 0) {
        this.id = id
        this.title = title
        this.tempId = tempId
        this.content = content
        this.time = Date.valueOf(time)
        this.replays = mutableListOf()
    }
}