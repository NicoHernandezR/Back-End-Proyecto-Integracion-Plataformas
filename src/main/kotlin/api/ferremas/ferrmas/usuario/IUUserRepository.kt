package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.producto.productoModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface IUUserRepository : JpaRepository<UserModel, Long>{
    fun findByGmail(gmail: String): UserModel?
    fun findAllByOrderByIdDesc(): MutableList<UserModel>
    fun findByGmailAndPassword(gmail: String, password: String): UserModel?

    @Query("delete from UserModel u WHERE u.gmail = :gmail")
    fun borrarUsuario(@Param("gmail") gmail: String)

    fun deleteByGmail(gmail: String)


    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.pnombre = :pnombre, u.snombre = :snombre, " +
            "u.appaterno = :appaterno, u.apmaterno = :apmaterno, u.password = :password, " +
            "u.tipoUsuarioId.id = :tipoUsuarioId, u.gmail = :gmail " +
            "WHERE u.gmail = :gmail")
    fun actualizarUsuario(
        @Param("pnombre") pnombre: String?,
        @Param("snombre") snombre: String?,
        @Param("appaterno") appaterno: String?,
        @Param("apmaterno") apmaterno: String?,
        @Param("gmail") gmail: String?,
        @Param("password") password: String?,
        @Param("tipoUsuarioId") tipoUsuarioId : Long?
    ): Int

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
}