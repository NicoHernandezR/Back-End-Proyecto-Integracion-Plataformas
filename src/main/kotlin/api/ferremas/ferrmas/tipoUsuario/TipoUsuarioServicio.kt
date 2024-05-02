package api.ferremas.ferrmas.tipoUsuario

import org.springframework.stereotype.Service


@Service
class TipoUsuarioServicio (val tipoUsuarioRepository: TipoUsuarioRepository) {

    fun getTipoUsuario(tipo:String): TipoUsuarioModel? {
        return tipoUsuarioRepository.findByTipo(tipo)
    }

    fun saveTipoUsuario(tipoUsuario: TipoUsuarioModel): TipoUsuarioModel {
        return tipoUsuarioRepository.save(tipoUsuario)
    }

    fun getAllTipos(): List<TipoUsuarioModel> {
        return tipoUsuarioRepository.findAll()
    }
}