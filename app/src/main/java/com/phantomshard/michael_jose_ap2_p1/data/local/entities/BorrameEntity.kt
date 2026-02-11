package com.phantomshard.michael_jose_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey
    val borrameId: Int = 0
)
