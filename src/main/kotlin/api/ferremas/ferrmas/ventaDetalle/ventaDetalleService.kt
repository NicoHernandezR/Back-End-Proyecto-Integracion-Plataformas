package api.ferremas.ferrmas.ventaDetalle

import api.ferremas.ferrmas.producto.productoModel
import org.springframework.stereotype.Service


@Service
class ventaDetalleService (val ventaDetalleRepository: ventaDetalleRepository){

    fun getVentaDetalleByCantidadProducto(cant_producto:Number): ventaDetalleModel? {
        return ventaDetalleRepository.findByCantProducto(cant_producto)
    }

    fun getVentaDetalleByPrecioDetalle(precio_detalle:Double): ventaDetalleModel? {
        return ventaDetalleRepository.findByPrecioDetalle(precio_detalle)
    }

    fun saveDetalle(detalle: ventaDetalleModel): ventaDetalleModel {
        return ventaDetalleRepository.save(detalle)
    }

    fun getAllDetalles(): List<ventaDetalleModel>{
        return ventaDetalleRepository.findAll()
    }
}