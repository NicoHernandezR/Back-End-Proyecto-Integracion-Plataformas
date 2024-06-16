package api.ferremas.ferrmas.herramienta

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

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

    fun getHerramientaById(id:Long): Optional<herramientaModel> {
        return herramientaRepository.findById(id)
    }

    fun updatedHer(her: herramientaModel): Int {
        return herramientaRepository.actualizarHer(
            her.tipoHerramienta,
            her.idTipoProducto.id,
            her.id
        )
    }

    @Transactional
    fun deleteHerById(id: Long) {
        herramientaRepository.deleteById(id)
    }

    fun getLastId(): Long{
        return herramientaRepository.getLastId()
    }

}