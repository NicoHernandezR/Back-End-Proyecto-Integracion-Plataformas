package api.ferremas.ferrmas.marcaProducto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface marcaProductoRepository : JpaRepository<marcaProductoModel, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE marcaProductoModel m set m.gmail = :gmail, m.nombre = :nombre, m.direccion = :direccion " +
            "where m.id = :id")
    fun actualizarMarca(
        @Param("id") id: Long?,
        @Param("nombre") nombre: String?,
        @Param("gmail") gmail: String?,
        @Param("direccion") direccion: String?
    ) : Int
}