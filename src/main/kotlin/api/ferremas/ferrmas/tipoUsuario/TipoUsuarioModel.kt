package api.ferremas.ferrmas.tipoUsuario

import jakarta.persistence.*


@Entity
@Table(name = "tp_usuario")
class TipoUsuarioModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipo: String? = null,
){
}