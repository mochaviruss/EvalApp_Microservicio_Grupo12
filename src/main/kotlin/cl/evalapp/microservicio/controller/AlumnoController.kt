package cl.evalapp.microservicio.controller

import cl.evalapp.microservicio.model.Alumno
import cl.evalapp.microservicio.service.AlumnoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/alumnos")
class AlumnoController(private val service: AlumnoService) {

    @GetMapping
    fun listar(): List<Alumno> = service.listar()

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): ResponseEntity<Alumno> {
        val alumno = service.buscar(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(alumno)
    }

    @PostMapping
    fun crear(@Valid @RequestBody alumno: Alumno): ResponseEntity<Alumno> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(alumno))
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable("id") id: Long, @Valid @RequestBody alumno: Alumno): ResponseEntity<Alumno> {
        val actualizado = service.actualizar(id, alumno) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable("id") id: Long): ResponseEntity<Void> {
        if (!service.eliminar(id)) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.noContent().build()
    }
}
