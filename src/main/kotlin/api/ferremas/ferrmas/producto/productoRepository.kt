package api.ferremas.ferrmas.producto

import org.springframework.data.jpa.repository.JpaRepository

interface productoRepository : JpaRepository<productoModel, Long> {

    fun findByCodigoProducto(codigoProducto : Long) : productoModel?



}