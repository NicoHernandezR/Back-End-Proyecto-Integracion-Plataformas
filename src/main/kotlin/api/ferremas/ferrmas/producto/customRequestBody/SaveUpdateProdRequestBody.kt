package api.ferremas.ferrmas.producto.customRequestBody

import api.ferremas.ferrmas.producto.productoModel
import api.ferremas.ferrmas.token.Token

class SaveUpdateProdRequestBody {
    var producto: productoModel? = null
    var token : Token? = null
    var gmail: String? = null
    var codigoProducto: Long? = null

}