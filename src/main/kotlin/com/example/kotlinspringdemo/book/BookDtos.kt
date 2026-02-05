package com.example.kotlinspringdemo.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class BookRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val author: String,
    val publishedYear: Int?,
    @field:NotBlank
    @field:Size(max = 32)
    val isbn: String
)

data class BookResponse(
    val id: Long,
    val title: String,
    val author: String,
    val publishedYear: Int?,
    val isbn: String
)

fun Book.toResponse(): BookResponse = BookResponse(
    id = id,
    title = title,
    author = author,
    publishedYear = publishedYear,
    isbn = isbn
)
