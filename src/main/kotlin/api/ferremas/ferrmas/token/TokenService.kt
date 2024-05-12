package api.ferremas.ferrmas.token

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class TokenService(val tokenRepository: TokenRepository) {


    fun validateToken(token: UUID?): TokenModel?{
        val tokenDB : TokenModel = tokenRepository.findByToken(token) ?: return null
        val date : LocalDateTime = LocalDateTime.now()
        if(date < tokenDB.fechaVencimiento){
            return tokenDB
        }
        return null
    }

}