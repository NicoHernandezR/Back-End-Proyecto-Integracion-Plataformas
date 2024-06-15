package api.ferremas.ferrmas.herramienta

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface herramientaRepository : JpaRepository<herramientaModel, Long> {

    fun findByTipoHerramienta(tipoHerramienta : String) : herramientaModel?

    @Transactional
    @Modifying
    @Query("UPDATE herramientaModel h SET h.tipoHerramienta = :tipoHerramienta, h.idTipoProducto.id = :idTipoHerramienta WHERE h.id = :id")
    fun actualizarHer(
        @Param("tipoHerramienta") tipoHerramienta: String?,
        @Param("idTipoHerramienta") idTipoHerramienta: Long?,
        @Param("id") id: Long?
    ): Int
}