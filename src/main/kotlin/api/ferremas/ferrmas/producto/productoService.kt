package api.ferremas.ferrmas.producto

import org.springframework.stereotype.Service


@Service
class productoService (val productoRepository: productoRepository){

    fun getProductoByNombre(nombre:String): productoModel? {
        return productoRepository.findByNombre(nombre)
    }

    fun getProductoByPrecio(precio:Double): productoModel? {
        return productoRepository.findByPrecio(precio)
    }

    fun getProductoByDescripcion(descripcion:String): productoModel? {
        return productoRepository.findByDescripcion(descripcion)
    }

    fun getProductoByStock(stock:Number): productoModel? {
        return productoRepository.findByStock(stock)
    }

    fun saveProducto(producto: productoModel): productoModel {
        return productoRepository.save(producto)
    }

    fun getAllProductos(): List<productoModel>{
        return productoRepository.findAll()
    }

}