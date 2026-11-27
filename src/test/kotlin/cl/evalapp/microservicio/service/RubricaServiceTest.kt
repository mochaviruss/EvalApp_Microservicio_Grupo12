package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.Criterio
import cl.evalapp.microservicio.model.Nivel
import cl.evalapp.microservicio.model.Rubrica
import cl.evalapp.microservicio.repository.RubricaRepository
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

class RubricaServiceTest {

    private val repository = mockk<RubricaRepository>()
    private val service = RubricaService(repository)

    private fun rubrica() = Rubrica(
        nombre = "Rúbrica Prueba",
        asignatura = "Taller",
        criterios = mutableListOf(Criterio("Participa", "", 5)),
        niveles = mutableListOf(Nivel("Logrado", 100), Nivel("No logrado", 0))
    )

    @Test
    fun listarDevuelveLosDatosDelRepositorio() {
        every { repository.findAllByOrderByNombreAsc() } returns listOf(rubrica())

        assertEquals(1, service.listar().size)
    }

    @Test
    fun buscarDevuelveElRegistroSiExiste() {
        every { repository.findById(1L) } returns Optional.of(rubrica())

        assertNotNull(service.buscar(1L))
    }

    @Test
    fun buscarDevuelveNullSiNoExiste() {
        every { repository.findById(1L) } returns Optional.empty()

        assertNull(service.buscar(1L))
    }

    @Test
    fun crearIgnoraElIdQueVieneDeLaApp() {
        val nuevo = rubrica()
        nuevo.id = 99
        every { repository.save(any<Rubrica>()) } answers { firstArg() }

        val creado = service.crear(nuevo)

        assertNull(creado.id)
        assertEquals("Rúbrica Prueba", creado.nombre)
    }

    @Test
    fun actualizarUsaElIdDeLaRuta() {
        every { repository.existsById(5L) } returns true
        every { repository.save(any<Rubrica>()) } answers { firstArg() }

        val actualizado = service.actualizar(5L, rubrica())

        assertEquals(5L, actualizado?.id)
    }

    @Test
    fun actualizarUnoQueNoExisteDevuelveNull() {
        every { repository.existsById(5L) } returns false

        assertNull(service.actualizar(5L, rubrica()))
        verify(exactly = 0) { repository.save(any<Rubrica>()) }
    }

    @Test
    fun eliminarUnoQueExiste() {
        every { repository.existsById(1L) } returns true
        every { repository.deleteById(1L) } just Runs

        assertTrue(service.eliminar(1L))
        verify { repository.deleteById(1L) }
    }

    @Test
    fun eliminarUnoQueNoExiste() {
        every { repository.existsById(1L) } returns false

        assertFalse(service.eliminar(1L))
        verify(exactly = 0) { repository.deleteById(any()) }
    }
}
