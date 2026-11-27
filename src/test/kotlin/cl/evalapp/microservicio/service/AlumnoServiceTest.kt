package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.Alumno
import cl.evalapp.microservicio.repository.AlumnoRepository
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

class AlumnoServiceTest {

    private val repository = mockk<AlumnoRepository>()
    private val service = AlumnoService(repository)

    private fun alumno() = Alumno(nombre = "Alumno Prueba", curso = "Sección A")

    @Test
    fun listarDevuelveLosDatosDelRepositorio() {
        every { repository.findAllByOrderByCursoAscNombreAsc() } returns listOf(alumno())

        assertEquals(1, service.listar().size)
    }

    @Test
    fun buscarDevuelveElRegistroSiExiste() {
        every { repository.findById(1L) } returns Optional.of(alumno())

        assertNotNull(service.buscar(1L))
    }

    @Test
    fun buscarDevuelveNullSiNoExiste() {
        every { repository.findById(1L) } returns Optional.empty()

        assertNull(service.buscar(1L))
    }

    @Test
    fun crearIgnoraElIdQueVieneDeLaApp() {
        val nuevo = alumno()
        nuevo.id = 99
        every { repository.save(any<Alumno>()) } answers { firstArg() }

        val creado = service.crear(nuevo)

        assertNull(creado.id)
        assertEquals("Alumno Prueba", creado.nombre)
    }

    @Test
    fun actualizarUsaElIdDeLaRuta() {
        every { repository.existsById(5L) } returns true
        every { repository.save(any<Alumno>()) } answers { firstArg() }

        val actualizado = service.actualizar(5L, alumno())

        assertEquals(5L, actualizado?.id)
    }

    @Test
    fun actualizarUnoQueNoExisteDevuelveNull() {
        every { repository.existsById(5L) } returns false

        assertNull(service.actualizar(5L, alumno()))
        verify(exactly = 0) { repository.save(any<Alumno>()) }
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
