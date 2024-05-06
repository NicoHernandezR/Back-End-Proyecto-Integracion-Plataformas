package api.ferremas.ferrmas.marcaProducto


import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/marcaProducto")
class marcaProductoController (val marcaProductoService: marcaProductoService){

    @GetMapping("/{nombre}")
    fun getNombre(@PathVariable nombre:String): marcaProductoModel?{
        return marcaProductoService.getMarcaProductoByNombre(nombre)
    }

    @GetMapping("/{direccion}")
    fun getDireccion(@PathVariable direccion:String): marcaProductoModel?{
        return marcaProductoService.getMarcaProductoByDireccion(direccion)
    }

    @GetMapping("/{gmail}")
    fun getGmail(@PathVariable gmail:String): marcaProductoModel?{
        return marcaProductoService.getMarcaProductoByGmail(gmail)
    }

    @GetMapping
    fun getAllMarcas(): List<marcaProductoModel> {
        return marcaProductoService.getAllMarcas()
    }

    @PostMapping
    fun saveMarca(@RequestBody marca: marcaProductoModel): marcaProductoModel {
        return marcaProductoService.saveMarca(marca)
    }
}