package api.ferremas.ferrmas.ventaDetalle

import api.ferremas.ferrmas.producto.productoModel
import org.springframework.stereotype.Service


@Service
class ventaDetalleService (val ventaDetalleRepository: ventaDetalleRepository){

    fun getVentaDetalle(cant_producto:Number): ventaDetalleModel? {
        return ventaDetalleRepository.findByCant_producto(cant_producto)
    }

    fun getVentaDetalle(precio_detalle:Double): ventaDetalleModel? {
        return ventaDetalleRepository.findByPrecio_detalle(precio_detalle)
    }

    fun saveDetalle(detalle: ventaDetalleModel): ventaDetalleModel {
        return ventaDetalleRepository.save(detalle)
    }

    fun getAllDetalles(): List<ventaDetalleModel>{
        return ventaDetalleRepository.findAll()
    }
}