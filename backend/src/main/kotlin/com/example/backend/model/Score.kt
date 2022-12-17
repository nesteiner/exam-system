package com.example.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "Score")
class Score(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false)
    val examCode: Int,

    @Column(nullable = false)
    val studentId: Long,

    @Column(length = 20, nullable = false)
    val subject: String,

    @Column(nullable = false)
    val usualScore: Int,

    @Column(nullable = false)
    val finalScore: Int,

    @Column(nullable = false)
    val score: Int,

    @Column(length = 10, nullable = false)
    val answerDate: String
)