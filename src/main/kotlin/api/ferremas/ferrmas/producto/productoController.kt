package api.ferremas.ferrmas.producto

import api.ferremas.ferrmas.producto.customRequestBody.FiltrosRequestBody
import api.ferremas.ferrmas.producto.customRequestBody.SaveProdRequestBody
import api.ferremas.ferrmas.responseHandler.ResponseHandler
import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/producto")
class productoController (val productoService: productoService, val tokenService: TokenService){

    @GetMapping
    fun getAllProductos(@RequestBody token: Token): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token) ?:
            return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)
            return ResponseHandler.generarResponse("Enviando todos los productos",  HttpStatus.OK, productoService.getAllProductos())
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar todos los Productos", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @GetMapping("/{codigoProducto}")
    fun getProductoCodigo(@PathVariable codigoProducto: Long, @RequestBody token: Token): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)
            return ResponseHandler.generarResponse("Enviando Producto por el Codigo",  HttpStatus.OK, productoService.getProductoCod(codigoProducto))
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Obtener los Productos", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
  }

    @GetMapping("/filtro")
   fun filtrarProductos(@RequestBody requestCustom : FiltrosRequestBody): ResponseEntity<out Any> {
       try {
           val token : UUID? = requestCustom.token?.token
           val filtros = requestCustom.filtros
           tokenService.validateToken(token) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)
           return ResponseHandler.generarResponse("Productod Filtrados", HttpStatus.OK,
               filtros?.let { productoService.filtrarProductos(it) })
       } catch (ex: Exception) {
           return ResponseHandler.generarResponse("Error al Filtrar los productos", HttpStatus.INTERNAL_SERVER_ERROR, null)
       }

    }

    @PostMapping
    fun saveProductos(@RequestBody requestCustom: SaveProdRequestBody): ResponseEntity<out Any> {
        try {
            val token : UUID? = requestCustom.token?.token
            val producto = requestCustom.producto
            tokenService.validateToken(token) ?:
            return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Producto Guardado", HttpStatus.CREATED,
                producto?.let { productoService.saveProducto(it) })

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al crear el Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }
}