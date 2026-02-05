package com.example.kotlinspringdemo.book

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(private val service: BookService) {

    @GetMapping
    fun listBooks(): List<BookResponse> = service.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Long): BookResponse = service.findById(id).toResponse()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@Valid @RequestBody request: BookRequest): BookResponse =
        service.create(request).toResponse()

    @PutMapping("/{id}")
    fun updateBook(
        @PathVariable id: Long,
        @Valid @RequestBody request: BookRequest
    ): BookResponse = service.update(id, request).toResponse()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Long) {
        service.delete(id)
    }
}
