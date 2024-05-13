package api.ferremas.ferrmas.marcaProducto


import api.ferremas.ferrmas.herramienta.herramientaModel
import api.ferremas.ferrmas.marcaProducto.customRequestBody.SaveUpdateMarcaRequestBody
import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/marcaProducto")
class marcaProductoController (val marcaProductoService: marcaProductoService, val tokenService: TokenService) {


    @GetMapping("/id={id}?gmail={gmail}")
    fun getById(@PathVariable id: Long, @PathVariable gmail: String, @RequestBody token: Token ) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L, 2L, 3L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val marca: Optional<marcaProductoModel> = marcaProductoService.getById(id)

            if (!marca.isPresent) {
                return ResponseHandler.generarResponse(
                    "No se encontraro Marca con ID: $id",
                    HttpStatus.BAD_REQUEST,
                    null
                )
            }

            return ResponseHandler.generarResponse("Herramienta enviada", HttpStatus.OK, marca)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al enviar la marca",
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

            val marcas: List<marcaProductoModel> = marcaProductoService.getAllMarcas()

            if (marcas.isEmpty()) {
                return ResponseHandler.generarResponse("No se encontraron Marcas", HttpStatus.BAD_REQUEST, null)
            }

            return ResponseHandler.generarResponse("Todas las herramientas enviadas", HttpStatus.OK, marcas)

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
        @RequestBody requestCustom: SaveUpdateMarcaRequestBody,
        @PathVariable gmail: String
    ): ResponseEntity<out Any> {
        try {
            val token = requestCustom.token?.token
            val marca = requestCustom.marca ?: return ResponseHandler.generarResponse(
                "Marca no recibida",
                HttpStatus.BAD_REQUEST,
                null
            )
            tokenService.validateToken(token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Marca Guardada", HttpStatus.CREATED,
                marca.let { marcaProductoService.saveMarca(it) })


        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al guardar la Marca",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
            )

        }
    }

    @PutMapping("/gmail={gmail}?id={id}")
    fun updateMarca(
        @PathVariable gmail: String,
        @PathVariable id: Long,
        @RequestBody requestCustom: SaveUpdateMarcaRequestBody
    ): ResponseEntity<out Any> {
        try {
            val token = requestCustom.token?.token
            val marca = requestCustom.marca ?: return ResponseHandler.generarResponse(
                "Marca no recibida",
                HttpStatus.BAD_REQUEST,
                null
            )

            tokenService.validateToken(token, gmail, arrayOf(1L, 2L))
                ?: return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val marDb: Optional<marcaProductoModel> = marcaProductoService.getById(id)

            marca.id = id
            if (marca == marDb.get()) {
                val prodUp = marcaProductoService.updatedMarca(marca)
                return ResponseHandler.generarResponse(
                    "Marca con ID: $id actualizada",
                    HttpStatus.CREATED,
                    prodUp
                )
            }

            return ResponseHandler.generarResponse(
                "La Marca no coincide con el ID enviado",
                HttpStatus.BAD_REQUEST,
                null
            )

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse(
                "Error al Actualizar la Marca",
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

            val marca: Optional<marcaProductoModel> = marcaProductoService.getById(id)

            if (!marca.isPresent) {
                return ResponseHandler.generarResponse(
                    "No se encontraro Marca con ID: $id",
                    HttpStatus.BAD_REQUEST,
                    null
                )
            }

            marcaProductoService.deleteMarcaById(id)
            val MarcaDel : Optional<marcaProductoModel> = marcaProductoService.getById(id)

            if (MarcaDel.isPresent) {
                return ResponseHandler.generarResponse("No se pudo borrar la Marca", HttpStatus.ACCEPTED,
                    null)
            }
            return ResponseHandler.generarResponse("Marca Borrado con exito", HttpStatus.ACCEPTED,
                marca)
        }   catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al borrar la Marca", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

}