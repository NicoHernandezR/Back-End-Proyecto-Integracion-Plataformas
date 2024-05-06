package api.ferremas.ferrmas.venta

import api.ferremas.ferrmas.estadoVenta.estadoVentaModel
import api.ferremas.ferrmas.usuario.UserModel
import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "venta")
class ventaModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigoVenta: Long? = null,
    var precioTotal: Double? = null,
    var estado: String? = null,
    var fechaVenta: Date? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    val idUsuario: UserModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstadoVenta")
    val idEstadoVenta : estadoVentaModel
    ){
}