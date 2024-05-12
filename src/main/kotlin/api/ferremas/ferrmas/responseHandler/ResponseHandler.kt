package api.ferremas.ferrmas.responseHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime


class ResponseHandler {

    companion object {
        fun generarResponse(mensaje: String, status: HttpStatus, objeto: Any?): ResponseEntity<Any> {
            val map: MutableMap<String, Any> = HashMap()
            map["message"] = mensaje
            map["Date"] = LocalDateTime.now()
            map["status"] = status.value()
            if(objeto != null){
                map["data"] = objeto
            }
            return ResponseEntity(map, status)
        }
    }
}