package api.ferremas.ferrmas.token

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID


@Entity
@Table(name="token")
class TokenModel (
    @Id @GeneratedValue(strategy = GenerationType.UUID) var id: UUID? = null,
    var fechaVencimiento: LocalDateTime = LocalDateTime.now().plusDays(14)
){
}