package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.token.TokenModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService) {

    @GetMapping
    fun getUsers(): Iterable<UserModel> {
        return userService.getUsers()
    }

    @PostMapping
    fun saveUser(@RequestBody user: UserModel): ResponseEntity<UserModel> {
        return ResponseEntity(userService.saveUser(user), HttpStatus.CREATED)
    }


    @PostMapping("/login")
    fun login(@RequestBody loginUser: LoginUser): ResponseEntity<TokenModel?> {
        return userService.login(loginUser.gmail, loginUser.password)
    }
}
