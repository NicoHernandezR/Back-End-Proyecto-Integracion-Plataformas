package api.ferremas.ferrmas.herramienta

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface herramientaRepository : JpaRepository<herramientaModel, Long> {

    fun findByTipoHerramienta(tipoHerramienta : String) : herramientaModel?

    @Modifying
    @Transactional
    @Query("UPDATE herramientaModel h set h.tipoHerramienta = :tipoHerramienta, h.idTipoProducto.id = :idTipoProducto " +
            "where h.id = :id")
    fun actualizarHer(
        @Param("id") id: Long?,
        @Param("tipoHerramienta") tipoHerramienta: String?,
        @Param("idTipoProducto") idTipoProducto: Long?
    ) : Int
}