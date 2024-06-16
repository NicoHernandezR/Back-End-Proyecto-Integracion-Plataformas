package api.ferremas.ferrmas.usuario

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

    @Query("SELECT max(u.id) FROM UserModel u")
    fun getLastId() : Long

}