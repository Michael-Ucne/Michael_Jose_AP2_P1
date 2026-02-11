package com.phantomshard.michael_jose_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.phantomshard.michael_jose_ap2_p1.data.local.entities.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {
    @Upsert
    suspend fun upsert(cerveza: CervezaEntity)

    @Delete
    suspend fun delete(cerveza: CervezaEntity)

    @Query("SELECT * FROM Cervezas WHERE cervezaId = :id")
    suspend fun find(id: Int): CervezaEntity?

    @Query("SELECT * FROM Cervezas")
    fun getAll(): Flow<List<CervezaEntity>>
}
