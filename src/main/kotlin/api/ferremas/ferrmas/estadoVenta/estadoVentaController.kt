package api.ferremas.ferrmas.estadoVenta

import api.ferremas.ferrmas.tipoProducto.TipoProductoModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipoVenta")
class estadoVentaController (val estadoVentaService: estadoVentaService){

    @GetMapping("/{tipo}")
    fun getTipoVenta(@PathVariable tipo:String): estadoVentaModel? {
        return estadoVentaService.getTipoVenta(tipo)
    }

    @GetMapping
    fun getAllTipos(): List<estadoVentaModel> {
        return estadoVentaService.getAllTipos()
    }

    @PostMapping
    fun saveUser(@RequestBody tipo: estadoVentaModel): estadoVentaModel {
        return estadoVentaService.saveTipoVenta(tipo)
    }
}