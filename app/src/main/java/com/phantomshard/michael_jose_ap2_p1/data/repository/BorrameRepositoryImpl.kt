package com.phantomshard.michael_jose_ap2_p1.data.repository

import com.phantomshard.michael_jose_ap2_p1.domain.repository.BorrameRepository
import com.phantomshard.michael_jose_ap2_p1.domain.model.Borrame
import javax.inject.Inject

class BorrameRepositoryImpl @Inject constructor(
) : BorrameRepository {
    override suspend fun upsert(borrame: Borrame): Int {
        return 0
    }
}
