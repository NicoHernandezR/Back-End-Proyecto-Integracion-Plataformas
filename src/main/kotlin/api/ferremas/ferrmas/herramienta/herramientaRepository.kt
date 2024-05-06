package api.ferremas.ferrmas.herramienta

import api.ferremas.ferrmas.marcaProducto.marcaProductoModel
import org.springframework.data.jpa.repository.JpaRepository

interface herramientaRepository : JpaRepository<herramientaModel, Long> {

    fun findByTipoHerramienta(tipoHerramienta : String) : herramientaModel?
}