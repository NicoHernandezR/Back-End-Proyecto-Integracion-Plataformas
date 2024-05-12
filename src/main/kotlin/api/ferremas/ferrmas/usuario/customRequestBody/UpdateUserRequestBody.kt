package api.ferremas.ferrmas.usuario.customRequestBody

import api.ferremas.ferrmas.token.Token
import api.ferremas.ferrmas.usuario.UserModel

class UpdateUserRequestBody {
    var user : UserModel? = null
    var token : Token? = null
}