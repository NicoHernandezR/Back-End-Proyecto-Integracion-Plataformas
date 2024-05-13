package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.token.TokenModel
import api.ferremas.ferrmas.token.TokenRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class UserService (val userRepository: IUUserRepository, val tokenRepository: TokenRepository){

    fun getUsers(): List<UserModel> {
        return userRepository.findAll()
    }

    fun getUserByGmail(gmail: String) : UserModel? {
        return userRepository.findByGmail(gmail)
    }

    fun saveUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

    fun updateUser(user: UserModel): Int {
        return userRepository.actualizarUsuario(
            user.pnombre,
            user.snombre,
            user.appaterno,
            user.apmaterno,
            user.gmail,
            user.password,
            user.tipoUsuarioId.id)
    }

    @Transactional
    fun deleteUser(gmail: String) {
        return userRepository.deleteByGmail(gmail)
    }

    fun login(gmail: String, password: String): ResponseEntity<out Any> {
        val user: UserModel? = userRepository.findByGmailAndPassword(gmail, password)
        if (user == null) {
            return ResponseHandler.generarResponse("Credenciales Invalidas", HttpStatus.UNAUTHORIZED, null)
        }

        val tokenExiste : TokenModel? = tokenRepository.findByGmailUsuario(gmail)

        if (tokenExiste == null){
            val tokenGenerated = TokenModel(gmailUsuario = gmail)
            val tokenSaved = tokenRepository.save(tokenGenerated)
            return ResponseHandler.generarResponse("Acceso Autorizado", HttpStatus.ACCEPTED, tokenSaved)
        }

        if(LocalDateTime.now() <= tokenExiste.fechaVencimiento){
            return ResponseHandler.generarResponse("Acceso Autorizado", HttpStatus.ACCEPTED, tokenExiste)
        }

        tokenRepository.deleteByGmailUsuario(gmail)
        val tokenGenerated = TokenModel(gmailUsuario = gmail)
        val tokenSaved = tokenRepository.save(tokenGenerated)
        return ResponseHandler.generarResponse("Acceso Autorizado", HttpStatus.ACCEPTED, tokenSaved)
    }
}