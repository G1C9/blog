package com.example.blog.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class MainController {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("title", "Главная страница")
        return "home"
    }

}