package com.example.blog.repo

import com.example.blog.models.Com
import org.springframework.data.repository.CrudRepository

interface ComRepository: CrudRepository<Com, Long> {
}