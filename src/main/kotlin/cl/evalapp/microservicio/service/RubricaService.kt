package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.Rubrica
import cl.evalapp.microservicio.repository.RubricaRepository
import org.springframework.stereotype.Service

@Service
class RubricaService(private val repository: RubricaRepository) {

    fun listar(): List<Rubrica> = repository.findAllByOrderByNombreAsc()

    fun buscar(id: Long): Rubrica? = repository.findById(id).orElse(null)

    fun crear(rubrica: Rubrica): Rubrica {
        rubrica.id = null
        return repository.save(rubrica)
    }

    fun actualizar(id: Long, rubrica: Rubrica): Rubrica? {
        if (!repository.existsById(id)) {
            return null
        }
        rubrica.id = id
        return repository.save(rubrica)
    }

    fun eliminar(id: Long): Boolean {
        if (!repository.existsById(id)) {
            return false
        }
        repository.deleteById(id)
        return true
    }
}
