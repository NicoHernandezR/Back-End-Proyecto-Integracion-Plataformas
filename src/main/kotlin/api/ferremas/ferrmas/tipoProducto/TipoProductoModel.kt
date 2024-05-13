package api.ferremas.ferrmas.tipoProducto

import jakarta.persistence.*


@Entity
@Table(name = "tipoProducto")
class TipoProductoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipo: String? = null,
    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Si es la misma instancia, retorna true
        if (other !is TipoProductoModel) return false // Si el otro objeto no es de la misma clase, retorna false

        // Compara las propiedades relevantes para determinar si los objetos son iguales
        return id == other.id && tipo == other.tipo
    }
}