package api.ferremas.ferrmas.venta

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Date

interface ventaRepository : JpaRepository<ventaModel, Long> {

    fun findByPrecioTotal(precioTotal : Double): ventaModel?

    fun findByEstado(estado : String) : ventaModel?

    fun findByFechaVenta(fechaVenta : Date) : ventaModel?
}