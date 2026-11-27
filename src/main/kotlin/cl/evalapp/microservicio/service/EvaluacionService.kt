package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.Evaluacion
import cl.evalapp.microservicio.repository.EvaluacionRepository
import org.springframework.stereotype.Service

@Service
class EvaluacionService(private val repository: EvaluacionRepository) {

    fun listar(): List<Evaluacion> = repository.findAllByOrderByFechaDesc()

    fun buscar(id: Long): Evaluacion? = repository.findById(id).orElse(null)

    fun crear(evaluacion: Evaluacion): Evaluacion {
        evaluacion.id = null
        return repository.save(evaluacion)
    }

    fun actualizar(id: Long, evaluacion: Evaluacion): Evaluacion? {
        if (!repository.existsById(id)) {
            return null
        }
        evaluacion.id = id
        return repository.save(evaluacion)
    }

    fun eliminar(id: Long): Boolean {
        if (!repository.existsById(id)) {
            return false
        }
        repository.deleteById(id)
        return true
    }
}
