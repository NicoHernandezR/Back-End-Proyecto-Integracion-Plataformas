package api.ferremas.ferrmas

import api.ferremas.ferrmas.herramienta.herramientaModel
import api.ferremas.ferrmas.marcaProducto.marcaProductoModel
import api.ferremas.ferrmas.producto.productoModel
import api.ferremas.ferrmas.producto.productoService
import api.ferremas.ferrmas.tipoProducto.TipoProductoModel
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FerrmasApplicationTests {

    @Autowired
    lateinit var productoService: productoService


    @Test
    fun testGetAllProductos() {
        // Ejecutar la función que quieres probar
        val resultado = productoService.getAllProductos()



        // Verificar que el resultado no sea nulo y tenga contenido, según tu implementación
        assert(resultado.isNotEmpty()) { "La lista de productos no debe estar vacía" }


        // Opcional: Verificar otras condiciones sobre los datos devueltos
        // assert(resultado.size == 10) { "Se esperan 10 productos, pero se encontraron ${resultado.size}" }
    }

    @Test
    fun validarProductoConId() {
        val get1Producto = productoService.getProductoCod(1L)
        val prodc  = productoModel(1, "Martillo de carpintero",
            20.99, "Martillo de acero forjado", 1,
            herramientaModel(id = 1L, tipoHerramienta = "ayuda", idTipoProducto = TipoProductoModel()), marcaProductoModel()
        )
        assert(get1Producto == prodc)
    }
}
