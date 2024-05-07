package api.ferremas.ferrmas.token


import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


@Repository
interface TokenRepository : JpaRepository<TokenModel, UUID>{

}