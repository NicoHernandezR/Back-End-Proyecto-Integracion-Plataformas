package api.ferremas.ferrmas.usuario

import api.ferremas.ferrmas.tipoUsuario.TipoUsuarioModel
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn


@Entity
@Table(name="user")
class UserModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var pnombre: String? = null,
    var snombre: String? = null,
    var appaterno: String? = null,
    var apmaterno: String? = null,
    var gmail: String? = null,
    var password: String? = null,
    @JsonProperty("tipoUsuarioId")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoUsuarioId")
    val tipoUsuarioId: TipoUsuarioModel)
{
}