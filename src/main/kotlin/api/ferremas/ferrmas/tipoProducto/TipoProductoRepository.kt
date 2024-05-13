package api.ferremas.ferrmas.tipoProducto

import org.springframework.data.jpa.repository.JpaRepository

interface TipoProductoRepository : JpaRepository<TipoProductoModel, Long> {

    fun findByTipo(tipo : String): TipoProductoModel?

}