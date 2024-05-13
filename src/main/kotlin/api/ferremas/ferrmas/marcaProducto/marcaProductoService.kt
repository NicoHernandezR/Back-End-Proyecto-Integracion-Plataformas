package api.ferremas.ferrmas.marcaProducto

import org.springframework.stereotype.Service
import java.util.*


@Service
class marcaProductoService (val marcaProductoRepository: marcaProductoRepository){

    fun getById(id:Long): Optional<marcaProductoModel> {
        return marcaProductoRepository.findById(id)
    }

    fun saveMarca(marca: marcaProductoModel): marcaProductoModel {
        return marcaProductoRepository.save(marca)
    }

    fun getAllMarcas(): List<marcaProductoModel>{
        return marcaProductoRepository.findAll()
    }

    fun updatedMarca(marca: marcaProductoModel): Int {
        return marcaProductoRepository.actualizarMarca(
            marca.id,
            marca.nombre,
            marca.gmail,
            marca.direccion
        )
    }

}