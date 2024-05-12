package api.ferremas.ferrmas.producto

import org.springframework.stereotype.Service


@Service
class productoService (val productoRepository: productoRepository){

    fun saveProducto(producto: productoModel): productoModel {
        return productoRepository.save(producto)
    }

    fun getAllProductos(): List<productoModel>{
        return productoRepository.findAll()
    }

    fun getProductoCod(codigoProducto: Long): productoModel?{
        return productoRepository.buscarPorCodigo(codigoProducto)
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

}