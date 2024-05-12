package api.ferremas.ferrmas.venta

import org.springframework.data.jpa.repository.JpaRepository

interface ventaRepository : JpaRepository<ventaModel, Long> {

    fun findByCodigoVenta(codigoVenta: Long): List<ventaModel>
}