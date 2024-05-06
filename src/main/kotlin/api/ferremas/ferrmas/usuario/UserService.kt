package api.ferremas.ferrmas.usuario

import org.springframework.stereotype.Service
import api.ferremas.ferrmas.usuario.UserModel


@Service
class UserService (val userRepository: IUUserRepository){

    fun getUsers(): Iterable<UserModel> {
        return userRepository.findAll()
    }

    fun saveUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

}