package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.tipoUsuario.TipoUsuarioModel
import jakarta.persistence.*


@Entity
@Table(name="user")
class UserModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var pnombre: String? = null,
    var snombre: String? = null,
    var appaterno: String? = null,
    var apmaterno: String? = null,
    var gmail: String? = null,
    var password: String? = null,){
}