package com.example.blog.controllers

import com.example.blog.models.Com
import com.example.blog.repo.ComRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.Optional

@Controller
class ComController(private val comRepository: ComRepository) {

    @GetMapping("/comments")
    fun comments(model: Model): String {
        val comments: MutableIterable<Com> = comRepository.findAll()
        model.addAttribute("comments", comments)
        return "comments"
    }

    @GetMapping("/comments/add")
    fun commentsAdd(model: Model): String {
        return "comments-add"
    }

    @PostMapping("/comments/add")
    fun commentsPostAdd(@RequestParam title :String, @RequestParam name: String, @RequestParam descr: String, @RequestParam text: String, model: Model): String {
        val com: Com = Com(title, name, descr, text)
        comRepository.save(com)
        return "redirect:/comments"
    }

    @GetMapping("/comments/{id}")
    fun commentsDetails(@PathVariable(value = "id") id: Long, model: Model): String {
        if (!comRepository.existsById(id)){
            return "redirect:/comments"
        }

        val comments: Optional<Com> = comRepository.findById(id)
        val res = ArrayList<Com>()
        comments.ifPresent(res::add)
        model.addAttribute("comments", res)
        return "comments-details"
    }

    @DeleteMapping("/comments/{id}/remove")
    fun commentsPostRemove(@PathVariable(value = "id") id: Long, model: Model): String {
        val com: Com = comRepository.findById(id).orElseThrow()
        comRepository.delete(com)
        return "redirect:/comments"
    }


}