package com.example.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity(name = "ExamManage")
class ExamManage(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "myid")
    @GenericGenerator(name = "myid", strategy = "com.example.backend.generator.ManualInsertGenerator")
    val examCode: Int?,

    @Column(length = 50, nullable = false)
    val description: String,

    @Column(length = 20, nullable = false)
    val source: String,

    @Column(nullable = false)
    val paperId: Long,

    @Column(length = 10, nullable = false)
    val examDate: String,

    @Column(nullable = false)
    val totalTime: Int,

    @Column(length = 10, nullable = false)
    val grade: String,

    @Column(length = 10, nullable = false)
    val term: String,

    @Column(length = 20, nullable = false)
    val major: String,

    @Column(length = 20, nullable = false)
    val institute: String,

    @Column(nullable = false)
    val totalScore: Int,

    @Column(length = 255, nullable = false)
    val type: String,

    @Column(length = 255, nullable = false)
    val tips: String
)