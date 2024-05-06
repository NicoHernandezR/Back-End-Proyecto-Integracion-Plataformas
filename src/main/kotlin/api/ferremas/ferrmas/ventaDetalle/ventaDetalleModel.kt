package api.ferremas.ferrmas.ventaDetalle

import jakarta.persistence.*


@Entity
@Table(name = "ventaDetalle")
class ventaDetalleModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigo_detalle: Long? = null,
    var cant_producto: Number? = null,
    var precio_detalle: Double? = null,
){
}