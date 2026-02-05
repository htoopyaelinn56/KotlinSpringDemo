package com.example.kotlinspringdemo.book

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BookService(private val repository: BookRepository) {

    fun findAll(): List<Book> = repository.findAll()

    fun findById(id: Long): Book = repository.findByIdOrNull(id)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book $id not found")

    fun create(request: BookRequest): Book {
        val book = Book(
            title = request.title.trim(),
            author = request.author.trim(),
            publishedYear = request.publishedYear,
            isbn = request.isbn.trim()
        )
        return repository.save(book)
    }

    fun update(id: Long, request: BookRequest): Book {
        val existing = findById(id)
        existing.title = request.title.trim()
        existing.author = request.author.trim()
        existing.publishedYear = request.publishedYear
        existing.isbn = request.isbn.trim()
        return repository.save(existing)
    }

    fun delete(id: Long) {
        try {
            repository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Book $id not found")
        }
    }
}
