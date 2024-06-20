package api.ferremas.ferrmas

import api.ferremas.ferrmas.herramienta.herramientaModel
import api.ferremas.ferrmas.herramienta.herramientaService
import api.ferremas.ferrmas.marcaProducto.marcaProductoModel
import api.ferremas.ferrmas.marcaProducto.marcaProductoService
import api.ferremas.ferrmas.producto.productoModel
import api.ferremas.ferrmas.producto.productoService
import api.ferremas.ferrmas.tipoProducto.TipoProductoModel
import api.ferremas.ferrmas.tipoProducto.TipoProductoService
import api.ferremas.ferrmas.tipoUsuario.TipoUsuarioModel
import api.ferremas.ferrmas.token.TokenService
import api.ferremas.ferrmas.usuario.UserModel
import api.ferremas.ferrmas.usuario.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import java.util.*
import kotlin.random.Random

@SpringBootTest
class FerrmasApplicationTests() {


    @Autowired
    lateinit var productoService: productoService

    @Autowired
    lateinit var herramientaService: herramientaService

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var marcaService: marcaProductoService

    @Autowired
    lateinit var tipoService: TipoProductoService

    @Autowired
    lateinit var tokenService : TokenService


    @Test
    fun generarProducto() {

        val lastId = productoService.getLastId() + 1

        val prodGuardar = productoModel(
            codigoProducto = lastId,
            nombre = "Martillo de carpintero",
            precio = 20.99,
            descripcion = "Martillo de acero forjado",
            stock = 100,
            herramientaModel(
                id = 1L,
                tipoHerramienta = "Martillo",
                idTipoProducto = TipoProductoModel(
                    id = 1L,
                    tipo = "Herramienta"
                )
            ),
            marcaProductoModel(
                id = 2L,
                direccion = "Avenida Principal 456",
                gmail = "info@marcamartillos.com",
                nombre = "Herramientas Profesionales"
            ),
        )

        val prodNew = productoService.saveProducto(prodGuardar)

        assert(prodGuardar == prodNew) { "No se guardo correctamente el producto"}
    }

    @Test
    fun generarHerramienta() {
        val lastId = herramientaService.getLastId() + 1

        val herrGuardar = herramientaModel(
            id = lastId,
            tipoHerramienta = "Martillo",
            idTipoProducto = TipoProductoModel(
                id = 1L,
                tipo = "Herramienta"
            ))

        val newHer = herramientaService.saveHerramienta(herrGuardar)

        assert(herrGuardar == newHer) { "No se guardo correctamente la herramienta"}

    }

    @Test
    fun generarUsuario() {
        val lastId = userService.getLastId() + 1

        val nombres = listOf("juan", "maria", "pedro", "ana", "luis", "carla", "diego", "laura", "carlos", "elena")
        val dominio = "@example.com"

        val nombreAleatorio = nombres.random()
        val numeroAleatorio = Random.nextInt(1000, 9999)

        val gmail = nombreAleatorio + numeroAleatorio.toString() + dominio

        val userGuardar = UserModel(
            id = lastId,
            appaterno = "González",
            apmaterno = "Pérez",
            gmail = gmail,
            password = "contrasena",
            pnombre = "Juan",
            snombre = "Carlos",
            tipoUsuarioId = TipoUsuarioModel(
                id = 1L,
                tipo = "Administrador"
            )
        )

        val newUser = userService.saveUser(userGuardar)

        assert(userGuardar == newUser) { "Usuario no se guardo correctamente"}
    }

    @Test
    fun generarMarca(){
        val lastId = marcaService.getLastId() + 1

        val marcaGuardar = marcaProductoModel(
            id = lastId,
            direccion = "Avenida Principal 456",
            gmail = "info@marcamartillos.com",
            nombre = "Herramientas Profesionales"
        )

        val newMarca = marcaService.saveMarca(marcaGuardar)

        assert(marcaGuardar == newMarca) { "No se guardo correctamente la marca"}

    }

    @Test
    fun generarTipoProducto(){
        val lastId = tipoService.getLastId() + 1

        val tipoProdGuardar = TipoProductoModel(
            id = lastId,
            tipo = "Herramienta"
        )

        val newTipoProd = tipoService.saveTipoProducto(tipoProdGuardar)

        assert(tipoProdGuardar == newTipoProd) {"No se guardo correctamente el tipo producto"}
    }

    @Test
    fun validarToken() {
        val tokenUser: UUID = UUID.fromString("a5e519ac-4e94-4cd6-961f-7ea4b0b0b899")
        val gmail = "gonzalez@example.com"
        val newToken = tokenService.validateToken(tokenUser, gmail)
        assert(tokenUser == newToken?.token) { "El token no es valido"}
    }

    @Test
    fun validarLogin() {
        val gmail = "gonzalez@example.com"
        val pw = "contrasena"

        // Ejecutar la función login y obtener la respuesta
        val response: ResponseEntity<out Any> = userService.login(gmail, pw)

        // Verificar el mensaje de respuesta
        val mensaje: String = response.body?.toString() ?: ""
        val status: HttpStatusCode = response.statusCode

        // Asserts para verificar el resultado esperado
        assert(status == HttpStatus.ACCEPTED) { "Se esperaba HttpStatus.ACCEPTED pero se obtuvo $status" }
        assert(mensaje.contains("Acceso Autorizado")) { "Se esperaba 'Acceso Autorizado' en el mensaje pero se obtuvo '$mensaje'" }

    }

}