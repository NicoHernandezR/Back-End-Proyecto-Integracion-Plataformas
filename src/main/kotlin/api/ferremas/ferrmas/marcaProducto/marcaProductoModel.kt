package api.ferremas.ferrmas.marcaProducto

import jakarta.persistence.*


@Entity
@Table(name = "marcaProducto")
class marcaProductoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var nombre: String? = null,
    var direccion: String? = null,
    var gmail: String? = null,
){
}