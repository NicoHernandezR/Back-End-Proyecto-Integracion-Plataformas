package api.ferremas.ferrmas.venta

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Date

interface ventaRepository : JpaRepository<ventaModel, Long> {

    fun findByPrecio_total(precio_total : Double): ventaModel?

    fun findByEstado(estado : String) : ventaModel?

    fun findByFecha_venta(fecha_venta : Date) : ventaModel?
}