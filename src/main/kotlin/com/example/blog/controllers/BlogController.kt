package com.example.blog.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import com.example.blog.models.Post
import com.example.blog.repo.PostRepositoty
import org.springframework.web.bind.annotation.*
import java.util.Optional


@Controller
class BlogController(private val postRepository: PostRepositoty){

    @GetMapping("/blog")
    fun blogMain(model: Model): String {
        val posts: MutableIterable<Post> = postRepository.findAll()
        model.addAttribute("posts", posts)
        return "blog-main"
    }

    @GetMapping("/blog/add")
    fun blogAdd(model: Model): String {
        return "blog-add"
    }

    @PostMapping("/blog/add")
    //@RequestParam принимает параметры из формы добавления нового поста
    fun blogPostAdd(@RequestParam title: String, @RequestParam anons: String, @RequestParam full_text: String, model: Model): String {
        val post: Post = Post(title, anons, full_text)
        postRepository.save(post)
        return "redirect:/blog"
    }

    @GetMapping("/blog/{id}")
    //Параметр id является path-переменной, которая будет извлечена из URL
    fun blogDetails(@PathVariable(value = "id") id: Long, model: Model): String {
        // проверка существования записи по id
        if (!postRepository.existsById(id)){
            return "redirect:/blog"
        }

        //Optional - это контейнер, который может не содержать значение
        val post: Optional<Post> = postRepository.findById(id)
        //Если запись существует, она добавляется в список res
        val res = ArrayList<Post>()
        post.ifPresent(res::add)
        model.addAttribute("post", res)
        return "blog-details"
    }

    @GetMapping("/blog/{id}/edit")
    fun blogEdit(@PathVariable(value = "id") id: Long, model: Model): String {
        if (!postRepository.existsById(id)){
            return "redirect:/blog"
        }

        val post: Optional<Post> = postRepository.findById(id)
        val res = ArrayList<Post>()
        post.ifPresent(res::add)
        model.addAttribute("post", res)
        return "blog-edit"
    }

    @PutMapping("/blog/{id}/edit")
    fun blogPostUpdate(@PathVariable(value = "id") id: Long, @RequestParam title: String, @RequestParam anons: String, @RequestParam full_text: String, model: Model): String {
        //Метод orElseThrow() используется для получения значения из объекта Optional или генерации исключения, если объект Optional пустой
        val post: Post = postRepository.findById(id).orElseThrow()
        post.title = title
        post.anons = anons
        post.full_text = full_text
        postRepository.save(post)
        return "redirect:/blog"
    }

    @DeleteMapping("/blog/{id}/remove")
    fun blogPostDelete(@PathVariable(value = "id") id: Long, model: Model): String {
        val post: Post = postRepository.findById(id).orElseThrow()
        postRepository.delete(post)
        return "redirect:/blog";
    }

}