package com.phantomshard.michael_jose_ap2_p1.domain.usecase

import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import com.phantomshard.michael_jose_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCervezasUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(): Flow<List<Cerveza>> = repository.getAll()
}
