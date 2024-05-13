package api.ferremas.ferrmas.herramienta

import api.ferremas.ferrmas.tipoProducto.TipoProductoModel
import jakarta.persistence.*

@Entity
@Table(name = "herramienta")
class herramientaModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipoHerramienta: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoProducto")
    val idTipoProducto: TipoProductoModel
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Si es la misma instancia, retorna true
        if (other !is herramientaModel) return false // Si el otro objeto no es de la misma clase, retorna false

        // Compara las propiedades relevantes para determinar si los objetos son iguales
        return id == other.id && tipoHerramienta == other.tipoHerramienta && idTipoProducto.id == other.idTipoProducto.id
    }
}