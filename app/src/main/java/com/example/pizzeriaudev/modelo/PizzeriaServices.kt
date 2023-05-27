package com.example.pizzeriaudev.modelo

import com.example.pizzeriaudev.controlador.Pizza
import com.example.pizzeriaudev.controlador.PizzaListResponce
import retrofit2.Call
import retrofit2.http.GET

interface PizzeriaServices {

    @GET("pizzas")
    fun obtenerPizzas(): Call<List<Pizza>>
}