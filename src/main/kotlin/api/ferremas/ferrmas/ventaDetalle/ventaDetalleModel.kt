package api.ferremas.ferrmas.ventaDetalle

import api.ferremas.ferrmas.producto.productoModel
import api.ferremas.ferrmas.venta.ventaModel
import jakarta.persistence.*


@Entity
@Table(name = "ventaDetalle")
class ventaDetalleModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigoDetalle: Long? = null,
    var cantProducto: Number? = null,
    var precioDetalle: Double? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto")
    val idProducto: productoModel,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVenta")
    val idVenta: ventaModel
){
}