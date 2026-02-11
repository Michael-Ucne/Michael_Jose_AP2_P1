package com.phantomshard.michael_jose_ap2_p1.data.local.mapper

import com.phantomshard.michael_jose_ap2_p1.data.local.entities.CervezaEntity
import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza

fun CervezaEntity.toCerveza() = Cerveza(
    cervezaId = cervezaId,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)

fun Cerveza.toCervezaEntity() = CervezaEntity(
    cervezaId = cervezaId,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)
