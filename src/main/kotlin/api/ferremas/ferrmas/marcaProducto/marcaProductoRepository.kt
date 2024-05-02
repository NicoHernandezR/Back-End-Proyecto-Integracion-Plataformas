package api.ferremas.ferrmas.marcaProducto

import org.springframework.data.jpa.repository.JpaRepository

interface marcaProductoRepository : JpaRepository<marcaProductoModel, Long> {

    fun findByNombre(nombre : String) : marcaProductoModel?

    fun findByDireccion(direccion : String) : marcaProductoModel?

    fun findByGmail(gmail : String) : marcaProductoModel?
}