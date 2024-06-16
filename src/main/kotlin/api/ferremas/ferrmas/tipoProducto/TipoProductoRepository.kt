package api.ferremas.ferrmas.tipoProducto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TipoProductoRepository : JpaRepository<TipoProductoModel, Long> {

    fun findByTipo(tipo: String): TipoProductoModel?

    @Query("UPDATE TipoProductoModel t SET t.tipo = :tipo WHERE t.id = :id ")
    fun actualizarProd(
        @Param("id") id: Long?,
        @Param("tipo") tipo: String?
    ): Int

    @Query("SELECT max(t.id) FROM TipoProductoModel t")
    fun getLastId(): Long

}