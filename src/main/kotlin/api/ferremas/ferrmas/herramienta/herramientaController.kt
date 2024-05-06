package api.ferremas.ferrmas.herramienta

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/herramienta")
class herramientaController (val herramientaService: herramientaService){

    @GetMapping("/{tipo_herramienta}")
    fun getHerramienta(@PathVariable tipo_herramienta:String): herramientaModel?{
        return herramientaService.getHerramienta(tipo_herramienta)
    }

    @GetMapping
    fun getAllHerramientas(): List<herramientaModel> {
        return herramientaService.getAllHerramientas()
    }

    @PostMapping
    fun saveHerramienta(@RequestBody herramienta: herramientaModel): herramientaModel {
        return herramientaService.saveHerramienta(herramienta)
    }
}