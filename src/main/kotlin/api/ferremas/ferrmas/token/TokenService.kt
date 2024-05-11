package api.ferremas.ferrmas.token

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class TokenService(val tokenRepository: TokenRepository) {


    fun validateToken(token: String): TokenModel?{
        val tokenUUID = parseJsonTokenToUUID(token)
        val tokenDB : TokenModel = tokenRepository.findByToken(tokenUUID) ?: return null
        val date : LocalDateTime = LocalDateTime.now()
        if(date < tokenDB.fechaVencimiento){
            return tokenDB
        }
        return null
    }

    fun parseJsonTokenToUUID(token:String) : UUID{
        val jsonString = token.trimIndent()

        val tokenPattern = """"token"\s*:\s*"([^"]+)"""".toRegex()
        val matchResult = tokenPattern.find(jsonString)
        val tokenValue = matchResult?.groups?.get(1)?.value ?: ""

        val uuid = tokenValue.substringAfter(":").trim()
        return UUID.fromString(uuid)
    }
}