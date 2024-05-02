package api.ferremas.ferrmas.estadoVenta

import org.springframework.data.jpa.repository.JpaRepository

interface estadoVentaRepository : JpaRepository<estadoVentaModel, Long> {

    fun findByTipo(tipo : String) : estadoVentaModel?
}