package api.ferremas.ferrmas.producto

import api.ferremas.ferrmas.venta.ventaModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/producto")
class productoController (val productoService: productoService){

    @GetMapping("/{nombre}")
    fun getNombre(@PathVariable nombre:String): productoModel?{
        return productoService.getProductoByNombre(nombre)
    }

    @GetMapping("/{precio}")
    fun getPrecio(@PathVariable precio:Double): productoModel?{
        return productoService.getProductoByPrecio(precio)
    }

    @GetMapping("/{descripcion}")
    fun getDescripcion(@PathVariable descripcion:String): productoModel?{
        return productoService.getProductoByDescripcion(descripcion)
    }

    @GetMapping("/{stock}")
    fun getStock(@PathVariable stock:Number): productoModel?{
        return productoService.getProductoByStock(stock)
    }

    @GetMapping
    fun getAllProductos(): List<productoModel> {
        return productoService.getAllProductos()
    }

    @PostMapping
    fun saveProductos(@RequestBody producto: productoModel): productoModel {
        return productoService.saveProducto(producto)
    }
}