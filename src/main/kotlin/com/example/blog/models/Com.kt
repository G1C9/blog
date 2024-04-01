package com.example.blog.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Com {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var title: String = ""
    var name: String = ""
    var descr: String = ""
    var text: String = ""

    constructor()

    constructor(title: String, name: String, descr: String, text: String) {
        this.title = title
        this.name = name
        this.descr = descr
        this.text = text
    }

}