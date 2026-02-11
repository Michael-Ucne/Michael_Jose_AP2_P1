package com.phantomshard.michael_jose_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.phantomshard.michael_jose_ap2_p1.data.local.entities.BorrameEntity

@Dao
interface BorrameDao {
    @Upsert
    suspend fun upsert(entity: BorrameEntity)
}
