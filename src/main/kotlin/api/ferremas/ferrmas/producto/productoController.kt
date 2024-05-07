package api.ferremas.ferrmas.producto

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/producto")
class productoController (val productoService: productoService){

    @GetMapping
    fun getAllProductos(@RequestBody token: String): List<productoModel> {
        return productoService.getAllProductos()
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