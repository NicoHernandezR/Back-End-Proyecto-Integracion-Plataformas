package api.ferremas.ferrmas.token

import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.usuario.IUUserRepository
import api.ferremas.ferrmas.usuario.UserModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    fun validateTokenWithResponse(token: UUID?, gmail: String? = null, idTipo: Array<Long?>? = null): ResponseEntity<Any>? {
        val tokenDB : TokenModel = tokenRepository.findByToken(token) ?: return null
        var error = false

        if (gmail != null) {
            val valWithGmail = tokenDB.gmailUsuario == gmail
            if (!valWithGmail){
                error =  true
            }
        }

        if(gmail != null && idTipo != null) {
            val valIdTipo = validateTipoUsuario(gmail, idTipo)
            if (!valIdTipo) {
                error =  true
            }
        }

        val date : LocalDateTime = LocalDateTime.now()
        if(date > tokenDB.fechaVencimiento){
            error =  true
        }

        if(error){
            return ResponseHandler.generarResponse(
                "Token Invalido",
                HttpStatus.BAD_REQUEST,
                null
            )
        }

        return null

    }


    fun validateTipoUsuario(gmail: String, idTipo: Array<Long?>) : Boolean {
        val user : UserModel = userRepository.findByGmail(gmail) ?: return false
        return user.tipoUsuarioId.id in idTipo
    }

}