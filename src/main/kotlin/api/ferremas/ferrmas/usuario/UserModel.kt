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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true // Si es la misma instancia, retorna true
        if (other !is UserModel) return false // Si el otro objeto no es de la misma clase, retorna false

        // Compara las propiedades relevantes para determinar si los objetos son iguales
        return id == other.id &&
                pnombre == other.pnombre &&
                snombre == other.snombre &&
                appaterno == other.appaterno &&
                apmaterno == other.apmaterno &&
                gmail == other.gmail &&
                password == other.password
                tipoUsuarioId.id == other.tipoUsuarioId.id
    }
}