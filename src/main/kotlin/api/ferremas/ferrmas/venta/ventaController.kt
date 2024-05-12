package api.ferremas.ferrmas.venta


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/venta")
class ventaController (val ventaService: ventaService){

    @GetMapping
    fun getAllVentas(): ResponseEntity<List<ventaModel>> {
        return ResponseEntity(ventaService.getAllVentas(), HttpStatus.OK)
    }

    @GetMapping("/{codigo}")
    fun getByCodigo(@PathVariable codigo: Long): ResponseEntity<List<ventaModel>> {
        return ResponseEntity(ventaService.getVentaByCodigo(codigo), HttpStatus.OK)
    }

    @PostMapping
    fun saveVenta(@RequestBody venta: ventaModel): ResponseEntity<ventaModel> {
        return ResponseEntity(ventaService.saveVenta(venta), HttpStatus.CREATED)
    }

}