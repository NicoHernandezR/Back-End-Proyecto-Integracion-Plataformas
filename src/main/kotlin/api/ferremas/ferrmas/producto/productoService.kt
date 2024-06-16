package api.ferremas.ferrmas.producto

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class productoService (val productoRepository: productoRepository){

    fun saveProducto(producto: productoModel): productoModel {
        return productoRepository.save(producto)
    }

    fun getAllProductos(): List<productoModel>{
        return productoRepository.findAll()
    }

    fun getProductoCod(codigoProducto: Long): productoModel?{
        return productoRepository.findByCodigoProducto(codigoProducto)
    }

    fun filtrarProductos(filtros: Filtros): List<productoModel?> {
        return productoRepository.filtrarProductos(filtros.minPrecio,
            filtros.maxPrecio,
            filtros.idMarca,
            filtros.idHerramienta,
            filtros.stockDisponible,
            filtros.idTipoProducto,
            filtros.nombreProd)
    }

    fun updateProd(codigoProducto: Long,prod: productoModel) : Int {
        return productoRepository.actualizarProd(codigoProducto,
            prod.nombre,
            prod.precio,
            prod.descripcion,
            prod.stock,
            prod.idHerramienta.id,
            prod.idMarca.id)
    }

    @Transactional
    fun deleteProdByCod(codigoProducto: Long){
        return productoRepository.deleteByCodigoProducto(codigoProducto)
    }

    fun getLastId(): Long{
        return productoRepository.getLastId()
    }

}