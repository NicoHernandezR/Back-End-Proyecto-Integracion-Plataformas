package api.ferremas.ferrmas.token

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name="token")
class TokenModel (
    @Id @GeneratedValue(strategy = GenerationType.UUID) var token: UUID? = null,
    var fechaVencimiento: LocalDateTime = LocalDateTime.now().plusDays(365),
    var gmailUsuario: String? = null
){
}