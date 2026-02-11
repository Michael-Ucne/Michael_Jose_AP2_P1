package com.phantomshard.michael_jose_ap2_p1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        com.phantomshard.michael_jose_ap2_p1.data.local.entities.CervezaEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cervezaDao(): com.phantomshard.michael_jose_ap2_p1.data.local.dao.CervezaDao
}
