package com.phantomshard.michael_jose_ap2_p1.domain.repository

import com.phantomshard.michael_jose_ap2_p1.domain.model.Borrame

interface BorrameRepository {
    suspend fun upsert(borrame: Borrame): Int
}
