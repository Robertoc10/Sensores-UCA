package com.uca.edu.campussensors.model

data class Lectura(
    val lectura_id: Int,
    val lectura_valor: Float,
    val dispositivo_id: String,
    val createdAt: String
)