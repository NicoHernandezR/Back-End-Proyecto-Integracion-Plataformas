package api.ferremas.ferrmas.usuario

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import api.ferremas.ferrmas.usuario.UserModel


@Repository
interface IUUserRepository : JpaRepository<UserModel, Long>{
    fun findByGmail(gmail: String): UserModel?
    fun findAllByOrderByIdDesc(): MutableList<UserModel>
}