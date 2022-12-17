package com.example.backend.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass

@Entity(name = "PaperManage")
@IdClass(PaperManageId::class)
class PaperManage(
    @Id
    @Column(nullable = false)
    val paperId: Long,

    @Id
    @Column(nullable = false)
    val questionType: Int,

    @Id
    @Column(nullable = false)
    val questionId: Long
)