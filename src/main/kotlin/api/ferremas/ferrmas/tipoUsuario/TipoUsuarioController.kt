package api.ferremas.ferrmas.tipoUsuario

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tipoUsuario")
class TipoUsuarioController(val tipoUsuarioServicio: TipoUsuarioServicio) {

    @GetMapping("/{tipo}")
    fun getTipoUsuario(@PathVariable tipo:String): TipoUsuarioModel? {
        return tipoUsuarioServicio.getTipoUsuario(tipo)
    }

    @GetMapping
    fun getAllTipos(): List<TipoUsuarioModel> {
        return tipoUsuarioServicio.getAllTipos()
    }

    @PostMapping
    fun saveUser(@RequestBody tipo: TipoUsuarioModel): TipoUsuarioModel {
        return tipoUsuarioServicio.saveTipoUsuario(tipo)
    }
}