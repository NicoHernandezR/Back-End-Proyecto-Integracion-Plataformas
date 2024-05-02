package api.ferremas.ferrmas.tipoUsuario

import org.springframework.data.jpa.repository.JpaRepository

interface TipoUsuarioRepository : JpaRepository<TipoUsuarioModel, Long> {

    fun findByTipo(tipo: String): TipoUsuarioModel?
}