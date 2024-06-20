package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.token.TokenService
import api.ferremas.ferrmas.usuario.customRequestBody.UpdateUserRequestBody
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService, val tokenService: TokenService) {

    @GetMapping("/getAll/{gmail}")
    fun getUsers(@RequestBody token : Token, @PathVariable gmail : String): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L)) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            val users : List<UserModel> = userService.getUsers()

            if(users.isEmpty()){
                return ResponseHandler.generarResponse("No hay usuarios",HttpStatus.OK, null)
            }

            return ResponseHandler.generarResponse("Enviando todos los Usuarios", HttpStatus.OK, userService.getUsers())
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar todos los Usuarios", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }

    }

    @GetMapping("/{gmail}")
    fun getUsersByGmail(@RequestBody token : Token, @PathVariable gmail : String): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)
            val user = userService.getUserByGmail(gmail)
                ?: return ResponseHandler.generarResponse("Usuario no encontrado con Gmail: $gmail", HttpStatus.INTERNAL_SERVER_ERROR, null)

            return ResponseHandler.generarResponse("Usuaro enviado con exito", HttpStatus.OK, user)
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar el Usuario con gmail: $gmail", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }

    }

    @PostMapping
    fun saveUser(@RequestBody user: UserModel): ResponseEntity<out Any> {
        try {
            val savedUser: UserModel = userService.saveUser(user)
            return ResponseHandler.generarResponse("Usuario Guardado Con éxito", HttpStatus.CREATED, savedUser)
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al crear la cuenta", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @PutMapping
    fun updateUser(@RequestBody requestCustom: UpdateUserRequestBody) : ResponseEntity<out Any> {
        try {
            val token : UUID? = requestCustom.token?.token
            val user = requestCustom.user
                ?: return ResponseHandler.generarResponse("Usuario no recibido",HttpStatus.BAD_REQUEST, null)

            if(user.gmail == null) {
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)
            }

            tokenService.validateToken(token, user.gmail) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)


            val userDb = user.gmail?.let { userService.getUserByGmail(it) }

            if(userDb == null) {
                return ResponseHandler.generarResponse("Usuario con Gmail: ${user.gmail} No encontrado", HttpStatus.BAD_REQUEST, null)
            }

            val userUp = user.let { userService.updateUser(it) }

            return ResponseHandler.generarResponse("Cuenta actualizada con exito", HttpStatus.OK,
                userUp)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al actualizar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @DeleteMapping("/{gmail}")
    fun deleteUser(@RequestBody token: Token, @PathVariable gmail : String) : ResponseEntity<out Any>{
        try {
            tokenService.validateToken(token.token, gmail) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            val user = userService.getUserByGmail(gmail)
                ?: return ResponseHandler.generarResponse("La cuenta no existe", HttpStatus.ACCEPTED,
                    null)

            userService.deleteUser(gmail)
            val userDel = userService.getUserByGmail(gmail)

            if (userDel != null) {
                return ResponseHandler.generarResponse("No se pudo borrar la cuenta", HttpStatus.ACCEPTED,
                null)
            }
            return ResponseHandler.generarResponse("Cuenta Borrada con exito", HttpStatus.ACCEPTED,
                user)

        } catch (ex: Exception) {
            println(ex)
        return ResponseHandler.generarResponse("Error al borrar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUser: LoginUser): ResponseEntity<out Any> {
        try{
            return userService.login(loginUser.gmail, loginUser.password)
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al logearse", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }
}
