package api.ferremas.ferrmas.producto

import api.ferremas.ferrmas.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/producto")
class productoController (val productoService: productoService, val tokenService: TokenService){

    @GetMapping
    fun getAllProductos(@RequestBody token: String): ResponseEntity<List<productoModel>> {
        tokenService.validateToken(token) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        return ResponseEntity.ok(this.productoService.getAllProductos())
    }

    @GetMapping("/{filtro}")
    fun getAllProductosByFilter(@PathVariable filtro: String): List<productoModel>? {
        return null
    }

    @PostMapping
    fun saveProductos(@RequestBody producto: productoModel): productoModel {
        return productoService.saveProducto(producto)
    }
}