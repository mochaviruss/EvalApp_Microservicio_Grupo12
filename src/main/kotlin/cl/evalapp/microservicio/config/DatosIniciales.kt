package cl.evalapp.microservicio.config

import cl.evalapp.microservicio.model.Alumno
import cl.evalapp.microservicio.model.Criterio
import cl.evalapp.microservicio.model.Nivel
import cl.evalapp.microservicio.model.Rubrica
import cl.evalapp.microservicio.repository.AlumnoRepository
import cl.evalapp.microservicio.repository.RubricaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatosIniciales(
    private val alumnoRepository: AlumnoRepository,
    private val rubricaRepository: RubricaRepository
) : CommandLineRunner {

    private fun cuatroNiveles() = mutableListOf(
        Nivel("Muy buen desempeño", 100),
        Nivel("Logrado", 80),
        Nivel("Aceptable", 60),
        Nivel("No logrado", 0)
    )

    private fun tresNiveles() = mutableListOf(
        Nivel("Logrado completamente", 100),
        Nivel("Logrado parcialmente", 60),
        Nivel("No logrado", 0)
    )

    override fun run(vararg args: String) {
        if (alumnoRepository.count() > 0) {
            return
        }

        alumnoRepository.save(Alumno(nombre = "Camila Rojas Soto", curso = "Sección A"))
        alumnoRepository.save(Alumno(nombre = "Diego Muñoz Vera", curso = "Sección A"))
        alumnoRepository.save(Alumno(nombre = "Ana Torres Díaz", curso = "Sección A"))
        alumnoRepository.save(Alumno(nombre = "Benjamín Silva Pino", curso = "Sección A"))
        alumnoRepository.save(Alumno(nombre = "Matías Pérez Lagos", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Valentina Castro Ruiz", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Tomás Herrera Gómez", curso = "Sección B"))
        alumnoRepository.save(Alumno(nombre = "Fernanda Soto Leiva", curso = "Sección B"))

        rubricaRepository.save(
            Rubrica(
                nombre = "Presentación oral",
                asignatura = "Comunicación oral",
                criterios = mutableListOf(
                    Criterio("Dominio del tema", "Explica el contenido con seguridad y sin leer.", 5),
                    Criterio("Voz y postura", "Habla fuerte y claro, y mira al público.", 5),
                    Criterio("Uso del tiempo", "Respeta el tiempo asignado.", 5),
                    Criterio("Responde preguntas", "Contesta las preguntas con argumentos.", 5)
                ),
                niveles = cuatroNiveles()
            )
        )

        rubricaRepository.save(
            Rubrica(
                nombre = "Ensayo argumentativo",
                asignatura = "Comunicación escrita",
                criterios = mutableListOf(
                    Criterio("Presenta una tesis clara", "La postura se entiende desde el primer párrafo.", 5),
                    Criterio("Usa argumentos con evidencia", "Cada argumento tiene datos o ejemplos.", 5),
                    Criterio("Redacción y ortografía", "El texto es ordenado y sin faltas.", 5),
                    Criterio("Conclusión coherente", "Retoma la tesis y cierra las ideas.", 5)
                ),
                niveles = cuatroNiveles()
            )
        )

        rubricaRepository.save(
            Rubrica(
                nombre = "Procedimiento de primeros auxilios",
                asignatura = "Primeros auxilios",
                criterios = mutableListOf(
                    Criterio("Evalúa la seguridad de la escena", "Revisa que no haya peligro antes de acercarse.", 5),
                    Criterio("Pide ayuda y comunica la emergencia", "Llama o pide a alguien que llame a emergencias.", 5),
                    Criterio("Aplica la técnica correctamente", "Sigue los pasos en el orden correcto.", 5),
                    Criterio("Usa el equipo de protección", "Se pone guantes antes de atender.", 5)
                ),
                niveles = tresNiveles()
            )
        )
    }
}
