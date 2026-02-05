package com.example.kotlinspringdemo.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var author: String,
    @Column(name = "published_year")
    var publishedYear: Int?,
    @Column(nullable = false, unique = true, length = 32)
    var isbn: String
)
