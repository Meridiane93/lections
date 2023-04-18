package com.meridiane.teacher.domain.models

data class Teacher(
    var full_name: String ?= null,
    var email: String ?= null,
    var telegram: String ?= null,
    var password: String ?= null,
    var students: String ?= null,
    var id: Int ?= null,
    var image: String ?= null
)