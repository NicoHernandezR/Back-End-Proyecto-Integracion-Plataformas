package api.ferremas.ferrmas.producto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface productoRepository : JpaRepository<productoModel, Long> {

    fun findByCodigoProducto(codigoProducto : Long) : productoModel?

    @Query("select c from productoModel c where c.codigoProducto = :codigoProducto")
    fun buscarPorCodigo(@Param("codigoProducto") codigoProducto : Long) : productoModel?

    @Query("SELECT p FROM productoModel p WHERE (:minPrecio IS NULL OR p.precio >= :minPrecio) AND (:maxPrecio IS NULL OR p.precio <= :maxPrecio) AND (:idMarca IS NULL OR p.idMarca.id = :idMarca) AND (:idHerramienta IS NULL OR p.idHerramienta.id = :idHerramienta)")
    fun filtrarProductos(@Param("minPrecio") minPrecio: Double?,
                         @Param("maxPrecio") maxPrecio: Double?,
                         @Param("idMarca") idMarca: Long?,
                         @Param("idHerramienta") idHerramienta: Long?): List<productoModel?>
}