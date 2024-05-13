Usar El Xampp Para abrir un Servidor en MySQL port 3306
Crear una conexion en WorkBench 8.0 De nombre localhost, usuario root y sin contraseña
Usar Jdk 21.0.2 o Jdk 21.0.3
Usar de IDE IntelliJ y clonar repositorio
Se usa de URL del Backend http://localhost:4500
Las que tienen funciones API implementadas son
/producto
/user
/herramienta
/marcaProducto
/tipoProducto
Todas estas cuentan por defecto con el Read All, Create, Update, Delete.
Funciones especificas esta para producto /producto/filtro En donde se manda 1 json con este formato
{
  "filtros" : {    val minPrecio: Double? = null
    maxPrecio: ,
    idHerramienta: , 
    idTipoProducto: , 
    idMarca: ,
    stockDisponible: ,
    nombreProd: ,
  } 
}

Ademas para ocupar la gran mayoria de funciones API se necesita de un Token,
Para obtener el token tienes que iniciar sesion en /user/login y este token es unico del usuario
Esto esta para impedir aprovechamiento de las APIS y para validar el tipo de usuario que esta accediendo a estas

Si la api pide mas de un objeto en el json este es el formato
{
  "objetoApi" : {
  paramatros
  },
  "token" : {
    "token" : "UUID del token"
  }

}

Para los que piden solo el token por JSON se mandan asi
 {
    "token" : "UUID del token"
}

Tambien tener cuidado con los parametros que se piden por url, estos se separan por /
por ejemplo
Para actualizar un producto con Codigo X la url es  http://localhost:4500/producto/{codigoProducto}/{gmail}
y aparte se le mandaria el objeto json
con el formato ya antes mencionado




