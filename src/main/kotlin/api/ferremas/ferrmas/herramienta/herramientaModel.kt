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
}