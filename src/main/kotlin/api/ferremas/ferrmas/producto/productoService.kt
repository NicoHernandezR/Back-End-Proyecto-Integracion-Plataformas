package api.ferremas.ferrmas.producto

import org.springframework.stereotype.Service


@Service
class productoService (val productoRepository: productoRepository){

    fun saveProducto(producto: productoModel): productoModel {
        return productoRepository.save(producto)
    }

    fun getAllProductos(): List<productoModel>{
        return productoRepository.findAll()
    }

    /*fun getProductoByFiltro(filtro: String): List<productoModel>{
        val jsonString: String = filtro.trimIndent()
        val map = parseJsonToMap(jsonString)


        map.forEach { (key, value) ->
            productoRepository.filtrarPor(key, value)
        }

    }*/

    fun parseJsonToMap(jsonString: String): Map<String, Any> {
        val map = mutableMapOf<String, Any>()

        // Eliminar los espacios en blanco del JSON
        val json = jsonString.replace("\\s".toRegex(), "")

        // Quitar las llaves iniciales y finales
        val cleanJson = json.trim().removeSurrounding("{", "}")

        // Separar por comas para obtener los pares clave-valor
        val keyValuePairs = cleanJson.split(",")

        for (pair in keyValuePairs) {
            // Separar cada par clave-valor por el primer ":" encontrado
            val (key, value) = pair.split(":", limit = 2)

            // Eliminar las comillas de las claves y valores de cadena
            val cleanKey = key.trim().removeSurrounding("\"")
            val cleanValue = when {
                value.startsWith("\"") && value.endsWith("\"") -> value.trim().removeSurrounding("\"")
                else -> value.trim()
            }

            // Convertir los valores numéricos a Int o Double según corresponda
            val parsedValue = cleanValue.toDoubleOrNull() ?: cleanValue.toIntOrNull() ?: cleanValue

            // Agregar el par clave-valor al mapa
            map[cleanKey] = parsedValue
        }

        return map
    }


}