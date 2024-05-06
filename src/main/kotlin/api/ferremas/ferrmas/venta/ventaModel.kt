package api.ferremas.ferrmas.venta

import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "venta")
class ventaModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigo_venta: Long? = null,
    var precio_total: Double? = null,
    var estado: String? = null,
    var fecha_venta: Date? = null,
    ){
}