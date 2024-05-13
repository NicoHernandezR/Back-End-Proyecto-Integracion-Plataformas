package api.ferremas.ferrmas.token

import api.ferremas.ferrmas.usuario.IUUserRepository
import api.ferremas.ferrmas.usuario.UserModel
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


@Service
class TokenService(val tokenRepository: TokenRepository, val userRepository: IUUserRepository) {


    fun validateToken(token: UUID?, gmail: String? = null, idTipo: Array<Long?>? = null): TokenModel?{
        val tokenDB : TokenModel = tokenRepository.findByToken(token) ?: return null

        if (gmail != null) {
            val valWithGmail = tokenDB.gmailUsuario == gmail
            if (!valWithGmail){
                return null
            }
        }

        if(gmail != null && idTipo != null) {
            val valIdTipo = validateTipoUsuario(gmail, idTipo)
            if (!valIdTipo) {
                return null
            }
        }

        val date : LocalDateTime = LocalDateTime.now()
        if(date < tokenDB.fechaVencimiento){
            return tokenDB
        }
        return null
    }


    fun validateTipoUsuario(gmail: String, idTipo: Array<Long?>) : Boolean {
        val user : UserModel = userRepository.findByGmail(gmail) ?: return false
        return user.tipoUsuarioId.id in idTipo
    }

}