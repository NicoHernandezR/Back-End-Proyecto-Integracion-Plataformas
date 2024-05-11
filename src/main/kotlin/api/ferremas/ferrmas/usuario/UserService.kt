package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.token.TokenModel
import api.ferremas.ferrmas.token.TokenRepository
import org.springframework.stereotype.Service


@Service
class UserService (val userRepository: IUUserRepository, val tokenRepository: TokenRepository){

    fun getUsers(): Iterable<UserModel> {
        return userRepository.findAll()
    }

    fun saveUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

    fun login(gmail: String, password: String): TokenModel? {
        val user: UserModel? = userRepository.findByGmailAndPassword(gmail, password)
        if (user != null) {
            val token = TokenModel(gmailUsuario = gmail)
            return tokenRepository.save(token)
        } else {
            return null
        }
    }
}