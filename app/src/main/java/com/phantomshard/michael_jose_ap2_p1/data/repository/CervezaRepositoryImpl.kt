package com.phantomshard.michael_jose_ap2_p1.data.repository

import com.phantomshard.michael_jose_ap2_p1.data.local.dao.CervezaDao
import com.phantomshard.michael_jose_ap2_p1.data.local.mapper.toCerveza
import com.phantomshard.michael_jose_ap2_p1.data.local.mapper.toCervezaEntity
import com.phantomshard.michael_jose_ap2_p1.domain.model.Cerveza
import com.phantomshard.michael_jose_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val cervezaDao: CervezaDao
) : CervezaRepository {
    override suspend fun save(cerveza: Cerveza) {
        cervezaDao.save(cerveza.toCervezaEntity())
    }

    override suspend fun delete(cerveza: Cerveza) {
        cervezaDao.delete(cerveza.toCervezaEntity())
    }

    override suspend fun find(id: Int): Cerveza? {
        return cervezaDao.find(id)?.toCerveza()
    }

    override fun getAll(): Flow<List<Cerveza>> {
        return cervezaDao.getAll().map { list ->
            list.map { it.toCerveza() }
        }
    }
}
