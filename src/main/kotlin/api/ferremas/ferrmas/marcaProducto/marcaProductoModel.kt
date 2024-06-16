package api.ferremas.ferrmas.marcaProducto

import jakarta.persistence.*


@Entity
@Table(name = "marcaProducto")
class marcaProductoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var nombre: String? = null,
    var direccion: String? = null,
    var gmail: String? = null,
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Si es la misma instancia, retorna true
        if (other !is marcaProductoModel) return false // Si el otro objeto no es de la misma clase, retorna false

        // Compara las propiedades relevantes para determinar si los objetos son iguales
        return id == other.id &&
                nombre == other.nombre &&
                direccion == other.direccion &&
                gmail == other.gmail
    }
}