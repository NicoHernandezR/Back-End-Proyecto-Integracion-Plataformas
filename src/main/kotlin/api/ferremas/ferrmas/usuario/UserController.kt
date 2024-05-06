package api.ferremas.ferrmas.usuario

import org.springframework.web.bind.annotation.*
import api.ferremas.ferrmas.usuario.UserModel


@RestController
@RequestMapping("/user")
class UserController (val userService: UserService){

    @GetMapping
    fun getUsers(): Iterable<UserModel> {
        return userService.getUsers()
    }

    @PostMapping
    fun saveUser(@RequestBody user: UserModel): UserModel {
        return userService.saveUser(user)
    }
}