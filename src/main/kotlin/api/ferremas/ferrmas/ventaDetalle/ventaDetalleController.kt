package api.ferremas.ferrmas.ventaDetalle

import api.ferremas.ferrmas.producto.productoModel
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/ventaDetalle")
class ventaDetalleController (val ventaDetalleService: ventaDetalleService){

    @GetMapping("/{cant_producto}")
    fun getCantProducto(@PathVariable cant_producto:Number): ventaDetalleModel?{
        return ventaDetalleService.getVentaDetalle(cant_producto)
    }

    @GetMapping("/{precio_detalle}")
    fun getCantProducto(@PathVariable precio_detalle:Double): ventaDetalleModel?{
        return ventaDetalleService.getVentaDetalle(precio_detalle)
    }

    @GetMapping
    fun getAllDetalles(): List<ventaDetalleModel> {
        return ventaDetalleService.getAllDetalles()
    }

    @PostMapping
    fun saveDetalle(@RequestBody detalle: ventaDetalleModel): ventaDetalleModel {
        return ventaDetalleService.saveDetalle(detalle)
    }
}