package api.ferremas.ferrmas.ventaDetalle

import org.springframework.data.jpa.repository.JpaRepository

interface ventaDetalleRepository : JpaRepository<ventaDetalleModel, Long> {

    fun findByCant_producto(cant_producto : Number) : ventaDetalleModel?

    fun findByPrecio_detalle(precio_detalle : Double) : ventaDetalleModel?

}