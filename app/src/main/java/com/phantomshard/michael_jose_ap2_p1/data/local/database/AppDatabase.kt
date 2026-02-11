package com.phantomshard.michael_jose_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phantomshard.michael_jose_ap2_p1.data.local.entities.BorrameEntity

@Database(
    entities = [
        BorrameEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun borrameDao(): com.phantomshard.michael_jose_ap2_p1.data.local.dao.BorrameDao
}
