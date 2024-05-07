package api.ferremas.ferrmas.token

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class TokenValidationAspect (val tokenRepository: TokenRepository){

    @Before("@annotation(api.ferremas.ferrmas.token.Token) && args(token, ..)")
    fun validateToken(token: String): TokenModel? {

        println(token)
        println("Annotation")
        return null
        //val tokenM : TokenModel = tokenRepository.findByToken(token) ?: return null

        //return tokenM
    }
}