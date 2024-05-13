package api.ferremas.ferrmas.tipoProducto

import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.tipoProducto.customRequestBody.SaveTipoProd
import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tipoProducto")
class TipoProductoController (val tipoProductoServicio: TipoProductoService, val tokenService: TokenService) {

    @GetMapping("/tipo={tipo}")
    fun getTipoProducto(@PathVariable tipo:String): TipoProductoModel? {
        return tipoProductoServicio.getTipoProducto(tipo)
    }

    @GetMapping("/id={id}?gmail={gmail}")
    fun getById(@PathVariable id : Long, @PathVariable gmail : String, @RequestBody token: Token ) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L)) ?:
                return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val tipo: Optional<TipoProductoModel> = tipoProductoServicio.getById(id)

            if (!tipo.isPresent) {
                return ResponseHandler.generarResponse("Tipo Producto con ID: $id No encontrado", HttpStatus.BAD_REQUEST, null)
            }

            return ResponseHandler.generarResponse("Enviando Tipo Producto",  HttpStatus.OK, tipo)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar El tipo producto con ID: $id", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @GetMapping("/{gmail}")
    fun getAllTipos(@PathVariable gmail : String, @RequestBody token: Token ) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L)) ?:
            return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val tipos: List<TipoProductoModel> = tipoProductoServicio.getAllTipos()

            if (tipos.isEmpty()) {
                return ResponseHandler.generarResponse("No se encontraron Tipos Productos", HttpStatus.BAD_REQUEST, null)
            }

            return ResponseHandler.generarResponse("Enviando Tipo Producto",  HttpStatus.OK, tipos)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar todos los tipos Productos", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @PostMapping("/{gmail}")
    fun saveUser(@RequestBody requestCustom: SaveTipoProd, @PathVariable gmail : String): ResponseEntity<out Any> {
        try {
            val tipo = requestCustom.tipoProducto
            val token : UUID? = requestCustom.token?.token

            tokenService.validateToken(token, gmail, arrayOf(1L)) ?:
                return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Tipo Producto Guardado", HttpStatus.CREATED,
                tipo?.let { tipoProductoServicio.saveTipoProducto(it) })

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Guardar El tipo de Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }
}