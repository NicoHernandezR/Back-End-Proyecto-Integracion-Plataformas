package api.ferremas.ferrmas.tipoProducto

import jakarta.persistence.*


@Entity
@Table(name = "tipoProducto")
class TipoProductoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipo: String? = null,
    ){
}