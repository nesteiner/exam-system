package com.example.backend.model

import java.io.Serializable

class PaperManageId(
    val paperId: Long,
    val questionType: Int,
    val questionId: Long
): Serializable {
    constructor(): this(0, 0, 0)
}