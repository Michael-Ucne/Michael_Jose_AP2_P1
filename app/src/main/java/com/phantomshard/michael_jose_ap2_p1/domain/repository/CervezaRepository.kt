package com.phantomshard.michael_jose_ap2_p1.domain.repository

import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    suspend fun upsert(cerveza: Cerveza)
    suspend fun delete(cerveza: Cerveza)
    suspend fun getCerveza(id: Int): Cerveza?
    fun getAllCervezas(): Flow<List<Cerveza>>
}
