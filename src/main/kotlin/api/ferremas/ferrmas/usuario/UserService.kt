package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.token.TokenModel
import api.ferremas.ferrmas.token.TokenRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class UserService (val userRepository: IUUserRepository, val tokenRepository: TokenRepository){

    fun getUsers(): Iterable<UserModel> {
        return userRepository.findAll()
    }

    fun saveUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

    fun login(gmail: String, password: String): ResponseEntity<TokenModel?> {
        val user: UserModel? = userRepository.findByGmailAndPassword(gmail, password)
        if (user != null) {
            val token = TokenModel(gmailUsuario = gmail)
            return ResponseEntity.ok(tokenRepository.save(token))
        } else {
            return ResponseEntity.notFound().build()
        }
    }
}