package com.meridiane.teacher.domain.models

data class Lesson(
    var lesson_date: String ?= null,
    var tittle: String ?= null,
    var grade: String ?= null,
    var description: String ?= null,
    var teacher_id: String ?= null,
    var student_id: String ?= null,
    var date: Long ?= null,
    var like: Int ?= null,
    var id: String ?= null
)
