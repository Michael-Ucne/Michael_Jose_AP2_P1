package com.phantomshard.michael_jose_ap2_p1.data.local.dao

import androidx.room.*
import com.phantomshard.michael_jose_ap2_p1.data.local.entities.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {
    @Upsert
    suspend fun save(cerveza: CervezaEntity)

    @Delete
    suspend fun delete(cerveza: CervezaEntity)

    @Query("SELECT * FROM cervezas WHERE cervezaId = :id")
    suspend fun find(id: Int): CervezaEntity?

    @Query("SELECT * FROM cervezas")
    fun getAll(): Flow<List<CervezaEntity>>
}
