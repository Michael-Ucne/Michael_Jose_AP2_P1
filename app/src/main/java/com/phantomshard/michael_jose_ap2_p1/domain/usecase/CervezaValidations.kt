package com.phantomshard.michael_jose_ap2_p1.domain.usecase

import javax.inject.Inject

class CervezaValidations @Inject constructor() {
    fun validateNombre(nombre: String): String? {
        return if (nombre.isBlank()) "El nombre es obligatorio" else null
    }

    fun validateMarca(marca: String): String? {
        return if (marca.isBlank()) "La marca es obligatoria" else null
    }

    fun validatePuntuacion(puntuacion: Int): String? {
        return if (puntuacion < 1) "La puntuacion debe ser entre 1 y 5" else null
    }

    fun validate(nombre: String, marca: String, puntuacion: Int): Map<String, String?> {
        return mapOf(
            "nombre" to validateNombre(nombre),
            "marca" to validateMarca(marca),
            "puntuacion" to validatePuntuacion(puntuacion)
        )
    }
}
