package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.CriterioEvaluado
import cl.evalapp.microservicio.model.Evaluacion
import cl.evalapp.microservicio.model.Nivel
import cl.evalapp.microservicio.repository.EvaluacionRepository
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.Runs
import io.mockk.verify
import java.util.Optional
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class EvaluacionServiceTest {

    private val repository = mockk<EvaluacionRepository>()
    private val service = EvaluacionService(repository)

    private fun evaluacion() = Evaluacion(
        alumnoId = 1,
        alumno = "Alumno Prueba",
        curso = "Sección A",
        rubricaId = 1,
        rubrica = "Presentación oral",
        fecha = "2026-11-20 10:00",
        puntaje = 4,
        puntajeMaximo = 5,
        nota = 5.5,
        criterios = mutableListOf(CriterioEvaluado("Dominio del tema", "", 5, "Logrado", 4, "")),
        niveles = mutableListOf(Nivel("Logrado", 80), Nivel("No logrado", 0)),
        evaluador = "Instructor Demo"
    )

    @Test
    fun listarDevuelveLasEvaluacionesDelRepositorio() {
        every { repository.findAllByOrderByFechaDesc() } returns listOf(evaluacion())

        assertEquals(1, service.listar().size)
    }

    @Test
    fun buscarDevuelveLaEvaluacionSiExiste() {
        every { repository.findById(1L) } returns Optional.of(evaluacion())

        assertNotNull(service.buscar(1L))
    }

    @Test
    fun buscarDevuelveNullSiNoExiste() {
        every { repository.findById(1L) } returns Optional.empty()

        assertNull(service.buscar(1L))
    }

    @Test
    fun crearIgnoraElIdQueVieneDeLaApp() {
        val nueva = evaluacion()
        nueva.id = 99
        every { repository.save(any<Evaluacion>()) } answers { firstArg() }

        val creada = service.crear(nueva)

        assertNull(creada.id)
        assertEquals(5.5, creada.nota)
        assertEquals(1, creada.criterios.size)
    }

    @Test
    fun actualizarUsaElIdDeLaRuta() {
        every { repository.existsById(7L) } returns true
        every { repository.save(any<Evaluacion>()) } answers { firstArg() }

        val actualizada = service.actualizar(7L, evaluacion())

        assertEquals(7L, actualizada?.id)
    }

    @Test
    fun actualizarUnaQueNoExisteDevuelveNull() {
        every { repository.existsById(7L) } returns false

        assertNull(service.actualizar(7L, evaluacion()))
        verify(exactly = 0) { repository.save(any<Evaluacion>()) }
    }

    @Test
    fun eliminarUnaQueExiste() {
        every { repository.existsById(1L) } returns true
        every { repository.deleteById(1L) } just Runs

        assertTrue(service.eliminar(1L))
        verify { repository.deleteById(1L) }
    }

    @Test
    fun eliminarUnaQueNoExiste() {
        every { repository.existsById(1L) } returns false

        assertFalse(service.eliminar(1L))
        verify(exactly = 0) { repository.deleteById(any()) }
    }
}
