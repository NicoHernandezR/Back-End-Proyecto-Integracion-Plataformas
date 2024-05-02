package api.ferremas.ferrmas.venta

import org.springframework.stereotype.Service
import java.util.Date

@Service
class ventaService(val ventaRepository: ventaRepository){

    fun getVenta(precio_total:Double): ventaModel? {
        return ventaRepository.findByPrecio_total(precio_total)
    }

    fun getVenta(estado:String): ventaModel? {
        return ventaRepository.findByEstado(estado)
    }

    fun getVenta(fecha_venta: Date): ventaModel? {
        return ventaRepository.findByFecha_venta(fecha_venta)
    }

    fun saveVenta(venta: ventaModel): ventaModel {
        return ventaRepository.save(venta)
    }

    fun getAllVentas(): List<ventaModel> {
        return ventaRepository.findAll()
    }
}