package api.ferremas.ferrmas.producto

import org.springframework.data.jpa.repository.JpaRepository

interface productoRepository : JpaRepository<productoModel, Long> {

    fun findByNombre(nombre : String) : productoModel?

    fun findByPrecio(precio : Double) : productoModel?

    fun findByDescripcion(descripcion : String) : productoModel?

    fun findByStock(stock : Number) : productoModel?

}