package api.ferremas.ferrmas.herramienta

import api.ferremas.ferrmas.marcaProducto.marcaProductoModel
import org.springframework.stereotype.Service

@Service
class herramientaService (val herramientaRepository: herramientaRepository) {

    fun getHerramienta(tipo_herramienta:String): herramientaModel? {
        return herramientaRepository.findByTipoHerramienta(tipo_herramienta)
    }

    fun saveHerramienta(herramienta: herramientaModel): herramientaModel {
        return herramientaRepository.save(herramienta)
    }

    fun getAllHerramientas(): List<herramientaModel>{
        return herramientaRepository.findAll()
    }
}