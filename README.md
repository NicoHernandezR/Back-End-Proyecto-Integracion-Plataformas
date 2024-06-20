Parece que estás buscando mejorar el formato y la claridad de las instrucciones en tu archivo README. Aquí tienes una versión revisada que sigue tus especificaciones y que busca ser más clara y concisa:
Configuración del Entorno

    Servidor MySQL: Utiliza XAMPP para abrir un servidor MySQL en el puerto 3306.
    Conexión en Workbench: Crea una conexión en MySQL Workbench 8.0 con los siguientes detalles:
        Nombre: localhost
        Usuario: root
        Contraseña: 1234
    Versión de Java: Utiliza JDK 21.0.2 o JDK 21.0.3.
    IDE: Recomendamos utilizar IntelliJ IDEA y clonar este repositorio.

Para la sentencia SQL Usar el archivo en el root de la branch Main ejecutar.sql

URL del Backend

El backend utiliza la siguiente URL base: http://localhost:4500
Funcionalidades API

Las siguientes rutas cuentan con funciones CRUD por defecto:

    /producto
    /user
    /herramienta
    /marcaProducto
    /tipoProducto

Función Específica /producto/filtro

Para filtrar productos, envía un JSON con el siguiente formato:

json

{
  "filtros": {
    "minPrecio": ,
    "maxPrecio": ,
    "idHerramienta": ,
    "idTipoProducto": ,
    "idMarca": ,
    "stockDisponible": ,
    "nombreProd": 
  }
}

Token de Autenticación

La mayoría de las funciones API requieren un token único. Para obtenerlo, inicia sesión en /user/login.

Para usar el token en las solicitudes, inclúyelo en el JSON de la siguiente manera:

json

{
  "token": "UUID del token"
}

Si la API solicita más de un objeto en el JSON, utiliza el siguiente formato:

json

{
  "objetoApi": {
    "parametros": "valores"
  },
  "token": {
    "token": "UUID del token"
  }
}

Parámetros por URL

Cuando se requieren parámetros en la URL, sepáralos por /. Por ejemplo, para actualizar un producto con un código específico, la URL sería: http://localhost:4500/producto/{codigoProducto}/{gmail}. Además, envía el objeto JSON como se mencionó anteriormente.




