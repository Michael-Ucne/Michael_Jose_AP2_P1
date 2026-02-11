package com.phantomshard.michael_jose_ap2_p1.domain.usecase

import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import com.phantomshard.michael_jose_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class DeleteCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza) {
        repository.delete(cerveza)
    }
}
