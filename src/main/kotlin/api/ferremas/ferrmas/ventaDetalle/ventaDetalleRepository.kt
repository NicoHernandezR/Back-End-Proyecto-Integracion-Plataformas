package api.ferremas.ferrmas.ventaDetalle

import org.springframework.data.jpa.repository.JpaRepository

interface ventaDetalleRepository : JpaRepository<ventaDetalleModel, Long> {

    fun findByCantProducto(cantProducto : Number) : ventaDetalleModel?

    fun findByPrecioDetalle(precioDetalle : Double) : ventaDetalleModel?

}