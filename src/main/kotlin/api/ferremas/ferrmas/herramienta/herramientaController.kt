package api.ferremas.ferrmas.herramienta

import api.ferremas.ferrmas.herramienta.customRequestBody.SaveUpdateHerRequestBody
import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/herramienta")
class herramientaController(val herramientaService: herramientaService, val tokenService: TokenService) {

    @GetMapping("/id={id}?gmail={gmail}")
    fun getHerramienta(
        @PathVariable id: Long,
        @PathVariable gmail: String,
        @RequestBody token: Token
    ): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val herramienta: Optional<herramientaModel> = herramientaService.getHerramientaById(id)

            if (!herramienta.isPresent) {
                return ResponseHandler.generarResponse(
                    "No se encontraro Herramienta con ID: $id",
                    HttpStatus.BAD_REQUEST,
                    null
                )
            }

            return ResponseHandler.generarResponse("Herramienta enviada", HttpStatus.OK, herramienta)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al enviar la herramienta",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            )
        }

    }

    @GetMapping("/{gmail}")
    fun getAllHerramientas(@PathVariable gmail: String, @RequestBody token: Token): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val herramientas: List<herramientaModel> = herramientaService.getAllHerramientas()

            if (herramientas.isEmpty()) {
                return ResponseHandler.generarResponse("No se encontraron Herramientas", HttpStatus.BAD_REQUEST, null)
            }

            return ResponseHandler.generarResponse("Todas las herramientas enviadas", HttpStatus.OK, herramientas)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al Enviar Todas las herramientas enviadas",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            )
        }
    }

    @PostMapping("/{gmail}")
    fun saveHerramienta(
        @RequestBody requestCustom: SaveUpdateHerRequestBody,
        @PathVariable gmail: String
    ): ResponseEntity<out Any> {
        try {
            val token = requestCustom.token?.token
            val herramienta = requestCustom.herramienta ?: return ResponseHandler.generarResponse(
                "Herramienta no recibida",
                HttpStatus.BAD_REQUEST,
                null
            )
            tokenService.validateToken(token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Herramienta Guardada", HttpStatus.CREATED,
                herramienta.let { herramientaService.saveHerramienta(it) })


        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al guardar la Herramienta",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            )

        }
    }

    @PutMapping("/gmail={gmail}?id={id}")
    fun updateHerramienta(
        @PathVariable gmail: String,
        @PathVariable id: Long,
        @RequestBody requestCustom: SaveUpdateHerRequestBody
    ): ResponseEntity<out Any> {
        try {
            val token = requestCustom.token?.token
            val herramienta = requestCustom.herramienta ?: return ResponseHandler.generarResponse(
                "Herramienta no recibida",
                HttpStatus.BAD_REQUEST,
                null
            )

            tokenService.validateToken(token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val herDb: Optional<herramientaModel> = herramientaService.getHerramientaById(id)

            herramienta.id = id
            if (herramienta == herDb.get()) {
                val prodUp = herramientaService.updatedHer(herramienta)
                return ResponseHandler.generarResponse(
                    "Herramienta con ID: $id actualizada",
                    HttpStatus.CREATED,
                    prodUp
                )
            }

            return ResponseHandler.generarResponse(
                "La herramienta no coincide con el ID enviado",
                HttpStatus.BAD_REQUEST,
                null
            )

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al Actualizar la Herramienta",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            )
        }
    }

    @DeleteMapping("/gmail={gmail}?id={id}")
    fun deleteHer(@RequestBody token : Token, @PathVariable id: Long,
                   @PathVariable gmail : String) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L,2L)) ?: return ResponseHandler.generarResponse(
                "Token Invalido",
                HttpStatus.BAD_REQUEST,
                null
            )

            val her: Optional<herramientaModel> = herramientaService.getHerramientaById(id)

            if (!her.isPresent) {
                return ResponseHandler.generarResponse(
                    "No se encontraro Herramienta con ID: $id",
                    HttpStatus.BAD_REQUEST,
                    null
                )
            }

            herramientaService.deleteHerById(id)
            val HerDel : Optional<herramientaModel> = herramientaService.getHerramientaById(id)

            if (HerDel.isPresent) {
                return ResponseHandler.generarResponse("No se pudo borrar la Herramienta", HttpStatus.ACCEPTED,
                    null)
            }
            return ResponseHandler.generarResponse("Herramienta Borrado con exito", HttpStatus.ACCEPTED,
                her)
        }   catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al borrar la Herramienta", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }
}