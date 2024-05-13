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


    @GetMapping("/{id}/{gmail}")
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
    fun saveTipoProd(@RequestBody requestCustom: SaveTipoProd, @PathVariable gmail : String): ResponseEntity<out Any> {
        try {
            val token = requestCustom.token?.token
            val tipo = requestCustom.tipoProducto ?:
                return ResponseHandler.generarResponse("Tipo Producto no recibido",HttpStatus.BAD_REQUEST, null)


            tokenService.validateToken(token, gmail, arrayOf(1L)) ?:
                return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Tipo Producto Guardado", HttpStatus.CREATED,
                tipo.let { tipoProductoServicio.saveTipoProducto(it) })

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Guardar El tipo de Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @PutMapping("/{gmail}/{id}")
    fun updateTipoProd(@PathVariable gmail : String, @PathVariable id : Long,@RequestBody requestCustom: SaveTipoProd) : ResponseEntity<out Any> {
        try {
            val token : UUID? = requestCustom.token?.token
            val tipo = requestCustom.tipoProducto ?:
            return ResponseHandler.generarResponse("Tipo Producto no recibido",HttpStatus.BAD_REQUEST, null)

            tokenService.validateToken(token, gmail, arrayOf(1L)) ?:
                return ResponseHandler.generarResponse("Token Invalido", HttpStatus.BAD_REQUEST, null)

            val tipoDb : Optional<TipoProductoModel> = tipoProductoServicio.getById(id)

            if(!tipoDb.isPresent) {
                return ResponseHandler.generarResponse("Tipo Producto con ID: ${id} No encontrado", HttpStatus.BAD_REQUEST, null)
            }

            tipo.id = id

            val tipUp = tipoProductoServicio.actualizarTipo(tipo)
            return ResponseHandler.generarResponse("Tipo Producto Actualizado", HttpStatus.OK, tipUp)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Actualizar El tipo de Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }


    }

    @DeleteMapping("/{id}/{gmail}")
    fun deleteTipoProd(@PathVariable id: Long, @PathVariable gmail : String, @RequestBody token: Token) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L)) ?:
            return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            val tipo: Optional<TipoProductoModel> = tipoProductoServicio.getById(id)

            if (!tipo.isPresent) {
                return ResponseHandler.generarResponse("No se encontraro El Tipos Productos ID: $id", HttpStatus.BAD_REQUEST, null)
            }

            tipoProductoServicio.deleteTipoProd(id)
            val tipoDel : Optional<TipoProductoModel> = tipoProductoServicio.getById(id)

            if (tipoDel.isPresent) {
                return ResponseHandler.generarResponse("No se pudo el tipo Producto ID: $id", HttpStatus.ACCEPTED,
                    null)
            }

            return ResponseHandler.generarResponse("Tipo Producto Borrado con exito", HttpStatus.ACCEPTED,
                tipo)

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Tipo Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }
}