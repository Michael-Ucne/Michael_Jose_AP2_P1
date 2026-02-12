package com.phantomshard.michael_jose_ap2_p1.domain.repository

import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    suspend fun save(cerveza: Cerveza)
    suspend fun delete(cerveza: Cerveza)
    suspend fun find(id: Int): Cerveza?
    fun getAll(): Flow<List<Cerveza>>
}