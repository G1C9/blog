package com.example.blog.repo

import com.example.blog.models.Post
import org.springframework.data.repository.CrudRepository

interface PostRepositoty: CrudRepository<Post, Long> {
}