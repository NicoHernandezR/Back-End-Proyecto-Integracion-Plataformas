package api.ferremas.ferrmas.venta

import org.springframework.stereotype.Service
import java.util.Date

@Service
class ventaService(val ventaRepository: ventaRepository){

    fun getVentaByPrecioTotal(precio_total:Double): ventaModel? {
        return ventaRepository.findByPrecioTotal(precio_total)
    }

    fun getVentaByEstado(estado:String): ventaModel? {
        return ventaRepository.findByEstado(estado)
    }

    fun getVentaByFecha(fecha_venta: Date): ventaModel? {
        return ventaRepository.findByFechaVenta(fecha_venta)
    }

    fun saveVenta(venta: ventaModel): ventaModel {
        return ventaRepository.save(venta)
    }

    fun getAllVentas(): List<ventaModel> {
        return ventaRepository.findAll()
    }
}