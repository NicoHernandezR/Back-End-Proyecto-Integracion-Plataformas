package api.ferremas.ferrmas.token


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface TokenRepository : JpaRepository<TokenModel, UUID>{

    fun findByToken(token: UUID) : TokenModel?
}