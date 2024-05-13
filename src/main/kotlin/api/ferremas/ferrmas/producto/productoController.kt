package api.ferremas.ferrmas.producto

import api.ferremas.ferrmas.producto.customRequestBody.FiltrosRequestBody
import api.ferremas.ferrmas.producto.customRequestBody.SaveUpdateProdRequestBody
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

            val prods: List<productoModel?> = productoService.getAllProductos()

            if(prods.isEmpty()){
                return ResponseHandler.generarResponse("Productos No Encontrados",HttpStatus.BAD_REQUEST, null)
            }

            return ResponseHandler.generarResponse("Enviando todos los productos",  HttpStatus.OK, prods)
        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al Enviar todos los Productos", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @GetMapping("/{codigoProducto}")
    fun getProductoCodigo(@PathVariable codigoProducto: Long, @RequestBody token: Token): ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            val prod = productoService.getProductoCod(codigoProducto)
                ?: return ResponseHandler.generarResponse("Producto con Codigo: $codigoProducto", HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Enviando Producto por el Codigo",  HttpStatus.OK, prod)
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

    @PostMapping("/gmail={gmail}")
    fun saveProductos(@RequestBody requestCustom: SaveUpdateProdRequestBody,
                      @PathVariable gmail : String): ResponseEntity<out Any> {
        try {
            val token : UUID? = requestCustom.token?.token
            val producto = requestCustom.producto
            tokenService.validateToken(token, gmail, arrayOf(1L,2L)) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            return ResponseHandler.generarResponse("Producto Guardado", HttpStatus.CREATED,
                producto?.let { productoService.saveProducto(it) })

        } catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al crear el Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @PutMapping("/{codigoProducto}/gmail={gmail}")
    fun updateProd(@RequestBody requestCustom : SaveUpdateProdRequestBody,
                   @PathVariable codigoProducto: Long, @PathVariable gmail : String
                   ) : ResponseEntity<out Any> {
        try {
            val token : UUID? = requestCustom.token?.token
            val producto : productoModel? = requestCustom.producto
            tokenService.validateToken(token, gmail, arrayOf(1L,2L)) ?:
                return ResponseHandler.generarResponse("Token Invalido",HttpStatus.BAD_REQUEST, null)

            if (producto == null) {
                return ResponseHandler.generarResponse("Producto no recibido",HttpStatus.BAD_REQUEST, null)
            }

            productoService.getProductoCod(codigoProducto)
                ?: return ResponseHandler.generarResponse("Producto con Codigo: $codigoProducto No encontrado", HttpStatus.BAD_REQUEST, null)

            val prodUp = productoService.updateProd(codigoProducto, producto)

            return ResponseHandler.generarResponse("Producto con Codigo: $codigoProducto actualizado", HttpStatus.CREATED, prodUp)

        }   catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al actualizar el Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

    @DeleteMapping("/{codigoProducto}/gmail={gmail}")
    fun deleteProd(@RequestBody token : Token, @PathVariable codigoProducto: Long,
                   @PathVariable gmail : String) : ResponseEntity<out Any> {
        try {
            tokenService.validateToken(token.token, gmail, arrayOf(1L,2L)) ?: return ResponseHandler.generarResponse(
                "Token Invalido",
                HttpStatus.BAD_REQUEST,
                null
            )

            val prod = productoService.getProductoCod(codigoProducto)
                ?: return ResponseHandler.generarResponse("El producto con el Codigo: $codigoProducto no existe", HttpStatus.ACCEPTED,
                    null)

            productoService.deleteProdByCod(codigoProducto)
            val prodDel = productoService.getProductoCod(codigoProducto)

            if (prodDel != null) {
                return ResponseHandler.generarResponse("No se pudo borrar el producto", HttpStatus.ACCEPTED,
                    null)
            }
            return ResponseHandler.generarResponse("producto Borrado con exito", HttpStatus.ACCEPTED,
                prod)
        }   catch (ex: Exception) {
            return ResponseHandler.generarResponse("Error al borrar el Producto", HttpStatus.INTERNAL_SERVER_ERROR, null)
        }
    }

}