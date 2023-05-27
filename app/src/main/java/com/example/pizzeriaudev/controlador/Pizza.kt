package com.example.pizzeriaudev.controlador

data class Pizza(
    var id: Long,
    var name: String,
    var description: String,
    var toppings: List<String>
)
