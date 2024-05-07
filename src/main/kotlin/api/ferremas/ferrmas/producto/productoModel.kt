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
}