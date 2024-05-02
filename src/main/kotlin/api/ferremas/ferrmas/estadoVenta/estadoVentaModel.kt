package api.ferremas.ferrmas.estadoVenta

import jakarta.persistence.*


@Entity
@Table(name = "estadoVenta")
class estadoVentaModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipo : String? = null,
    ) {
}