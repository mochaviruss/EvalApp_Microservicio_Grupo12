package cl.evalapp.microservicio.controller

import cl.evalapp.microservicio.model.Evaluacion
import cl.evalapp.microservicio.service.EvaluacionService
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
@RequestMapping("/api/evaluaciones")
class EvaluacionController(private val service: EvaluacionService) {

    @GetMapping
    fun listar(): List<Evaluacion> = service.listar()

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): ResponseEntity<Evaluacion> {
        val evaluacion = service.buscar(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(evaluacion)
    }

    @PostMapping
    fun crear(@Valid @RequestBody evaluacion: Evaluacion): ResponseEntity<Evaluacion> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(evaluacion))
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable("id") id: Long, @Valid @RequestBody evaluacion: Evaluacion): ResponseEntity<Evaluacion> {
        val actualizado = service.actualizar(id, evaluacion) ?: return ResponseEntity.notFound().build()
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
