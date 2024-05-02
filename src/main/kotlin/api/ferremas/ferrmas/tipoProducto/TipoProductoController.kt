package api.ferremas.ferrmas.tipoProducto

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipoProducto")
class TipoProductoController (val tipoProductoServicio: TipoProductoService) {

    @GetMapping("/{tipo}")
    fun getTipoProducto(@PathVariable tipo:String): TipoProductoModel? {
        return tipoProductoServicio.getTipoProducto(tipo)
    }

    @GetMapping
    fun getAllTipos(): List<TipoProductoModel> {
        return tipoProductoServicio.getAllTipos()
    }

    @PostMapping
    fun saveUser(@RequestBody tipo: TipoProductoModel): TipoProductoModel {
        return tipoProductoServicio.saveTipoProducto(tipo)
    }
}