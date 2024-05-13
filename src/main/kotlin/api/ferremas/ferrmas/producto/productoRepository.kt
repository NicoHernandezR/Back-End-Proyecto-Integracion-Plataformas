package api.ferremas.ferrmas.producto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface productoRepository : JpaRepository<productoModel, Long> {

    fun findByCodigoProducto(codigoProducto: Long) : productoModel?

    @Query("select c from productoModel c where c.codigoProducto = :codigoProducto")
    fun buscarPorCodigo(@Param("codigoProducto") codigoProducto : Long) : productoModel?

    @Query("SELECT p FROM productoModel p WHERE (:minPrecio IS NULL OR p.precio >= :minPrecio) AND " +
            "(:maxPrecio IS NULL OR p.precio <= :maxPrecio) AND " +
            "(:idMarca IS NULL OR p.idMarca.id = :idMarca) AND " +
            "(:idHerramienta IS NULL OR p.idHerramienta.id = :idHerramienta) AND" +
            "(:stockDisponible IS NULL OR (:stockDisponible = false AND p.stock = 0) OR" +
            "(:stockDisponible = true AND p.stock > 0)) AND" +
            "(:idTipoProducto IS NULL OR p.idHerramienta.idTipoProducto.id = :idTipoProducto) AND" +
            "(:nombreProd IS NULL OR LOWER(p.nombre) LIKE CONCAT('%', LOWER(:nombreProd), '%'))")
    fun filtrarProductos(@Param("minPrecio") minPrecio: Double?,
                         @Param("maxPrecio") maxPrecio: Double?,
                         @Param("idMarca") idMarca: Long?,
                         @Param("idHerramienta") idHerramienta: Long?,
                         @Param("stockDisponible") stockDisponible: Boolean?,
                         @Param("idTipoProducto") idTipoProducto: Long?,
                         @Param("nombreProd") nombreProd: String?): List<productoModel?>

    @Modifying
    @Transactional
    @Query("UPDATE productoModel p SET p.nombre = :nombre, " +
            "p.precio = :precio, p.descripcion = :descripcion, " +
            "p.stock = :stock, p.idHerramienta.id = :idHerramienta, " +
            "p.idMarca.id = :idMarca WHERE p.codigoProducto = :codigoProducto")
    fun actualizarProd(
        @Param("codigoProducto") codigoProducto: Long?,
        @Param("nombre") nombre: String?,
        @Param("precio") precio: Double?,
        @Param("descripcion") descripcion: String?,
        @Param("stock") stock: Short?,
        @Param("idHerramienta") idHerramienta: Long?,
        @Param("idMarca") idMarca: Long?,
    ): Int

    fun deleteByCodigoProducto(codigoProducto: Long)
}
