package cl.evalapp.microservicio.controller

import cl.evalapp.microservicio.model.Rubrica
import cl.evalapp.microservicio.service.RubricaService
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
@RequestMapping("/api/rubricas")
class RubricaController(private val service: RubricaService) {

    @GetMapping
    fun listar(): List<Rubrica> = service.listar()

    @GetMapping("/{id}")
    fun buscar(@PathVariable("id") id: Long): ResponseEntity<Rubrica> {
        val rubrica = service.buscar(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(rubrica)
    }

    @PostMapping
    fun crear(@Valid @RequestBody rubrica: Rubrica): ResponseEntity<Rubrica> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(rubrica))
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable("id") id: Long, @Valid @RequestBody rubrica: Rubrica): ResponseEntity<Rubrica> {
        val actualizado = service.actualizar(id, rubrica) ?: return ResponseEntity.notFound().build()
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
