package com.meridiane.teacher.domain.models

data class Student(
    var full_name: String ?= null,
    var telegram: String ?= null,
    var teacher_id: Int ?= null,
    var address: String ?= null,
    var telephone_number: String ?= null,
    var image: String ?= null,
    var id: Int ?= null
)
