package api.ferremas.ferrmas.tipoProducto

import org.springframework.stereotype.Service
import java.util.*


@Service
class TipoProductoService(val tipoProductoRepository: TipoProductoRepository) {

    fun getTipoProducto(tipo:String): TipoProductoModel? {
        return tipoProductoRepository.findByTipo(tipo)
    }

    fun saveTipoProducto(tipoProducto: TipoProductoModel): TipoProductoModel {
        return tipoProductoRepository.save(tipoProducto)
    }

    fun getAllTipos(): List<TipoProductoModel> {
        return tipoProductoRepository.findAll()
    }

    fun getById(id: Long) : Optional<TipoProductoModel> {
        return tipoProductoRepository.findById(id)
    }
}