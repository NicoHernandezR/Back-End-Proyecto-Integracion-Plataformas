package api.ferremas.ferrmas.herramienta

import jakarta.persistence.*

@Entity
@Table(name = "herramienta")
class herramientaModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var tipo_herramienta: String? = null,
) {
}