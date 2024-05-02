package api.ferremas.ferrmas.marcaProducto

import api.ferremas.ferrmas.ventaDetalle.ventaDetalleModel
import org.springframework.stereotype.Service


@Service
class marcaProductoService (val marcaProductoRepository: marcaProductoRepository){

    fun getMarcaProductoByNombre(nombre:String): marcaProductoModel? {
        return marcaProductoRepository.findByNombre(nombre)
    }

    fun getMarcaProductoByDireccion(direccion:String): marcaProductoModel? {
        return marcaProductoRepository.findByNombre(direccion)
    }

    fun getMarcaProductoByGmail(gmail:String): marcaProductoModel? {
        return marcaProductoRepository.findByNombre(gmail)
    }

    fun saveMarca(marca: marcaProductoModel): marcaProductoModel {
        return marcaProductoRepository.save(marca)
    }

    fun getAllMarcas(): List<marcaProductoModel>{
        return marcaProductoRepository.findAll()
    }
}