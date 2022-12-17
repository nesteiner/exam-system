package com.example.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity(name = "MultiQuestion")
class MultiQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    val id: Long?,

    @Column(length = 20, nullable = false)
    val subject: String,

    @Column(length = 255, nullable = false)
    val question: String,

    @Column(length = 255, nullable = false)
    val answerA: String,

    @Column(length = 255, nullable = false)
    val answerB: String,

    @Column(length = 255, nullable = false)
    val answerC: String,

    @Column(length = 255, nullable = false)
    val answerD: String,

    @Column(length = 10, nullable = false)
    val rightAnswer: String,

    @Column(length = 255, nullable = false)
    val analysis: String,

    @Column(nullable = false)
    val score: Int,

    @Column(length = 20, nullable = false)
    val section: String,

    @Column(length = 1, nullable = false)
    val level: String,
): Question()