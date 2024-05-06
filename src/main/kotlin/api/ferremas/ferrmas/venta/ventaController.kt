package api.ferremas.ferrmas.venta


import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/venta")
class ventaController (val ventaService: ventaService){

    @GetMapping("/{precio_total}")
    fun getPrecio_total(@PathVariable precio_total:Double): ventaModel? {
        return ventaService.getVentaByPrecioTotal(precio_total)
    }

    @GetMapping("/{estado}")
    fun getEstado(@PathVariable estado:String): ventaModel? {
        return ventaService.getVentaByEstado(estado)
    }

    @GetMapping("/{fecha_venta}")
    fun getFecha_venta(@PathVariable fecha_venta: Date): ventaModel? {
        return ventaService.getVentaByFecha(fecha_venta)
    }

    @GetMapping
    fun getAllVentas(): List<ventaModel> {
        return ventaService.getAllVentas()
    }

    @PostMapping
    fun saveVenta(@RequestBody venta: ventaModel): ventaModel {
        return ventaService.saveVenta(venta)
    }

}