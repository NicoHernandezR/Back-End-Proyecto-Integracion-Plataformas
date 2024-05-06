package api.ferremas.ferrmas.producto

import jakarta.persistence.*


@Entity
@Table(name = "producto")
class productoModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var codigo_producto: Long? = null,
    var nombre: String? = null,
    var precio: Double? = null,
    var descripcion: String? = null,
    var stock: Number? = null,
    ){
}