package com.example.blog.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

//Аннотация указывает, что класс Post является сущностью, которая будет отображаться в базе данных
@Entity
class Post {
    //Аннотация указывает, что поле id является первичным ключом сущности
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var title: String = ""
    var anons: String = ""
    var full_text: String = ""
    var views: Int = 0

    //пустой конструктор позволяет Hibernate создавать объекты при извлечении их из базы данных.
    constructor() {
    }

    //при создании нового объекта Post с использованием этого конструктора, значения будут присвоены соответствующим полям объекта Post
    constructor(title: String, anons: String, full_text: String) {
        this.title = title
        this.anons = anons
        this.full_text = full_text
    }

}