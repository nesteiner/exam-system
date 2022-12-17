package com.example.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity(name = "FillQuestion")
class FillQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    val id: Long?,

    @Column(length = 20, nullable = false)
    val subject: String,

    @Column(length = 255, nullable = false)
    val question: String,

    @Column(length = 255, nullable = false)
    val answer: String,

    @Column(length = 255, nullable = false)
    val analysis: String,

    @Column(nullable = false)
    val score: Int,

    @Column(length = 5, nullable = false)
    val level: String,

    @Column(length = 20, nullable = false)
    val section: String
): Question()