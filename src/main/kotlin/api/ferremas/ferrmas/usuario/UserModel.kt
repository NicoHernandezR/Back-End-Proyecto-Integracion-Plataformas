package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.tipoUsuario.TipoUsuarioModel
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*


@Entity
@Table(name="usuario")
class UserModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var pnombre: String? = null,
    var snombre: String? = null,
    var appaterno: String? = null,
    var apmaterno: String? = null,
    @Column(unique = true) var gmail: String? = null,
    var password: String? = null,
    @JsonProperty("tipoUsuarioId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoUsuarioId")
    val tipoUsuarioId: TipoUsuarioModel)
{
}