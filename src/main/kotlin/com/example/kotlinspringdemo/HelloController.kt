package com.example.kotlinspringdemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// Simple typed response
data class HelloResponse(val message: String)

@RestController
class HelloController {

    @GetMapping("/", "/hello")
    fun hello(): HelloResponse = HelloResponse("Hello, World!")
}
