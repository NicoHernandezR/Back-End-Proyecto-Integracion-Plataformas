package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.token.TokenModel
import org.springframework.web.bind.annotation.*
import api.ferremas.ferrmas.usuario.LoginUser


@RestController
@RequestMapping("/user")
class UserController (val userService: UserService) {

    @GetMapping
    fun getUsers(): Iterable<UserModel> {
        return userService.getUsers()
    }

    @PostMapping
    fun saveUser(@RequestBody user: UserModel): UserModel {
        return userService.saveUser(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUser: LoginUser): TokenModel? {
        return userService.login(loginUser.gmail, loginUser.password)
    }
}