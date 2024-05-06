package api.ferremas.ferrmas.estadoVenta

import org.springframework.stereotype.Service

@Service
class estadoVentaService (val estadoVentaRepository: estadoVentaRepository){

    fun getTipoVenta(tipo:String): estadoVentaModel? {
        return estadoVentaRepository.findByTipo(tipo)
    }

    fun saveTipoVenta(tipoVenta: estadoVentaModel): estadoVentaModel {
        return estadoVentaRepository.save(tipoVenta)
    }

    fun getAllTipos(): List<estadoVentaModel> {
        return estadoVentaRepository.findAll()
    }
}