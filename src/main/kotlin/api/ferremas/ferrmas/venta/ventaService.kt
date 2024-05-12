package api.ferremas.ferrmas.venta

import org.springframework.stereotype.Service

@Service
class ventaService(val ventaRepository: ventaRepository){

    fun saveVenta(venta: ventaModel): ventaModel {
        return ventaRepository.save(venta)
    }

    fun getAllVentas(): List<ventaModel> {
        return ventaRepository.findAll()
    }

    fun getVentaByCodigo(codigo: Long) : List<ventaModel> {
        return ventaRepository.findByCodigoVenta(codigo)
    }
}