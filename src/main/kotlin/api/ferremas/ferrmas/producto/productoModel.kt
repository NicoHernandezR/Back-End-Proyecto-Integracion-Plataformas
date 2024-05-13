package api.ferremas.ferrmas.producto

import api.ferremas.ferrmas.herramienta.herramientaModel
import api.ferremas.ferrmas.marcaProducto.marcaProductoModel
import jakarta.persistence.*


@Entity
@Table(name = "producto")
class productoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigoProducto: Long? = null,
    var nombre: String? = null,
    var precio: Double? = null,
    var descripcion: String? = null,
    var stock: Short? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHerramienta")
    val idHerramienta: herramientaModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMarca")
    val idMarca: marcaProductoModel
    ){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Si es la misma instancia, retorna true
        if (other !is productoModel) return false // Si el otro objeto no es de la misma clase, retorna false

        // Compara las propiedades relevantes para determinar si los objetos son iguales
        return codigoProducto == other.codigoProducto &&
                nombre == other.nombre &&
                precio == other.precio &&
                descripcion == other.descripcion &&
                stock == other.stock &&
                idHerramienta.id == other.idHerramienta.id &&
                idMarca.id == other.idMarca.id
    }
}